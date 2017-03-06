package com.sageit.spring.boot.jpa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sageit.spring.boot.jpa.domain.ReservationDetail;
import com.sageit.spring.boot.jpa.entity.Reservation;
import com.sageit.spring.boot.jpa.handler.ReservationTransformer;
import com.sageit.spring.boot.jpa.repository.ReservationRepository;

@Service("reservationservice")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ReservationTransformer reservationTransformer;

	@Override
	public ReservationDetail createReservation(ReservationDetail reservationDetail) {
		
		Reservation reservation = reservationRepository.save(reservationTransformer.transformReservation(reservationDetail));
		return reservationTransformer.transformReservationDetail(reservation);
	}

	@Override
	public ReservationDetail updateReservation(ReservationDetail reservationDetail) {
		Reservation reservation = reservationRepository.findOne(reservationDetail.getPnr());
		if (reservation == null) {
			return null;
		}
		Reservation updatedReservation = reservationRepository.save(reservationTransformer.transformReservation(reservationDetail));
		return reservationTransformer.transformReservationDetail(updatedReservation);
	}

	@Override
	public ReservationDetail getReservation(String pnr) {
		Reservation reservation=reservationRepository.findOne(pnr);
		if(reservation == null){
			return null;
		}
		return reservationTransformer.transformReservationDetail(reservation);
	}

	@Override
	public ReservationDetail deleteReservation(String pnr) {
		Reservation reservation = reservationRepository.findOne(pnr);
		if (reservation != null) {
			reservationRepository.delete(reservation);
			return reservationTransformer.transformReservationDetail(reservation);
		}
		return null;
	}

	@Override
	public List<ReservationDetail> getReservationByDepartDate(Date departDate) {
		List<Reservation> reservations = reservationRepository.findByDepartureDate(departDate);
		if (CollectionUtils.isEmpty(reservations)) {
			return null;
		}
		return getReservationDetails(reservations);
	}
	
	@Override
	public List<ReservationDetail> getAllReservations() {
		Iterable<Reservation> reservations=reservationRepository.findAll();
		if(reservations == null){
			return null;
		}
		return getReservationDetails(reservations);
	}

	private List<ReservationDetail> getReservationDetails(Iterable<Reservation> reservations) {
		List<ReservationDetail> reservationDetails=new ArrayList<>();
		for(Reservation reservation:reservations){
			reservationDetails.add(reservationTransformer.transformReservationDetail(reservation));
		}
		return reservationDetails;
	}

	private List<ReservationDetail> getReservationDetails(List<Reservation> reservations) {
		List<ReservationDetail> reservationDetails=new ArrayList<>();
		for(Reservation reservation:reservations){
			reservationDetails.add(reservationTransformer.transformReservationDetail(reservation));
		}
		return reservationDetails;
	}
	

}
