package com.livros.literalura.repositorio;

import com.livros.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
}
