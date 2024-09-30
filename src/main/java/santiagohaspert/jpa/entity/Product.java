package santiagohaspert.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @Column(name = "price")
    @Getter @Setter
    private double price;

    @Column(name = "stock")
    @Getter @Setter
    private int stock;
}
