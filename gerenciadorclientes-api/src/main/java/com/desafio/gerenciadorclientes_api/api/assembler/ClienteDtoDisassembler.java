package com.desafio.gerenciadorclientes_api.api.assembler;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.gerenciadorclientes_api.api.model.input.ClienteDtoInput;
import com.desafio.gerenciadorclientes_api.domain.model.Cliente;
import com.desafio.gerenciadorclientes_api.domain.model.Email;
import com.desafio.gerenciadorclientes_api.domain.model.Telefone;

@Component
public class ClienteDtoDisassembler {
  
  @Autowired
  private ModelMapper modelMapper;

  public Cliente toDomainObject(ClienteDtoInput clienteDtoInput) {
    return modelMapper.map(clienteDtoInput, Cliente.class);
  }

  public void copyToDomainObject(ClienteDtoInput clienteDtoInput, Cliente cliente) {
    cliente.setEmails(new ArrayList<Email>());
    cliente.setTelefones(new ArrayList<Telefone>());

    modelMapper.map(clienteDtoInput, cliente);
  }
}
