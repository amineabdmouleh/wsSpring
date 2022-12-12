package org.sid.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sid.entities.Reclamation;
import org.sid.repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assurance")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServiceReclamation {
	@Autowired
	ReclamationRepository reclamationRepository;

	@GetMapping("/Reclamations")
	public ResponseEntity<List<Reclamation>> getAllReclamations(@RequestParam(required = false) String title) {
		try {
			List<Reclamation> Reclamations = new ArrayList<Reclamation>();

			if (title == null)
				reclamationRepository.findAll().forEach(Reclamations::add);
			else
				reclamationRepository.findByTitleContaining(title).forEach(Reclamations::add);

			if (Reclamations.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Reclamations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Reclamations/{id}")
	public ResponseEntity<Reclamation> getReclamationById(@PathVariable("id") long id) {
		Optional<Reclamation> ReclamationData = reclamationRepository.findById(id);

		if (ReclamationData.isPresent()) {
			return new ResponseEntity<>(ReclamationData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Reclamations")
	public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation Reclamation) {
		try {
			Reclamation _Reclamation = reclamationRepository
					.save(new Reclamation(Reclamation.getTitle(), Reclamation.getDescription(), false));
			return new ResponseEntity<>(_Reclamation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Reclamations/{id}")
	public ResponseEntity<Reclamation> updateReclamation(@PathVariable("id") long id,
			@RequestBody Reclamation Reclamation) {
		Optional<Reclamation> ReclamationData = reclamationRepository.findById(id);

		if (ReclamationData.isPresent()) {
			Reclamation _Reclamation = ReclamationData.get();
			_Reclamation.setTitle(Reclamation.getTitle());
			_Reclamation.setDescription(Reclamation.getDescription());
			_Reclamation.setTraité(Reclamation.isTraité());
			return new ResponseEntity<>(reclamationRepository.save(_Reclamation), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Reclamations/{id}")
	public ResponseEntity<HttpStatus> deleteReclamation(@PathVariable("id") long id) {
		try {
			reclamationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Reclamations")
	public ResponseEntity<HttpStatus> deleteAllReclamations() {
		try {
			reclamationRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/Reclamations/traité")
	public ResponseEntity<List<Reclamation>> findByTraite() {
		try {
			List<Reclamation> Reclamations = reclamationRepository.findBytraité(true);

			if (Reclamations.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Reclamations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/Reclamations/nontraité")
	public ResponseEntity<List<Reclamation>> findByNonTraite() {
		try {
			List<Reclamation> Reclamations = reclamationRepository.findBytraité(false);

			if (Reclamations.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Reclamations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
