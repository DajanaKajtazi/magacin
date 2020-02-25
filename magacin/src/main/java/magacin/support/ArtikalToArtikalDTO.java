package magacin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import magacin.model.Artikal;
import magacin.web.dto.ArtikalDTO;
@Component
public class ArtikalToArtikalDTO implements Converter<Artikal, ArtikalDTO> {

	@Override
	public ArtikalDTO convert(Artikal artikal) {
		ArtikalDTO artikalDto = new ArtikalDTO();
		
		artikalDto.setId(artikal.getId());
		artikalDto.setNaziv(artikal.getNaziv());
		artikalDto.setJedinicaMere(artikal.getJedinicaMere());
		artikalDto.setKalkulisanaCena(artikal.getKalkulisanaCena());
		artikalDto.setKolicina(artikal.getKolicina());
		artikalDto.setPakovanje(artikal.getPakovanje());
		
		artikalDto.setMagacinNaziv(artikal.getMagacin().getNaziv());
		artikalDto.setMagacinId(artikal.getMagacin().getId());
		
		return artikalDto;
	}
	
	public List<ArtikalDTO> convert(List<Artikal> artikli){
		
		List<ArtikalDTO> artikliDto = new ArrayList<>();
		
		for(Artikal artikal:artikli) {
			artikliDto.add(convert(artikal));
		}
		return artikliDto;
	}

}
