package magacin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import magacin.model.Artikal;
import magacin.model.Magacin;
import magacin.service.ArtikalService;
import magacin.service.MagacinService;
import magacin.support.ArtikalToArtikalDTO;
import magacin.support.MagacinToMagacinDTO;
import magacin.web.dto.ArtikalDTO;
import magacin.web.dto.MagacinDTO;

@RestController
@RequestMapping(value = "/api/magacini")
public class ApiMagacinController {

	@Autowired
	MagacinService magacinService;
	
	@Autowired
	ArtikalService artikalService;
	
	@Autowired
	MagacinToMagacinDTO magacinToDto;
	
	@Autowired
	ArtikalToArtikalDTO artikalToDto;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<MagacinDTO>> getAll(){
		
		List<Magacin> magacini= magacinService.getAll();
		
		if(magacini.isEmpty() || magacini == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(magacinToDto.convert(magacini), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/artikli")
	ResponseEntity<List<ArtikalDTO>> getByMagacinId(@PathVariable Long id){
		
		List<Artikal> artikli=artikalService.findByMagacinId(id);
		
		if(artikli==null || artikli.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(artikalToDto.convert(artikli), HttpStatus.OK);
	}
}
