package com.apap.tutorial4.controller;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;



@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;

	@RequestMapping("/")
	private String home() {
		return "home";
	}
	@RequestMapping(value ="/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value ="/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	@RequestMapping(value="/dealer/view", method = RequestMethod.GET)
	private String view(@RequestParam("dealerId") Long dealerId, Model model) {
		DealerModel listDealer = dealerService.getDealerDetailById(dealerId).get();
		List<CarModel> listCarGet = listDealer.getListCar();
		Collections.sort(listCarGet, new SortCar());
		long idCar = dealerId;
		model.addAttribute("dealer", listDealer );
		model.addAttribute("idDealer", dealerId);
		model.addAttribute("listCar",listCarGet);
		return "view-dealer";
	
	
	}
	

	
	
	class SortCar implements Comparator<CarModel>{
		public int compare(CarModel a, CarModel b) {
			return (int) (a.getPrice() - b.getPrice());
		}
	}

	@RequestMapping(value = "/dealer/delete/{dealerId}", method = RequestMethod.GET)
	private String deleteDealer(@PathVariable(value = "dealerId") Long dealerId) {
		dealerService.deleteDealer(dealerId);
		return "dealer-delete";
	}
	@RequestMapping("/dealer/viewall")
	public String viewall(Model model) {
		List<DealerModel> list = dealerService.getAll();
		model.addAttribute("listDealer", list);
		return "viewall";
	
		
	}
	
	
	
	
}



