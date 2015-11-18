package com.bionic.edu.dao;

import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.SaleParcel;

public interface AccountantDao {

	//User Story #16
	public Payment registerPayment(Payment payment);
	
	//User Story #16
	public double needToPay(SaleParcel saleParcel);
	
	//User Story #16
	public double alreadyPayed(SaleParcel saleParcel);
}
