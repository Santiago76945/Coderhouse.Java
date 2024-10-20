package santiagohaspert.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    // Relación OneToMany con Sale, con @JsonIgnore para evitar la recursividad
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Sale> sales;
}

