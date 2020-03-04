package com.rname.niosocket;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class JAVANIOServer {
    private Map<Integer, Integer> rejectedThreadCountMap = new ConcurrentHashMap<>();

    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                int hashcode = r.hashCode();
                Integer count = rejectedThreadCountMap.get(hashcode);
                if (count == null) {
                    count = 0;
                    rejectedThreadCountMap.put(hashcode, count);
                } else {
                    count++;
                    rejectedThreadCountMap.put(hashcode, count);
                }
                if (count < 1) {
                    executor.execute(r);
                } else {
                    if (r instanceof WriteClientSocketHandler) {
                        WriteClientSocketHandler realThread = (WriteClientSocketHandler) r;
                        System.out.println("服务系统繁忙,客户端WriteClientSocketHandler[" + realThread.client + "]请求被拒绝处理！");
                        SelectionKey selectionKey = realThread.client.keyFor(selector);
                        if (selectionKey != null) {
                            selectionKey.cancel();
                        }
                        if (realThread.client != null) {
                            try {
                                realThread.client.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        AtomicBoolean isWriting = isWritingMap.get(realThread.client);
                        isWriting.set(false);
                    } else if (r instanceof ReadClientSocketHandler) {
                        ReadClientSocketHandler realThread = (ReadClientSocketHandler) r;
                        System.out.println("服务系统繁忙,客户端ReadClientSocketHandler[" + realThread.client + "]请求被拒绝处理！");
                        SelectionKey selectionKey = realThread.client.keyFor(selector);
                        if (selectionKey != null) {
                            selectionKey.cancel();
                        }
                        if (realThread.client != null) {
                            try {
                                realThread.client.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        AtomicBoolean isReading = isReadingMap.get(realThread.client);
                        isReading.set(false);
                    } else {
                        System.out.println("服务系统繁忙,系统线程[" + r.getClass().getName() + "]被拒绝处理！");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("RejectedExecutionHandler处理发生异常：" + e.getMessage());
            }
        }
    };

    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(30, 50, 300, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(5), rejectedExecutionHandler);

    private Map<SocketChannel, AtomicBoolean> isReadingMap = new ConcurrentHashMap<SocketChannel, AtomicBoolean>();
    private Map<SocketChannel, AtomicBoolean> isWritingMap = new ConcurrentHashMap<SocketChannel, AtomicBoolean>();

    private Selector selector = null;
    private ServerSocketChannel ss = null;
    private volatile boolean isClose = false;

    private Handler handler;

    /**
     * 创建非阻塞服务器绑定5555端口
     */
    public JAVANIOServer(Handler handler) {
        this.handler = handler;
        try {
            ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(6666));
            ss.configureBlocking(false);
            selector = Selector.open();
            ss.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
            close();
        }
    }

    public boolean isClose() {
        return isClose;
    }

    /**
     * 关闭服务器
     */
    private void close() {
        isClose = true;
        threadPool.shutdown();
        try {
            if (ss != null) {
                ss.close();
            }
            if (selector != null) {
                selector.close();
            }
        } catch (IOException e) {
            System.out.println("服务器关闭发生异常：" + e.getMessage());
        }
    }

    /**
     * 启动选择器监听客户端事件
     */
    public void start() {
        threadPool.execute(new SelectorGuardHandler());
    }

    private class SelectorGuardHandler implements Runnable {

        @Override
        public void run() {

            while (!isClose) {
                try {
                    if (selector.select(10) == 0) {
                        continue;
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectedKey = iterator.next();
                        iterator.remove();
                        try {
                            if (selectedKey.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
                                AtomicBoolean isReading = isReadingMap.get(socketChannel);
                                if (isReading == null) {
                                    isReading = new AtomicBoolean(false);
                                    isReadingMap.put(socketChannel, isReading);
                                }
                                while (isReading.get()) {
                                    Thread.sleep(5);
                                }
                                isReading.set(true);
                                threadPool.execute(new ReadClientSocketHandler(socketChannel));
                            } else if (selectedKey.isWritable()) {
                                Object responseMessage = selectedKey.attachment();
                                SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
                                selectedKey.interestOps(SelectionKey.OP_READ);
                                threadPool.execute(new WriteClientSocketHandler(socketChannel, responseMessage));
                            } else if (selectedKey.isAcceptable()) {
                                ServerSocketChannel ssc = (ServerSocketChannel) selectedKey.channel();
                                SocketChannel clientSocket = ssc.accept();
                                clientSocket.configureBlocking(false);
                                clientSocket.register(selector, SelectionKey.OP_READ);
                            }
                        } catch (CancelledKeyException e) { selectedKey.cancel();
                            Message message = Message.obtain();
                            message.obj = "服务器启动或运行发生异常：" + e.getMessage();
                            handler.sendMessage(message);
                        }

                    }
                } catch (Exception e) {
                    if (e instanceof NullPointerException) {
                        e.printStackTrace();
                        Message message = Message.obtain();
                        message.obj =  e.getMessage();
                        handler.sendMessage(message);
                        close();
                    } else {
                        Message message = Message.obtain();
                        message.obj = "服务器启动或运行发生异常：" + e.getMessage();
                        handler.sendMessage(message);
                        close();
                    }
                    break;
                }
            }
        }
    }

    /**
     * 响应数据给客户端线程
     *
     * @author haoguo
     *
     */
    private class WriteClientSocketHandler implements Runnable {
        private SocketChannel client;

        private Object responseMessage;

        private WriteClientSocketHandler(SocketChannel client, Object responseMessage) {
            this.client = client;
            this.responseMessage = responseMessage;
        }

        @Override
        public void run() {
            try {
                byte[] responseByteData = null;
                String logResponseString = "";
                if (responseMessage instanceof byte[]) {
                    responseByteData = (byte[]) responseMessage;
                    logResponseString = new String(responseByteData);
                } else if (responseMessage instanceof String) {
                    logResponseString = (String) responseMessage;
                    responseByteData = logResponseString.getBytes();
                } else if (responseMessage != null) {
                    Message message = Message.obtain();
                    message.obj = "不支持的数据类型" + responseMessage.getClass();
                    handler.sendMessage(message);
                    return;
                }
                if (responseByteData == null || responseByteData.length == 0) {

                    Message message = Message.obtain();
                    message.obj = "服务器响应的数据为空";
                    handler.sendMessage(message);
                    return;
                }

                ByteBuffer data = ByteBuffer.allocate(responseByteData.length + 4);
                data.putInt(responseByteData.length);
                data.put(responseByteData);
                data.flip();
                while (data.hasRemaining()) {
                    client.write(data);
                }
                Message message = Message.obtain();
                message.obj = "server响应客户端[" + client.getRemoteAddress() + "]数据 :["
                        + new String(logResponseString) + "]";
                handler.sendMessage(message);
            } catch (Exception e) {
                try {
                    Message message = Message.obtain();
                    message.obj = "server响应客户端[" + client.getRemoteAddress() + "]数据 异常[" + e.getMessage() + "]";
                    handler.sendMessage(message);
                    SelectionKey selectionKey = client.keyFor(selector);
                    if (selectionKey != null) {
                        selectionKey.cancel();
                    }
                    if (client != null) {
                        client.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } finally {
                AtomicBoolean isWriting = isWritingMap.get(client);
                isWriting.set(false);
            }
        }

    }

    /**
     * 读客户端发送数据线程
     *
     * @author haoguo
     *
     */
    private class ReadClientSocketHandler implements Runnable {
        private SocketChannel client;
        private ByteBuffer dataLen = ByteBuffer.allocate(4);

        private ReadClientSocketHandler(SocketChannel client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                dataLen.clear();
                int len = 4;
                while (len > 0) {
                    int readLen = client.read(dataLen);
                    if (readLen == -1) {
                        throw new Exception("读取客户端的长度readLen==" + readLen);
                    }
                    len -= readLen;
                }
                int data_length = dataLen.getInt(0);
                ByteBuffer data = ByteBuffer.allocate(data_length);
                while (data.hasRemaining()) {
                    client.read(data);
                }
                String readData = new String(data.array());
                Message message = Message.obtain();
                message.obj = Thread.currentThread().getId() + "服务器接收到客户端[" + client.getRemoteAddress() + "]数据 :["
                        + readData + "]";
                handler.sendMessage(message);

                // dosomthing
                byte[] response = ("response" + readData.substring(0, 3)).getBytes();
                AtomicBoolean isWriting = isWritingMap.get(client);
                if (isWriting == null) {
                    isWriting = new AtomicBoolean(false);
                    isWritingMap.put(client, isWriting);
                }
                while (isWriting.get()) {
                    Thread.sleep(5);
                }
                isWriting.set(true);
                client.register(selector, SelectionKey.OP_WRITE, response);
            } catch (Exception e) {
                try {
                    SelectionKey selectionKey = client.keyFor(selector);
                    if (selectionKey != null) {
                        selectionKey.cancel();
                    }
                    Message message = Message.obtain();
                    message.obj = "客户端[" + client + "]关闭了连接,原因[" + e.getMessage() + "]";
                    handler.sendMessage(message);
                    if (client != null) {
                        client.close();
                    }

                } catch (IOException e1) {
                    Message message = Message.obtain();
                    message.obj ="客户端[" + client + "]关闭异常" + e1.getMessage();
                    handler.sendMessage(message);
                }
            } finally {
                AtomicBoolean isReading = isReadingMap.get(client);
                isReading.set(false);
            }

        }

    }

}
