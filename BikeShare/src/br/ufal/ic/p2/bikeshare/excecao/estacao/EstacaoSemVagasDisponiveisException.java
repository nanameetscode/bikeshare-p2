package br.ufal.ic.p2.bikeshare.excecao.estacao;

/** Lancada ao cadastrar bicicleta em estacao lotada. */
public class EstacaoSemVagasDisponiveisException extends EstacaoSemVagaException {

    private static final long serialVersionUID = 1L;

    public EstacaoSemVagasDisponiveisException() {
        super("Estacao sem vagas disponiveis.");
    }
}
