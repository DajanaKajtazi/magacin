package magacin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import magacin.model.Artikal;
import magacin.model.StavkaPrijema;
import magacin.repository.ArtikalRepository;
import magacin.repository.StavkaPrijemaRepository;
import magacin.service.StavkaPrijemaService;

@Service
public class JpaStavkaPrijemaService implements StavkaPrijemaService{

	@Autowired
	StavkaPrijemaRepository stavkaPrijemaRepository;
	
	@Autowired
	ArtikalRepository artikalRepository;
	
	@Override
	public StavkaPrijema getOne(Long id) {
		
		return stavkaPrijemaRepository.findOne(id);
	}

	@Override
	public StavkaPrijema add(Long artikalId, StavkaPrijema stavka) {
		
		if(artikalId==null) {
			throw new IllegalArgumentException("Id ne moze da bude null!");
		}
		
		Artikal artikal=artikalRepository.findOne(artikalId);
		
		if(artikal==null) {
			throw new IllegalArgumentException("Ne postoji artikl sa trazenim id!");
		}
		stavka.setArtikal(artikal);
		
		Integer kolicina= artikal.getKolicina();
		Double kalkulisanaCena = artikal.getKalkulisanaCena();
		
		kolicina = artikal.getKolicina() + stavka.getKolicina();
		kalkulisanaCena = (artikal.getKolicina()*artikal.getKalkulisanaCena() + stavka.getKolicina()*stavka.getJedinicnaCena())/(artikal.getKolicina()+stavka.getKolicina());
		
		artikal.setKolicina(kolicina);
		artikal.setKalkulisanaCena(kalkulisanaCena);
		
		artikalRepository.save(artikal);
		stavkaPrijemaRepository.save(stavka);
		
		return stavka;
	}

}
