package com.wangdm.ui.service;

import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.ui.dto.HotspotShowDto;
import com.wangdm.ui.query.HotspotQuery;

public interface HotspotService extends Service<HotspotQuery>{
    
    public List<HotspotShowDto> showHotspot(int cout);

}
