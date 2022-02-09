package com.gabriel.clients.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gabriel.clients.dtos.ClientDto;
import com.gabriel.clients.entities.Client;
import com.gabriel.clients.repositories.ClientRepository;

public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	public Page<ClientDto> findAllPaged(PageRequest request) {
		Page<Client> list = clientRepository.findAll(request);
		return list.map(item -> new ClientDto(item));
	}
	
	public ClientDto findById(Long id) {
		return null;
	}
}
