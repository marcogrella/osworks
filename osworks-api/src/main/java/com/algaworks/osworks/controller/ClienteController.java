package com.algaworks.osworks.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes") // diz que o controlador intercepta tudo com esta url
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping
	public List<Cliente> listar() {
		
		return clienteRepository.findAll();
	    
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// o RequestBody diz para o Spring que quando vier uma requisição
	// em um objeto do tipo Cliente
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED) // já assimila ao método um status ao criar
	public Cliente adicionar(@RequestBody Cliente cliente) {
		
		return clienteRepository.save(cliente);		
		
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, 
			@RequestBody Cliente cliente){
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		} else {
		
			// o ideal é não salvar direto, estamos querendo mudar. Então setamos
			// o id (porque o corpo não envia o id e por isso pode incluir como um novo
			// cliente. Por isso pegamos o id e setamos em cliente recebido. 
			cliente.setId(clienteId);
			// agora podemos salvar pois já tem o id e basta dar o comando para salvar
			cliente = clienteRepository.save(cliente);
			
			return ResponseEntity.ok(cliente);
		}
			
		
	}
	
	
}
