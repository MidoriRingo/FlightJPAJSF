package edu.flight.web;

import java.util.List;  

import javax.faces.model.ListDataModel;   

import org.primefaces.model.SelectableDataModel;  

import edu.flight.entity.Flight;
  
public class FlightDataModel extends ListDataModel<Flight> implements SelectableDataModel<Flight> {    
  
    public FlightDataModel() {  
    }  
  
    public FlightDataModel(List<Flight> data) {  
        super(data);  
    }  
      
    public Flight getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Flight> flights = (List<Flight>) getWrappedData();  
          
        for(Flight flight : flights) {  
            if(flight.getFlightNo().equals(rowKey))  
                return flight;  
        }  
          
        return null;  
    }  
  
    public Object getRowKey(Flight flight) {  
        return flight.getFlightNo();  
    }  
}  