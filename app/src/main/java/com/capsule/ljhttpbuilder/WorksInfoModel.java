package com.capsule.ljhttpbuilder;

/**
 * Created by kakalee on 15/8/1.
 */
public class WorksInfoModel implements BaseModel {

    private String worksid;

    private String userid;

    private String user_face_url;

    private String name;

    private String mainPic_url;

    private String artist_name;

    private String thumbPicUrl;

    public String getWorksid() {
        return worksid;
    }

    public void setWorksid(String worksid) {
        this.worksid = worksid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUser_face_url() {
        return user_face_url;
    }

    public void setUser_face_url(String user_face_url) {
        this.user_face_url = user_face_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainPic_url() {
        return mainPic_url;
    }

    public void setMainPic_url(String mainPic_url) {
        this.mainPic_url = mainPic_url;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getThumbPicUrl() {
        if(thumbPicUrl == null) {
            int index = mainPic_url.lastIndexOf("/");
            thumbPicUrl = mainPic_url.substring(0, index) + "/200x200/" + mainPic_url.substring(index + 1);
        }
        return thumbPicUrl;
    }
}
