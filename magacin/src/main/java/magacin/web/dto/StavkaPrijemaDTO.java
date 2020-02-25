package magacin.web.dto;

public class StavkaPrijemaDTO {

	private Long id;
	
	private Integer kolicina;
	
	private Double jedinicnaCena;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Double getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(Double jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}


	
	
}
