package magacin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import magacin.model.Artikal;



public interface ArtikalService {

	Page<Artikal> getAll(int pageNum);
	Artikal getOne(Long id);
	Artikal save(Artikal artikal);
	Artikal delete(Long id);
	List<Artikal> findByMagacinId(Long magacinId);
	Page<Artikal> search(String naziv, Long magacinId, int pageNum);
	
	
}
