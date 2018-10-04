package com.apap.tutorial4.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.DealerDb;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	@Autowired
	private DealerDb dealerDb;
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id){
		return dealerDb.findById(id);
	}
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}
	public void deleteDealer(long dealerId) {
		// TODO Auto-generated method stub
		dealerDb.deleteById(dealerId);
	
	}
	public List<DealerModel> getAll() {
		return dealerDb.findAll();
	}
	public void updateDealer(Long id,String alamat, String noTelp) {
		dealerDb.findById(id).get().setAlamat(alamat);
		dealerDb.findById(id).get().setNoTelp(noTelp);
	}	
	
	
	
}
