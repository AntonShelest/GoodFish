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
	
	@Inject
	private CustomerService customerService;
	
	public CustomerBean() { }
	
	@PostConstruct
	public void init(){
		user = currentUser.getUser();
		parcel = new SaleParcel();
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
	
	public String addItem(FishItem fi, double weight){
		SaleParcelItem spi = new SaleParcelItem();
		spi.setFishItem(fi);
		spi.setPrice(fi.getPrice());
		spi.setSaleParcel(parcel);
		spi.setWeight(weight);
		List<SaleParcelItem> spiList = new ArrayList<SaleParcelItem>();
		if (parcel.getSaleParcelItems() != null){
			spiList = (List<SaleParcelItem>)parcel.getSaleParcelItems();
		}
		spiList.add(spi);
		parcel.setSaleParcelItems(spiList);
		return "Customer";
	}
}
