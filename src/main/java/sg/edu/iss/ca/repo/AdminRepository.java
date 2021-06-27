package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findAdminByUserNameAndUserPassword(String userName, String userPassword);
    public Admin findAdminByUserName(String userName);
	
}
