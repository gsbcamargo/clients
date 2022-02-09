package com.gabriel.clients.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.clients.dtos.ClientDto;
import com.gabriel.clients.entities.Client;
import com.gabriel.clients.repositories.ClientRepository;
import com.gabriel.clients.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = true)
	public ClientDto insert(ClientDto dto) {
		Client entity = new Client();
		toDto(dto, entity);
		entity = clientRepository.save(entity);
		return new ClientDto(entity);
	}

	private void toDto(ClientDto dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
}
