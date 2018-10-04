package com.apap.tutorial4.service;
import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.CarDb;
import com.apap.tutorial4.repository.DealerDb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


@Service
@Transactional
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		carDb.save(car);
	}
	public void deleteCar(long carId) {
		// TODO Auto-generated method stub
		carDb.deleteById(carId);
	}
	public void updateCar(Long id, String amount, String brand, String price, String type) {
		carDb.getOne(id).setAmount(amount);
		carDb.getOne(id).setBrand(brand);
		carDb.getOne(id).setPrice(price);
		carDb.getOne(id).setType(type);
	}
	
	
	
	

}
