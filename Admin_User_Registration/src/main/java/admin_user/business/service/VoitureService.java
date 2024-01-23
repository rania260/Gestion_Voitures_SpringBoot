package admin_user.business.service;

import java.util.List;
import java.util.Optional;

import admin_user.dao.model.Voiture;



public interface VoitureService {
     // Retrieves all cars.
     public List<Voiture> getAllVoitures();

    
     
     // Retrieves a car by ID.
     public Optional<Voiture> getVoiture(Long id);

     // Retrieves a car by Code.
     public Optional<Voiture> getVoitureByImmatriculation(String immatriculation);
 
     // Adds a new car.
     public Voiture addVoiture(Voiture P);
 
     // Updates an existing car.
     public Voiture updateVoiture(Voiture P);
 
     // Deletes a car by their ID.
     public void deleteVoiture(Long carId);
 
    
    
}