package weatherservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface LocationRepository extends JpaRepository<Location, Long> {
    
}
