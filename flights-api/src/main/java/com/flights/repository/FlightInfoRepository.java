package com.flights.repository;

import com.flights.model.FlightInfo;
import org.springframework.data.repository.CrudRepository;

public interface FlightInfoRepository extends CrudRepository<FlightInfo, Integer> {

}