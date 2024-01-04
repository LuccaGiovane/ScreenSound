package br.com.screenSound.ScreenSound.principal;

import br.com.screenSound.ScreenSound.models.Artista;
import br.com.screenSound.ScreenSound.models.Musica;
import br.com.screenSound.ScreenSound.models.TipoArtista;
import br.com.screenSound.ScreenSound.repositorio.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ArtistaRepository repositorio;
    private Scanner input = new Scanner(System.in);
    public Principal(ArtistaRepository repositorio)
    {
        this.repositorio = repositorio;
    }
    public void exibeMenu()
    {
        var opcao = -1;

        while(opcao != 0)
        {
            var menu = """
                    [1] Cadastrar Artista
                    [2] Cadastrar Música
                    [3] Listar músicas  
                    [4] Buscar músicas por artistas                         
                    [0] Sair                                 
                    """;

            System.out.println(menu);
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;

                case 2:
                    cadastrarMusica();
                    break;

                case 3:
                    listarMusicas();
                    break;

                case 4:
                    buscarMusicasPorArtista();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarArtista()
    {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s"))
        {
            System.out.printf("Insira o nome do artista: ");
            var nomeArtista = input.nextLine();
            System.out.printf("Informe o tipo do artista (Solo|Dupla|Banda): ");
            var tipo = input.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());

            Artista artista = new Artista(nomeArtista, tipoArtista);

            repositorio.save(artista);

            System.out.printf("Cadastrar mais artistas?(S|N)");
            cadastrarNovo = input.nextLine();
        }

    }

     private void cadastrarMusica() {
         System.out.printf("Insira o nome do artista para o cadastro da música:");
         var nome = input.nextLine();
         Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);

         if(artista.isPresent())
         {
             System.out.printf("Informe o nome da música:");
             var nomeMusica = input.nextLine();

             Musica musica = new Musica(nomeMusica);

             // one to many e many to one pede que mapeia dos 2 lados pra dar certo no BD2
             artista.get().getMusicas().add(musica);
             musica.setArtista(artista.get());

             repositorio.save(artista.get());
         }
         else
         {
             System.out.println("Artista não encontrado.");
         }
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(System.out::println);

    }
    private void buscarMusicasPorArtista() {
        System.out.println("Insira o nome do artista:");
        var nomeArtista = input.nextLine();

        List<Musica> musicas = repositorio.musicasPorArtista(nomeArtista);
        musicas.forEach(System.out::println);
        
    }
}
