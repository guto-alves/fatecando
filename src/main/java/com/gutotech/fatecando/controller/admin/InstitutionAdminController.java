package com.gutotech.fatecando.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Institution;
import com.gutotech.fatecando.service.InstitutionService;

@Controller
@RequestMapping("admin/institutions")
public class InstitutionAdminController {

	@Autowired
	private InstitutionService institutionService;

	@ModelAttribute("institutions")
	public List<Institution> findAllInstitutions() {
		return institutionService.findAll();
	}

	@GetMapping
	public String showInstitutionsPage(Model model) {
		model.addAttribute("institution", new Institution());
		return "admin/institutions";
	}

	@PostMapping
	public String processInstitutionCreationForm(@Valid Institution institution, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(institution);
			return "admin/institutions";
		}

		institutionService.save(institution);

		redirectAttributes.addFlashAttribute("message",
				"A instituição " + institution.getName() + " foi cadastrada com sucesso.");

		return "redirect:/admin/institutions";
	}

	@PostMapping("update")
	public String processInstitutionUpdateForm(@Valid @RequestBody Institution institution) {
		institutionService.update(institution);
		return "redirect:/admin/institutions";
	}
}
