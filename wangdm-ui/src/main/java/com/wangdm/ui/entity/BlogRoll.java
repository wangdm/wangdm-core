package com.wangdm.ui.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wangdm.core.entity.BaseEntity;

/*
 * 课程视频的分类实体
 */
@Entity
@Table(name = "blogroll")
public class BlogRoll extends BaseEntity{

	private static final long serialVersionUID = 1L;

    @Column(name="title", nullable=false, length=40)
    private String title;

    @Column(name="url", nullable=false, length=300)
    private String url;

    @Column(name="image", nullable=false, length=100)
    private String image;

    @Column(name="idx", nullable=false)
    private Integer idx = 0;

    @Column(name="display", nullable=false)
    private Boolean display = false;

    @Column(name="create_time", nullable=false, updatable=false)
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
	
}