package com.example.flower.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
import org.springframework.web.bind.annotation.RestController;
import com.datastax.driver.core.utils.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.flower.entity.*;
import com.example.flower.repository.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FlowerController {
	
	private static Logger logger = LoggerFactory.getLogger(FlowerController.class);
	
	@Autowired
	FlowerRepository repository;
	
	@GetMapping("/flowers")
	public List<Flower> getAllFlowers(){
		logger.info("Get all Flowers ...");
		return repository.findAll();
	}
	
	@PostMapping("/flowers/create")
	public ResponseEntity<Flower> postFlower(@RequestBody Flower flower){
		logger.info("Create Flower: "+ flower.getName()+" ...");
		flower.setId(UUIDs.timeBased());
		Flower newFlower = repository.save(flower);
		return new ResponseEntity<>(newFlower, HttpStatus.OK);
	}
	
	@DeleteMapping("/flowers/{id}")
	public ResponseEntity<String> deleteFlower(@PathVariable("id") UUID id){
		logger.info("Delete Flower with ID = "+ id + "...");
		repository.deleteById(id);
		return new ResponseEntity<>("Flower has been deleted!!!", HttpStatus.OK);
	}
	
	@DeleteMapping("/flowers/delete")
	public ResponseEntity<String> deleteAllFlowers(){
		logger.info("Delete all flowers...");
		repository.deleteAll();
		return new ResponseEntity<>("All flowers have been deleted!!!", HttpStatus.OK);
	}
	
	@GetMapping("/flowers/price/{price}")
	public List<Flower> findByPrice(@PathVariable double price){
		List<Flower> flowers = repository.findByPrice(price);
		return flowers;
	}
	
	@PutMapping("/flowers/{id}")
	public ResponseEntity<Flower> updateFlower(@PathVariable("id") UUID id, @RequestBody Flower flower){
		logger.info("Update Flower with ID = " + id +" ...");
		Optional<Flower> flowerData = repository.findById(id);
		if (flowerData.isPresent()) {
			Flower flowerUpd = flowerData.get();
			flowerUpd.setName(flower.getName());
			flowerUpd.setPrice(flower.getPrice());
			flowerUpd.setActive(flower.isActive());
			return new ResponseEntity<>(repository.save(flowerUpd),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
