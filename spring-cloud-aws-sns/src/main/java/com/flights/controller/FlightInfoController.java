package com.flights.controller;

import com.flights.model.FlightInfo;
import com.flights.repository.FlightInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/flights")
public class FlightInfoController {
    @Autowired
    private FlightInfoRepository flightInfoRepository;

    @GetMapping("/getallflights")
    public @ResponseBody Iterable<FlightInfo> getAllFlights() {
        return flightInfoRepository.findAll();
    }
}
