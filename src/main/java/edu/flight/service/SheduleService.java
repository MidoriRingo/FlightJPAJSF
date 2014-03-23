package edu.flight.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.flight.dao.FlightDAO;
import edu.flight.entity.Flight;

@Controller
public class SheduleService {
 
        @Inject
        FlightDAO flightDAO;
 
         @RequestMapping("/shedule")
           public @ResponseBody String showFlights() {
 
                List<Flight> list = flightDAO.shedule();
                
                String result = "<table style=\"border: 1px #0000CD; background: #ADD8E6; font-color:#0000CD; text-align:center; width:600px; border-radius: 10px\">"
                        + "<tr>"
                        + "<th>Flight No</th>"
                        + "<th>Departure Date&Time</th>"
                        + "<th>Arrival Date&Time</th>"
                        + "<th>Arrival City</th>"
                        + "<th>Number of Tickets</th>"
                        + "<th>Ticket price</th>"
                        + "</tr>";
         StringBuilder data = new StringBuilder(result);
         SimpleDateFormat dtFrm = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
         
         for(Flight f: list) {
                 data.append(
                                "<tr>"
                                + "<td>" + f.getFlightNo() + "</td>"
                                + "<td>" + dtFrm.format(f.getDepartureDateTime()) + "</td>"
                                + "<td>" + dtFrm.format(f.getArrivalDateTime()) + "</td>"
                                + "<td>" + f.getArrivalCity()+ "</td>"
                                + "<td>" + f.getAmountOfTickets() + "</td>"
                                + "<td>" + f.getTicketPrice() + "</td>"
                                + "</tr>");
         }
         result.concat(data.toString());
                
                return data.toString();
         
             
           }
 
}
