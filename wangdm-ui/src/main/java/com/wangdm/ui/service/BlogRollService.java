package com.wangdm.ui.service;

import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.ui.dto.BlogRollShowDto;

public interface BlogRollService extends Service{

    List<BlogRollShowDto> showBlogRoll();
}
