package com.hoooopa.hoopa.hoopa.bean.cookbean;

import java.util.List;

/**
 * Created by Pray on 2018/4/28.
 */

public class CookBase {

    private String id;

    private String classid;

    private String name;

    private String peoplenum;

    private String preparetime;

    private String cookingtime;

    private String content;

    private String pic;

    private String tag;

    private List<CookMaterial> material;

    private List<CookProcess> process;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(String peoplenum) {
        this.peoplenum = peoplenum;
    }

    public String getPreparetime() {
        return preparetime;
    }

    public void setPreparetime(String preparetime) {
        this.preparetime = preparetime;
    }

    public String getCookingtime() {
        return cookingtime;
    }

    public void setCookingtime(String cookingtime) {
        this.cookingtime = cookingtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<CookMaterial> getMaterial() {
        return material;
    }

    public void setMaterial(List<CookMaterial> material) {
        this.material = material;
    }

    public List<CookProcess> getProcess() {
        return process;
    }

    public void setProcess(List<CookProcess> process) {
        this.process = process;
    }
}
