package com.capsule.ljhttpbuilder;

/**
 * Created by kakalee on 15/8/1.
 */
public class WorksInfoListModel {

    private WorksInfoDataModel data;

    private Integer errno;

    private String error;

    public WorksInfoDataModel getData() {
        return data;
    }

    public void setData(WorksInfoDataModel data) {
        this.data = data;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
