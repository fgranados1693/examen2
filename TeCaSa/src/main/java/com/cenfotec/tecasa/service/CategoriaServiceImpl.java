package com.cenfotec.tecasa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.tecasa.domain.Categoria;
import com.cenfotec.tecasa.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	CategoriaRepository categoriaRepo;
	
	
	public void saveCategoria(Categoria categoria) {
		categoriaRepo.save(categoria);
	}
	
	@Override
	public List<Categoria> getAll() {
		return categoriaRepo.findAll();
	}
	
	@Override
	public Optional<Categoria> get(Long id) {
		return categoriaRepo.findById(id);
	}

	@Override
	public void deleteCategoria(Categoria categoria) {
		categoriaRepo.delete(categoria);
		
	}	
	
}
