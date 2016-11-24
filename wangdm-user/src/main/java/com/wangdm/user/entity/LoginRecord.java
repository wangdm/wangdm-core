package com.wangdm.user.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wangdm.core.entity.BaseEntity;

/**
 * @author wangdm
 * @desc 用户登录记录
 * @version 1.0
 * @created 2016.06.27
 */
@Entity
@Table(name = "LoginRecord")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LoginRecord extends BaseEntity {

	private static final long serialVersionUID = -8898888308858793464L;

	//用户
    @ManyToOne
    @JoinColumn(name="uid", nullable=false)
    private User user;
    
    //登录时间
    @Column(name="loginTime", nullable=false)
    private Timestamp loginTime;
    
    //注销时间
    @Column(name="logoutTime")
    private Timestamp logoutTime;
    
    //评论IP
    @Column(name="ip")
    private String ip;
    
    //评论客户端
    @Column(name="agent")
    private String agent;
    
    public LoginRecord(){
    	
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
    
}