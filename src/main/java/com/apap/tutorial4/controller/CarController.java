package com.apap.tutorial4.controller;

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
public class CarController {
	@Autowired
	private CarService carService;
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add", method = RequestMethod.GET)
	private String add(@RequestParam (value = "dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		model.addAttribute("car", car);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
	}
	
	@RequestMapping(value = "/car/delete/{dealerId}/{carId}", method = RequestMethod.GET)
	private String deleteCar(
			@PathVariable(value = "dealerId") Long dealerId,
			@PathVariable(value = "carId") Long carId
			) {
		carService.deleteCar(carId);
		return "home";
	}
	
	@RequestMapping(value = "/car/update/{dealerId}/{carId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId,@PathVariable(value = "carId") Long carId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		car.setId(carId);
		model.addAttribute("car", car);
		return "updateCar";
	}
	
	
	
	@RequestMapping(value = "/car/update/{carId}", method = RequestMethod.POST)
	private String update(@ModelAttribute CarModel car,@PathVariable(value = "carId") Long carId, Model model) {
		carService.updateCar(carId, car.getAmount(), car.getBrand(), car.getPrice(), car.getType());
		return "updateCarBerhasil";
	}
	

}
