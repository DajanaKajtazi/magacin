package magacin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import magacin.model.StavkaPrijema;

@Repository
public interface StavkaPrijemaRepository extends JpaRepository<StavkaPrijema, Long> {

}
