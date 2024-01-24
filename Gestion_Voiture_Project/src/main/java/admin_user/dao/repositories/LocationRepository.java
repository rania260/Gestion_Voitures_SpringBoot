package admin_user.dao.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import admin_user.dao.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{
    
}