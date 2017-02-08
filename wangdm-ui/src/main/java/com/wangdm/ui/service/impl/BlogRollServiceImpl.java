package com.wangdm.ui.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constant.OrderType;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.Dao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.query.QueryResult;
import com.wangdm.core.service.BaseService;
import com.wangdm.ui.dto.BlogRollDto;
import com.wangdm.ui.dto.BlogRollShowDto;
import com.wangdm.ui.entity.BlogRoll;
import com.wangdm.ui.query.BlogRollQuery;
import com.wangdm.ui.service.BlogRollService;

@Service("blogrollService")
@Transactional
public class BlogRollServiceImpl extends BaseService<BlogRoll> implements BlogRollService {

	private static final Logger log = LoggerFactory.getLogger(BlogRollServiceImpl.class);

	@Autowired
	private ConstraintFactory constraintFactory;
	@Autowired
	private Dao<BlogRoll> baseDao;

	@Override
	public Dto findById(Serializable id) {
		BlogRoll entiry = baseDao.findById(id, BlogRoll.class);
		if (entiry == null) {
			log.info("No such BlogRoll[id=" + id + "] is found");
			return null;
		}
		BlogRollDto dto = new BlogRollDto();
		dto.fromEntity(entiry);
		return dto;
	}

	@Override
	public QueryResult query(Query q) {
	    
	    BlogRollQuery query = (BlogRollQuery)q;

		Constraint constraint = constraintFactory.createConstraint(BlogRoll.class);

        if(query.getDisplay()!=null){
            constraint.addEqualCondition("display", query.getDisplay());
        }

        constraint.setOrderProperty("idx");
        constraint.setOrderType(OrderType.ASC);

        constraint.setPage(query.getPage());
        constraint.setSize(query.getSize());

		List<BlogRoll> entityList = baseDao.findByConstraint(constraint);
		if (entityList == null || entityList.size() <= 0) {
			return null;
		}

		List<Dto> dtoList = new ArrayList<Dto>(entityList.size());
		for (BlogRoll entity : entityList) {
			Dto dto = new BlogRollDto();
			dto.fromEntity(entity);
			dtoList.add(dto);
		}

		return new QueryResult(query.getPage(), query.getSize(), constraint.getAmount(), dtoList);
	}

    @Override
    public List<BlogRollShowDto> showBlogRoll() {

        Constraint constraint = constraintFactory.createConstraint(BlogRoll.class);
        
        constraint.addEqualCondition("display", true);

        constraint.setOrderProperty("idx");
        constraint.setOrderType(OrderType.ASC);
        
        constraint.setSize(8);

        List<BlogRoll> entityList = baseDao.findByConstraint(constraint);
        if (entityList == null || entityList.size() <= 0) {
            return null;
        }

        List<BlogRollShowDto> dtoList = new ArrayList<BlogRollShowDto>(entityList.size());
        for (BlogRoll entity : entityList) {
            BlogRollShowDto dto = new BlogRollShowDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
