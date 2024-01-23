package admin_user.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import admin_user.business.service.LocationService;
import admin_user.business.service.VoitureService;
import admin_user.dao.model.Location;
import admin_user.dao.model.Voiture;
import admin_user.web.dto.LocationForm;

@Controller
@RequestMapping("/locations")
public class LocationController {
    
    @Autowired
    LocationService locationService;

    @Autowired
    VoitureService voitureService;
    // Read
    @GetMapping
    public String showLocationList(Model model) {
        model.addAttribute("locations", this.locationService.getAllOperation());
        return "locations";
    }

    // Create
  @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("locationForm", new LocationForm());
        model.addAttribute("voitures", voitureService.getAllVoitures());
        return "createLocation";
    }
    @PostMapping("/create")
    public String createLocation(@ModelAttribute("locationForm") @Validated LocationForm locationForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("voitures", voitureService.getAllVoitures());
            return "createLocation";

        }
        if (locationService.getOperation(locationForm.getId()).isPresent()) {

            bindingResult.rejectValue("id", "duplicate", "The code must be unique");
            model.addAttribute("voitures", voitureService.getAllVoitures());
            return "createLocation";
        }
        // Create a new product object from the request body
        Location location = new Location();
        location.setDateDebut(locationForm.getDateDebut());
        location.setDateFin(locationForm.getDateFin());
        location.setFraisLocation(locationForm.getFraisLocation());
        location.setModePaiement(locationForm.getModePaiement());
        Optional<Voiture> voiture = voitureService.getVoiture(locationForm.getVoiture().getId());
        location.setVoiture(voiture.get());
        locationService.addOperation(location);

        return "redirect:/locations";
    }

     // Update
     @GetMapping("/{id}/edit")
     public String showEditForm(@PathVariable("id") Long id, Model model) {
         Optional<Location> location = locationService.getOperation(id);
         if (location == null) {
             // Handle product not found
         }
 
         LocationForm locationForm = new LocationForm(location.get().getId(),
         location.get().getDateDebut(),
         location.get().getDateFin(),
         location.get().getFraisLocation(),
         location.get().getModePaiement(),
         location.get().getVoiture());
         model.addAttribute("locationForm", locationForm);
         model.addAttribute("voitures", voitureService.getAllVoitures());
         return "modifierLocation";
     }

     @PostMapping("/{id}/edit")
    public String updateLocation(@PathVariable("id") Long id,
            @ModelAttribute("locationForm") @Validated LocationForm locationForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("voitures", voitureService.getAllVoitures());
            return "modifierLocation";
        }
        Optional<Location> location = locationService.getOperation(id);
        if (locationService.getOperation(locationForm.getId()).isPresent()
                && locationService.getOperation(locationForm.getId()).get().getId() != id) {

            bindingResult.rejectValue("id", "duplicate", "The code must be unique");
            model.addAttribute("voitures", voitureService.getAllVoitures());
            return "modifierLocation";
        }

        if (location.isPresent()) {
            location.get().setDateDebut(locationForm.getDateDebut());
            location.get().setDateFin(locationForm.getDateFin());
            location.get().setFraisLocation(locationForm.getFraisLocation());
            location.get().setModePaiement(locationForm.getModePaiement());
            locationService.updateOperation(location.get());
        } else {
            // Handle product not found
        }

        return "redirect:/locations";
    }
 

    //delete
    @PostMapping("/{id}/delete")
    public String deleteOperation(@PathVariable("id") Long id) {
        Optional<Location> location = locationService.getOperation(id);
        if (!location.isPresent()) {
            // Handle product not found
        }
        this.locationService.deleteOperation(id);
        return "redirect:/locations";
    }
}