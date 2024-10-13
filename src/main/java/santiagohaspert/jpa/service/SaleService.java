package santiagohaspert.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santiagohaspert.jpa.entity.Sale;
import santiagohaspert.jpa.repository.SaleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    // Obtener todas las ventas
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Obtener venta por ID
    public Optional<Sale> getSaleById(int id) {
        return saleRepository.findById(id);
    }

    // Crear nueva venta
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    // Actualizar venta existente
    public Sale updateSale(int id, Sale saleDetails) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id :: " + id));

        sale.setSaleDate(saleDetails.getSaleDate());
        sale.setTotal(saleDetails.getTotal());
        sale.setClient(saleDetails.getClient());
        sale.setProducts(saleDetails.getProducts());

        return saleRepository.save(sale);
    }

    // Eliminar venta
    public void deleteSale(int id) {
        saleRepository.deleteById(id);
    }
}

