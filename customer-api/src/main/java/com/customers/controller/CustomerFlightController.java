package com.customers.controller;

import com.customers.model.CustomerFlight;
import com.customers.repository.CustomerFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customerflight")
public class CustomerFlightController {
    @Autowired
    private CustomerFlightRepository customerFlightRepository;

    @PostMapping("add")
    public @ResponseBody String addNewCustomerFlight (@RequestParam Integer flightid,
                                 @RequestParam Integer customerid) {
        CustomerFlight customerFlight = new CustomerFlight();
        customerFlight.setFlightId(flightid);
        customerFlight.setCustomerId(customerid);
        customerFlightRepository.save(customerFlight);
        return "Saved to customerFlight";
    }

    @PostMapping("addjson")
    public ResponseEntity addNewCustomerFlightJson (@RequestBody CustomerFlight customerFlightJson) {
        customerFlightRepository.save(customerFlightJson);
        return new ResponseEntity(customerFlightJson, HttpStatus.OK);
    }

    @GetMapping("getall")
    public @ResponseBody Iterable<CustomerFlight> getAllCustomerFlight() {
        return customerFlightRepository.findAll();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCustomerFlight(@PathVariable Integer id) {
        CustomerFlight customerFlightToBeDeleted = customerFlightRepository.findById(id).orElse(null);
        customerFlightRepository.deleteById(id);
        return new ResponseEntity(customerFlightToBeDeleted, HttpStatus.OK);
    }

}