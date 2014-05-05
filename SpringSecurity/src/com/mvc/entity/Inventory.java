package com.mvc.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(catalog="petstore",name="inventory")
@NamedQueries({
	@NamedQuery(name = "findInventoryByType", query = "from Inventory where type=:inventory")
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inventory implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	//private long itemNo;
	//入货时间
	private Date purchaseDate;
	
	//品牌
	private String brand;
	//商品名
	private String name;
	//生产厂家
	private String factory;
	//适用范围
	private String suit;
	//功效
	private String effect;
	//原料
	private String material;
	
	//数量
	private long amount;
	
	//用品类型：狗，猫
	private String type;
	private List<Commoditiez> commoditiezs;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
//	public long getItemNo() {
//		return itemNo;
//	}
//	public void setItemNo(long itemNo) {
//		this.itemNo = itemNo;
//	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	@Lob
	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	@Lob
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	@OneToMany(mappedBy ="inventory")
	public List<Commoditiez> getCommoditiezs() {
		return commoditiezs;
	}

	public void setCommoditiezs(List<Commoditiez> commoditiezs) {
		this.commoditiezs = commoditiezs;
	}	
	
	
}
