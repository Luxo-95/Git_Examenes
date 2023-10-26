package com.cibertec.service;

import com.cibertec.entity.Categoria;
import com.cibertec.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        return categoriaOptional.orElse(null);
    }

    public void agregarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void actualizarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void eliminarCategoriaPorId(Long id) {
        categoriaRepository.deleteById(id);
    }
}

