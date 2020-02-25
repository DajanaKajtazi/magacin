package magacin.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import magacin.model.StavkaPrijema;
import magacin.web.dto.StavkaPrijemaDTO;

@Component
public class StavkaPrijemaToStavkaPrijemaDTO implements Converter<StavkaPrijema, StavkaPrijemaDTO> {

	@Override
	public StavkaPrijemaDTO convert(StavkaPrijema stavkaPrijema) {
		StavkaPrijemaDTO stavkaPrijemaDto = new StavkaPrijemaDTO();
		
		stavkaPrijemaDto.setId(stavkaPrijema.getId());
		stavkaPrijemaDto.setJedinicnaCena(stavkaPrijema.getJedinicnaCena());
		stavkaPrijemaDto.setKolicina(stavkaPrijema.getKolicina());
		
		return stavkaPrijemaDto;
	}

}
