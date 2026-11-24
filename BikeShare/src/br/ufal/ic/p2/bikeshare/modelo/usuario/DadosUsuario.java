package br.ufal.ic.p2.bikeshare.modelo.usuario;

/** Dados de acesso comuns a qualquer usuario, agrupados para o cadastro. */
public record DadosUsuario(String nome, String email, String senha) {
}
