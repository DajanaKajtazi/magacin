package magacin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import magacin.model.Artikal;
import magacin.model.Magacin;
import magacin.service.ArtikalService;
import magacin.service.MagacinService;

@Component
public class TestData {

	@Autowired
	MagacinService magacinServic;
	
	@Autowired
	ArtikalService artikalServic;
	
	@PostConstruct
	public void init() {
		
		Magacin m1= new Magacin();
		m1.setNaziv("Magacin1");
		m1.setAdresa("Adresa1");
		magacinServic.save(m1);
		
		Magacin m2= new Magacin();
		m2.setNaziv("Magacin2");
		m2.setAdresa("Adresa2");
		magacinServic.save(m2);
		
		Artikal a1= new Artikal();
		a1.setJedinicaMere("l");
		a1.setKalkulisanaCena(230.00);
		a1.setKolicina(2);
		a1.setNaziv("Mleko");
		a1.setPakovanje(1.5);
		a1.setMagacin(m1);
		artikalServic.save(a1);
		
		Artikal a2= new Artikal();
		a2.setJedinicaMere("kg");
		a2.setKalkulisanaCena(1300.00);
		a2.setKolicina(1);
		a2.setNaziv("Lesnik");
		a2.setPakovanje(1.00);
		a2.setMagacin(m1);
		artikalServic.save(a2);
		
		Artikal a3= new Artikal();
		a3.setJedinicaMere("g");
		a3.setKalkulisanaCena(270.00);
		a3.setKolicina(3);
		a3.setNaziv("Cokolada");
		a3.setPakovanje(100.00);
		a3.setMagacin(m2);
		artikalServic.save(a3);
	}
}
