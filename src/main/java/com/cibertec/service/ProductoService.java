package com.cibertec.service;

import com.cibertec.entity.Categoria;
import com.cibertec.entity.Producto;
import com.cibertec.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        return productoOptional.orElse(null);
    }

    public List<Producto> obtenerProductosPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public void agregarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void actualizarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void eliminarProductoPorId(Long id) {
        productoRepository.deleteById(id);
    }
}

