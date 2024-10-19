package santiagohaspert.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import santiagohaspert.jpa.service.SaleService;

import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // Otros métodos (si los hay)...

    // POST: Crear nueva venta
    @PostMapping
    public ResponseEntity<Map<String, Object>> createSale(@RequestBody Map<String, Object> saleRequest) {
        Map<String, Object> response = saleService.createSale(saleRequest);
        if (response.containsKey("error")) {
            return ResponseEntity.badRequest().body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}

