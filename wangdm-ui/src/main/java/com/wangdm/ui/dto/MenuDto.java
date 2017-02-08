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
    private String idx;

    @DtoMapper(entity=Menu.class, field="display")
    private String display;

    @DtoMapper(entity=Menu.class, field="type")
    private String type;

    @DtoMapper(entity=Menu.class, field="parent.id")
    private String parentId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
