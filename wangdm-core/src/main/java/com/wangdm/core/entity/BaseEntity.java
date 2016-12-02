package com.wangdm.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.wangdm.core.constant.EntityStatus;

@MappedSuperclass
public abstract class BaseEntity extends Pojo implements Entity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private Long id;
    
    @Column(name="status", nullable=false)
    private EntityStatus status = EntityStatus.NORMAL;
    
    @Override
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (id == null || other.id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
