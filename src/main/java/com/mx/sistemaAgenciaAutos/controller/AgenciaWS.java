package com.mx.sistemaAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sistemaAgenciaAutos.model.Marcas;
import com.mx.sistemaAgenciaAutos.service.MarcasImp;

////________________________________cambiar nombre a marcas
@RestController
@RequestMapping("AgenciaWS")
@CrossOrigin
public class AgenciaWS {
	
    @Autowired
    MarcasImp marcaImp;
	
  //URL: http://localhost:9000/AgenciaWS/listar
    @GetMapping("/listar")
    public List<Marcas> listar() {
        return marcaImp.listar();
    }
    
    /*
    //--------------------Guardar1
    //URL: http://localhost:9000/AgenciaWS/guardarX
    @PostMapping("guardarX")
    public String guardar(@RequestBody Marcas marca) {
        return marcaImp.guardar(marca);
    }*/
    
    
    //_______________________GuardarpConCiclos
    
    @PostMapping(path="guardar")
    public ResponseEntity<?> guardar(@RequestBody Marcas marca){
    	String response = marcaImp.guardar(marca);
    	
    	if(response.equals("idExistente"))
    		return new ResponseEntity<>("Ese id ya existe", HttpStatus.OK);
    	else if (response.equals("nombreExistente"))
    		return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.OK);
    	else
    		return new ResponseEntity<>(marca,HttpStatus.CREATED);
   	
    }
    
    
        
    //_______________________buscar
    //URL: http://localhost:9000/AgenciaWS/buscar
    @PostMapping("buscar")
    public Marcas buscar(@RequestBody Marcas marca) {
        return marcaImp.buscar(marca.getId());
    }
    
    //____________________________editar:
    //URL: http://localhost:9000/AgenciaWS/editar
    @PostMapping(path="editar")
    public ResponseEntity<?>editar(@RequestBody Marcas marca){
    	boolean response = marcaImp.editar(marca);
    	
    	if(response==false)
    		return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
    	else
    		return new ResponseEntity<>(marca, HttpStatus.CREATED);
    }
    
    //_______________________________Eliminar:
    //URL: http://localhost:9000/AgenciaWS/eliminar
    @PostMapping(path="eliminar")
    public ResponseEntity<?>eliminar(@RequestBody Marcas marca){
    	boolean response = marcaImp.eliminar(marca.getId());
    	
    	if(response==false)
    		return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
    	else
    		return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);
    }
    


}
