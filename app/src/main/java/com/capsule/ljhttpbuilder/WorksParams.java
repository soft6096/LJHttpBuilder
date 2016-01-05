package com.capsule.ljhttpbuilder;

import com.capsule.baseframe.http.request.Params;

/**
 * Created by kakalee on 15/8/1.
 */
public class WorksParams extends Params {

    public WorksParams setKeyword(String keyword) {
        set("keyword", keyword);
        return this;
    }

    public WorksParams setTicai(String ticai) {
        set("ticai", ticai);
        return this;
    }

    public WorksParams setGongyi(String gongyi) {
        set("gongyi", gongyi);
        return this;
    }

    public WorksParams setQixing(String qixing) {
        set("qixing", qixing);
        return this;
    }

    public WorksParams setPage(int page) {
        set("page", String.valueOf(page));
        return this;
    }
}
