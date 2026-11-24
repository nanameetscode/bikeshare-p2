package br.ufal.ic.p2.bikeshare.modelo.aluguel;

import br.ufal.ic.p2.bikeshare.excecao.aluguel.EstadoAluguelInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoNuloException;
import br.ufal.ic.p2.bikeshare.util.Validador;

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
        this.id = Validador.obrigatorio(id, "Identificacao do aluguel nao pode ser nula.");
        this.usuario = Validador.obrigatorio(usuario, "Identificacao do usuario nao pode ser nula.");
        this.bicicleta = Validador.obrigatorio(bicicleta, "Identificacao da bicicleta nao pode ser nula.");
        this.estacaoOrigem = Validador.obrigatorio(estacaoOrigem, "Identificacao da estacao nao pode ser nula.");
        this.inicio = exigirData(inicio);
    }

    private static LocalDateTime exigirData(LocalDateTime data) {
        if (data == null) {
            throw new CampoNuloException("Data nao pode ser nula.");
        }
        return data;
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

    public void finalizar(String destino, LocalDateTime momento, BigDecimal valor) {
        encerrar(EstadoAluguel.FINALIZADO, destino, momento, valor);
    }

    /** Devolucao na mesma estacao dentro da carencia: nada e cobrado. */
    public void cancelar(String destino, LocalDateTime momento) {
        encerrar(EstadoAluguel.CANCELADO, destino, momento, BigDecimal.ZERO);
    }

    /** Devolucao depois do tempo maximo: a tarifa nao e cobrada (o usuario recebe multa). */
    public void expirar(String destino, LocalDateTime momento) {
        encerrar(EstadoAluguel.EXPIRADO, destino, momento, BigDecimal.ZERO);
    }

    private void encerrar(EstadoAluguel novoEstado, String destino, LocalDateTime momento, BigDecimal valor) {
        estado.exigirEmAndamento();
        minutosAte(exigirData(momento));
        estacaoDestino = Validador.obrigatorio(destino, "Identificacao da estacao nao pode ser nula.");
        valorCobrado = Validador.naoNegativo(valor, "Valor nao pode ser negativo.");
        fim = momento;
        estado = novoEstado;
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
