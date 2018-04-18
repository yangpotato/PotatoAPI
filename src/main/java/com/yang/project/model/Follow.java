package com.yang.project.model;

public class Follow {
    private String id;
    private String userId;
    private String followId;
    private String createTime;
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTiem) {
        this.createTime = createTiem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
