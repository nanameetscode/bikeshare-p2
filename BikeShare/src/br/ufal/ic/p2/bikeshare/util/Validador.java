package br.ufal.ic.p2.bikeshare.util;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * Validacoes de entrada reaproveitadas pelo dominio e pelos servicos: campo obrigatorio,
 * leitura de numeros (decimais com virgula ou ponto) e formato de email. As mensagens
 * ficam nos enums {@link CampoObrigatorio} e {@link ValorRecusado}.
 */
public final class Validador {

    private static final Pattern INTEIRO = Pattern.compile("-?[0-9]{1,18}");
    private static final Pattern DECIMAL = Pattern.compile("-?[0-9]+([,.][0-9]+)?");
    private static final Pattern EMAIL = Pattern.compile("[^@ ]+@[^@ ]+");

    private Validador() {
    }

    /** Devolve o valor se ele tiver algum caractere visivel; senao recusa com o erro do campo. */
    public static String obrigatorio(String valor, CampoObrigatorio campo) {
        return obrigatorio(valor, campo::erro);
    }

    public static String obrigatorio(String valor, Supplier<? extends BikeShareException> erro) {
        if (valor == null || valor.isBlank()) {
            throw erro.get();
        }
        return valor;
    }

    public static int inteiro(String valor, CampoObrigatorio nulo, ValorRecusado naoNumerico) {
        return inteiro(valor, nulo::erro, naoNumerico::erro);
    }

    /** Le um inteiro; numeros que nao cabem em um {@code int} contam como nao numericos. */
    public static int inteiro(String valor, Supplier<? extends BikeShareException> nulo,
                              Supplier<? extends BikeShareException> naoNumerico) {
        String texto = obrigatorio(valor, nulo).trim();
        if (!INTEIRO.matcher(texto).matches()) {
            throw naoNumerico.get();
        }
        long numero = Long.parseLong(texto);
        if (numero < Integer.MIN_VALUE || numero > Integer.MAX_VALUE) {
            throw naoNumerico.get();
        }
        return (int) numero;
    }

    public static BigDecimal decimal(String valor, Supplier<? extends BikeShareException> nulo,
                                     Supplier<? extends BikeShareException> naoNumerico) {
        String texto = obrigatorio(valor, nulo).trim();
        if (!DECIMAL.matcher(texto).matches()) {
            throw naoNumerico.get();
        }
        return new BigDecimal(texto.replace(',', '.'));
    }

    /** Le um valor em dinheiro, ja arredondado para centavos. */
    public static BigDecimal dinheiro(String valor, CampoObrigatorio nulo, ValorRecusado naoNumerico) {
        return Formato.centavos(decimal(valor, nulo::erro, naoNumerico::erro));
    }

    /** Le um valor em dinheiro informado pelo usuario (recarga, penalidade). */
    public static BigDecimal valor(String valor) {
        return dinheiro(valor, CampoObrigatorio.VALOR, ValorRecusado.VALOR_NAO_NUMERICO);
    }

    public static BigDecimal naoNegativo(BigDecimal numero, ValorRecusado motivo) {
        return naoNegativo(numero, motivo::erro);
    }

    public static BigDecimal naoNegativo(BigDecimal numero, Supplier<? extends BikeShareException> erro) {
        if (numero == null || numero.signum() < 0) {
            throw erro.get();
        }
        return numero;
    }

    public static void exigirIgual(String informado, String esperado, ValorRecusado motivo) {
        if (!esperado.equals(informado)) {
            throw motivo.erro();
        }
    }

    public static String email(String valor) {
        String email = obrigatorio(valor, CampoObrigatorio.EMAIL);
        if (!EMAIL.matcher(email).matches()) {
            throw ValorRecusado.EMAIL.erro();
        }
        return email;
    }
}
