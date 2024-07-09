package com.livros.literalura.repositorio;

import com.livros.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    Livro findByTitulo(String nome);
}