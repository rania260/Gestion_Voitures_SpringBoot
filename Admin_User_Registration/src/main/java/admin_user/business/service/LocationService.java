package admin_user.business.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import admin_user.dao.model.Location;

public interface LocationService {
    
    public List<Location> getAllOperation();

     // Retrieves a operation de location by ID.
     public Optional<Location> getOperation(Long id);

     // Retrieves a operation de location by Code.
     //public Optional<Location> getOperationByDataFin(Date dateFin);
 
     // Adds a new operation de location.
     public Location addOperation(Location operation);
 
     // Updates an existing operation de location.
     public Location updateOperation(Location operation);
 
     // Deletes a operation de location by their ID.
     public void deleteOperation(Long id);
}