package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.util.Validador;

/** Pagamento por chave pix. */
public class Pix extends MetodoPagamento {

    public static final String TIPO = "pix";
    private static final long serialVersionUID = 1L;

    private final String chave;

    public Pix(String chave) {
        this.chave = Validador.obrigatorio(chave, CampoObrigatorio.CHAVE_PIX);
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public String getDado(String atributo) {
        if (DadoPagamento.CHAVE_PIX.getAtributo().equals(atributo)) {
            return chave;
        }
        return super.getDado(atributo);
    }
}
