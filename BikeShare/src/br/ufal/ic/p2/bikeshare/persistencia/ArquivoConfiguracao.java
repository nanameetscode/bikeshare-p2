package br.ufal.ic.p2.bikeshare.persistencia;

import br.ufal.ic.p2.bikeshare.excecao.sistema.PersistenciaException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Le os parametros do arquivo {@code .properties}; sem o arquivo, valem os padroes. */
public final class ArquivoConfiguracao {

    private ArquivoConfiguracao() {
    }

    public static Properties carregar(Path arquivo) {
        Properties valores = new Properties();
        if (!Files.exists(arquivo)) {
            return valores;
        }
        try (Reader leitor = Files.newBufferedReader(arquivo)) {
            valores.load(leitor);
        } catch (IOException e) {
            throw new PersistenciaException("Nao foi possivel ler a configuracao do sistema.", e);
        }
        return valores;
    }
}
