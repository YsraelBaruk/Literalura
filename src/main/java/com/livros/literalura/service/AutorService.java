package com.livros.literalura.service;

import com.livros.literalura.model.Autor;
import com.livros.literalura.repositorio.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    @Autowired
    private AutorRepositorio autorRepositorio;

    public Autor salvar(Autor autor){
        return autorRepositorio.save(autor);
    }

    public Autor findByNome(String nome){
        return autorRepositorio.findByNome(nome);
    }
}
