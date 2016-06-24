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
import org.springframework.web.bind.annotation.RequestParam;

import cc.printshop.model.Client;
import cc.printshop.repository.ClientRepository;
import cc.printshop.repository.WorkRepository;

@Controller
@RequestMapping("/clientes")
public class ClientController {
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	// LIST
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("clients", clientRepository.findAll());
		return "client/list";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String searchByCpf(@RequestParam("cpf") String cpf, Client client, Model model){
		client = clientRepository.findByCpf(cpf);
		if (client == null) {
			model.addAttribute("clients", clientRepository.findAll());
			model.addAttribute("error", "Cliente não encontrado.");
			return "client/list";
		}
		return "redirect:/clientes/" + client.getId();
	}
	
	// SHOW
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		Client client = clientRepository.findOne(id);
		
		model.addAttribute("client", client);
		model.addAttribute("works", workRepository.showByClient(client));
		return "client/show";
	}
	
	// CREATE (NEW)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String create(Client client) {
		return "client/form";
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String create(@Valid Client client, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "client/form";
		}
		clientRepository.save(client);
		model.addAttribute("client", client);
		return "redirect:/clientes/" + client.getId();
	}
	
	// UPDATE (EDIT)
	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("client", clientRepository.findOne(id));
		return "client/form";
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
	public String update(@Valid Client client, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "client/form";
		}
		clientRepository.save(client);
		model.addAttribute("client", client);
		return "redirect:/clientes/" + client.getId();
	}
}
