package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.excecao.plano.PlanoInvalidoException;

import java.io.Serializable;
import java.util.LinkedHashMap;

/** Planos disponiveis para contratacao, buscados pelo nome. */
public final class CatalogoPlanos implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LinkedHashMap<String, Plano> planos = new LinkedHashMap<>();
    private final Plano planoPadrao;

    public CatalogoPlanos(Plano planoPadrao) {
        this.planoPadrao = planoPadrao;
        adicionar(planoPadrao);
    }

    public void adicionar(Plano plano) {
        planos.put(plano.getNome(), plano);
    }

    public Plano buscar(String nome) {
        Plano plano = planos.get(nome);
        if (plano == null) {
            throw new PlanoInvalidoException();
        }
        return plano;
    }

    public Plano getPlanoPadrao() {
        return planoPadrao;
    }
}
