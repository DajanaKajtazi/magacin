package magacin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Magacin {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;
	
	@Column(nullable = false)
	private String adresa;
	
	@OneToMany(mappedBy = "magacin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Artikal> artikli = new ArrayList<>();
	
	public Magacin() {
		super();
		
	}
	
	
	public Magacin(Long id, String naziv, String adresa) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	
	public List<Artikal> getArtikli() {
		return artikli;
	}


	public void setArtikli(List<Artikal> artikli) {
		this.artikli = artikli;
	}


	public void addArtikal(Artikal artikal) {
		
		if(artikal.getMagacin()!=this) {
			artikal.setMagacin(this);
		}
		artikli.add(artikal);
	}
	
}
