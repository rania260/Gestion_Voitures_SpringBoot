package admin_user.dao.model;


import org.springframework.format.annotation.DateTimeFormat;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "cars", uniqueConstraints = @UniqueConstraint(columnNames = "immatriculation"))
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String immatriculation; 
    @Column 
    private String marque;
    @Column
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    
    //@Temporal(TemporalType.DATE)
    //@DateTimeFormat(iso=ISO.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dateCirculation ;
    
    @Column 
    private Double price;
    @Column
    private String dispo;
    @Column
    private String image;
    
}