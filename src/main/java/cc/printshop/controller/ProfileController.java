package cc.printshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.printshop.form.ChangePasswordForm;
import cc.printshop.model.Employee;
import cc.printshop.repository.EmployeeRepository;

@Controller
@RequestMapping("/perfil")
public class ProfileController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(value = "/alterar-senha", method = RequestMethod.GET)
	public String changePassword(ChangePasswordForm form) {
		return "profile/password";
	}

	@RequestMapping(value = "/alterar-senha", method = RequestMethod.POST)
	public String changePassword(@Valid ChangePasswordForm form, Employee employee, Authentication authentication) {
		employee = employeeRepository.findByUsername(authentication.getName());
		if (BCrypt.checkpw(form.getCurrentPassword(), employee.getPassword()) &&
				form.getNewPassword().contentEquals(form.getRepeatNewPassword())) {
			employee.setPassword(form.getNewPassword());
			employeeRepository.save(employee);
		}
		return "profile/password";
	}
}
