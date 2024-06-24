package com.desafio.gerenciadorclientes_api.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.gerenciadorclientes_api.api.model.EnderecoResponseDTO;
import com.desafio.gerenciadorclientes_api.api.model.input.EnderecoDtoInput;
import com.desafio.gerenciadorclientes_api.domain.service.BuscarEnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

  @Autowired
  BuscarEnderecoService buscarEndereco;
  
  @GetMapping("/buscar")
  public ResponseEntity<EnderecoResponseDTO> buscar(@RequestBody EnderecoDtoInput enderecoDtoInput) {
    EnderecoResponseDTO endereco = buscarEndereco.executar(enderecoDtoInput);

    if (endereco.getCep() == null) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(endereco);
  }
}
