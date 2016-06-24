package cc.printshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.printshop.model.Role;
import cc.printshop.model.Employee;
import cc.printshop.repository.EmployeeRepository;

@Controller
@RequestMapping("/atendentes")
public class AttendantController {
	@Autowired
	private EmployeeRepository employeeRepository;

	// LIST
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("titleHeader", "Atendentes");
		model.addAttribute("employees", employeeRepository.findAllByRole(Role.ROLE_ATTENDANT));
		return "employee/list";
	}

	// SHOW
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("employee", employeeRepository.findOne(id));
		return "employee/show";
	}

	// CREATE (ADD)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String create(Employee user, Model model) {
		return "employee/form";
	}

	// UPDATE (EDIT)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("employee", employeeRepository.findOne(id));
		return "employee/form";
	}

	// CREATE OR UPDATE
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = { "/adicionar", "/editar/{id}" }, method = RequestMethod.POST)
	public String createOrUpdate(@Valid Employee user, BindingResult bindingResult, Model model) {
		user.setRole(Role.ROLE_ATTENDANT);
		if (bindingResult.hasErrors()) {
			return "employee/form";
		}
		employeeRepository.save(user);
		return "redirect:/atendentes/" + user.getId();
	}

	// DELETE (REMOVE)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {
		employeeRepository.delete(id);
		return "redirect:/atendentes";
	}
}
