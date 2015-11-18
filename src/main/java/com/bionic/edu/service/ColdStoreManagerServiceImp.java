package com.bionic.edu.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.ColdStoreManagerDao;
import com.bionic.edu.entity.FishItem;
import com.bionic.edu.entity.PurchaseParcel;
import com.bionic.edu.entity.SaleParcel;

@Named
public class ColdStoreManagerServiceImp implements ColdStoreManagerService {
	@Inject
	ColdStoreManagerDao coldStoreManagerDao;
	
	//User Story #13
	public List<PurchaseParcel> getParcelsToArrive(){
		return coldStoreManagerDao.getParcelsToArrive();
	}
	
	//User Story #13
	@Transactional
	public PurchaseParcel registerParcelArrival(PurchaseParcel purchaseParcel){
		return coldStoreManagerDao.registerParcelArrival(purchaseParcel);
	}
	
	//User Story #14
	public List<SaleParcel> getParcelsToShip(){
		return coldStoreManagerDao.getParcelsToShip();
	}
	
	//User Story #14
	@Transactional
	public SaleParcel registerShipment(SaleParcel saleParcel){
		if (saleParcel.getStatus().equals("R")){
			saleParcel.setStatus("H");
			return coldStoreManagerDao.registerShipment(saleParcel);
		}
		return saleParcel;
	}
	
	//User Story #15
	@Transactional
	public FishItem updateFishItem(FishItem fishItem){
		return coldStoreManagerDao.updateFishItem(fishItem);
	}
	
	//User Story #15
	public List<FishItem> getCurrentFishItems(){
		return coldStoreManagerDao.getCurrentFishItems();
	}
}
