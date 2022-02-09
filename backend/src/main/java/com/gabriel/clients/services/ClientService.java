package com.gabriel.clients.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.clients.dtos.ClientDto;
import com.gabriel.clients.entities.Client;
import com.gabriel.clients.repositories.ClientRepository;
import com.gabriel.clients.services.exceptions.ResourceNotFoundException;

public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Page<ClientDto> findAllPaged(PageRequest request) {
		Page<Client> list = clientRepository.findAll(request);
		return list.map(item -> new ClientDto(item));
	}

	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Optional<Client> clientOptional = clientRepository.findById(id);
		Client client = clientOptional.orElseThrow(
				() -> new ResourceNotFoundException(String.format("Sorry, entity of id %s was not found.", id)));
		return new ClientDto(client);
	}
}
