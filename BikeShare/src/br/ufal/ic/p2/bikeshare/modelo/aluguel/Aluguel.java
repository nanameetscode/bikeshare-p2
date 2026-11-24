package br.ufal.ic.p2.bikeshare.modelo.aluguel;

import br.ufal.ic.p2.bikeshare.excecao.aluguel.DevolucaoAntesDaRetiradaException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoNuloException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;
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
        this.id = Validador.obrigatorio(id, CampoObrigatorio.ID_ALUGUEL);
        this.usuario = Validador.obrigatorio(usuario, CampoObrigatorio.ID_USUARIO);
        this.bicicleta = Validador.obrigatorio(bicicleta, CampoObrigatorio.ID_BICICLETA);
        this.estacaoOrigem = Validador.obrigatorio(estacaoOrigem, CampoObrigatorio.ID_ESTACAO);
        this.inicio = exigirData(inicio);
    }

    private static LocalDateTime exigirData(LocalDateTime data) {
        if (data == null) {
            throw new CampoNuloException(CampoObrigatorio.DATA);
        }
        return data;
    }

    public long minutosAte(LocalDateTime momento) {
        if (momento.isBefore(inicio)) {
            throw new DevolucaoAntesDaRetiradaException();
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
        estacaoDestino = Validador.obrigatorio(destino, CampoObrigatorio.ID_ESTACAO);
        valorCobrado = Validador.naoNegativo(valor, ValorRecusado.VALOR_NEGATIVO);
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
