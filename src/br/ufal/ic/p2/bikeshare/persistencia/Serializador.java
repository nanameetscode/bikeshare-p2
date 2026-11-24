package br.ufal.ic.p2.bikeshare.persistencia;

import br.ufal.ic.p2.bikeshare.excecao.sistema.PersistenciaException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** Converte objetos para bytes e de volta com {@code java.io.Serializable}. */
public final class Serializador {

    private Serializador() {
    }

    public static byte[] paraBytes(Serializable objeto) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream saida = new ObjectOutputStream(bytes)) {
            saida.writeObject(objeto);
        } catch (IOException e) {
            throw new PersistenciaException("Nao foi possivel gravar o estado do sistema.", e);
        }
        return bytes.toByteArray();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deBytes(byte[] dados) {
        try (ObjectInputStream entrada = new ObjectInputStream(new ByteArrayInputStream(dados))) {
            return (T) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenciaException("Nao foi possivel ler o estado do sistema.", e);
        }
    }

    /** Copia profunda: o resultado nao compartilha nenhum objeto com o original. */
    public static <T extends Serializable> T copiar(T objeto) {
        return deBytes(paraBytes(objeto));
    }
}
