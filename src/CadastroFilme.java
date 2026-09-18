import java.util.ArrayList;
import java.util.Scanner;

public class CadastroFilme {

    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);

        ArrayList<Filme> filmes = new ArrayList<>();

        int opcao = 0;

        while (opcao != 3) {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar filme");
            System.out.println("2 - Listar filmes");
            System.out.println("3 - Sair");
            System.out.println("4 - Buscar filme");
            System.out.println("5 - Remover filme");
            System.out.println("6 - Editar filme");
            System.out.println("7 - Alterar nota");
            System.out.println("Escolha uma opção:");

            while (!leitura.hasNextInt()) {
                System.out.println("Digite uma opção válida:");
                leitura.next();
            }

            opcao = leitura.nextInt();

            switch (opcao) {

                case 1:
                    cadastrarFilme(leitura, filmes);
                    break;

                case 2:
                    listarFilmes(filmes);
                    break;

                case 3:
                    System.out.println("Programa encerrado!");
                    break;

                case 4:
                    buscarFilme(leitura, filmes);
                    break;

                case 5:
                    removerFilme(leitura, filmes);
                    break;

                case 6:
                    editarFilme(leitura, filmes);
                    break;

                case 7:
                    alterarNota(leitura, filmes);
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        leitura.close();
    }


    public static void cadastrarFilme(Scanner leitura, ArrayList<Filme> filmes) {

        leitura.nextLine();

        // NOME
        System.out.println("Digite o filme:");
        String filme = leitura.nextLine();

        while (!Validador.nomeValido(filme)) {
            System.out.println("Nome inválido! Digite o nome do filme:");
            filme = leitura.nextLine();
        }

        // VERIFICAR DUPLICADO
        boolean nomeExiste = false;

        for (Filme f : filmes) {

            if (f.getNome().equalsIgnoreCase(filme)) {
                nomeExiste = true;
            }
        }

        if (nomeExiste) {
            System.out.println("Esse filme já está cadastrado!");
            return;
        }


        // ANO
        System.out.println("Digite o ano de lançamento:");

        while (!leitura.hasNextInt()) {
            System.out.println("Digite um ano válido:");
            leitura.next();
        }

        int ano = leitura.nextInt();

        while (!Validador.anoValido(ano)) {
            System.out.println("Ano de lançamento inválido! Digite um ano entre 1800 e 2026:");
            ano = leitura.nextInt();
        }


        // DURAÇÃO
        System.out.println("Digite a duração:");

        while (!leitura.hasNextInt()) {
            System.out.println("Digite uma duração válida!");
            leitura.next();
        }

        int duracao = leitura.nextInt();

        while (!Validador.duracaoValido(duracao)) {
            System.out.println("Duração inválida! Digite uma duração maior que 0:");
            duracao = leitura.nextInt();
        }


        // NOTA
        System.out.println("Digite a nota do filme:");

        while (!leitura.hasNextDouble()) {
            System.out.println("Digite uma nota válida:");
            leitura.next();
        }

        double nota = leitura.nextDouble();

        while (!Validador.notaValido(nota)) {
            System.out.println("Nota inválida! Digite uma nota entre 0 e 10:");
            nota = leitura.nextDouble();
        }


        // CRIAR FILME
        Filme meuFilme = new Filme(filme, ano, duracao, nota);

        filmes.add(meuFilme);

        System.out.println("Filme cadastrado!");
    }


    public static void listarFilmes(ArrayList<Filme> filmes) {

        if (filmes.isEmpty()) {

            System.out.println("Nenhum filme cadastrado.");

        } else {

            System.out.println("\n===== FILMES CADASTRADOS =====");

            for (Filme f : filmes) {
                System.out.println(f);
            }
        }
    }


    public static void buscarFilme(Scanner leitura, ArrayList<Filme> filmes) {

        System.out.println("Digite o nome do filme:");

        leitura.nextLine();

        String busca = leitura.nextLine();

        boolean encontrado = false;

        for (Filme f : filmes) {

            if (f.getNome().equalsIgnoreCase(busca)) {

                System.out.println(f);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Filme não encontrado.");
        }
    }


    public static void removerFilme(Scanner leitura, ArrayList<Filme> filmes) {

        System.out.println("Digite o filme que deseja remover:");

        leitura.nextLine();

        String busca = leitura.nextLine();

        Filme filmeRemover = null;

        for (Filme f : filmes) {

            if (f.getNome().equalsIgnoreCase(busca)) {

                filmeRemover = f;
            }
        }

        if (filmeRemover != null) {

            filmes.remove(filmeRemover);

            System.out.println("Filme removido!");

        } else {

            System.out.println("Filme não encontrado!");
        }
    }


    public static void editarFilme(Scanner leitura, ArrayList<Filme> filmes) {

        System.out.println("Digite o filme que deseja editar:");

        leitura.nextLine();

        String busca = leitura.nextLine();

        Filme filmeEditar = null;

        for (Filme f : filmes) {

            if (f.getNome().equalsIgnoreCase(busca)) {

                filmeEditar = f;
            }
        }

        if (filmeEditar != null) {

            System.out.println("Filme encontrado!");

            // NOVO ANO
            System.out.println("Digite o novo ano:");

            while (!leitura.hasNextInt()) {
                System.out.println("Digite um ano válido:");
                leitura.next();
            }

            int novoAno = leitura.nextInt();

            while (!Validador.anoValido(novoAno)) {
                System.out.println("Ano inválido! Digite um ano entre 1800 e 2026:");
                novoAno = leitura.nextInt();
            }


            // NOVA DURAÇÃO
            System.out.println("Digite a nova duração:");

            while (!leitura.hasNextInt()) {
                System.out.println("Digite uma duração válida:");
                leitura.next();
            }

            int novaDuracao = leitura.nextInt();

            while (!Validador.duracaoValido(novaDuracao)) {
                System.out.println("Duração inválida! Digite uma duração maior que 0:");
                novaDuracao = leitura.nextInt();
            }


            // NOVA NOTA
            System.out.println("Digite a nova nota:");

            while (!leitura.hasNextDouble()) {
                System.out.println("Digite uma nota válida:");
                leitura.next();
            }

            double novaNota = leitura.nextDouble();

            while (!Validador.notaValido(novaNota)) {
                System.out.println("Nota inválida! Digite uma nota entre 0 e 10:");
                novaNota = leitura.nextDouble();
            }


            // ATUALIZAR
            filmeEditar.setAno(novoAno);
            filmeEditar.setDuracao(novaDuracao);
            filmeEditar.setNota(novaNota);

            System.out.println("Filme atualizado!");

        } else {

            System.out.println("Filme não encontrado!");
        }
    }


    public static void alterarNota(Scanner leitura, ArrayList<Filme> filmes) {

        System.out.println("Digite o filme que deseja alterar a nota:");

        leitura.nextLine();

        String busca = leitura.nextLine();

        Filme filmeEditar = null;

        for (Filme f : filmes) {

            if (f.getNome().equalsIgnoreCase(busca)) {

                filmeEditar = f;
            }
        }

        if (filmeEditar != null) {

            System.out.println("Filme encontrado!");
            System.out.println("Nota atual: " + filmeEditar.getNota());

            System.out.println("Digite a nova nota:");

            while (!leitura.hasNextDouble()) {
                System.out.println("Digite uma nota válida:");
                leitura.next();
            }

            double novaNota = leitura.nextDouble();

            while (!Validador.notaValido(novaNota)) {
                System.out.println("Nota inválida! Digite uma nota entre 0 e 10:");
                novaNota = leitura.nextDouble();
            }

            filmeEditar.setNota(novaNota);

            System.out.println("Nota atualizada!");

        } else {

            System.out.println("Filme não encontrado!");
        }
    }
}