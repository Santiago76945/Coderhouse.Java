package santiagohaspert.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Column(name = "sale_date")
    @Getter @Setter
    private LocalDate saleDate;

    @Column(name = "total")
    @Getter @Setter
    private double total;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter @Setter
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "sale_products",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Getter @Setter
    private List<Product> products;
}
