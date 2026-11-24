package br.ufal.ic.p2.bikeshare.persistencia;

import br.ufal.ic.p2.bikeshare.excecao.sistema.GravacaoEstadoException;
import br.ufal.ic.p2.bikeshare.excecao.sistema.LeituraEstadoException;
import br.ufal.ic.p2.bikeshare.sistema.EstadoSistema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
            return Optional.of(Serializador.deBytes(Files.readAllBytes(arquivo), EstadoSistema.class));
        } catch (IOException e) {
            throw new LeituraEstadoException(e);
        }
    }

    /** Grava primeiro num arquivo temporario: se falhar no meio, o arquivo anterior continua inteiro. */
    public void salvar(EstadoSistema estado) {
        Path temporario = arquivo.resolveSibling(arquivo.getFileName() + ".tmp");
        try {
            Files.write(temporario, Serializador.paraBytes(estado));
            Files.move(temporario, arquivo, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            throw new GravacaoEstadoException(e);
        }
    }
}
