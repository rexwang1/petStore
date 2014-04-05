package com.mvc.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(catalog="petstore",name="user_info")
@NamedQueries({
	@NamedQuery(name = "findUserInfoByUserName", query = "from UserInfo where username=:userInfo")
})

@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String username;
	
	
	private Date birthday;
	private String phone;
	private String address;
	private int sex;
	private String zipcode;
	
	private Collection<ShopList> shopLists;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Column(unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="userInfo",fetch = FetchType.LAZY)
//	@JoinTable(name="userinfo_shoplist",joinColumns={@JoinColumn(name="userlist_id")},
//			inverseJoinColumns={@JoinColumn(name="user_info_id")})
	public Collection<ShopList> getShopLists() {
		return shopLists;
	}
	public void setShopLists(Collection<ShopList> shopLists) {
		this.shopLists = shopLists;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	
}
