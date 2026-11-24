package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.bicicleta.BicicletaSemBateriaException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.util.Arrays;

/** Tipos de bicicleta aceitos no cadastro; cada um sabe criar a sua bicicleta. */
public enum TipoBicicleta {

    CONVENCIONAL("convencional") {
        @Override
        public Bicicleta criar(String id, String nivelBateria) {
            if (nivelBateria != null) {
                throw new BicicletaSemBateriaException();
            }
            return new BicicletaConvencional(id);
        }
    },

    ELETRICA("eletrica") {
        @Override
        public Bicicleta criar(String id, String nivelBateria) {
            int nivel = Validador.inteiro(nivelBateria,
                    CampoObrigatorio.NIVEL_BATERIA, ValorRecusado.NIVEL_BATERIA_NAO_NUMERICO);
            return new BicicletaEletrica(id, nivel);
        }
    };

    private final String rotulo;

    TipoBicicleta(String rotulo) {
        this.rotulo = rotulo;
    }

    /** Cria a bicicleta; {@code nivelBateria} e nulo quando o comando nao informou bateria. */
    public abstract Bicicleta criar(String id, String nivelBateria);

    public static TipoBicicleta deRotulo(String rotulo) {
        return Arrays.stream(values())
                .filter(tipo -> tipo.rotulo.equals(rotulo))
                .findFirst()
                .orElseThrow(() -> new ValorInvalidoException(ValorRecusado.TIPO));
    }

    public String getRotulo() {
        return rotulo;
    }
}
