package com.algaworks.osworks.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
	    cliente1.setNome("José Antunes da Silva");
		cliente1.setTelefone("4564564654");
		cliente1.setEmail("joao@email.com");
		
		
		var cliente2 = new Cliente();  
		cliente2.setId(2L);
		cliente2.setNome("Maria da Silva");
		cliente2.setTelefone("22222222");
		cliente2.setEmail("maria@email.com");
		
	    
	    return Arrays.asList(cliente1, cliente2);
	    
	}
	
}
