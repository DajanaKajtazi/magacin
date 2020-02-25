package magacin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import magacin.model.Magacin;
import magacin.web.dto.MagacinDTO;

@Component
public class MagacinToMagacinDTO implements Converter<Magacin, MagacinDTO> {

	@Override
	public MagacinDTO convert(Magacin magacin) {
		MagacinDTO magacinDto= new MagacinDTO();
		
		magacinDto.setId(magacin.getId());
		magacinDto.setNaziv(magacin.getNaziv());
		magacinDto.setAdresa(magacin.getAdresa());
		
		return magacinDto;
	}

	public List<MagacinDTO> convert(List<Magacin> magacini){
		
		List<MagacinDTO> magaciniDto = new ArrayList<>();
		
		for(Magacin magacin:magacini) {
			magaciniDto.add(convert(magacin));
		}
		
		return magaciniDto;
	}
}
