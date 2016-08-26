package com.wangdm.ui.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = -4984568164796899904L;

    @Column(name="name", nullable=false, length=20)
    private String name;

    @Column(name="url", nullable=false, length=300)
    private String url;

    @Column(name="icon", nullable=false, length=100)
    private String icon;

    @Column(name="idx", nullable=false)
    private Integer idx = 0;

    @Column(name="display", nullable=false)
    private Boolean show = false;

    @Column(name="create_time", nullable=false)
    private Timestamp createTime;

    @ManyToOne(targetEntity=Menu.class, fetch=FetchType.LAZY)
    @JoinColumn(name="parentId", referencedColumnName="id")
    private Menu parent;

    @OneToMany(targetEntity=Menu.class, mappedBy="parent")
    private List<Menu> children;

}
