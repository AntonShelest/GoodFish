package com.bionic.edu.service;

import java.util.List;

import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.SaleParcel;

public interface CustomerService {
	//User Story #1
	public List<FishItem> getFish4Sale();
	
	//User Story #2
	public SaleParcel sumbitSaleParcel(SaleParcel saleParcel);
}
