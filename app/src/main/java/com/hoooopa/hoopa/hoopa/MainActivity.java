package com.rname.niosocket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvMsg;

    Handler handler = new Handler() {
        StringBuilder sb = new StringBuilder();
        @Override
        public void handleMessage(@NonNull Message msg) {
            tvMsg.setText(sb.append((String) msg.obj + "\n").toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        JAVANIOServer server = new JAVANIOServer(handler);
        server.start();

    }
}
