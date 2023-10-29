package edu.ciberetc.cl2.service;

import edu.ciberetc.cl2.model.bd.Producto;
import edu.ciberetc.cl2.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoService {

    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }
    public Optional<Producto> obtenerProductoPorId(Integer id){
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isEmpty()){
            return Optional.empty();
        }else
            return producto;
    }

    public Optional<Producto> obtenerProductoPorNombre(String nomProductos){
        Optional<Producto> producto = productoRepository.findBynomProductos(nomProductos);
        if(producto.isEmpty())
            return  Optional.empty();
        else
            return producto;
    }

    public List<Producto> obtenerProductosPorFiltro(String filtro){
        return productoRepository.filtrarProdcutosPorNombreSQL(filtro);
    }

}
