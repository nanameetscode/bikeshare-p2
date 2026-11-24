package br.ufal.ic.p2.bikeshare.modelo.estacao;

import br.ufal.ic.p2.bikeshare.excecao.bicicleta.OperacaoBicicletaInvalidaException;
import br.ufal.ic.p2.bikeshare.excecao.estacao.EstacaoSemVagaException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.io.Serializable;
import java.util.ArrayList;

/** Ponto de retirada e devolucao, com um numero fixo de vagas. */
public class Estacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final String nome;
    private final String endereco;
    private final int capacidade;
    private final ArrayList<Bicicleta> bicicletas = new ArrayList<>();

    public Estacao(String id, String nome, String endereco, int capacidade) {
        this.id = id;
        this.nome = validarNome(nome);
        this.endereco = validarEndereco(endereco);
        this.capacidade = validarCapacidade(capacidade);
    }

    public static String validarNome(String nome) {
        return Validador.obrigatorio(nome, "Nome nao pode ser nulo.");
    }

    public static String validarEndereco(String endereco) {
        return Validador.obrigatorio(endereco, "Endereco nao pode ser nulo.");
    }

    public static int validarCapacidade(int capacidade) {
        if (capacidade <= 0) {
            throw new ValorInvalidoException("Capacidade deve ser positiva.");
        }
        return capacidade;
    }

    public void receber(Bicicleta bicicleta) {
        exigirVaga("Estacao sem vagas disponiveis.");
        bicicletas.add(bicicleta);
        bicicleta.estacionar(id);
    }

    public void liberar(Bicicleta bicicleta) {
        bicicletas.remove(bicicleta);
        bicicleta.sairDaEstacao();
    }

    public void exigirVaga(String mensagem) {
        if (getVagasLivres() <= 0) {
            throw new EstacaoSemVagaException(mensagem);
        }
    }

    public void exigirPresenca(Bicicleta bicicleta) {
        if (!bicicletas.contains(bicicleta)) {
            throw new OperacaoBicicletaInvalidaException("Bicicleta nao esta nesta estacao.");
        }
    }

    public int getVagasLivres() {
        return capacidade - bicicletas.size();
    }

    public long contarDisponiveis(int bateriaMinima) {
        return bicicletas.stream().filter(b -> b.podeSerRetirada(bateriaMinima)).count();
    }

    public String getId() {
        return id;
    }
}
