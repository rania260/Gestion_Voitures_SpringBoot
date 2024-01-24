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

import admin_user.business.service.VoitureService;
import admin_user.dao.model.Voiture;
import admin_user.web.dto.VoitureForm;



@Controller
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    VoitureService voitureService;
  //Read
    @GetMapping()
    public String getAllVoiture(Model model) {
        model.addAttribute("voitures",this.voitureService.getAllVoitures());
        return "voitures"; 
    }

    
    //create
    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("voitureForm", new VoitureForm());
        return "createVoiture";
    }

    @PostMapping("/create")
    public String createVoiture(@ModelAttribute("voitureForm") @Validated VoitureForm voitureForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "createVoiture";
        }

        //create a new voiture
        Voiture voiture = new Voiture();
        voiture.setImmatriculation(voitureForm.getImmatriculation());
        voiture.setMarque(voitureForm.getMarque());
        voiture.setDateCirculation(voitureForm.getDateCirculation());
        voiture.setDispo(voitureForm.getDispo());
        voiture.setPrice(voitureForm.getPrice());
        voiture.setImage(voitureForm.getImage());
        voitureService.addVoiture(voiture);
        return "redirect:/voitures";
    }

    //delete
    @PostMapping("/{id}/delete")
    public String deleteVoiture(@PathVariable("id")Long id){
        Optional <Voiture> voiture = voitureService.getVoiture(id);
         if(!voiture.isPresent()){
            
         }
         this.voitureService.deleteVoiture(id);
        return "redirect:/voitures";
    }


    //update
    @GetMapping("/{id}/edit")
     public String showEditVoiture(@PathVariable("id")Long id, Model model){
         Optional<Voiture> voiture = voitureService.getVoiture(id);
         if(voiture == null){
    
        }
        VoitureForm voitureForm = new VoitureForm( voiture.get().getId(), 
        voiture.get().getImmatriculation(),
        voiture.get().getMarque(),
        voiture.get().getDateCirculation(),
        voiture.get().getPrice(),
        voiture.get().getDispo(),
        voiture.get().getImage());
        model.addAttribute("voitureForm", voitureForm);
        return "modifierVoiture";
    }
    @PostMapping("/{id}/edit")
    public String editVoiture(@PathVariable("id")Long id,@ModelAttribute("voitureForm") @Validated VoitureForm voitureForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "modifierVoiture";
        }
        Optional<Voiture> voiture = voitureService.getVoiture(id);
        if(voiture.isPresent()){
            voiture.get().setImmatriculation(voitureForm.getImmatriculation()); 
            voiture.get().setDateCirculation(voitureForm.getDateCirculation());
            voiture.get().setMarque(voitureForm.getMarque());
            voiture.get().setDispo(voitureForm.getDispo());
            voiture.get().setPrice(voitureForm.getPrice()); 
            voiture.get().setImage(voitureForm.getImage());
            }
        voitureService.updateVoiture(voiture.get());
        return "redirect:/voitures";
    }

    
}