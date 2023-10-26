package com.cibertec.controller;

import com.cibertec.service.ProductoService;
import com.cibertec.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/index")
    public String mostrarCatalogo(Model model) {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return "index";
    }

    @PostMapping("/agregarAlCarrito/{id}")
    public String agregarAlCarrito(@PathVariable Long id, HttpSession session, Model model) {
        try {
            Producto producto = productoService.obtenerProductoPorId(id);

            if (producto != null) {
                List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

                if (carrito == null) {
                    carrito = new ArrayList<>();
                    session.setAttribute("carrito", carrito);
                }

                carrito.add(producto);

                model.addAttribute("mensaje", "Producto agregado al carrito exitosamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al agregar el producto al carrito. Por favor, inténtalo de nuevo.");
        }

        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);

        return "index";
    }
}

