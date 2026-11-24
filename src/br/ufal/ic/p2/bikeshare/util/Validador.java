package br.ufal.ic.p2.bikeshare.util;

import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoNuloException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Validacoes de entrada reaproveitadas por todos os servicos: campo obrigatorio,
 * leitura de numeros decimais (com virgula ou ponto) e formato de email.
 */
public final class Validador {

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

    public static BigDecimal decimal(String valor, String mensagemNulo, String mensagemNaoNumerico) {
        String texto = obrigatorio(valor, mensagemNulo).trim();
        if (!DECIMAL.matcher(texto).matches()) {
            throw new ValorInvalidoException(mensagemNaoNumerico);
        }
        return new BigDecimal(texto.replace(',', '.'));
    }

    public static String email(String valor) {
        String email = obrigatorio(valor, "Email nao pode ser nulo.");
        if (!EMAIL.matcher(email).matches()) {
            throw new ValorInvalidoException("Email invalido.");
        }
        return email;
    }
}
