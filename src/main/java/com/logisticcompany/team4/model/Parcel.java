package com.logisticcompany.team4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import constant.ParcelStatus;

@Entity
@Table(name = "parcel")
public class Parcel {
	
	@Id
	@Column(name = "parcel_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = true)
	Customer sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id", nullable = true)
	Customer receiver;
	
	double weight;
	String deliveryAddress;
	double price;
	ParcelStatus parcelStatus;
	
	public Parcel() {
	}

	public Parcel(int id, Customer sender, Customer receiver, double weight, String deliveryAddress, double price,
			ParcelStatus parcelStatus) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.weight = weight;
		this.deliveryAddress = deliveryAddress;
		this.price = price;
		this.parcelStatus = parcelStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getSender() {
		return sender;
	}

	public void setSender(Customer sender) {
		this.sender = sender;
	}

	public Customer getReceiver() {
		return receiver;
	}

	public void setReceiver(Customer receiver) {
		this.receiver = receiver;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ParcelStatus getParcelStatus() {
		return parcelStatus;
	}

	public void setParcelStatus(ParcelStatus parcelStatus) {
		this.parcelStatus = parcelStatus;
	}

}