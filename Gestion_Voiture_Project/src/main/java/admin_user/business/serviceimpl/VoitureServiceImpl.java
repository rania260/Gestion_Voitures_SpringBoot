package admin_user.business.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin_user.business.service.VoitureService;
import admin_user.dao.model.Voiture;
import admin_user.dao.repositories.VoitureRepository;


@Service
public class VoitureServiceImpl implements VoitureService{

    @Autowired
    VoitureRepository voitureRepository;

    @Override
    public List<Voiture> getAllVoitures() {
        return this.voitureRepository.findAll();
    }

    @Override
    public Optional<Voiture> getVoiture(Long id) {
        
        return this.voitureRepository.findById(id);
    }

    @Override
    public Optional<Voiture> getVoitureByImmatriculation(String immatriculation) {
       
        return this.voitureRepository.findByImmatriculation(immatriculation);
    }

    @Override
    public Voiture addVoiture(Voiture v) {
       
        return this.voitureRepository.save(v);
    }

    @Override
    public Voiture updateVoiture(Voiture v) {
       
        return this.voitureRepository.save(v);
    }

    @Override
    public void deleteVoiture(Long carId) {
        
        this.voitureRepository.deleteById(carId);
    }
   

    
    
}