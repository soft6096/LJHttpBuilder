package com.capsule.ljhttpbuilder;

import java.util.List;

/**
 * Created by kakalee on 15/8/1.
 */
public class WorksInfoDataModel implements BaseModel {

    private Integer worksTotal;

    private List<WorksInfoModel> worksList;

    private Integer isEnd;

    public Integer getWorksTotal() {
        return worksTotal;
    }

    public void setWorksTotal(Integer worksTotal) {
        this.worksTotal = worksTotal;
    }

    public List<WorksInfoModel> getWorksList() {
        return worksList;
    }

    public void setWorksList(List<WorksInfoModel> worksList) {
        this.worksList = worksList;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }
}
