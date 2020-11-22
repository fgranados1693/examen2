package com.cenfotec.tecasa.domain;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cenfotec.tecasa.domain.Tarea;


@Entity
public class Workshop {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long id;	
	private String nombre;
	private String autor;
	private String objetivo;
	private String palabraClave1;
	private String palabraClave2;
	private String palabraClave3;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getnombre() {
		return nombre;
	}
	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getPalabraClave1() {
		return palabraClave1;
	}
	public void setPalabraClave1(String palabraClave1) {
		this.palabraClave1 = palabraClave1;
	}
	public String getPalabraClave2() {
		return palabraClave2;
	}
	public void setPalabraClave2(String palabraClave2) {
		this.palabraClave2 = palabraClave2;
	}
	public String getPalabraClave3() {
		return palabraClave3;
	}
	public void setPalabraClave3(String palabraClave3) {
		this.palabraClave3 = palabraClave3;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}	
	
	// Tareas
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="workshop")
	
	private Set<Tarea> tareas;
	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}
}
