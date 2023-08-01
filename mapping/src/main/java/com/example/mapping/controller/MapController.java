package com.example.mapping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapping.model.Map;
import com.example.mapping.repository.MapRepository;

@RestController
@RequestMapping("/api")
public class MapController {
    
    @Autowired
    MapRepository mapRepository;

    @GetMapping("/show")
    public List<Map> getAllMap(){
        return (List<Map>) mapRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Map> createMap(@RequestBody Map mapping){
        Map _map =mapRepository
        .save (new Map(mapping.getName(),mapping.getCity(),mapping.getPassword()));
        return new ResponseEntity<Map>(_map, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllMap(){
        mapRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map> updateMap(@PathVariable("id") Long id,@RequestBody Map mapping){
    Optional<Map> _mapping= mapRepository.findById(id);
    if(_mapping.isPresent()){
             Map mappingid=_mapping.get();
             mappingid.setName(mapping.getName());
             mappingid.setCity(mapping.getCity());
             mappingid.setPassword(mapping.getPassword());
             return new ResponseEntity<>(mapRepository.save(mappingid), HttpStatus.OK);
    }
    else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/showid/{id}")
  public ResponseEntity<Map> showid(@PathVariable Long id){
   Optional<Map> _showid=mapRepository.findById(id);
    if(_showid.isPresent()){
        return new ResponseEntity<>(_showid.get(), HttpStatus.OK);
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  
  }

  @DeleteMapping("/deleteid/{id}")
  public ResponseEntity<Map> deleteid(@PathVariable Long id){
   mapRepository.deleteById(id);
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
