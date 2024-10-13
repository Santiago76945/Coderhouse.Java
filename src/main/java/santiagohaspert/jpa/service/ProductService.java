package santiagohaspert.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santiagohaspert.jpa.entity.Product;
import santiagohaspert.jpa.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Obtener producto por ID
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    // Crear nuevo producto
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Actualizar producto existente
    public Product updateProduct(int id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id :: " + id));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());

        return productRepository.save(product);
    }

    // Eliminar producto
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
