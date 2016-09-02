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
    private Boolean display = false;

    @Column(name="create_time", nullable=false)
    private Timestamp createTime;

    @ManyToOne(targetEntity=Menu.class, fetch=FetchType.LAZY)
    @JoinColumn(name="parentId", referencedColumnName="id")
    private Menu parent = null;

    @OneToMany(targetEntity=Menu.class, mappedBy="parent")
    private List<Menu> children = null;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

}
