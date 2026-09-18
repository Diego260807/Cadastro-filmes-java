public class Validador {

    public static boolean anoValido(int ano) {
        return ano >= 1800 && ano <= 2026;
    }

    public static boolean duracaoValido(int duracao) {
        return duracao > 0;
    }

    public static boolean notaValido(double nota) {
        return nota >= 0 && nota <= 10;
    }

    public static boolean nomeValido(String nome) {
        return nome.trim().length() > 0;
    }
}