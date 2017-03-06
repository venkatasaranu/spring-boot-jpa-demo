package com.sageit.spring.boot.jpa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sageit.spring.boot.jpa.domain.ReservationDetail;
import com.sageit.spring.boot.jpa.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@RequestMapping("/create")
	public ReservationDetail createReservation(@RequestBody ReservationDetail reservation) {
		return reservationService.createReservation(reservation);
	}

	@RequestMapping(value = "/update/{pnr}", method = RequestMethod.PUT)
	public ReservationDetail updateReservation(@RequestBody ReservationDetail reservationDetail,
			@PathVariable String pnr) {
		reservationDetail.setPnr(pnr);
		return reservationService.updateReservation(reservationDetail);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ReservationDetail deleteReservation(@RequestParam(name = "pnr") String pnr) {
		return reservationService.deleteReservation(pnr);
	}

	@RequestMapping(value = "/retrieve")
	public ReservationDetail retrieveReservation(@RequestParam(name = "pnr") String pnr) {
		return reservationService.getReservation(pnr);
	}

	@RequestMapping("/list")
	public List<ReservationDetail> retrieveAllReservations() {
		return reservationService.getAllReservations();
	}

	@RequestMapping(value = "/retrieve/{departureDate}")
	public List<ReservationDetail> retrieveReservationsByDate(@PathVariable String departDate) throws ParseException {
		if (departDate != null) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date departureDate = dateFormatter.parse(departDate);
			return reservationService.getReservationByDepartDate(departureDate);
		}
		return null;
	}
}
