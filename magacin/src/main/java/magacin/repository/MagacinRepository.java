package magacin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import magacin.model.Magacin;

@Repository
public interface MagacinRepository extends JpaRepository<Magacin, Long> {

}
