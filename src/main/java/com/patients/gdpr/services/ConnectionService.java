package com.patients.gdpr.services;

import com.patients.gdpr.dto.ConnectionDTO;
import com.patients.gdpr.model.Connection;
import com.patients.gdpr.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionService {
    
    @Autowired
    ConnectionRepository connectionRepository;
    
    public List<ConnectionDTO> getAllConnections() {
        List<ConnectionDTO> connectionDTOS = new ArrayList<>();
        connectionRepository.findAll().forEach(connection -> connectionDTOS.add(new ConnectionDTO(connection)));
        return connectionDTOS;
    }
    
    public ConnectionDTO createEntity(ConnectionDTO connectionDTO) {
        List<Connection> connections = connectionRepository.getAllByIp(connectionDTO.getIp());
        if (connections.size() == 0) {
            Connection connection = connectionRepository.save(DTOToEntity(connectionDTO));
            connectionDTO.setId(connection.getId());
        } else {
            for (Connection connection: connections) {
                connection.setConDate(connectionDTO.getConDate());
                connection.setCountry(connectionDTO.getCountry());
                connectionRepository.save(connection);
            }
        }
        return connectionDTO;
    }
    
    public ConnectionDTO entityToDTO(Connection connection) {
        return new ConnectionDTO(connection);
    }
    
    public Connection DTOToEntity(ConnectionDTO connectionDTO) {
        return new Connection(connectionDTO);
    }
    
}
