package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.bicicleta.OperacaoBicicletaInvalidaException;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bicicleta do sistema. A convencional usa o comportamento padrao desta classe;
 * a eletrica sobrescreve o que depende da bateria. Em qual estacao ela esta
 * e informacao da propria estacao.
 */
public abstract class Bicicleta implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private EstadoBicicleta estado = EstadoBicicleta.DISPONIVEL;

    protected Bicicleta(String id) {
        this.id = Validador.obrigatorio(id, "Identificacao da bicicleta nao pode ser nula.");
    }

    public abstract String getTipo();

    public int getNivelBateria() {
        throw new OperacaoBicicletaInvalidaException("Bicicleta nao possui bateria.");
    }

    public boolean temBateriaSuficiente(int nivelMinimo) {
        return true;
    }

    public BigDecimal calcularAdicional(long minutos, BigDecimal adicionalPorMinuto) {
        return BigDecimal.ZERO;
    }

    public boolean podeSerRetirada(int nivelMinimo) {
        return estado.estaDisponivel() && temBateriaSuficiente(nivelMinimo);
    }

    public void exigirDisponivel() {
        estado.exigirDisponivel();
    }

    public void exigirBateria(int nivelMinimo) {
        if (!temBateriaSuficiente(nivelMinimo)) {
            throw new OperacaoBicicletaInvalidaException("Nivel de bateria insuficiente.");
        }
    }

    public void retirar() {
        exigirDisponivel();
        estado = EstadoBicicleta.EM_USO;
    }

    public void devolver() {
        estado = EstadoBicicleta.DISPONIVEL;
    }

    public void enviarParaManutencao() {
        estado = EstadoBicicleta.EM_MANUTENCAO;
    }

    public String getId() {
        return id;
    }

    public EstadoBicicleta getEstado() {
        return estado;
    }
}
