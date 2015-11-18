package com.bionic.edu.service;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.AccountantDao;
import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.SaleParcel;

@Named
public class AccountantServiceImp implements AccountantService{
	@Inject
	AccountantDao accountantDao;
	
	//User Story #16
	@Transactional
	public double registerPayment(Payment payment){
		double remainedAmount = payment.getAmount();
    	double required = 0;
    	SaleParcel saleParcel = payment.getSaleParcel();	
		if ((required = accountantDao.needToPay(saleParcel) - 
						accountantDao.alreadyPayed(saleParcel)) <= remainedAmount) {
			remainedAmount -= required;
			payment.setAmount(required);
			
			saleParcel.setStatus("P");
			if (saleParcel.getShipped() != null){
				saleParcel.setClosed(java.sql.Timestamp.valueOf(LocalDateTime.now()));
				saleParcel.setStatus("C");
			}
		}
		else {		
			if (remainedAmount + accountantDao.alreadyPayed(saleParcel) >=
					accountantDao.needToPay(saleParcel) * 
					saleParcel.getUser().getPrepaymentPercent()/100)
				saleParcel.setStatus("R");
			remainedAmount = 0;
		}	

		accountantDao.registerPayment(payment);
		return remainedAmount;
	}
}
