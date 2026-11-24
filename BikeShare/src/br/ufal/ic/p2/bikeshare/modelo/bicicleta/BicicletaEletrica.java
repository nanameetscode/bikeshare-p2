package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;

import java.math.BigDecimal;

/** Bicicleta com bateria: so pode ser retirada com carga acima do minimo configurado. */
public class BicicletaEletrica extends Bicicleta {

    private static final long serialVersionUID = 1L;
    private static final int CARGA_MINIMA = 0;
    private static final int CARGA_MAXIMA = 100;

    private final int nivelBateria;

    public BicicletaEletrica(String id, int nivelBateria) {
        super(id);
        if (nivelBateria < CARGA_MINIMA || nivelBateria > CARGA_MAXIMA) {
            throw new ValorInvalidoException("Nivel de bateria deve estar entre "
                    + CARGA_MINIMA + " e " + CARGA_MAXIMA + ".");
        }
        this.nivelBateria = nivelBateria;
    }

    @Override
    public String getTipo() {
        return TipoBicicleta.ELETRICA.getRotulo();
    }

    @Override
    public int getNivelBateria() {
        return nivelBateria;
    }

    @Override
    public boolean temBateriaSuficiente(int nivelMinimo) {
        return nivelBateria >= nivelMinimo;
    }

    @Override
    public BigDecimal calcularAdicional(long minutos, BigDecimal adicionalPorMinuto) {
        return adicionalPorMinuto.multiply(BigDecimal.valueOf(minutos));
    }
}
