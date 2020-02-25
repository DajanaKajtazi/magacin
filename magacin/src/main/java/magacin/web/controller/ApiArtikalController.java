package magacin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import magacin.model.Artikal;
import magacin.model.StavkaPrijema;
import magacin.service.ArtikalService;
import magacin.service.StavkaPrijemaService;
import magacin.support.ArtikalDTOToArtikal;
import magacin.support.ArtikalToArtikalDTO;
import magacin.support.StavkaPrijemaDTOToStavkaPrijema;
import magacin.support.StavkaPrijemaToStavkaPrijemaDTO;
import magacin.web.dto.ArtikalDTO;
import magacin.web.dto.StavkaPrijemaDTO;

@RestController
@RequestMapping(value = "/api/artikli")
public class ApiArtikalController {

	@Autowired
	ArtikalService artikalService;
	
	@Autowired
	StavkaPrijemaService stavkaPrijemaService;
	
	@Autowired
	ArtikalToArtikalDTO artikalToDto;
	
	@Autowired
	ArtikalDTOToArtikal dtoToArtikal;
	
	@Autowired
	StavkaPrijemaToStavkaPrijemaDTO stavkaToDto;
	
	@Autowired
	StavkaPrijemaDTOToStavkaPrijema dtoToStavka;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ArtikalDTO>> getAll(@RequestParam(required = false) String naziv,
			@RequestParam(required = false) Long magacinId,
			@RequestParam(defaultValue = "0") int pageNum){
		
		Page<Artikal> artikliPage= null;
		
		if(naziv!=null || magacinId!=null) {
			artikliPage= artikalService.search(naziv, magacinId, pageNum);
		}else {
			artikliPage=artikalService.getAll(pageNum);
		}
		
		HttpHeaders headers= new HttpHeaders();
		headers.add("totalPages", Integer.toString(artikliPage.getTotalPages()));
		
		return new ResponseEntity<>(artikalToDto.convert(artikliPage.getContent()), headers,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<ArtikalDTO> getOne(@PathVariable Long id){
		
		Artikal artikal = artikalService.getOne(id);
		if(artikal == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(artikalToDto.convert(artikal),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<ArtikalDTO> add(@Validated @RequestBody ArtikalDTO artikalDto){
		
		Artikal artikal = artikalService.save(dtoToArtikal.convert(artikalDto));
		
		return new ResponseEntity<>(artikalToDto.convert(artikal), HttpStatus.CREATED);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	ResponseEntity<ArtikalDTO> edit(@PathVariable Long id, @Validated @RequestBody ArtikalDTO artikalDto ){
		
		if(!id.equals(artikalDto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Artikal artikal = artikalService.save(dtoToArtikal.convert(artikalDto));
		
		return new ResponseEntity<>(artikalToDto.convert(artikal), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	ResponseEntity<ArtikalDTO> delete(@PathVariable Long id){
		
		Artikal artikal= artikalService.delete(id);
		
		if(artikal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(artikalToDto.convert(artikal), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/stavka", consumes = "application/json")
	ResponseEntity<StavkaPrijemaDTO> addStavkaPrijema(@PathVariable Long id,
			@RequestBody StavkaPrijemaDTO stavkaDto){
		
	StavkaPrijema stavkaPrijema = stavkaPrijemaService.add(id, dtoToStavka.convert(stavkaDto));
	
		return new ResponseEntity<>(stavkaToDto.convert(stavkaPrijema), HttpStatus.CREATED);
		
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle(){
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
