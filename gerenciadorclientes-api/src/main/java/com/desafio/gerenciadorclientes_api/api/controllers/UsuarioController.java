package com.desafio.gerenciadorclientes_api.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.gerenciadorclientes_api.api.model.UsuarioDTO;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDTO create(@RequestBody UsuarioDTO usuarioDTO) {
    return usuarioDTO;
  }
}
