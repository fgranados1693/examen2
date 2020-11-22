package com.cenfotec.tecasa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.tecasa.domain.Categoria;
import com.cenfotec.tecasa.domain.Tarea;
import com.cenfotec.tecasa.domain.Workshop;
import com.cenfotec.tecasa.repository.WorkshopRepository;
import com.cenfotec.tecasa.service.CategoriaService;
import com.cenfotec.tecasa.service.TareaService;
import com.cenfotec.tecasa.service.WorkshopService;



@Controller
public class WorkshopController {

	@Autowired
	WorkshopService workshopService;
	@Autowired
	WorkshopRepository workshopRepository;
	@Autowired
	TareaService tareaService;
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Workshop());
		//model.addAttribute("categorias", categoriaService.getAll());
		return "crearWorkshop";
	}	
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.POST)
	public String insertarAction(Workshop workshop, BindingResult result, Model model) {
		workshopService.save(workshop);
		return "index";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("workshops",workshopService.getAll());
		return "listarWorkshops";
	}
	
	@GetMapping("/editar/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<Workshop> workshop = workshopService.get(id);	    
	    
		if(workshop.isPresent()) {
			model.addAttribute("workshop",workshop);
			return "editarWorkshop";
		}
	    return "index";
	}
		
	@PostMapping("/editar/{id}")
	public String updateWorkshop(@PathVariable("id") long id, Workshop workshop, 
	  BindingResult result, Model model) {
		
	    if (result.hasErrors()) {
	    	workshop.setId(id);
	        return "editarWorkshop/{id}";
	    }
	    workshopService.save(workshop);
	    model.addAttribute("workshops", workshopService.getAll());
	    return "listarWorkshops";
	}

	// Funciones para agregar las tareas
	
	@RequestMapping(value="/detalle/{id}")
	public String saveEdition(Model model, @PathVariable long id) {
		Optional<Workshop> possibleData = workshopService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("workshopData",possibleData.get());
			return "detalleWorkshop";	
		}
		return "workshopNoEncontrado";
	}

	@RequestMapping(value="/agregarTarea/{id}")
	public String recoverForAddTarea(Model model, @PathVariable long id) {
		Optional<Workshop> workshop = workshopService.get(id);
		Tarea newTarea = new Tarea();
		if (workshop.isPresent()) {
			newTarea.setWorkshop(workshop.get());
			model.addAttribute("workshop",workshop.get());
			model.addAttribute("tarea",newTarea);
			return "agregarTarea";	
		}
		return "workshopNoEncontrado";
	}
	
	@RequestMapping(value="/agregarTarea/{id}", method = RequestMethod.POST)
	public String saveTarea(Tarea tarea, Model model, @PathVariable long id) {
		Optional<Workshop> workshop = workshopService.get(id);
		if (workshop.isPresent()) {
			tarea.setWorkshop(workshop.get());
			tareaService.save(tarea);
			return "index";
		}
		return "errorTarea";
	}

	// Funciones para agregar las categorias
	
	@RequestMapping(value = "/crudCategorias",  method = RequestMethod.GET)
	public String mostrarMenuCategorias(Model model) {
		model.addAttribute(new Categoria());		
		return "crudCategorias";
	}	

	@RequestMapping(value = "/agregarCategoria",  method = RequestMethod.GET)
	public String insertarCategoria(Model model) {
		model.addAttribute(new Categoria());		
		return "agregarCategoria";
	}	
	
	@RequestMapping(value = "/agregarCategoria",  method = RequestMethod.POST)
	public String insertarActionCategoria(Categoria categoria, BindingResult result, Model model) {
		categoriaService.saveCategoria(categoria);
		return "index";
	}
	
	@RequestMapping("/listarCategorias")
	public String listarCategoria(Model model) {
		model.addAttribute("categorias",categoriaService.getAll());
		return "listarCategorias";
	}
	
	@GetMapping("/editarCategoria/{id}")
	public String showUpdateFormCategoria(@PathVariable("id") long id, Model model) {
		Optional<Categoria> categoria = categoriaService.get(id);	    
	    
		if(categoria.isPresent()) {
			model.addAttribute("categoria",categoria);
			return "editarCategoria";
		}
	    return "index";
	}
		
	@PostMapping("/editarCategoria/{id}")
	public String updateCategoria(@PathVariable("id") long id, Categoria categoria, 
	  BindingResult result, Model model) {
		
	    if (result.hasErrors()) {
	    	categoria.setId(id);
	        return "editarCategoria/{id}";
	    }
	    categoriaService.saveCategoria(categoria);
	    model.addAttribute("categorias", categoriaService.getAll());
	    return "listarCategorias";
	}
	
	@GetMapping("/deleteCategoria/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
	    Categoria categoria = categoriaService.get(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    categoriaService.deleteCategoria(categoria);
	    model.addAttribute("categorias", categoriaService.getAll());
	    return "index";
	}

}
