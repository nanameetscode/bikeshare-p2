package br.ufal.ic.p2.bikeshare.excecao.usuario;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** O email ja pertence a outro usuario (sem diferenciar maiusculas de minusculas). */
public class EmailJaCadastradoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public EmailJaCadastradoException() {
        super("Email ja cadastrado.");
    }
}
