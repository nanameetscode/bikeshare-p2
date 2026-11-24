package br.ufal.ic.p2.bikeshare.sistema;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;
import br.ufal.ic.p2.bikeshare.excecao.sistema.IdentificadorDuplicadoException;
import br.ufal.ic.p2.bikeshare.excecao.sistema.IdentificadorInexistenteException;
import br.ufal.ic.p2.bikeshare.excecao.sistema.RegistroInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.function.Supplier;

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

    /** Guarda o item; recusa item nulo, id vazio ou id ja usado, para o cadastro nunca ficar inconsistente. */
    public void adicionar(String id, T item) {
        if (item == null || id == null || id.isBlank()) {
            throw new RegistroInvalidoException();
        }
        if (contem(id)) {
            throw new IdentificadorDuplicadoException();
        }
        itens.put(id, item);
    }

    public Optional<T> buscar(String id) {
        return Optional.ofNullable(itens.get(id));
    }

    /** Busca pelo id, recusando id vazio ou inexistente com os erros informados. */
    public T exigir(String id, CampoObrigatorio idNulo, Supplier<? extends BikeShareException> naoExiste) {
        Validador.obrigatorio(id, idNulo);
        return buscar(id).orElseThrow(naoExiste);
    }

    public boolean contem(String id) {
        return itens.containsKey(id);
    }

    public void remover(String id) {
        if (!contem(id)) {
            throw new IdentificadorInexistenteException();
        }
        itens.remove(id);
    }

    public Collection<T> valores() {
        return Collections.unmodifiableCollection(itens.values());
    }
}
