package edu.ciberetc.cl2.repository;

import edu.ciberetc.cl2.model.bd.Producto;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto,Integer> {

    Optional<Producto> findBynomProductos(String categoryName);

    List<Producto> findBynomProductosContainingIgnoreCase(String filtro);

    @Query("SELECT c FROM productos c WHERE c.nomProductos LIKE %:filtro%")
    List<Producto> filtrarProductosPorNombre(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM productos WHERE nomProductos LIKE %:filtro%",
            nativeQuery = true)
    List<Producto> filtrarProdcutosPorNombreSQL(@Param("filtro") String filtro);



}
