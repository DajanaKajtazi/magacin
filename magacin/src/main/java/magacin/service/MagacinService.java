package magacin.service;

import java.util.List;

import magacin.model.Magacin;

public interface MagacinService {

	List<Magacin> getAll();
	Magacin getOne(Long id);
	Magacin save(Magacin magacin);
	void delete(Long id);
}
