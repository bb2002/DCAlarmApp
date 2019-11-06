package kr.saintdev.dcalarmapp.modules.notifimanager;

import java.util.Date;

import kr.saintdev.dcalarmapp.modules.utils.PostMeta;

public class NotificationAlarmMeta {
    public NotificationAlarmMeta(PostMeta post, Date ndate) {
        this.post = post;
        this.ndate = ndate;
    }

    private PostMeta post = null;
    private Date ndate = null;

    public PostMeta getPost() {
        return post;
    }

    public Date getNdate() {
        return ndate;
    }

    public void setPost(PostMeta post) {
        this.post = post;
    }

    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }
}
