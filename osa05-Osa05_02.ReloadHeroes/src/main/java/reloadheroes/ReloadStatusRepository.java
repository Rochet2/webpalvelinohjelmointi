package reloadheroes;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReloadStatusRepository extends JpaRepository<ReloadStatus, Long> {

    ReloadStatus findByName(String name);
    List<ReloadStatus> findAllByOrderByReloadsDesc(Pageable pageable);

}
