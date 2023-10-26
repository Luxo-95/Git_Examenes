package com.cibertec.controller;

import com.cibertec.entity.Producto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class CarritoController {

    @GetMapping("/carrito")
    public String mostrarCarrito(Model model, HttpSession session) {
        try {
        	
            List<Producto> productosEnCarrito = (List<Producto>) session.getAttribute("carrito");

            if (productosEnCarrito == null) {
                productosEnCarrito = new ArrayList<>();
            }

            double totalCompra = calcularTotalCompra(productosEnCarrito);

            model.addAttribute("productosEnCarrito", productosEnCarrito);
            model.addAttribute("totalCompra", totalCompra);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar el carrito de compras. Por favor, inténtalo de nuevo.");
        }

        return "carrito";
    }

    @PostMapping("/procesarCompra")
    public String procesarCompra(HttpSession session, Model model) {
        try {

            List<Producto> productosEnCarrito = (List<Producto>) session.getAttribute("carrito");

            if (productosEnCarrito != null) {
                session.removeAttribute("carrito");
                model.addAttribute("mensaje", "Compra procesada exitosamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al procesar la compra. Por favor, inténtalo de nuevo.");
        }

        return "redirect:/carrito";
    }

    private double calcularTotalCompra(List<Producto> productos) {
        double total = 0.0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }
    @PostMapping("/cancelarCompra")
    public String cancelarCompra(HttpSession session, Model model) {
        try {
            session.removeAttribute("carrito");
            model.addAttribute("mensaje", "Compra cancelada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cancelar la compra. Por favor, inténtalo de nuevo.");
        }

        return "redirect:/carrito";
    }

}
