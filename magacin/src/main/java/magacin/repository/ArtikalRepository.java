package magacin.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import magacin.model.Artikal;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal, Long> {

	
	List<Artikal> findByMagacinId(Long magacinId);
	
	@Query("SELECT a FROM Artikal a WHERE "
			+ "(:naziv IS NULL or a.naziv like :naziv ) AND "
			+ "(:magacinId IS NULL OR a.magacin.id = :magacinId)"
			)
	Page<Artikal> search(@Param("naziv") String naziv, @Param("magacinId") Long magacinId, Pageable pageRequest);
}
