package com.wangdm.ui.dto;

import java.util.List;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.ui.entity.Menu;

public class MenuShowDto extends BaseDto {
    
    @DtoMapper(entity=Menu.class, field="name")
    private String name;
    
    @DtoMapper(entity=Menu.class, field="url")
    private String url;
    
    @DtoMapper(entity=Menu.class, field="icon")
    private String icon;
    
    private List<MenuShowDto> children;

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

    public List<MenuShowDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuShowDto> children) {
        this.children = children;
    }

}
