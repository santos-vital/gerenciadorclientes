package com.desafio.gerenciadorclientes_api.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.gerenciadorclientes_api.api.assembler.UsuarioDtoAssembler;
import com.desafio.gerenciadorclientes_api.api.assembler.UsuarioDtoDisassembler;
import com.desafio.gerenciadorclientes_api.api.model.UsuarioDTO;
import com.desafio.gerenciadorclientes_api.api.model.input.UsuarioDtoInput;
import com.desafio.gerenciadorclientes_api.domain.model.Usuario;
import com.desafio.gerenciadorclientes_api.domain.repository.UsuarioRepository;
import com.desafio.gerenciadorclientes_api.domain.service.CadastroUsuarioService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private CadastroUsuarioService cadastroUsuario;

  @Autowired
  private UsuarioDtoAssembler usuarioDtoAssembler;

  @Autowired UsuarioDtoDisassembler usuarioDtoDisassembler;
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDTO create(@RequestBody @Valid UsuarioDtoInput usuarioDtoInput) {
    
    Usuario usuario = usuarioDtoDisassembler.toDomainObject(usuarioDtoInput);
    usuario = cadastroUsuario.salvar(usuario);

    return usuarioDtoAssembler.toDTO(usuario);
  }

  @GetMapping("/{usuarioId}")
  public UsuarioDTO buscar(@PathVariable Long usuarioId) {
    Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

    return usuarioDtoAssembler.toDTO(usuario);
  }

  @GetMapping
  public List<UsuarioDTO> listar() {
    
    return usuarioDtoAssembler.toCollectionDTO(usuarioRepository.findAll());
  }
  
  @PutMapping("/{usuarioId}")
  public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioDtoInput usuarioDtoInput) {
      Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);

      usuarioDtoDisassembler.copyToDomainObject(usuarioDtoInput, usuarioAtual);
      usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
      
      return usuarioDtoAssembler.toDTO(cadastroUsuario.salvar(usuarioAtual));
  }
}
