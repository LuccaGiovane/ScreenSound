package br.com.screenSound.ScreenSound.principal;

import java.util.Scanner;

public class Principal {

    private Scanner input = new Scanner(System.in);
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
                    [5] Pesquisar dados sobre um cantor
                                    
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

                case 5:
                    pesquisarDadosCantor();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarArtista() {

    }

     private void cadastrarMusica() {

    }
    private void pesquisarDadosCantor() {
    }

    private void listarMusicas() {

    }
    private void buscarMusicasPorArtista() {
        
    }

}
