package br.ufal.ic.p2.bikeshare.sistema;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * Colecao de entidades indexadas pelo identificador, com o contador que gera
 * ids sequenciais ({@code U1}, {@code E1}...). O contador nunca volta atras,
 * entao ids removidos nao sao reaproveitados.
 */
public class Cadastro<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LinkedHashMap<String, T> itens = new LinkedHashMap<>();
    private final String prefixo;
    private int contador;

    public Cadastro(String prefixo) {
        this.prefixo = prefixo;
    }

    public String gerarId() {
        contador++;
        return prefixo + contador;
    }

    public void adicionar(String id, T item) {
        itens.put(id, item);
    }

    public Optional<T> buscar(String id) {
        return Optional.ofNullable(itens.get(id));
    }

    public void remover(String id) {
        itens.remove(id);
    }

    public Collection<T> valores() {
        return Collections.unmodifiableCollection(itens.values());
    }
}
