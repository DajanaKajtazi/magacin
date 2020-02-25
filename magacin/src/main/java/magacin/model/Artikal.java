package magacin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Artikal {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false)
	private String naziv;
	@Column(nullable = false)
	private Double pakovanje;
	@Column
	private String jedinicaMere;
	@Column
	private Integer kolicina;
	@Column
	private Double kalkulisanaCena;
	@ManyToOne(fetch = FetchType.EAGER)
	private Magacin magacin;
	@OneToMany(mappedBy = "artikal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StavkaPrijema> stavke = new ArrayList<>();

	public Artikal() {
		super();
		
	}

	public Artikal(Long id, String naziv, Double pakovanje, String jedinicaMere, Integer kolicina,
			Double kalkulisanaCena, Magacin magacin) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.pakovanje = pakovanje;
		this.jedinicaMere = jedinicaMere;
		this.kolicina = kolicina;
		this.kalkulisanaCena = kalkulisanaCena;
		this.magacin = magacin;
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

	public Double getPakovanje() {
		return pakovanje;
	}

	public void setPakovanje(Double pakovanje) {
		this.pakovanje = pakovanje;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Double getKalkulisanaCena() {
		return kalkulisanaCena;
	}

	public void setKalkulisanaCena(Double kalkulisanaCena) {
		this.kalkulisanaCena = kalkulisanaCena;
	}

	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
		if(!magacin.getArtikli().contains(this)) {
			magacin.getArtikli().add(this);
		}
	}

	public List<StavkaPrijema> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaPrijema> stavke) {
		this.stavke = stavke;
	}
	
	public void addStavka(StavkaPrijema stavkaPrijema) {
		
		if(stavkaPrijema.getArtikal()!=this) {
			stavkaPrijema.setArtikal(this);
		}
		
		stavke.add(stavkaPrijema);
	}
	
}
