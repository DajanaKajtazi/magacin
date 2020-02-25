package magacin.web.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;


public class ArtikalDTO {

	private Long id;
	
	private String naziv;
	
	@Min(value = 0)
	private Double pakovanje;
	@NotBlank
	private String jedinicaMere;
	
	private Integer kolicina;
	
	private Double kalkulisanaCena;
	
	private Long magacinId;
	
	private String magacinNaziv;

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

	public Long getMagacinId() {
		return magacinId;
	}

	public void setMagacinId(Long magacinId) {
		this.magacinId = magacinId;
	}

	public String getMagacinNaziv() {
		return magacinNaziv;
	}

	public void setMagacinNaziv(String magacinNaziv) {
		this.magacinNaziv = magacinNaziv;
	}
	
	
}
