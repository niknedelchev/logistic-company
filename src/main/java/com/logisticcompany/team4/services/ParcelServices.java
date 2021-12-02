package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.Parcel;
import com.logisticcompany.team4.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ParcelServices {
    
	@Autowired
    private ParcelRepository parcelRepository;

    public List<Parcel> findAll() {
        List<Parcel> parcels = parcelRepository.findAll();
        return parcels;
    }

    public void addParcel(@ModelAttribute Parcel parcel) {
        parcelRepository.save(parcel);
    }

    public Parcel findParcelById(@PathVariable("id") int id) {
        Parcel parcel = parcelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid parcel Id:" + id));
        return parcel;
    }

    public void updateParcel(@ModelAttribute Parcel parcel) throws Exception {
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
    }

    public void deleteParcel(@PathVariable("id") int id) {
        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid parcel Id:" + id));
        parcelRepository.delete(parcel);
    }
}
