package santiagohaspert.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Fecha de la venta
    @Column(name = "sale_date")
    private LocalDate saleDate;

    // Precio total de la venta
    @Column(name = "total")
    private double total;

    // Cantidad total de productos vendidos
    @Column(name = "total_quantity")
    private int totalQuantity;

    // Relación con Cliente
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    // Relación con SaleLines
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleLine> lines;
}
