package com.m1zyuk1.astilbe.model;

import java.io.Serializable;

/**
 * Created by yukian on 2018/03/03.
 */

public class Schedule implements Serializable {
    // タイトル・工程(List)・開始時間・終了時間をもつ
    private String title;

    public Schedule(String title){
        this.setTitle(title);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
