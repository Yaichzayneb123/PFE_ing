package com.auth.security.Service;

import com.auth.security.DTO.ClientDTO;
import com.auth.security.Entity.Client;
import com.auth.security.Repository.ClientDAO;
import com.sun.jdi.connect.spi.Connection;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    @Autowired

    private ClientDAO clientDAO;
    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        // Convertir l'objet DTO en entité
        Client client = ClientDTO.toEntity(clientDTO);

        // Enregistrer l'entité en base de données
        client = clientDAO.save(client);

        // Mettre à jour l'ID de l'objet DTO
        clientDTO.setId(client.getId());

        // Retourner l'objet DTO mis à jour
        return clientDTO;
    }
@Override
    public List<ClientDTO> getClientsByIdEntreprise(Integer idEntreprise) {
        List<ClientDTO> clientsDTO = new ArrayList<>();

        // Récupérer tous les clients de l'entreprise
        List<Client> clients = clientDAO.getClientsByIdEntreprise(idEntreprise);

        // Convertir chaque entité client en objet DTO
        for (Client client : clients) {
            ClientDTO clientDTO = ClientDTO.fromEntity(client);
            clientsDTO.add(clientDTO);
        }

        return clientsDTO;
    }
    @Override
    public void Delete(Integer id) {
        clientDAO.deleteById(id);
    }
@Override
public ClientDTO update(Integer id, ClientDTO clientDTO) {
    // Vérifier que l'ID du client est valide
    if (id == null || id <= 0) {
        throw new IllegalArgumentException("Invalid client ID");
    }

    // Récupérer le client correspondant à l'ID
    Client existingClient = clientDAO.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Client not found"));

    // Mettre à jour les propriétés du client avec les valeurs du DTO
    existingClient.setFirstName(clientDTO.getFirstName());
    existingClient.setLastName(clientDTO.getLastName());
    existingClient.setEmail(clientDTO.getEmail());
    existingClient.setPhone(clientDTO.getPhone());
    existingClient.setAddress(clientDTO.getAddress());
    existingClient.setIdEntreprise(clientDTO.getIdEntreprise());

    // Enregistrer le client mis à jour en base de données
    existingClient = clientDAO.save(existingClient);

    // Retourner le DTO mis à jour
    return ClientDTO.fromEntity(existingClient);
}
@Override
    public int countClients() {
        return clientDAO.countAllClients();
    }



}
