package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Ticket;
import com.gutotech.fatecando.model.TicketResponse;

@Service
public class TicketService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/tickets";

	public List<Ticket> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Ticket>>() {
		});
	}

	public Ticket findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", Ticket.class, id);
	}

	public Ticket save(Ticket ticket) {
		return restTemplate.postForObject(URL, ticket, Ticket.class);
	}

	public void update(Ticket ticket) {
		restTemplate.put(URL + "/{id}", ticket, ticket.getId());
	}

	public List<TicketResponse> findResponses(Long id) {
		return restTemplate.getForObjects(URL + "/{id}/responses",
				new ParameterizedTypeReference<List<TicketResponse>>() {}, id);
	}

	public TicketResponse addTicketResponse(Ticket ticket, TicketResponse response) {
		return restTemplate.postForObject(URL + "/{id}/responses", response, TicketResponse.class, ticket.getId());
	}

}
