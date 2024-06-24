package com.desafio.gerenciadorclientes_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.gerenciadorclientes_api.api.model.EnderecoDTO;
import com.desafio.gerenciadorclientes_api.api.model.EnderecoResponseDTO;
import com.desafio.gerenciadorclientes_api.api.model.input.EnderecoDtoInput;
import com.desafio.gerenciadorclientes_api.core.feignclient.EnderecoFeign;

@Service
public class BuscarEnderecoService {

  @Autowired
  EnderecoFeign enderecoFeign;
  
  public EnderecoResponseDTO executar(EnderecoDtoInput enderecoDtoInput) {
    
    // return enderecoFeign.buscaEnderecoCep(enderecoDtoInput.getCep());

    EnderecoDTO enderecoDTO = enderecoFeign.buscaEnderecoCep(enderecoDtoInput.getCep());

    // Transforma EnderecoDTO em EnderecoResponseDTO
    EnderecoResponseDTO responseDTO = transformarParaEnderecoResponseDTO(enderecoDTO);

    return responseDTO;
  }

  private EnderecoResponseDTO transformarParaEnderecoResponseDTO(EnderecoDTO enderecoDTO) {
      EnderecoResponseDTO responseDTO = new EnderecoResponseDTO();
      responseDTO.setCep(enderecoDTO.getCep());
      responseDTO.setLogradouro(enderecoDTO.getLogradouro());
      responseDTO.setComplemento(enderecoDTO.getComplemento());
      responseDTO.setBairro(enderecoDTO.getBairro());
      responseDTO.setCidade(enderecoDTO.getLocalidade()); // Transforma localidade em cidade
      responseDTO.setUf(enderecoDTO.getUf());
      return responseDTO;
  }
}
