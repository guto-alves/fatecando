package com.gutotech.fatecando.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.fatecando.model.Ticket;
import com.gutotech.fatecando.model.Ticket.Category;
import com.gutotech.fatecando.model.Ticket.Status;
import com.gutotech.fatecando.model.TicketResponse;
import com.gutotech.fatecando.service.TicketService;

@Controller
@RequestMapping("admin/support")
public class SupportAdminController {

	@Autowired
	private TicketService ticketService;

	@ModelAttribute("categories")
	public Category[] getCategories() {
		return Category.values();
	}

	@GetMapping
	public String showSupportPage(Model model) {
		model.addAttribute("tickets", ticketService.findAll());
		return "admin/support-tickets";
	}

	@GetMapping("ticket/{id}")
	public String showTicketPage(@PathVariable Long id, Model model) {
		Ticket ticket = ticketService.findById(id);
		model.addAttribute("ticket", ticket);
		model.addAttribute("responses", ticketService.findResponses(id));
		model.addAttribute("isClosed", ticket.getStatus() == Status.CLOSED);
		model.addAttribute("response", new TicketResponse());
		model.addAttribute("adminMode", true);
		model.addAttribute("status", Ticket.Status.values());
		return "support/ticket-view";
	}

	@PostMapping("ticket/{id}")
	public String sendResponse(TicketResponse response, Ticket ticket, Model model) {
		ticketService.addTicketResponse(ticket, response);
		return "redirect:/admin/support/ticket/{id}";
	}

	@ResponseBody
	@PostMapping("ticket/{id}/status")
	public String updateStatus(Ticket ticket, @RequestBody String status, Model model) {
		ticketService.updateStatus(Ticket.Status.valueOf(status), ticket);
		return "Status do Ticket atualizado com sucesso.";
	}

}
