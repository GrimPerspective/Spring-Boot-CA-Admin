package sg.edu.iss.ca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Admin;
import sg.edu.iss.ca.repo.AdminRepository;

@Service
public class AdminService implements AdminInterface {
	
	@Autowired
	AdminRepository aRepo;

	@Override
	public boolean authenticate(Admin admin) {
		Admin verifyAdmin = aRepo.findAdminByUserNameAndUserPassword(admin.getUserName(), admin.getUserPassword());
		if (verifyAdmin != null)
			return true;
		else
			return false;
	}

	@Override
	public Admin findByName(String theName) {
		return aRepo.findAdminByUserName(theName);
	}


}
