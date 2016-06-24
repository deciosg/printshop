package cc.printshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.printshop.model.Work;
import cc.printshop.repository.WorkRepository;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private WorkRepository workRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		
		return "dashboard/index";
	}
	
	@RequestMapping(value = "/json/works", method = RequestMethod.GET)
	public @ResponseBody Iterable<Work> jsonWorks() {
		return workRepository.findAll();
	}
}
