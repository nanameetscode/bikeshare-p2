package br.ufal.ic.p2.bikeshare.util;

import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoNuloException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Validacoes de entrada reaproveitadas pelo dominio e pelos servicos: campo obrigatorio,
 * leitura de numeros (decimais com virgula ou ponto) e formato de email.
 */
public final class Validador {

    private static final Pattern INTEIRO = Pattern.compile("-?[0-9]{1,18}");
    private static final Pattern DECIMAL = Pattern.compile("-?[0-9]+([,.][0-9]+)?");
    private static final Pattern EMAIL = Pattern.compile("[^@ ]+@[^@ ]+");

    private Validador() {
    }

    /** Devolve o valor se ele tiver algum caractere visivel; senao lanca o erro de campo nulo. */
    public static String obrigatorio(String valor, String mensagemNulo) {
        if (valor == null || valor.isBlank()) {
            throw new CampoNuloException(mensagemNulo);
        }
        return valor;
    }

    /** Le um inteiro; numeros que nao cabem em um {@code int} contam como nao numericos. */
    public static int inteiro(String valor, String mensagemNulo, String mensagemNaoNumerico) {
        String texto = obrigatorio(valor, mensagemNulo).trim();
        if (!INTEIRO.matcher(texto).matches()) {
            throw new ValorInvalidoException(mensagemNaoNumerico);
        }
        long numero = Long.parseLong(texto);
        if (numero < Integer.MIN_VALUE || numero > Integer.MAX_VALUE) {
            throw new ValorInvalidoException(mensagemNaoNumerico);
        }
        return (int) numero;
    }

    public static BigDecimal decimal(String valor, String mensagemNulo, String mensagemNaoNumerico) {
        String texto = obrigatorio(valor, mensagemNulo).trim();
        if (!DECIMAL.matcher(texto).matches()) {
            throw new ValorInvalidoException(mensagemNaoNumerico);
        }
        return new BigDecimal(texto.replace(',', '.'));
    }

    /** Le um valor em dinheiro informado pelo usuario (recarga, penalidade). */
    public static BigDecimal valor(String valor) {
        return decimal(valor, "Valor nao pode ser nulo.", "Valor deve ser numerico.");
    }

    public static BigDecimal naoNegativo(BigDecimal numero, String mensagem) {
        if (numero == null || numero.signum() < 0) {
            throw new ValorInvalidoException(mensagem);
        }
        return numero;
    }

    public static void exigirIgual(String informado, String esperado, String mensagem) {
        if (!esperado.equals(informado)) {
            throw new ValorInvalidoException(mensagem);
        }
    }

    public static String email(String valor) {
        String email = obrigatorio(valor, "Email nao pode ser nulo.");
        if (!EMAIL.matcher(email).matches()) {
            throw new ValorInvalidoException("Email invalido.");
        }
        return email;
    }
}
