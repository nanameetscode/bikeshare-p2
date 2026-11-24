package br.ufal.ic.p2.bikeshare.persistencia;

import br.ufal.ic.p2.bikeshare.excecao.sistema.PersistenciaException;
import br.ufal.ic.p2.bikeshare.sistema.EstadoSistema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/** Unico ponto que le e grava o estado do sistema em disco (arquivo {@code .ser}). */
public class ArquivoEstado {

    private final Path arquivo;

    public ArquivoEstado(Path arquivo) {
        this.arquivo = arquivo;
    }

    /** Le o estado gravado; vazio se o sistema ainda nao foi encerrado nenhuma vez. */
    public Optional<EstadoSistema> carregar() {
        if (!Files.exists(arquivo)) {
            return Optional.empty();
        }
        try {
            return Optional.of(Serializador.deBytes(Files.readAllBytes(arquivo)));
        } catch (IOException e) {
            throw new PersistenciaException("Nao foi possivel ler o estado do sistema.", e);
        }
    }

    public void salvar(EstadoSistema estado) {
        try {
            Files.write(arquivo, Serializador.paraBytes(estado));
        } catch (IOException e) {
            throw new PersistenciaException("Nao foi possivel gravar o estado do sistema.", e);
        }
    }
}
