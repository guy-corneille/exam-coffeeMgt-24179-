package com.exam.thecoffeezone.service.impl;

import com.exam.thecoffeezone.model.ClientTable;
import com.exam.thecoffeezone.repository.ClientTableRepo;
import com.exam.thecoffeezone.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientTableRepo clientTableRepo;

    @Autowired
    public ClientServiceImpl(ClientTableRepo clientTableRepo){
        this.clientTableRepo=clientTableRepo;
    }
    @Override
    public List<ClientTable> findAllClientTables() {
        return clientTableRepo.findAll();
    }

    @Override
    public void saveClientTable(ClientTable clientTable) {
        clientTableRepo.save(clientTable);
    }

    @Override
    public ClientTable findByID(int clientID) {
        return clientTableRepo.findById(clientID).orElse(null);
    }

    @Override
    public void updateClientTable(int clientID, ClientTable updatedClientTable) {
        Optional<ClientTable> optionalClientTable = clientTableRepo.findById(clientID);
        if(optionalClientTable.isPresent()){
            ClientTable existClientTable = optionalClientTable.get();
            existClientTable.setFullNames(updatedClientTable.getFullNames());
            existClientTable.setEmail(updatedClientTable.getEmail());
            existClientTable.setPhone(updatedClientTable.getPhone());
            existClientTable.setNber(updatedClientTable.getNber());
            existClientTable.setSuggestion(updatedClientTable.getSuggestion());

            clientTableRepo.save(existClientTable);
        }else {
            throw new RuntimeException ("ClientTable with ID " + clientID + "is not found");
        }
    }

    @Override
    public void deleteClientTable(int clientID) {
        clientTableRepo.deleteById(clientID);
    }
}
