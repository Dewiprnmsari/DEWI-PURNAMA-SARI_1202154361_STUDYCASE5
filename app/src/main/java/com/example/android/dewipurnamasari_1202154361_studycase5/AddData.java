package com.example.android.dewipurnamasari_1202154361_studycase5;

/**
 * Created by User on 3/25/2018.
 */

public class AddData {
    String todo, desc, prior;

    //konstruktor
    public AddData(String todo, String desc, String prior ){
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }

    //method setter dan getter to do, description dan priority
    public String getTodo(){
        return todo;
    }

    public void setTodo(String todo){
        this.todo = todo;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getPrior(){
        return  prior;
    }

    public void setPrior(String prior){
        this.prior = prior;
    }
}
