package com.desafio.gerenciadorclientes_api.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.gerenciadorclientes_api.api.model.input.UsuarioDtoInput;
import com.desafio.gerenciadorclientes_api.domain.model.Usuario;

@Component
public class UsuarioDtoDisassembler {
  
  @Autowired
  private ModelMapper modelMapper;

  public Usuario toDomainObject(UsuarioDtoInput usuarioDtoInput) {
    return modelMapper.map(usuarioDtoInput, Usuario.class);
  }

  public void copyToDomainObject(UsuarioDtoInput usuarioDtoInput, Usuario usuario) {
    modelMapper.map(usuarioDtoInput, usuario);
  }
}
