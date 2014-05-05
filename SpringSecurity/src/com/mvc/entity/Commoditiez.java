package com.mvc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 商品信息表，主要记录商品ID、品牌、厂家、有效期、用途和原材料
 * @author LiangJiaYuan
 *
 */

@Entity
@Table(catalog="petstore",name="commodities")

@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Commoditiez implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//商品ID号
	private long itemNo;
	
	//规格
	private String standard;
	
	private double price;
	//过期时间
	private Date expiryDate;
	
	//生产时间
	private Date createDate;
	
	
	private Inventory inventory;
	
	@ManyToOne
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getItemNo() {
		return itemNo;
	}
	public void setItemNo(long itemNo) {
		this.itemNo = itemNo;
	}
	
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
	
	
}
