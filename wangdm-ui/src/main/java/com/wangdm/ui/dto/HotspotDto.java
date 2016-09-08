package com.wangdm.ui.dto;

import java.sql.Timestamp;

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
    private Integer idx = 0;

    @DtoMapper(entity = Hotspot.class, field = "display")
    private Boolean display = false;

    @DtoMapper(entity = Hotspot.class, field = "createTime")
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    @DtoMapper(entity = Hotspot.class, field = "expireTime")
    private Timestamp expireTime = new Timestamp(System.currentTimeMillis()+30*24*60*60*1000);

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

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }
    
}
