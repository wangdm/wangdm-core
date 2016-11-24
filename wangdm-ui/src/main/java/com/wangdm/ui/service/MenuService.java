package com.wangdm.ui.service;

import java.io.Serializable;
import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.ui.dto.MenuShowDto;

public interface MenuService extends Service {

    public List<MenuShowDto> showChildrenMenu(Serializable id);
    
    public List<MenuShowDto> showAdminMainMenu();
    
    public List<MenuShowDto> showNavigationMenu();

}
