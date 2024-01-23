package admin_user.business.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin_user.business.service.LocationService;
import admin_user.dao.model.Location;
import admin_user.dao.repositories.LocationRepository;
@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationRepository repo;

    @Override
    public List<Location> getAllOperation() {
        
        return this.repo.findAll();
    }

    @Override
    public Optional<Location> getOperation(Long id) {
    
        return this.repo.findById(id);
    }


    @Override
    public Location addOperation(Location operation) {
      
        return this.repo.save(operation);
    }

    @Override
    public Location updateOperation(Location operation) {
       
        return this.repo.save(operation);
    }

    @Override
    public void deleteOperation(Long id) {
        
        this.repo.deleteById(id);
    }
    
}