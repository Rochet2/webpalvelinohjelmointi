package weatherservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private LocationRepository locationRepository;

    @Cacheable("locations")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Cacheable("locations-id")
    public Location getLocation(Long id) {
        return locationRepository.getOne(id);
    }

    @Caching(evict = {
        @CacheEvict(cacheNames = "locations", allEntries = true)
        , @CacheEvict(cacheNames = "locations-id", allEntries = true)})
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Caching(evict = {
        @CacheEvict(cacheNames = "locations", allEntries = true)
        , @CacheEvict(cacheNames = "locations-id", allEntries = true)})
    public void flushcaches() {
    }
}
