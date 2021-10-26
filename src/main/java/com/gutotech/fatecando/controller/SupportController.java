package com.gutotech.fatecando.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Ticket;
import com.gutotech.fatecando.model.Ticket.Category;
import com.gutotech.fatecando.model.Ticket.Status;
import com.gutotech.fatecando.model.TicketResponse;
import com.gutotech.fatecando.service.TicketService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("support")
public class SupportController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;

	@ModelAttribute("categories")
	public Category[] getCategories() {
		return Category.values();
	}

	@GetMapping
	public String showSupportPage(Model model) {
		model.addAttribute("tickets", userService.findMyTickets());
		return "support/support";
	}

	@GetMapping("ticket")
	public String showTicketFormPage(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "support/ticket-new";
	}

	@PostMapping("ticket")
	public String processTicketCreationForm(@Valid Ticket ticket, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(ticket);
			return "support/ticket-new";
		}

		ticketService.save(ticket);

		redirectAttributes.addFlashAttribute("successMessage", "Ticket criado com sucesso!");

		return "redirect:/support";
	}

	@GetMapping("ticket/{id}")
	public String showTicketPage(@PathVariable Long id, Model model) {
		Ticket ticket = ticketService.findById(id);
		model.addAttribute("ticket", ticket);
		model.addAttribute("responses", ticketService.findResponses(id));
		model.addAttribute("isClosed", ticket.getStatus() == Status.CLOSED);
		model.addAttribute("response", new TicketResponse());
		model.addAttribute("status", Ticket.Status.values());
		return "support/ticket-view";
	}

	@PostMapping("ticket/{id}")
	public String sendResponse(TicketResponse response, Ticket ticket, Model model) {
		ticketService.addTicketResponse(ticket, response);
		return "redirect:/support/ticket/{id}";
	}

}
