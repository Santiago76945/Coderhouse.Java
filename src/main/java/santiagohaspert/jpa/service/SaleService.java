package santiagohaspert.jpa.service;

import santiagohaspert.jpa.entity.*;
import santiagohaspert.jpa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleLineRepository saleLineRepository;

    public Map<String, Object> createSale(Map<String, Object> saleRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Extraer ID del cliente
            Map<String, Object> clienteMap = (Map<String, Object>) saleRequest.get("cliente");
            Integer clienteId = (Integer) clienteMap.get("clienteid");

            // Verificar que el cliente existe
            Optional<Client> clientOpt = clientRepository.findById(clienteId);
            if (!clientOpt.isPresent()) {
                response.put("error", "Cliente con id " + clienteId + " no existe");
                return response;
            }
            Client client = clientOpt.get();

            // Extraer las líneas de venta
            List<Map<String, Object>> lineas = (List<Map<String, Object>>) saleRequest.get("lineas");
            if (lineas == null || lineas.isEmpty()) {
                response.put("error", "La venta debe tener al menos una línea");
                return response;
            }

            List<SaleLine> saleLines = new ArrayList<>();
            double totalPrice = 0;
            int totalQuantity = 0;

            for (Map<String, Object> linea : lineas) {
                Integer cantidad = (Integer) linea.get("cantidad");
                if (cantidad == null || cantidad <= 0) {
                    response.put("error", "La cantidad debe ser mayor a cero");
                    return response;
                }

                Map<String, Object> productoMap = (Map<String, Object>) linea.get("producto");
                Integer productoId = (Integer) productoMap.get("productoid");

                // Verificar que el producto existe
                Optional<Product> productOpt = productRepository.findById(productoId);
                if (!productOpt.isPresent()) {
                    response.put("error", "Producto con id " + productoId + " no existe");
                    return response;
                }
                Product product = productOpt.get();

                // Verificar stock disponible
                if (cantidad > product.getStock()) {
                    response.put("error", "Stock insuficiente para el producto con id " + productoId);
                    return response;
                }

                // Reducir el stock
                product.setStock(product.getStock() - cantidad);
                productRepository.save(product);

                // Crear SaleLine
                SaleLine saleLine = new SaleLine();
                saleLine.setProduct(product);
                saleLine.setQuantity(cantidad);
                saleLine.setPriceAtSale(product.getPrice()); // Guardar el precio en el momento de la venta
                saleLines.add(saleLine);

                // Actualizar totales
                totalPrice += product.getPrice() * cantidad;
                totalQuantity += cantidad;
            }

            // Crear la venta
            Sale sale = new Sale();
            sale.setClient(client);
            sale.setLines(saleLines);
            sale.setTotal(totalPrice);
            sale.setTotalQuantity(totalQuantity);

            // Obtener la fecha de la venta desde el servicio REST
            LocalDate saleDate = getSaleDateFromApi();
            sale.setSaleDate(saleDate);

            // Establecer la referencia de la venta en cada línea
            for (SaleLine saleLine : saleLines) {
                saleLine.setSale(sale);
            }

            // Guardar la venta (se guardan las líneas en cascada)
            saleRepository.save(sale);

            response.put("message", "Venta creada exitosamente");
            response.put("saleId", sale.getId());
            response.put("saleDate", sale.getSaleDate());
            response.put("totalPrice", sale.getTotal());
            response.put("totalQuantity", sale.getTotalQuantity());
            return response;

        } catch (Exception e) {
            response.put("error", "Error al crear la venta: " + e.getMessage());
            return response;
        }
    }

    private LocalDate getSaleDateFromApi() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> apiResponse = restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", Map.class);
            String currentDateTime = (String) apiResponse.get("currentDateTime");
            return LocalDate.parse(currentDateTime.substring(0, 10)); // Extraer la parte de la fecha
        } catch (Exception e) {
            // Si falla, usar la fecha local
            return LocalDate.now();
        }
    }

    // Otros métodos (si los hay)...
}


