package br.ufal.ic.p2.bikeshare.modelo.aluguel;

import br.ufal.ic.p2.bikeshare.excecao.aluguel.EstadoAluguelInvalidoException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Uma corrida: da retirada da bicicleta ate a devolucao. Fim, destino e valor
 * so existem depois que o aluguel e encerrado.
 */
public class Aluguel implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final String usuario;
    private final String bicicleta;
    private final String estacaoOrigem;
    private final LocalDateTime inicio;
    private EstadoAluguel estado = EstadoAluguel.EM_ANDAMENTO;
    private LocalDateTime fim;
    private String estacaoDestino;
    private BigDecimal valorCobrado;

    public Aluguel(String id, String usuario, String bicicleta, String estacaoOrigem, LocalDateTime inicio) {
        this.id = id;
        this.usuario = usuario;
        this.bicicleta = bicicleta;
        this.estacaoOrigem = estacaoOrigem;
        this.inicio = inicio;
    }

    public long minutosAte(LocalDateTime momento) {
        if (momento.isBefore(inicio)) {
            throw new EstadoAluguelInvalidoException("Data de devolucao anterior a data de retirada.");
        }
        return Duration.between(inicio, momento).toMinutes();
    }

    public boolean comecouEm(String estacao) {
        return estacaoOrigem.equals(estacao);
    }

    public void encerrar(EstadoAluguel novoEstado, String destino, LocalDateTime momento, BigDecimal valor) {
        estado.exigirEmAndamento();
        estado = novoEstado;
        estacaoDestino = destino;
        fim = momento;
        valorCobrado = valor;
    }

    public void exigirEmAndamento() {
        estado.exigirEmAndamento();
    }

    public String getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getBicicleta() {
        return bicicleta;
    }

    public String getEstacaoOrigem() {
        return estacaoOrigem;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public EstadoAluguel getEstado() {
        return estado;
    }

    public LocalDateTime getFim() {
        estado.exigirEncerrado();
        return fim;
    }

    public String getEstacaoDestino() {
        estado.exigirEncerrado();
        return estacaoDestino;
    }

    public BigDecimal getValorCobrado() {
        estado.exigirEncerrado();
        return valorCobrado;
    }
}
