package com.wangdm.core.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ForeignEntity extends Pojo implements Entity {

	private static final long serialVersionUID = 558471171157871925L;

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
        return result;
    }
    
    @Override
    public boolean equals( Object obj ) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ForeignEntity other = (ForeignEntity) obj;
        if (this.getId() != other.getId())
            return false;
        return true;
    }

}
