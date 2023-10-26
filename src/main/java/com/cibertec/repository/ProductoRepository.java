package com.cibertec.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.entity.Categoria;
import com.cibertec.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria categoria);
}
