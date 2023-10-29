package edu.ciberetc.cl2.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "prodcutos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductos ;

    @Column(name = "nomProductos")
    private String nomProductos;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
   private Integer cantidad;

    @Column(name = "fechaVencimiento")
   private Date fechaVencimiento;


}
