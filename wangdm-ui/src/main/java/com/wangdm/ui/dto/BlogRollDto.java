package com.wangdm.ui.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.ui.entity.BlogRoll;

/*
 * 直播课程的实体
 */
public class BlogRollDto extends BaseDto {

    @DtoMapper(entity = BlogRoll.class, field = "id")
    private String id;

    @DtoMapper(entity = BlogRoll.class, field = "status")
    private String status;

    @DtoMapper(entity = BlogRoll.class, field = "title")
    private String title;

    @DtoMapper(entity = BlogRoll.class, field = "url")
    private String url;

    @DtoMapper(entity = BlogRoll.class, field = "image")
    private String image;

    @DtoMapper(entity = BlogRoll.class, field = "idx")
    private String idx ;

    @DtoMapper(entity = BlogRoll.class, field = "display")
    private String display;

    @DtoMapper(entity = BlogRoll.class, field = "createTime")
    private String createTime;

    @Override
    public Long getEntityId() {
        return Long.parseLong(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}