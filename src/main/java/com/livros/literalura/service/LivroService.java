package com.livros.literalura.service;

import com.livros.literalura.model.Livro;
import com.livros.literalura.repositorio.LivroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    private LivroRepositorio livroRepositorio;

    public Livro salvar(Livro livro){
        return livroRepositorio.save(livro);
    }

    public Livro findByTitulo(String nome){
        return livroRepositorio.findByTitulo(nome);
    }
}
