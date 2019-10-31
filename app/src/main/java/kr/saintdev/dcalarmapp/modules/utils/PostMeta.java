package kr.saintdev.dcalarmapp.modules.utils;

import java.util.Date;

public class PostMeta {
    public String uuid;
    public String url;
    public String title;
    public String writer;
    public Date date;
    public int viewCount;

    public PostMeta(String uuid, String url, String title, String writer, Date date, int viewCount) {
        this.uuid = uuid;
        this.url = url;
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.viewCount = viewCount;
    }
}
