package br.ufal.ic.p2.bikeshare.servico.estacao;

import br.ufal.ic.p2.bikeshare.excecao.estacao.EstacaoNaoEncontradaException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.sistema.Cadastro;
import br.ufal.ic.p2.bikeshare.sistema.Configuracao;
import br.ufal.ic.p2.bikeshare.sistema.EstadoSistema;

import java.util.Optional;

/** Cadastro de estacoes e consulta de disponibilidade. */
public class EstacaoService {

    private final EstadoSistema estado;
    private final Configuracao configuracao;

    public EstacaoService(EstadoSistema estado, Configuracao configuracao) {
        this.estado = estado;
        this.configuracao = configuracao;
    }

    public String criar(String nome, String endereco, String capacidade) {
        Estacao estacao = new Estacao(estacoes().gerarId(), nome, endereco, capacidade);
        estacoes().adicionar(estacao.getId(), estacao);
        return estacao.getId();
    }

    public Estacao buscar(String id) {
        return estacoes().exigir(id, "Identificacao da estacao nao pode ser nula.",
                EstacaoNaoEncontradaException::new);
    }

    /** Estacao em que a bicicleta esta estacionada, se estiver em alguma. */
    public Optional<Estacao> ondeEsta(Bicicleta bicicleta) {
        return estacoes().valores().stream().filter(e -> e.contem(bicicleta)).findFirst();
    }

    public String getVagasDisponiveis(String id) {
        return String.valueOf(buscar(id).getVagasLivres());
    }

    public String getBicicletasDisponiveis(String id) {
        return String.valueOf(buscar(id).contarDisponiveis(configuracao.getNivelMinimoBateria()));
    }

    private Cadastro<Estacao> estacoes() {
        return estado.getEstacoes();
    }
}
