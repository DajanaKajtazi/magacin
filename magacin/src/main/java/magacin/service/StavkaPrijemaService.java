package magacin.service;

import magacin.model.StavkaPrijema;

public interface StavkaPrijemaService {

	StavkaPrijema getOne(Long id);
	StavkaPrijema add(Long artikalId, StavkaPrijema stavka);
}
