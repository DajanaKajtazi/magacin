package magacin.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import magacin.model.Artikal;
import magacin.model.Magacin;
import magacin.service.ArtikalService;
import magacin.service.MagacinService;
import magacin.web.dto.ArtikalDTO;
@Component
public class ArtikalDTOToArtikal implements Converter<ArtikalDTO, Artikal>{

	@Autowired
	ArtikalService artikalService;
	@Autowired
	MagacinService magacinService;
	
	@Override
	public Artikal convert(ArtikalDTO artikalDto) {
		
		Magacin magacin = magacinService.getOne(artikalDto.getMagacinId());
		
		if(magacin!=null) {
			Artikal artikal = null;
			if(artikalDto.getId()!=null) {
				artikal= artikalService.getOne(artikalDto.getId());
			}else {
				artikal= new Artikal();
			}
			
			artikal.setJedinicaMere(artikalDto.getJedinicaMere());
			artikal.setKalkulisanaCena(artikalDto.getKalkulisanaCena());
			artikal.setKolicina(artikalDto.getKolicina());
			artikal.setNaziv(artikalDto.getNaziv());
			artikal.setPakovanje(artikalDto.getPakovanje());
			
			artikal.setMagacin(magacin);
			
			return artikal;
		}else {
			throw new IllegalStateException("Magacin ne postoji!");
		}
	
	}
	

}
