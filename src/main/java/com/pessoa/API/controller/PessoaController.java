package com.pessoa.API.controller;

import com.pessoa.API.model.Pessoa;
import com.pessoa.API.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa editar(@PathVariable long id, @RequestBody Pessoa pessoa) {
        var pesquisaPessoa = pessoaRepository.findById(id);
        if (pesquisaPessoa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    @GetMapping("/{id}")
    public Pessoa consultar(@PathVariable long id) {
        var pesquisaPessoa = pessoaRepository.findById(id);
        if (pesquisaPessoa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pessoaRepository.findById(id);
    }

    @GetMapping("/endereco/{id}")
    public Pessoa listarEndereco(@PathVariable long id) {
        var pesquisaPessoa = pessoaRepository.findById(id);
        if (pesquisaPessoa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return null; //pessoaRepository.findById(id).getLogradouro();
    }
}
