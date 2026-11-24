package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.CartaoVencidoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/** Cartao de credito, com numero e validade no formato {@code MM/AAAA}. */
public class Cartao extends MetodoPagamento {

    public static final String TIPO = "cartao";
    private static final long serialVersionUID = 1L;
    private static final Pattern VALIDADE = Pattern.compile("(0[1-9]|1[0-2])/[0-9]{4}");
    private static final DateTimeFormatter FORMATO_VALIDADE = DateTimeFormatter.ofPattern("MM/uuuu");

    private final String numero;
    private final String validade;

    public Cartao(String numero, String validade) {
        this.numero = Validador.obrigatorio(numero, CampoObrigatorio.NUMERO_CARTAO);
        this.validade = validarValidade(validade);
    }

    private static String validarValidade(String validade) {
        Validador.obrigatorio(validade, CampoObrigatorio.VALIDADE_CARTAO);
        if (!VALIDADE.matcher(validade).matches()) {
            throw new ValorInvalidoException(ValorRecusado.VALIDADE_CARTAO);
        }
        return validade;
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public String getDado(String atributo) {
        if (DadoPagamento.NUMERO_CARTAO.getAtributo().equals(atributo)) {
            return numero;
        }
        if (DadoPagamento.VALIDADE_CARTAO.getAtributo().equals(atributo)) {
            return validade;
        }
        return super.getDado(atributo);
    }

    /** O enunciado exige metodo de pagamento valido: cartao vencido nao libera a retirada. */
    @Override
    public void autorizarRetirada(LocalDateTime data) {
        if (YearMonth.parse(validade, FORMATO_VALIDADE).isBefore(YearMonth.from(data))) {
            throw new CartaoVencidoException();
        }
    }
}
