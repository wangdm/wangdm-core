package com.wangdm.ui.service;

import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.ui.dto.HotspotShowDto;

public interface HotspotService extends Service{
    
    public List<HotspotShowDto> showHotspot(int cout);

}
