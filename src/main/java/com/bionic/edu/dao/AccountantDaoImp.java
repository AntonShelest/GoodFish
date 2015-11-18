package com.bionic.edu.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.Payment;
import com.bionic.edu.entity.SaleParcel;

@Repository
public class AccountantDaoImp implements AccountantDao {
	@PersistenceContext
	EntityManager em;
	
	//User Story #16
	public Payment registerPayment(Payment payment){
		em.merge(payment);
		return payment;
	}
	
	//User Story #16
	public double needToPay(SaleParcel saleParcel){
		String sql = "SELECT SUM(DISTINCT spi.weight*spi.price) FROM SaleParcelItem spi "
				+ "WHERE spi.saleParcel.id = :id";
		TypedQuery<Double> query = em.createQuery(sql, Double.class);
		query.setParameter("id", saleParcel.getId());
		return query.getSingleResult();
	}
	
	//User Story #16
	public double alreadyPayed(SaleParcel saleParcel){
		String sql = "SELECT SUM(DISTINCT p.amount) FROM Payment p "
				+ "WHERE p.saleParcel.id = :id";
		TypedQuery<Double> query = em.createQuery(sql, Double.class);
		query.setParameter("id", saleParcel.getId());
		try{return query.getSingleResult();}
		catch(NullPointerException e){return 0;}
	}
}
