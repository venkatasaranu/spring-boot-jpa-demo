package com.sageit.spring.boot.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sageit.spring.boot.jpa.entity.Reservation;
@Transactional
public interface ReservationRepository extends CrudRepository<Reservation, String>{
	public List<Reservation> findByDepartureDate(Date departDate);
}
