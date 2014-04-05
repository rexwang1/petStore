package com.mvc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(catalog="petstore",name="shoplist")
@NamedQueries({
	@NamedQuery(name = "findBySuit", 
			query = "from ShopList s where s.userInfo=:userInfo and s.status =:status" +
					" order by s.createDate asc")
})
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ShopList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Date createDate;
	private double money;
	private int amount;
	
	//取值 0:无效(已经购买) 1:待选购 2:已下单待购
	private int status;
	
	//预计发货事件
	private Date preDeliveryTime;
	
	//实际收获时间
	private Date receiveTime;
	
	//收件人姓名，默热为本人
	private String addressee;
	
	//收货地址,默认为本人地址
	private String consigneeAddress;
	//收获人电话 默认为本人电话
	private String consigneePhone;
	
	private UserInfo userInfo;
	private List<Commoditiez> commoditiezs;
	private PaymentType paymentType;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIME)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	

	@Temporal(TemporalType.TIME)
	public Date getPreDeliveryTime() {
		return preDeliveryTime;
	}

	public void setPreDeliveryTime(Date preDeliveryTime) {
		this.preDeliveryTime = preDeliveryTime;
	}

	@Temporal(TemporalType.TIME)
	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	@OneToMany()
	@JoinTable(name="shoplist_comdities",joinColumns={@JoinColumn(name="shoplist_id")},
			inverseJoinColumns={@JoinColumn(name="item_no")})
	public List<Commoditiez> getCommoditiezs() {
		return commoditiezs;
	}

	
	public void setCommoditiezs(List<Commoditiez> commoditiezs) {
		this.commoditiezs = commoditiezs;
	}

	
	
	@OneToOne(cascade={CascadeType.ALL})
	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="userInfo_id",referencedColumnName="id")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	
	
}
