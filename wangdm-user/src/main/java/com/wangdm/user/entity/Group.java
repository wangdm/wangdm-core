package com.wangdm.user.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="usergroup")
public class Group extends BaseEntity {

    private static final long serialVersionUID = -6051400523431369139L;
    
    public static final EntityType entityType = EntityType.GROUP;
    
    @Column(name="name", nullable=false, length=20)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="parentId", referencedColumnName="id")
    private Group parent;
    
    @OneToMany(targetEntity=Group.class, mappedBy="parent")
    private List<Group> children;

    @OneToMany(targetEntity=GroupRole.class, mappedBy="role")
    private Set<GroupRole> role;

}
