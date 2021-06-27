package sg.edu.iss.ca.service;

import sg.edu.iss.ca.model.Admin;

public interface AdminInterface {

	public boolean authenticate(Admin admin);
	public Admin findByName(String theName);

	
}
