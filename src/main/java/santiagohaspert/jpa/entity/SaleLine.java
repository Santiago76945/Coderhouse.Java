package santiagohaspert.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sale_lines")
@Getter
@Setter
@NoArgsConstructor
public class SaleLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Cantidad de productos en esta línea
    @Column(name = "quantity")
    private int quantity;

    // Precio del producto en el momento de la venta
    @Column(name = "price_at_sale")
    private double priceAtSale;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Relación con Venta
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
}

