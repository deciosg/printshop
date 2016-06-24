package cc.printshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.printshop.model.Item;
import cc.printshop.model.Role;
import cc.printshop.model.StatusItem;
import cc.printshop.model.Work;
import cc.printshop.repository.ClientRepository;
import cc.printshop.repository.EmployeeRepository;
import cc.printshop.repository.WorkRepository;
import cc.printshop.util.PaymentUtil;

@Controller
@RequestMapping("/servicos")
public class WorkController {
	@Autowired
	private WorkRepository workRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private EmployeeRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("works", workRepository.requestedDateDesc());
		return "work/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("work", workRepository.findOne(id));
		return "work/show";
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String create(Work work, final Model model) {
		model.addAttribute("clients", clientRepository.findAll());
		model.addAttribute("attendants", userRepository.findAllByRole(Role.ROLE_ATTENDANT));
		return "work/form";
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String create(Work work, @RequestParam("payment") Integer paymentType, BindingResult result, Authentication authentication) {
		if(result.hasErrors()) {
			return "work/form";
		}
		for (Item item : work.getItems()) {
			item.setStatus(StatusItem.REQUESTED);
		}
		for (Object auth : authentication.getAuthorities().toArray()) {
			if (auth.toString() == "ROLE_ATTENDANT") {
				work.setAttendant(userRepository.findByUsername(authentication.getName()));
			}
		}
		work.setPayments(PaymentUtil.payment(paymentType, work.getSumItems()));
		workRepository.save(work);
		return "redirect:/servicos/" + work.getId();
	}
}
