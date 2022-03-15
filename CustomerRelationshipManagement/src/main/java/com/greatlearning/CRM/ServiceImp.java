package com.greatlearning.CRM;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ServiceImp implements Services {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public ServiceImp(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		this.session = this.sessionFactory.openSession();
	}

	@Transactional
	public List<Customer> showAll() {

		Transaction transaction = session.beginTransaction();

		List<Customer> cust = session.createQuery("from Customer", Customer.class).list();

		transaction.commit();

		return cust;
	}

	
	
	@Transactional
	public Customer findById(int id) {
		Transaction transaction = session.beginTransaction();

		Customer cust = session.get(Customer.class, id);

		transaction.commit();
		return cust;
	}

	@Transactional
	public void save(Customer cust) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(cust);
		transaction.commit();
	}

	@Transactional
	public void deleteById(int id) {
		Transaction transaction = session.beginTransaction();
		try {
			Customer cust = session.get(Customer.class, id);
			session.delete(cust);
		} catch (Exception e) {
			System.out.println("Error in deleteById for id " + id);
			e.printStackTrace();
			
		} 
			transaction.commit();
		
	}

}