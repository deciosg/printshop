package cc.printshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cc.printshop.model.Employee;
import cc.printshop.model.Role;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	public List<Employee> findAllByRole(Role role);
	public Employee findByUsername(String username);
}
