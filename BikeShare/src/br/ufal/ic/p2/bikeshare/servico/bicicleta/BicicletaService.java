package br.ufal.ic.p2.bikeshare.servico.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.bicicleta.BicicletaJaExisteException;
import br.ufal.ic.p2.bikeshare.excecao.bicicleta.BicicletaNaoEncontradaException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.TipoBicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.servico.estacao.EstacaoService;
import br.ufal.ic.p2.bikeshare.sistema.Cadastro;
import br.ufal.ic.p2.bikeshare.sistema.EstadoSistema;
import br.ufal.ic.p2.bikeshare.util.Validador;

/** Entrada e saida de bicicletas nas estacoes e consulta dos seus dados. */
public class BicicletaService {

    private final EstadoSistema estado;
    private final EstacaoService estacoes;

    public BicicletaService(EstadoSistema estado, EstacaoService estacoes) {
        this.estado = estado;
        this.estacoes = estacoes;
    }

    /** Cadastra a bicicleta na estacao; {@code nivelBateria} e nulo quando nao foi informado. */
    public void adicionar(String idEstacao, String idBicicleta, String tipo, String nivelBateria) {
        Estacao estacao = estacoes.buscar(idEstacao);
        Validador.obrigatorio(idBicicleta, "Identificacao da bicicleta nao pode ser nula.");
        if (bicicletas().contem(idBicicleta)) {
            throw new BicicletaJaExisteException();
        }
        Bicicleta bicicleta = TipoBicicleta.deRotulo(tipo).criar(idBicicleta, nivelBateria);
        estacao.receber(bicicleta);
        bicicletas().adicionar(idBicicleta, bicicleta);
    }

    public void remover(String idEstacao, String idBicicleta) {
        Estacao estacao = estacoes.buscar(idEstacao);
        Bicicleta bicicleta = buscar(idBicicleta);
        estacao.exigirPresenca(bicicleta);
        bicicleta.exigirDisponivel();
        estacao.liberar(bicicleta);
        bicicletas().remover(idBicicleta);
    }

    public Bicicleta buscar(String id) {
        return bicicletas().exigir(id, "Identificacao da bicicleta nao pode ser nula.",
                BicicletaNaoEncontradaException::new);
    }

    public String getAtributo(String id, String atributo) {
        return AtributosBicicleta.ler(buscar(id), atributo);
    }

    public String getEstado(String id) {
        return buscar(id).getEstado().getRotulo();
    }

    private Cadastro<Bicicleta> bicicletas() {
        return estado.getBicicletas();
    }
}
