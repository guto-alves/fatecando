package com.gutotech.fatecando.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Discipline;
import com.gutotech.fatecando.service.DisciplineService;

@Controller
@RequestMapping("admin/disciplines")
public class DisciplineAdminController {

	@Autowired
	private DisciplineService disciplineService;

	@GetMapping
	public String initDisciplinesAdminPage(Model model) {
		model.addAttribute("disciplines", disciplineService.findAll());
		model.addAttribute("discipline", new Discipline());
		return "admin/disciplines";
	}

	@PostMapping
	public String processDisciplineCreationForm(@Valid Discipline discipline, Model model, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(discipline);
			return "admin/disciplines";
		}

		disciplineService.save(discipline);

		redirectAttributes.addFlashAttribute("message", "Disciplina " + discipline.getName() + " criada com sucesso");

		return "redirect:/admin/disciplines";
	}

	@PostMapping("update")
	public String processDisciplineCreationForm(@RequestBody @Valid Discipline discipline,
			BindingResult bindingResult) {
		disciplineService.update(discipline);
		return "redirect:/admin/disciplines";
	}
}
