package magacin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import magacin.model.Magacin;
import magacin.repository.MagacinRepository;
import magacin.service.MagacinService;

@Service
public class JpaMagacinService implements MagacinService {

	@Autowired
	MagacinRepository magacinRepository;
	
	@Override
	public List<Magacin> getAll() {
		
		return magacinRepository.findAll();
	}

	@Override
	public Magacin getOne(Long id) {
		
		return magacinRepository.findOne(id);
	}

	@Override
	public Magacin save(Magacin magacin) {
		
		return magacinRepository.save(magacin);
	}

	@Override
	public void delete(Long id) {
		magacinRepository.delete(id);
		
	}

}
