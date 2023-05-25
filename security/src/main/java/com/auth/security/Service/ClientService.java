package com.auth.security.Service;

import com.auth.security.DTO.ClientDTO;

import java.util.List;

public interface ClientService {
    ClientDTO save(ClientDTO clientDTO) ;
    List<ClientDTO> getClientsByIdEntreprise(Integer idEntreprise);
    void Delete (Integer id);
    ClientDTO update(Integer id, ClientDTO clientDTO) ;
    int countClients();




    }
