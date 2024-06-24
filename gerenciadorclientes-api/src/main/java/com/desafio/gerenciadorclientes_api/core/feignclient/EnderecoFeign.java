package com.desafio.gerenciadorclientes_api.core.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.desafio.gerenciadorclientes_api.api.model.EnderecoDTO;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface EnderecoFeign {
  
  @GetMapping("{cep}/json/")
  EnderecoDTO buscaEnderecoCep(@PathVariable("cep") String cep);
}
