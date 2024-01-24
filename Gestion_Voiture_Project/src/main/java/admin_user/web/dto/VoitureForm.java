package admin_user.web.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoitureForm {

    
    private Long id;
    private String immatriculation; 
    private String marque;
    private String dateCirculation;
    private Double price;
    private String dispo;
    private String image;

    
}