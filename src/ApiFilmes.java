// Importa as classes necessárias para fazer a requisição HTTP
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.ArrayList;


public class ApiFilmes {

    public static void main(String[] args) throws Exception {

        // Cria o Scanner para ler o nome do filme digitado
        Scanner leitura = new Scanner(System.in);

        // Cria uma lista para guardar os filmes
        ArrayList<Filme> filmes = new ArrayList<>();

        // Pergunta o nome do filme
        System.out.println("Digite o nome do filme:");
        String nomeFilme = leitura.nextLine();


        // Cria o cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        String chaveApi = System.getenv("OMDB_API_KEY");
        // Cria a requisição para a API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://www.omdbapi.com/?t="
                                + nomeFilme
                                +  "&apikey=" + chaveApi
                ))
                .build();


        // Envia a requisição para a API
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );


        // Mostra o código de status da resposta
        System.out.println("Status: " + response.statusCode());


        // Guarda a resposta da API em uma String
        String resposta = response.body();

        System.out.println("Resposta da API:");
        System.out.println(resposta);


        // =========================
        // TÍTULO
        // =========================

        // Procura onde começa o valor de Title
        int inicio = resposta.indexOf("\"Title\":\"") + 9;

        // Procura onde termina o valor do título
        int fim = resposta.indexOf("\"", inicio);

        // Pega somente o nome do filme
        String titulo = resposta.substring(inicio, fim);

        System.out.println("Título: " + titulo);


        // =========================
        // ANO
        // =========================

        // Procura onde começa o valor de Year
        int inicioAno = resposta.indexOf("\"Year\":\"") + 8;

        // Procura onde termina o ano
        int fimAno = resposta.indexOf("\"", inicioAno);

        // Pega somente o ano
        String ano = resposta.substring(inicioAno, fimAno);

        // Transforma o ano de String para int
        int anoInt = Integer.parseInt(ano);

        System.out.println("Ano: " + anoInt);


        // =========================
        // DURAÇÃO
        // =========================

        // Procura onde começa o valor de Runtime
        int inicioDuracao = resposta.indexOf("\"Runtime\":\"") + 11;

        // Procura onde termina a duração
        int fimDuracao = resposta.indexOf("\"", inicioDuracao);

        // Pega a duração completa
        // Exemplo: "126 min"
        String duracao = resposta.substring(inicioDuracao, fimDuracao);

        // Remove o " min"
        // "126 min" vira "126"
        String duracaoTexto = duracao.replace(" min", "");

        // Transforma de String para int
        int duracaoInt = Integer.parseInt(duracaoTexto);

        System.out.println("Duração: " + duracao);


        // =========================
        // NOTA
        // =========================

        // Procura onde começa o valor de imdbRating
        int inicioNota = resposta.indexOf("\"imdbRating\":\"") + 14;

        // Procura onde termina a nota
        int fimNota = resposta.indexOf("\"", inicioNota);

        // Pega somente a nota
        String nota = resposta.substring(inicioNota, fimNota);

        // Transforma de String para double
        double notaDouble = Double.parseDouble(nota);

        System.out.println("Nota: " + notaDouble);


        // =========================
        // CRIANDO O FILME
        // =========================

        // Cria um objeto Filme usando os dados da API
        Filme filmeApi = new Filme(
                titulo,
                anoInt,
                duracaoInt,
                notaDouble
        );


        // Adiciona o filme dentro do ArrayList
        filmes.add(filmeApi);


        // Mostra o filme criado
        System.out.println(filmeApi);


        // Fecha o Scanner
        leitura.close();
    }
}