package br.ufal.ic.p2.bikeshare.modelo.estacao;

import br.ufal.ic.p2.bikeshare.excecao.bicicleta.BicicletaJaExisteException;
import br.ufal.ic.p2.bikeshare.excecao.bicicleta.OperacaoBicicletaInvalidaException;
import br.ufal.ic.p2.bikeshare.excecao.estacao.EstacaoSemVagaException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ponto de retirada e devolucao, com um numero fixo de vagas. E a unica fonte
 * de verdade sobre quais bicicletas estao em cada estacao.
 */
public class Estacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final String nome;
    private final String endereco;
    private final int capacidade;
    private final ArrayList<Bicicleta> bicicletas = new ArrayList<>();

    /** Valida os dados na ordem nome, endereco e capacidade (informada como texto). */
    public Estacao(String id, String nome, String endereco, String capacidade) {
        this.id = Validador.obrigatorio(id, "Identificacao da estacao nao pode ser nula.");
        this.nome = Validador.obrigatorio(nome, "Nome nao pode ser nulo.");
        this.endereco = Validador.obrigatorio(endereco, "Endereco nao pode ser nulo.");
        this.capacidade = validarCapacidade(capacidade);
    }

    private static int validarCapacidade(String capacidade) {
        int vagas = Validador.inteiro(capacidade,
                "Capacidade nao pode ser nula.", "Capacidade deve ser numerica.");
        if (vagas <= 0) {
            throw new ValorInvalidoException("Capacidade deve ser positiva.");
        }
        return vagas;
    }

    public void receber(Bicicleta bicicleta) {
        if (contem(bicicleta)) {
            throw new BicicletaJaExisteException();
        }
        exigirVaga("Estacao sem vagas disponiveis.");
        bicicletas.add(bicicleta);
    }

    public void liberar(Bicicleta bicicleta) {
        exigirPresenca(bicicleta);
        bicicletas.remove(bicicleta);
    }

    public boolean contem(Bicicleta bicicleta) {
        return bicicletas.contains(bicicleta);
    }

    public void exigirVaga(String mensagem) {
        if (getVagasLivres() <= 0) {
            throw new EstacaoSemVagaException(mensagem);
        }
    }

    public void exigirPresenca(Bicicleta bicicleta) {
        if (!contem(bicicleta)) {
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
