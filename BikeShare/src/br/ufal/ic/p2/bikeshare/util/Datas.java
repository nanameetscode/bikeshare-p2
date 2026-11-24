package br.ufal.ic.p2.bikeshare.util;

import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/** Leitura e escrita de data e hora no formato dos testes ({@code 10/3/2025 8:00}). */
public final class Datas {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("d/M/uuuu H:mm").withResolverStyle(ResolverStyle.STRICT);

    private Datas() {
    }

    public static LocalDateTime ler(String data) {
        String texto = Validador.obrigatorio(data, "Data nao pode ser nula.").trim();
        try {
            return LocalDateTime.parse(texto, FORMATO);
        } catch (DateTimeParseException e) {
            throw new ValorInvalidoException("Data invalida.");
        }
    }

    public static String formatar(LocalDateTime data) {
        return data.format(FORMATO);
    }
}
