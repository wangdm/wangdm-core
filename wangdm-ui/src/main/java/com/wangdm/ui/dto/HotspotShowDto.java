package com.wangdm.ui.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.ui.entity.Hotspot;

public class HotspotShowDto extends BaseDto {

    @DtoMapper(entity = Hotspot.class, field = "title")
    private String title;

    @DtoMapper(entity = Hotspot.class, field = "url")
    private String url;

    @DtoMapper(entity = Hotspot.class, field = "image")
    private String image;

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

}
