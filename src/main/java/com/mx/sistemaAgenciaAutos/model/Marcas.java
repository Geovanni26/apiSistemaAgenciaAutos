package com.mx.sistemaAgenciaAutos.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 CREATE TABLE MARCAS_AGENCIA(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(80) NOT NULL,
ORIGEN VARCHAR2(70) NOT NULL,
ESLOGAN VARCHAR2(100)
)
 **/

@Entity
@Table(name="MARCAS_AGENCIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Marcas {
	//manera 3 de mapeo
	//Siempre en minusculas
	@Id
	private Long id;
	private String nombre;
	private String origen;
	private String eslogan;
	
	//Cardinalidad
	//1 marca tiene muchos modelos
	//Se puede eliminar la eliminacion en cascada
	//marca: variable de tipo objeto
	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
	//Se declara una lista de modelos
	@JsonIgnore //srirve para omitit una propiedad o lista de propiedades  en mi Json 
	List<Modelos> lista=new ArrayList<Modelos>();

}
