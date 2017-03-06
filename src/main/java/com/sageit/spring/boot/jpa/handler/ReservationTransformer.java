package com.sageit.spring.boot.jpa.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.sageit.spring.boot.jpa.domain.ReservationDetail;
import com.sageit.spring.boot.jpa.entity.Reservation;

@Component
public class ReservationTransformer {
	private static final String DATE_FORMAT="yyyy-MM-dd hh:mm";  
	public Reservation transformReservation(ReservationDetail reservationDetail){
		Reservation reservation=new Reservation();
		if(reservationDetail!=null){
		reservation.setPnr(reservationDetail.getPnr());
		reservation.setOrigin(reservationDetail.getOrigin());
		reservation.setDestination(reservationDetail.getDestination());
		reservation.setTripType(reservationDetail.getTripType());
		reservation.setPassengers(reservationDetail.getPassengers());
		reservation.setDepartureDate(convertDate(reservationDetail.getDepartureDate()));
		reservation.setReturnDate(convertDate(reservationDetail.getReturnDate()));
		}
		return reservation;
	}

	private Date convertDate(String dateString){
		
		if(dateString != null){
			SimpleDateFormat dateFormat=new SimpleDateFormat(DATE_FORMAT);
			try {
				return dateFormat.parse(dateString);
			} catch (ParseException e) {
				System.out.println("Date Parse Error"+e);
			}
		}
		return null;
	}
	public ReservationDetail transformReservationDetail(Reservation reservation){
		ReservationDetail reservationDetail=new ReservationDetail();
		if(reservation != null){
			reservationDetail.setPnr(reservation.getPnr());
			reservationDetail.setOrigin(reservation.getOrigin());
			reservationDetail.setDestination(reservation.getDestination());
			reservationDetail.setTripType(reservation.getTripType());
			reservationDetail.setPassengers(reservation.getPassengers());
			reservationDetail.setDepartureDate(convertDateString(reservation.getDepartureDate()));
			reservationDetail.setReturnDate(convertDateString(reservation.getReturnDate()));
		}
		return reservationDetail;
	}

	private String convertDateString(Date date) {
		if(date != null){
			SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
			return format.format(date);
		}
		return null;
	}
}
