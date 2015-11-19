package com.bionic.edu.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.SaleParcel;
import com.bionic.edu.entity.SaleParcelItem;
import com.bionic.edu.entity.User;
import com.bionic.edu.service.CustomerService;

@Named("customer")
@Scope("session")
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private UserBean currentUser;
	private User user = null;
	private SaleParcel parcel = null;
	private SaleParcelItem spi = null;
	private double itemWeight = 0;
	private String itemSelected = "false";
	
	@Inject
	private CustomerService customerService;
	
	public CustomerBean() { }
	
	@PostConstruct
	public void init(){
		user = currentUser.getUser();
		parcel = new SaleParcel();
		spi = new SaleParcelItem();
	}
	
	public UserBean getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserBean currentUser) {
		this.currentUser = currentUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public SaleParcel getParcel() {
		return parcel;
	}

	public void setParcel(SaleParcel parcel) {
		this.parcel = parcel;
	}

	public SaleParcelItem getSpi() {
		return spi;
	}

	public void setSpi(SaleParcelItem spi) {
		this.spi = spi;
	}
	
	public double getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
	}

	public String getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(String itemSelected) {
		this.itemSelected = itemSelected;
	}

	public String specifyItemWeight(){
		if (itemSelected.equals("false"))
			return "Customer";
		spi.setWeight(itemWeight);
		List<SaleParcelItem> spiList = new ArrayList<SaleParcelItem>();
		if (parcel.getSaleParcelItems() != null){
			spiList = (List<SaleParcelItem>)parcel.getSaleParcelItems();
		}
		spiList.add(spi);
		parcel.setSaleParcelItems(spiList);
		
		spi = new SaleParcelItem();
		itemSelected = "false";
		itemWeight = 0;
		return "Customer";
	}

	public String addItem(FishItem fi){
		spi.setFishItem(fi);
		spi.setPrice(fi.getPrice());
		spi.setSaleParcel(parcel);
		fi.setWeight(fi.getWeight() - spi.getWeight());
		itemSelected = "true";
		return "Customer";
	}
	
	public String getTotalWeight(){
		double sum = 0;
		if (parcel.getSaleParcelItems() != null)
			for(SaleParcelItem spi: parcel.getSaleParcelItems())
				sum += spi.getWeight();
		return " "+sum;
	}
	
	public double getTotalPrice(){
		double sum = 0;
		for(SaleParcelItem spi: parcel.getSaleParcelItems())
			sum += spi.getPrice();
		return sum;
	}
}
