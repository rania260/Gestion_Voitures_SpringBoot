package admin_user.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import admin_user.dao.model.Voiture;



public interface VoitureRepository extends JpaRepository<Voiture, Long>{
    Optional<Voiture> findByImmatriculation(String immatriculation);
    
}