package magacin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import magacin.model.Artikal;

import magacin.repository.ArtikalRepository;

import magacin.service.ArtikalService;

@Service
public class JpaArtikalService implements ArtikalService {

	@Autowired
	ArtikalRepository artikalRepository;
	
	
	
	@Override
	public Page<Artikal> getAll(int pageNum) {
		
		return artikalRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Artikal getOne(Long id) {
		
		return artikalRepository.findOne(id);
	}

	@Override
	public Artikal save(Artikal artikal) {
		
		return artikalRepository.save(artikal);
	}

	@Override
	public Artikal delete(Long id) {
		Artikal artikal=artikalRepository.findOne(id);
		if(artikal != null) {
			if(artikal.getKolicina()>0 && artikal.getKalkulisanaCena()>0) {
				artikalRepository.delete(artikal);
			}
		}
		return artikal;
	}

	@Override
	public List<Artikal> findByMagacinId(Long magacinId) {
		
		return artikalRepository.findByMagacinId(magacinId);
	}

	@Override
	public Page<Artikal> search(String naziv, Long magacinId, int pageNum) {
		
		if (naziv != null) {
			
			naziv= "%"+naziv+"%";
		}
		return artikalRepository.search(naziv, magacinId, new PageRequest(pageNum, 5));
	}

	
	

}
