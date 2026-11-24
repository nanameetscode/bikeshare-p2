package br.ufal.ic.p2.bikeshare.servico.aluguel;

import br.ufal.ic.p2.bikeshare.modelo.aluguel.Aluguel;
import br.ufal.ic.p2.bikeshare.modelo.aluguel.EstadoAluguel;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.servico.bicicleta.BicicletaService;
import br.ufal.ic.p2.bikeshare.servico.estacao.EstacaoService;
import br.ufal.ic.p2.bikeshare.servico.usuario.UsuarioService;
import br.ufal.ic.p2.bikeshare.sistema.Configuracao;
import br.ufal.ic.p2.bikeshare.util.Datas;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Devolucao de bicicletas. Conforme a duracao e o lugar, o aluguel termina expirado
 * (passou do tempo maximo), cancelado (mesma estacao dentro da carencia) ou finalizado.
 */
public class DevolucaoService {

    private final Configuracao cfg;
    private final AluguelService alugueis;
    private final UsuarioService usuarios;
    private final EstacaoService estacoes;
    private final BicicletaService bicicletas;

    public DevolucaoService(Configuracao cfg, AluguelService alugueis, UsuarioService usuarios,
                            EstacaoService estacoes, BicicletaService bicicletas) {
        this.cfg = cfg;
        this.alugueis = alugueis;
        this.usuarios = usuarios;
        this.estacoes = estacoes;
        this.bicicletas = bicicletas;
    }

    public void devolver(String idAluguel, String idEstacao, String data) {
        Aluguel aluguel = alugueis.buscar(idAluguel);
        Estacao destino = estacoes.buscar(idEstacao);
        LocalDateTime fim = Datas.ler(data);
        aluguel.exigirEmAndamento();
        long minutos = aluguel.minutosAte(fim);
        destino.exigirVaga("Estacao cheia.");
        Usuario usuario = usuarios.buscar(aluguel.getUsuario());
        Bicicleta bicicleta = bicicletas.buscar(aluguel.getBicicleta());
        if (minutos > cfg.getMinutosMaximoAluguel()) {
            expirar(aluguel, destino, fim, usuario, bicicleta);
        } else if (aluguel.comecouEm(destino.getId()) && minutos <= cfg.getMinutosCarencia()) {
            aluguel.encerrar(EstadoAluguel.CANCELADO, destino.getId(), fim, BigDecimal.ZERO);
            guardar(bicicleta, destino, usuario);
        } else {
            finalizar(aluguel, destino, fim, usuario, bicicleta, minutos);
        }
    }

    private void expirar(Aluguel aluguel, Estacao destino, LocalDateTime fim, Usuario usuario, Bicicleta bicicleta) {
        aluguel.encerrar(EstadoAluguel.EXPIRADO, destino.getId(), fim, BigDecimal.ZERO);
        usuario.registrarPenalidade(cfg.getValorPenalidadeAtraso());
        bicicleta.enviarParaManutencao();
        destino.receber(bicicleta);
        usuario.encerrarAluguel();
    }

    private void finalizar(Aluguel aluguel, Estacao destino, LocalDateTime fim,
                           Usuario usuario, Bicicleta bicicleta, long minutos) {
        BigDecimal valor = usuario.getAssinatura().getPlano().calcularTarifa(minutos)
                .add(bicicleta.calcularAdicional(minutos, cfg.getAdicionalMinutoEletrica()));
        usuario.pagar(valor);
        aluguel.encerrar(EstadoAluguel.FINALIZADO, destino.getId(), fim, valor);
        guardar(bicicleta, destino, usuario);
    }

    private static void guardar(Bicicleta bicicleta, Estacao destino, Usuario usuario) {
        bicicleta.devolver();
        destino.receber(bicicleta);
        usuario.encerrarAluguel();
    }
}
