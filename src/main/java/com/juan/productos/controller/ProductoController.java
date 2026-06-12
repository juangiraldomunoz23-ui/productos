package com.juan.productos.controller;

import com.juan.productos.entity.Producto;
import com.juan.productos.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    // Obtener todos los productos
    @GetMapping
    public List<Producto> listar() {
        return repository.findAll();
    }

    
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

   
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return repository.save(producto);
    }

  
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id,
                                @RequestBody Producto productoActualizado) {

        Producto producto = repository.findById(id).orElse(null);

        if (producto != null) {
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());

            return repository.save(producto);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}