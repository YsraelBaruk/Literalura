package com.livros.literalura.model;

import com.ctc.wstx.shaded.msv_core.util.LightStack;
import com.livros.literalura.dto.AutorDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer ano_nasc;
    private Integer ano_fale;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Livro> livros;

    public Autor(){}

    public Autor(AutorDTO autorDTO) {
        this.nome= autorDTO.name();
        this.ano_nasc= autorDTO.birth_year();
        this.ano_fale= autorDTO.death_year();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno_nasc() {
        return ano_nasc;
    }

    public void setAno_nasc(Integer ano_nasc) {
        this.ano_nasc = ano_nasc;
    }

    public Integer getAno_fale() {
        return ano_fale;
    }

    public void setAno_fale(Integer ano_fale) {
        this.ano_fale = ano_fale;
    }

    @Override
    public String toString() {
        return "Autor: "+nome+" ("+ano_nasc+" - "+ano_fale+")";
    }
}
