public class Filme {

    private String nome;
    private int ano;
    private int duracao;
    private double nota;

    public Filme(String nome, int ano, int duracao, double nota) {
        this.nome = nome;
        this.ano = ano;
        this.duracao = duracao;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public int getAno() {
        return ano;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Filme: " + nome +
                "\nAno: " + ano +
                "\nDuração: " + duracao + " minutos" +
                "\nNota: " + nota + "\n";
    }
}
