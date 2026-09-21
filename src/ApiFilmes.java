import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiFilmes {

    public static Filme buscarFilme(String nomeFilme) throws Exception {

        // Cria o cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Pega a chave da API através da variável de ambiente
        String chaveApi = System.getenv("OMDB_API_KEY");

        // Codifica o nome do filme para poder ser usado na URL
        // Exemplo: "Homem Aranha" -> "Homem+Aranha"
        String nomeCodificado = URLEncoder.encode(
                nomeFilme,
                StandardCharsets.UTF_8
        );

        // Cria a requisição para a API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://www.omdbapi.com/?t="
                                + nomeCodificado
                                + "&apikey=" + chaveApi
                ))
                .build();

        // Envia a requisição
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        // Mostra o código de status
        System.out.println("Status: " + response.statusCode());

        // Guarda a resposta da API
        String resposta = response.body();

        // Verifica se o filme não foi encontrado
        if (resposta.contains("\"Response\":\"False\"")) {
            System.out.println("Filme não encontrado.");
            return null;
        }

        // =========================
        // TÍTULO
        // =========================

        int inicio = resposta.indexOf("\"Title\":\"") + 9;
        int fim = resposta.indexOf("\"", inicio);

        String titulo = resposta.substring(inicio, fim);

        System.out.println("Título: " + titulo);


        // =========================
        // ANO
        // =========================

        int inicioAno = resposta.indexOf("\"Year\":\"") + 8;
        int fimAno = resposta.indexOf("\"", inicioAno);

        String ano = resposta.substring(inicioAno, fimAno);

        int anoInt = Integer.parseInt(ano);

        System.out.println("Ano: " + anoInt);


        // =========================
        // DURAÇÃO
        // =========================

        int inicioDuracao = resposta.indexOf("\"Runtime\":\"") + 11;
        int fimDuracao = resposta.indexOf("\"", inicioDuracao);

        String duracao = resposta.substring(inicioDuracao, fimDuracao);

        // Remove " min"
        String duracaoTexto = duracao.replace(" min", "");

        int duracaoInt = 0;

        // Verifica se a API não possui a duração
        if (!duracaoTexto.equals("N/A")) {
            duracaoInt = Integer.parseInt(duracaoTexto);
        }

        System.out.println("Duração: " + duracao);


        // =========================
        // NOTA
        // =========================

        int inicioNota = resposta.indexOf("\"imdbRating\":\"") + 14;
        int fimNota = resposta.indexOf("\"", inicioNota);

        String nota = resposta.substring(inicioNota, fimNota);

        double notaDouble = 0;

        // Verifica se a API não possui a nota
        if (!nota.equals("N/A")) {
            notaDouble = Double.parseDouble(nota);
        }

        System.out.println("Nota: " + notaDouble);


        // =========================
        // CRIANDO O FILME
        // =========================

        Filme filmeApi = new Filme(
                titulo,
                anoInt,
                duracaoInt,
                notaDouble
        );

        // Devolve o filme para o CadastroFilme
        return filmeApi;
    }
}