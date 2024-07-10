package com.livros.literalura.principal;

import com.livros.literalura.dto.LivroDTO;
import com.livros.literalura.model.Autor;
import com.livros.literalura.model.Livro;
import com.livros.literalura.service.AutorService;
import com.livros.literalura.service.ConsumoApi;
import com.livros.literalura.service.Conversor;
import com.livros.literalura.service.LivroService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner digitar = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private ConsumoApi consumoApi = new ConsumoApi();
    private Conversor conversor = new Conversor();
    private LivroService livroService;
    private AutorService autorService;

    public Principal(AutorService autorService, LivroService livroService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0){
            String menu = """
                \n1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registradores
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                0 - Sair
                """;
            System.out.println(menu);
            opcao = digitar.nextInt();
            digitar.nextLine();
            switch (opcao){
                case 1:
                    buscarLivroNome();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
            }
        }
    }

    public void buscarLivroNome() {
        System.out.println("Digite o nome do livro: ");
        var nomeLivro = digitar.nextLine();
        var json = consumoApi.busca(ENDERECO+nomeLivro.replaceAll(" ", "%20"));
        System.out.println(json);
        LivroDTO livroDTO = conversor.converteDados(json);
        System.out.println(livroDTO);
        try {
            List<Autor> autores = livroDTO.authors().stream().map(Autor::new).toList();
            var autor = autores.getFirst();
            Livro livro = new Livro(livroDTO, autor);
            System.out.println(livro);
            Optional<Autor> autorPresent = autorService.findByNome(autor.getNome());
            if (!(autorPresent.isPresent())){
                autorService.salvar(autor);
            }
            Optional<Livro> livroPresent = livroService.findByTitulo(livro.getTitulo());
            if (!(livroPresent.isPresent())){
                livroService.salvar(livro);
            }
            System.out.println(livro);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}