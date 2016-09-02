package com.wangdm.ui.service;

import java.io.Serializable;
import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.ui.dto.MenuShowDto;
import com.wangdm.ui.query.MenuQuery;

public interface MenuService extends Service<MenuQuery> {
    
    public List<MenuShowDto> showAdminMainMenu();

    public List<MenuShowDto> showAdminChildrenMenu(Serializable id);

}
