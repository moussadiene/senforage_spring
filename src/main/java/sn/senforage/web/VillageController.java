package sn.senforage.web;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sn.senforage.dao.VillageRepository;
import sn.senforage.entities.Client;
import sn.senforage.entities.Village;

@Controller
@RequestMapping(value = "/villages")
public class VillageController {

	@Autowired
	private VillageRepository villageRepository;
	
	@RequestMapping(value = "/indexe", method=RequestMethod.GET)
	public String indexe(Model model) {
		
		List<Village> villages=  villageRepository.findAll();
		
		//Page<Village> villages = villageRepository.findAll(new Page)
		model.addAttribute("listeVillages",villages); 
		model.addAttribute("village", new Village());
		return "liste";
	
	}
	@GetMapping(value = "/ajoutVillage")
	private String ajout(Model model) {
		
		model.addAttribute("village", new Village());

		return "villages/ajoutVillage";
	}
	
	@RequestMapping(value = {"/index",""}, method=RequestMethod.GET)
	public String index(Model model) {
		
		List<Village> villages=  villageRepository.findAll();
		
		//Page<Village> villages = villageRepository.findAll(new Page)
		model.addAttribute("listeVillages",villages); 
		model.addAttribute("village", new Village());
		return "villages/listeVillage";
	
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addVillage(Model model,Village village) {
		villageRepository.save(village);
		model.addAttribute("success", "données enregistrer");

		return "redirect:/villages/index";
	}
	
//	@RequestMapping(value = "/update", method=RequestMethod.POST)
//	public String update(Model model,Village village) {
//		villageRepository.save(village);
//		model.addAttribute("success", "données modifié");
//		return "redirect:/villages/index";
//	}
	
	@RequestMapping(value = "/show", method=RequestMethod.GET)
	public String show(Model model,Long village) {
		model.addAttribute("village", villageRepository.findById(village).get());
		return "villages/show";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(Model model,Long village) {
		Village v = new Village();
		v = villageRepository.findById(village).get();
		villageRepository.delete(v);
		model.addAttribute("success", "donnée supprimée");

		return "redirect:/villages/index";
	}
}
