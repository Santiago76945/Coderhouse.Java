package santiagohaspert.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import santiagohaspert.jpa.entity.Client;
import santiagohaspert.jpa.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Obtener todos los clientes
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Obtener cliente por ID
    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }

    // Crear nuevo cliente
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Actualizar cliente existente
    public Client updateClient(int id, Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id :: " + id));

        client.setName(clientDetails.getName());
        client.setEmail(clientDetails.getEmail());

        return clientRepository.save(client);
    }

    // Eliminar cliente
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
