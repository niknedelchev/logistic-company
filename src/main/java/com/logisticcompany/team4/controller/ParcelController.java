package com.logisticcompany.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logisticcompany.team4.model.Parcel;
import com.logisticcompany.team4.repository.ParcelRepository;


@Controller
public class ParcelController {

	@Autowired
	private ParcelRepository parcelRepository;
	
	@GetMapping(path = "/parcels")
	public String showParcelsPage(Model model) {
		
		List<Parcel> parcels = parcelRepository.findAll();
		model.addAttribute("parcels", parcels);
		
		return "parcels";
	}
	
	@GetMapping(path = "/parcels/add")
	public String showAddParcelPage(Model model) {
		model.addAttribute("parcel", new Parcel());
		
		return "parcels-add";
	}
	
	@PostMapping(path = "/parcels/add")
	public String addParcel(@ModelAttribute Parcel parcel) {
		parcelRepository.save(parcel);
		
		return "redirect:/parcels";
	}
	
	@GetMapping("/parcels/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Parcel parcel = parcelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid parcel Id:" + id));
	    
	    model.addAttribute("parcel", parcel);
	    return "parcels-edit";
	}
	
	@PostMapping("/parcels/edit/{id}")
	public String updateParcel(@ModelAttribute Parcel parcel) throws Exception {

		Parcel parcelInDB = parcelRepository.findById(parcel.getId()).orElse(null);
		if (parcelInDB != null) {
			parcelInDB.setWeight(parcel.getWeight());
			parcelInDB.setPrice(parcel.getPrice());
			parcelInDB.setDeliveryAddress(parcel.getDeliveryAddress());
			parcelInDB.setParcelStatus(parcel.getParcelStatus());
			parcelInDB.setReceiver(parcel.getReceiver());
			parcelInDB.setSender(parcel.getSender());
			parcelRepository.save(parcelInDB);
		} else {
			throw new Exception("Parcel not found");
		}
		
	    return "redirect:/parcels";
	}
	    
	@GetMapping("/parcels/delete/{id}")
	public String deleteParcel(@PathVariable("id") int id, Model model) {
	    Parcel parcel = parcelRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid parcel Id:" + id));
	    parcelRepository.delete(parcel);
	    return "redirect:/parcels";
	}
	
}
