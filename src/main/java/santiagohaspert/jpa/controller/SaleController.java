package santiagohaspert.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import santiagohaspert.jpa.entity.Sale;
import santiagohaspert.jpa.service.SaleService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // POST: Crear nueva venta con el formato de cliente y líneas
    @PostMapping
    public ResponseEntity<Map<String, Object>> createSale(@RequestBody Map<String, Object> saleRequest) {
        Map<String, Object> response = saleService.createSale(saleRequest);
        if (response.containsKey("error")) {
            return ResponseEntity.badRequest().body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }

    // GET: Obtener todas las ventas (resumen)
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }
}




