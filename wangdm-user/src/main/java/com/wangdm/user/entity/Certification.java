package com.wangdm.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wangdm.core.entity.ForeignEntity;
import com.wangdm.user.entity.User;

@Entity
@Table(name="certification")
public class Certification extends ForeignEntity {
	
	private static final long serialVersionUID = 8601087068297528256L;
	
	@Id
    @OneToOne
    @JoinColumn(name="id", referencedColumnName="id", nullable=false)
    private User user;
	
    @Column(name="realname", nullable=false, length=40)
	private String realname;

    @Column(name="number", nullable=false, unique=true, length=40)
	private String number;

	@Override
	public Long getId() {
		
		if(this.user!=null)
			return this.user.getId();
		
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
