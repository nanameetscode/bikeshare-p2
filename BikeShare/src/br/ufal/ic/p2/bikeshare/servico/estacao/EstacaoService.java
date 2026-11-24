package br.ufal.ic.p2.bikeshare.servico.estacao;

import br.ufal.ic.p2.bikeshare.excecao.estacao.EstacaoNaoEncontradaException;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.sistema.Cadastro;
import br.ufal.ic.p2.bikeshare.sistema.EstadoSistema;
import br.ufal.ic.p2.bikeshare.util.Validador;

/** Cadastro de estacoes e consulta de disponibilidade. */
public class EstacaoService {

    private final EstadoSistema estado;

    public EstacaoService(EstadoSistema estado) {
        this.estado = estado;
    }

    public String criar(String nome, String endereco, String capacidade) {
        Estacao.validarNome(nome);
        Estacao.validarEndereco(endereco);
        int vagas = Estacao.validarCapacidade(Validador.inteiro(capacidade,
                "Capacidade nao pode ser nula.", "Capacidade deve ser numerica."));
        Estacao estacao = new Estacao(estacoes().gerarId(), nome, endereco, vagas);
        estacoes().adicionar(estacao.getId(), estacao);
        return estacao.getId();
    }

    public Estacao buscar(String id) {
        return estacoes().exigir(id, "Identificacao da estacao nao pode ser nula.",
                EstacaoNaoEncontradaException::new);
    }

    public String getVagasDisponiveis(String id) {
        return String.valueOf(buscar(id).getVagasLivres());
    }

    public String getBicicletasDisponiveis(String id) {
        int bateriaMinima = estado.getConfiguracao().getNivelMinimoBateria();
        return String.valueOf(buscar(id).contarDisponiveis(bateriaMinima));
    }

    private Cadastro<Estacao> estacoes() {
        return estado.getEstacoes();
    }
}
