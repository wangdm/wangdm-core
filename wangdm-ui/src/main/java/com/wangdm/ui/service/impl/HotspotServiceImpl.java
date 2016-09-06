package com.wangdm.ui.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.constraint.Order.OrderType;
import com.wangdm.core.dao.BaseDao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
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
    private BaseDao<Hotspot> hotspotDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Serializable create(Dto dto) {
        
        HotspotDto hotspotDto = (HotspotDto)dto;
        
        Hotspot hotspot = (Hotspot)hotspotDto.toEntity(Hotspot.class);
        
        hotspot.setCreateTime(new Timestamp(System.currentTimeMillis()));
        hotspot.setExpireTime(new Timestamp(System.currentTimeMillis()+(long)30*24*60*60*1000));
        
        return hotspotDao.create(hotspot);
    }

    @Override
    public void update(Dto dto) {

        HotspotDto hotspotDto = (HotspotDto)dto;
        
        Hotspot hotspot = (Hotspot)hotspotDto.toEntity(Hotspot.class);
        
        hotspotDao.update(hotspot);

    }

    @Override
    public void delete(Serializable id) {
        
        hotspotDao.delete(Hotspot.class, id);

    }

    @Override
    public Dto findById(Serializable id) {
        
        Hotspot hotspot = hotspotDao.findById(Hotspot.class, id);
        
        HotspotDto dto = new HotspotDto();
        dto.fromEntity(hotspot);
        
        return dto;
    }

    @Override
    public List<Dto> query(Query q) {
        HotspotQuery query = (HotspotQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(Hotspot.class);
        
        if(query!=null){
            if(query.getDisplay()!=null){
                constraint.addEqualCondition("display", query.getDisplay());
            }
            
            if(query.getStatus()!=null){
                constraint.addEqualCondition("status", query.getStatus());
            }
            
            if(query.getOrder()!=null){
                constraint.setOrderProperty(query.getOrder());
            }
            
            constraint.setCurrentPage(query.getCurrentPage());
            constraint.setPageSize(query.getPageSize());
        }
        
        List<Hotspot> hotspotList = hotspotDao.findByConstraint(constraint);
        
        if(hotspotList == null || hotspotList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>();
        for(Hotspot hotspot : hotspotList){
            HotspotShowDto dto = new HotspotShowDto();
            dto.fromEntity(hotspot);
            dtoList.add(dto);
        }
        
        return dtoList;
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
        
        constraint.addOrder("idx", OrderType.ASC);
        
        constraint.setCurrentPage(0);
        constraint.setPageSize(count);
        
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
