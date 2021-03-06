package com.wangdm.ui.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.ui.entity.Hotspot;

public class HotspotDto extends BaseDto {

    @DtoMapper(entity = Hotspot.class, field = "id")
    private String id;

    @DtoMapper(entity = Hotspot.class, field = "status")
    private String status;

    @DtoMapper(entity = Hotspot.class, field = "title")
    private String title;

    @DtoMapper(entity = Hotspot.class, field = "url")
    private String url;

    @DtoMapper(entity = Hotspot.class, field = "image")
    private String image;

    @DtoMapper(entity = Hotspot.class, field = "idx")
    private String idx ;

    @DtoMapper(entity = Hotspot.class, field = "display")
    private String display;

    @DtoMapper(entity = Hotspot.class, field = "createTime")
    private String createTime;

    @DtoMapper(entity = Hotspot.class, field = "expireTime")
    private String expireTime;

    @Override
    public Long getEntityId() {
        return Long.valueOf(id);
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
    
}
