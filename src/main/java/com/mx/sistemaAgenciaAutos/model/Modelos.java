package com.mx.sistemaAgenciaAutos.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 CREATE TABLE MODELOS_AGENCIA(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(80) NOT NULL,
TIPO VARCHAR2(70) NOT NULL,
PRECIO NUMBER NOT NULL,
FECHA_LANZ DATE NOT NULL,
ID_MARCA NUMBER NOT NULL,
FOREIGN KEY(ID_MARCA) REFERENCES MARCAS_AGENCIA(ID)
);
 */

@Entity
@Table(name="MODELOS_AGENCIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Modelos {
	@Id
	private Long id;
	private String nombre;
	private String tipo;
	private Float precio;
	private Date fechaLanz;
	//llave foranea id_marca
	
	//cardinalidad
	//Muchos modelos pertenecen a una marca.
	//fetch-- Indicamos como debe ser cargada la entidad
	//FetchType --Indicamos que la relacion va ser cargada al momento
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MARCA")
	Marcas marca; //Esta variable debe ser la misma que Marcas.Java(variable de tipo objeto)
	
	
	
	

}
