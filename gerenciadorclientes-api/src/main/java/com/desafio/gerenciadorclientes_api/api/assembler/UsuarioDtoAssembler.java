package com.desafio.gerenciadorclientes_api.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.gerenciadorclientes_api.api.model.UsuarioDTO;
import com.desafio.gerenciadorclientes_api.domain.model.Usuario;

@Component
public class UsuarioDtoAssembler {
  
  @Autowired
  private ModelMapper modelMapper;

  public UsuarioDTO toDTO(Usuario usuario) {
    return modelMapper.map(usuario, UsuarioDTO.class);
  }

  public List<UsuarioDTO> toCollectionDTO(List<Usuario> usuarios) {
    
    return usuarios.stream().map(usuario -> toDTO(usuario)).collect(Collectors.toList());
  }
}
