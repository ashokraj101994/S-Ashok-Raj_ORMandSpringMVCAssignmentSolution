package com.greatlearning.CRM;

import java.util.List;

public interface Services {
	
	public List<Customer> showAll();
	
	public Customer findById(int id);
	
	public void save(Customer cust);
	
	public void deleteById(int id);
}