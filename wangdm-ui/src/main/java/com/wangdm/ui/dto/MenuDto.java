package com.wangdm.ui.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.ui.entity.Menu;

public class MenuDto extends BaseDto {

    @DtoMapper(entity=Menu.class, field="id")
    private String id;
    
    @DtoMapper(entity=Menu.class, field="name")
    private String name;

    @DtoMapper(entity=Menu.class, field="url")
    private String url;

    @DtoMapper(entity=Menu.class, field="icon")
    private String icon;

    @DtoMapper(entity=Menu.class, field="idx")
    private Integer idx = 0;

    @DtoMapper(entity=Menu.class, field="display")
    private Boolean display = false;

    @DtoMapper(entity=Menu.class, field="parent.id")
    private String parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
