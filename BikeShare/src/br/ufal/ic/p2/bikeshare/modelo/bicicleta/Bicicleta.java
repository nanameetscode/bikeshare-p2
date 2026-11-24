package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.bicicleta.OperacaoBicicletaInvalidaException;

import java.io.Serializable;
import java.util.Optional;

/**
 * Bicicleta do sistema. A convencional usa o comportamento padrao desta classe;
 * a eletrica sobrescreve o que depende da bateria.
 */
public abstract class Bicicleta implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private EstadoBicicleta estado = EstadoBicicleta.DISPONIVEL;
    private String estacao;

    protected Bicicleta(String id) {
        this.id = id;
    }

    public abstract String getTipo();

    public int getNivelBateria() {
        throw new OperacaoBicicletaInvalidaException("Bicicleta nao possui bateria.");
    }

    public boolean temBateriaSuficiente(int nivelMinimo) {
        return true;
    }

    public boolean podeSerRetirada(int nivelMinimo) {
        return estado == EstadoBicicleta.DISPONIVEL && temBateriaSuficiente(nivelMinimo);
    }

    public void exigirDisponivel() {
        if (estado != EstadoBicicleta.DISPONIVEL) {
            throw new OperacaoBicicletaInvalidaException("Bicicleta nao esta disponivel.");
        }
    }

    public void estacionar(String idEstacao) {
        estacao = idEstacao;
    }

    public void sairDaEstacao() {
        estacao = null;
    }

    public String getId() {
        return id;
    }

    public EstadoBicicleta getEstado() {
        return estado;
    }

    public Optional<String> getEstacao() {
        return Optional.ofNullable(estacao);
    }
}
