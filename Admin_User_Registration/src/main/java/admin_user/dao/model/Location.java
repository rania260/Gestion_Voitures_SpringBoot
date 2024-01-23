package admin_user.dao.model;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location", uniqueConstraints = @UniqueConstraint(columnNames = "id"))

public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id; 
    @Column 
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private String dateDebut;
    @Column 
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private String dateFin;
    @Column 
    private Double fraisLocation;
    @Column 
    private String modePaiement;

    @ManyToOne
    @JoinColumn(name = "voiture_id")
    private Voiture voiture;

}