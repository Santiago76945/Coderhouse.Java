package santiagohaspert.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private Double totalAmount;

    // Añadir los siguientes campos
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleLine> lines;

    private double total;
    private int totalQuantity;
    private LocalDate saleDate;

    // Relación bidireccional con Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

