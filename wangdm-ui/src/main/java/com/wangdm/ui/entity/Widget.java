package com.wangdm.ui.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="widget")
public class Widget extends BaseEntity {

    private static final long serialVersionUID = -7371525847890406249L;

    @Column(name="title", nullable=false, length=40)
    private String title;

}
