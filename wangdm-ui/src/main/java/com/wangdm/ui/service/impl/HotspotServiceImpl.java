package com.wangdm.ui.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.constant.OrderType;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.Dao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.query.QueryResult;
import com.wangdm.core.service.BaseService;
import com.wangdm.ui.dto.HotspotDto;
import com.wangdm.ui.dto.HotspotShowDto;
import com.wangdm.ui.entity.Hotspot;
import com.wangdm.ui.query.HotspotQuery;
import com.wangdm.ui.service.HotspotService;

@Service("hotspotService")
@Transactional
public class HotspotServiceImpl extends BaseService<Hotspot> implements HotspotService {

    @Autowired
    private Dao<Hotspot> hotspotDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;

    @Override
    public Dto findById(Serializable id) {
        
        Hotspot hotspot = hotspotDao.findById(id, Hotspot.class);
        
        HotspotDto dto = new HotspotDto();
        dto.fromEntity(hotspot);
        
        return dto;
    }

    @Override
    public QueryResult query(Query q) {
        
        HotspotQuery query = (HotspotQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(Hotspot.class);

        if(query.getDisplay()!=null){
            constraint.addEqualCondition("display", query.getDisplay());
        }
        
        constraint.addEqualCondition("status", EntityStatus.NORMAL);

        constraint.setOrderProperty("idx");
        constraint.setOrderType(OrderType.ASC);

        constraint.setPage(query.getPage());
        constraint.setSize(query.getSize());
        
        List<Hotspot> hotspotList = hotspotDao.findByConstraint(constraint);
        if(hotspotList == null || hotspotList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>();
        for(Hotspot hotspot : hotspotList){
            HotspotDto dto = new HotspotDto();
            dto.fromEntity(hotspot);
            dtoList.add(dto);
        }
        
        return new QueryResult(query.getPage(), query.getSize(), constraint.getAmount(), dtoList);
    }

    @Override
    public List<HotspotShowDto> showHotspot(int count) {
        
        Constraint constraint = constraintFactory.createConstraint(Hotspot.class);
        
        constraint.addEqualCondition("display", true);
        
        List<EntityStatus> entityTypeList = new ArrayList<EntityStatus>();
        entityTypeList.add(EntityStatus.NORMAL);
        constraint.addEqualCondition("status", entityTypeList);
        
        Timestamp time = new Timestamp(System.currentTimeMillis());
        constraint.addGreaterCondition("expireTime", time);

        constraint.setOrderProperty("idx");
        constraint.setOrderType(OrderType.ASC);

        constraint.setPage(0);
        constraint.setSize(count);
        
        List<Hotspot> hotspotList = hotspotDao.findByConstraint(constraint);
        
        if(hotspotList == null || hotspotList.size()<=0){
            return null;
        }
        
        List<HotspotShowDto> dtoList = new ArrayList<HotspotShowDto>();
        for(Hotspot hotspot : hotspotList){
            HotspotShowDto dto = new HotspotShowDto();
            dto.fromEntity(hotspot);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

}
