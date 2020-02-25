package magacin.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import magacin.model.StavkaPrijema;

import magacin.service.StavkaPrijemaService;
import magacin.web.dto.StavkaPrijemaDTO;

@Component
public class StavkaPrijemaDTOToStavkaPrijema implements Converter<StavkaPrijemaDTO, StavkaPrijema>{

	@Autowired
	StavkaPrijemaService stavkaPrijemaServic;
	

	
	@Override
	public StavkaPrijema convert(StavkaPrijemaDTO stavkaPrijemaDto) {
		
		
		StavkaPrijema stavkaPrijema = null;
		
		if(stavkaPrijemaDto.getId()!=null) {
			stavkaPrijema=stavkaPrijemaServic.getOne(stavkaPrijemaDto.getId());

		}else {
			stavkaPrijema= new StavkaPrijema();
		}
		
		
		stavkaPrijema.setJedinicnaCena(stavkaPrijemaDto.getJedinicnaCena());
		stavkaPrijema.setKolicina(stavkaPrijemaDto.getKolicina());
		
		return stavkaPrijema;
		
		
	}

	
}
