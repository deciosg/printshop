package cc.printshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.printshop.model.Item;
import cc.printshop.model.StatusItem;
import cc.printshop.repository.ItemRepository;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/para-analise", method = RequestMethod.POST)
	public String itemForAnalisis(@RequestParam("workId") Long workId, @RequestParam("itemId") Long itemId, Item item) {
		item = itemRepository.findOne(itemId);
		if (item.getStatus() == StatusItem.REQUESTED || item.getStatus() == StatusItem.REJECTED) {
			item.setStatus(StatusItem.IN_ANALYSIS);
			item.setStatusReason(null);
			itemRepository.save(item);
		}
		return "redirect:/servicos/" + workId;
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/cancelar", method = RequestMethod.POST)
	public String itemCancel(@RequestParam("workId") Long workId, @RequestParam("itemId") Long itemId,
			@RequestParam("reason") String reason, Item item) {
		item = itemRepository.findOne(itemId);
		if (item.getStatus() == StatusItem.REQUESTED || item.getStatus() == StatusItem.REJECTED) {
			item.setStatus(StatusItem.CANCELED);
			item.setStatusReason(reason);
			itemRepository.save(item);
		}
		return "redirect:/servicos/" + workId;
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('DESIGN')")
	@RequestMapping(value = "/aprovar", method = RequestMethod.POST)
	public String itemApprove(@RequestParam("workId") Long workId, @RequestParam("itemId") Long itemId, Item item) {
		item = itemRepository.findOne(itemId);
		if (item.getStatus() == StatusItem.IN_ANALYSIS) {
			item.setStatus(StatusItem.APPROVED);
			item.setStatusReason(null);
			itemRepository.save(item);
		}
		return "redirect:/servicos/" + workId;
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('DESIGN')")
	@RequestMapping(value = "/refugar", method = RequestMethod.POST)
	public String itemReject(@RequestParam("workId") Long workId, @RequestParam("itemId") Long itemId,
			@RequestParam("reason") String reason, Item item) {
		item = itemRepository.findOne(itemId);
		if (item.getStatus() == StatusItem.IN_ANALYSIS) {
			item.setStatus(StatusItem.REJECTED);
			item.setStatusReason(reason);
			itemRepository.save(item);
		}
		return "redirect:/servicos/" + workId;
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
	@RequestMapping(value = "/em-producao", method = RequestMethod.POST)
	public String itemProduction(@RequestParam("workId") Long workId, @RequestParam("itemId") Long itemId, Item item) {
		item = itemRepository.findOne(itemId);
		if (item.getStatus() == StatusItem.APPROVED) {
			item.setStatus(StatusItem.IN_PRODUCTION);
			item.setStatusReason(null);
			itemRepository.save(item);
		}
		return "redirect:/servicos/" + workId;
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
	@RequestMapping(value = "/disponivel-para-retirada", method = RequestMethod.POST)
	public String itemAvaliable(@RequestParam("workId") Long workId, @RequestParam("itemId") Long itemId, Item item) {
		item = itemRepository.findOne(itemId);
		if (item.getStatus() == StatusItem.IN_PRODUCTION) {
			item.setStatus(StatusItem.AVAILABLE_FOR_REMOVED);
			item.setStatusReason(null);
			itemRepository.save(item);
		}
		return "redirect:/servicos/" + workId;
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/retirar", method = RequestMethod.POST)
	public String itemRemove(@RequestParam("workId") Long workId, @RequestParam("itemId") Long itemId, Item item) {
		item = itemRepository.findOne(itemId);
		if (item.getStatus() == StatusItem.AVAILABLE_FOR_REMOVED) {
			item.setStatus(StatusItem.REMOVED);
			item.setStatusReason(null);
			itemRepository.save(item);
		}
		return "redirect:/servicos/" + workId;
	}
}
