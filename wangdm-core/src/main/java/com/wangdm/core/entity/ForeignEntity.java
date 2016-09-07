package com.wangdm.core.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wangdm.core.constant.EntityStatus;

@MappedSuperclass
public abstract class ForeignEntity extends Pojo implements Entity {

	private static final long serialVersionUID = 558471171157871925L;
	
    @Column(name="status", nullable=false)
    private EntityStatus status = EntityStatus.UNAURHORIZED;

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(EntityStatus status) {
        this.status = status;
    }

}
