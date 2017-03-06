package com.sageit.spring.boot.jpa.service;

import java.util.Date;
import java.util.List;

import com.sageit.spring.boot.jpa.domain.ReservationDetail;

public interface ReservationService {
	public ReservationDetail createReservation(ReservationDetail reservation);
	public ReservationDetail updateReservation(ReservationDetail reservation);
	public ReservationDetail getReservation(String pnr);
	public ReservationDetail deleteReservation(String pnr);
	public List<ReservationDetail> getReservationByDepartDate(Date departDate);
	public List<ReservationDetail> getAllReservations();
}
