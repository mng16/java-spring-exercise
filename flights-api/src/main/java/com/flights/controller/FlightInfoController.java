package com.flights.controller;

import com.flights.model.FlightInfo;
import com.flights.repository.FlightInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/flights")
public class FlightInfoController {
    @Autowired
    private FlightInfoRepository flightInfoRepository;

    @GetMapping("/getallflights")
    public @ResponseBody Iterable<FlightInfo> getAllFlights() {
        return flightInfoRepository.findAll();
    }

    @PostMapping("/addflight")
    public ResponseEntity addNewFlight(@RequestBody FlightInfo flightInfoJson) {
        flightInfoRepository.save(flightInfoJson);
        return new ResponseEntity(flightInfoJson, HttpStatus.OK);
    }

    @PutMapping("/updateflight/{id}")
    public ResponseEntity updateFlightInfo(@PathVariable Integer id, @RequestBody FlightInfo flightInfoJson) {

        FlightInfo flightInfo = flightInfoRepository.findById(id).orElse(null);

//        Optional<FlightInfo> flightInfo = flightInfoRepository.findById(id);
        // TO DO - handle case where entity is not found and change to using the non-outdated Option<T> method

        flightInfo.setArrivalTime(flightInfoJson.getArrivalTime());
        flightInfo.setDepartureTime(flightInfoJson.getDepartureTime());
        flightInfo.setDestination(flightInfoJson.getDestination());
        flightInfo.setFlightNo(flightInfoJson.getFlightNo());
        flightInfo.setGate(flightInfoJson.getGate());
        flightInfo.setStatus(flightInfoJson.getStatus());

        flightInfoRepository.save(flightInfo);


        return new ResponseEntity(flightInfo, HttpStatus.OK);

    }
}