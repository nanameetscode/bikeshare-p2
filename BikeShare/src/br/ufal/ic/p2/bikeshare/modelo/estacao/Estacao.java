package br.ufal.ic.p2.bikeshare.modelo.estacao;

import br.ufal.ic.p2.bikeshare.excecao.bicicleta.BicicletaForaDaEstacaoException;
import br.ufal.ic.p2.bikeshare.excecao.bicicleta.BicicletaJaExisteException;
import br.ufal.ic.p2.bikeshare.excecao.estacao.EstacaoSemVagaException;
import br.ufal.ic.p2.bikeshare.excecao.estacao.EstacaoSemVagasDisponiveisException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Supplier;

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
        this.id = Validador.obrigatorio(id, CampoObrigatorio.ID_ESTACAO);
        this.nome = Validador.obrigatorio(nome, CampoObrigatorio.NOME);
        this.endereco = Validador.obrigatorio(endereco, CampoObrigatorio.ENDERECO);
        this.capacidade = validarCapacidade(capacidade);
    }

    private static int validarCapacidade(String capacidade) {
        int vagas = Validador.inteiro(capacidade,
                CampoObrigatorio.CAPACIDADE, ValorRecusado.CAPACIDADE_NAO_NUMERICA);
        if (vagas <= 0) {
            throw new ValorInvalidoException(ValorRecusado.CAPACIDADE_NAO_POSITIVA);
        }
        return vagas;
    }

    public void receber(Bicicleta bicicleta) {
        if (contem(bicicleta)) {
            throw new BicicletaJaExisteException();
        }
        exigirVaga(EstacaoSemVagasDisponiveisException::new);
        bicicletas.add(bicicleta);
    }

    public void liberar(Bicicleta bicicleta) {
        exigirPresenca(bicicleta);
        bicicletas.remove(bicicleta);
    }

    public boolean contem(Bicicleta bicicleta) {
        return bicicletas.contains(bicicleta);
    }

    /** Recusa com o erro informado, que muda conforme a operacao (cadastro ou devolucao). */
    public void exigirVaga(Supplier<? extends EstacaoSemVagaException> erro) {
        if (getVagasLivres() <= 0) {
            throw erro.get();
        }
    }

    public void exigirPresenca(Bicicleta bicicleta) {
        if (!contem(bicicleta)) {
            throw new BicicletaForaDaEstacaoException();
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
