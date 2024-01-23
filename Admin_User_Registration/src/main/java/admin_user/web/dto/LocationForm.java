package admin_user.web.dto;
import java.util.Date;

import admin_user.dao.model.Voiture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationForm {
  
    
    private Long id; 
    private String dateDebut;
    private String dateFin;
    private Double fraisLocation;
    private String modePaiement;
    private Voiture voiture;
    
}