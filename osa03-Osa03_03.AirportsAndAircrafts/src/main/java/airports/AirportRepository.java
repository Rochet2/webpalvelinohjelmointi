package airports;

import org.springframework.data.jpa.repository.JpaRepository;
import airports.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
