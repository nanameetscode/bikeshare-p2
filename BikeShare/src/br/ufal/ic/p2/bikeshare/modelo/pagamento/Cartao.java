package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.util.regex.Pattern;

/** Cartao de credito, com numero e validade no formato {@code MM/AAAA}. */
public class Cartao extends MetodoPagamento {

    public static final String TIPO = "cartao";
    private static final long serialVersionUID = 1L;
    private static final Pattern VALIDADE = Pattern.compile("(0[1-9]|1[0-2])/[0-9]{4}");

    private final String numero;
    private final String validade;

    public Cartao(String numero, String validade) {
        this.numero = Validador.obrigatorio(numero, "Numero do cartao nao pode ser nulo.");
        this.validade = validarValidade(validade);
    }

    private static String validarValidade(String validade) {
        Validador.obrigatorio(validade, "Validade do cartao nao pode ser nula.");
        if (!VALIDADE.matcher(validade).matches()) {
            throw new ValorInvalidoException("Validade do cartao invalida.");
        }
        return validade;
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public String getNumeroCartao() {
        return numero;
    }

    @Override
    public String getValidadeCartao() {
        return validade;
    }
}
