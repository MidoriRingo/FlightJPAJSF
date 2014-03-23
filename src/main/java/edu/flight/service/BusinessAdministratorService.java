package edu.flight.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.flight.dao.FlightDAO;
import edu.flight.entity.Flight;

@Named
@Transactional
public class BusinessAdministratorService {
	@Inject
	private FlightDAO flightDAO;

	public void createFlight(Flight fl) {
		flightDAO.create(fl);
	}

	public void deleteFlight(Flight fl) {
		flightDAO.delete(fl.getCode());
	}

	public void updateFlight(Flight fl) {
		flightDAO.update(fl);
	}

	public void removeUnpayed() {
		flightDAO.removeUnpayed();
	}

	public List<Flight> showFlights() {
		return flightDAO.showFlights();

	}

}
