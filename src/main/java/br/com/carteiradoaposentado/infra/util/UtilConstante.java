package br.com.carteiradoaposentado.infra.util;

import br.com.carteiradoaposentado.infra.util.model.Constante;
import org.apache.commons.lang3.StringUtils;

public final class UtilConstante {

    private UtilConstante() { }

    public static <T> boolean isPresent(Constante<T> constante) {
        return constante != null && constante.getValor() != null;
    }

    public static <T> T getValor(Constante<T> constante) {
        return constante != null ? constante.getValor() : null;
    }

    public static <T> String getDescricao(Constante<T> constante) {
        return constante != null ? constante.getDescricao() : "";
    }

    public static Integer getValorInteger(Constante<Short> constante) {
        return constante != null ? ((Short) constante.getValor()).intValue() : null;
    }

    public static <T> Constante<T> requireNonNull(final Constante<T> obj, final String message) {
        if (isPresent(obj)) {
            return obj;
        } else if (StringUtils.isBlank(message)) {
            throw new NullPointerException("Constante não pode ser nula");
        } else {
            throw new NullPointerException(message);
        }
    }
}
