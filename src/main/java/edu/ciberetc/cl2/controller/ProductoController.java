package edu.ciberetc.cl2.controller;

import edu.ciberetc.cl2.model.bd.Producto;
import edu.ciberetc.cl2.service.ProductoService;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/productos")
public class ProductoController {

    private ProductoService productoService;

    @GetMapping("")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productoList = new ArrayList<>();
        productoService.listarProductos()
                .forEach(productoList::add);
        if(productoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(
            @PathVariable("id") Integer id){
        Producto producto = productoService
                .obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El PRODUCTO con el Id Nro. "+
                        id + " no existe."));

        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Producto> registrarProducto(
            @RequestBody Producto producto
    ){
        return new ResponseEntity<>(
                productoService.guardar(producto), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable("id") Integer id,
            @RequestBody Producto producto
    ){
        Producto olProducto = productoService.obtenerProductoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El PRODUCTO con el Id Nro. "+
                        id + " no existe."));
        olProducto.setNomProductos(producto.getNomProductos());
        olProducto.setDescripcion(producto.getDescripcion());
        olProducto.setCantidad(producto.getCantidad());
        return new ResponseEntity<>(
                productoService.guardar(olProducto), HttpStatus.OK
        );
    }



}
