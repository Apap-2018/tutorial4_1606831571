package com.apap.tutorial4.service;

import com.apap.tutorial4.model.CarModel;

public interface CarService {
	void addCar(CarModel car);
	void deleteCar(long carId);
	void updateCar(Long id, String amount, String brand, String price, String type);

}
