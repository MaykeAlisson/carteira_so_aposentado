package br.com.carteiradoaposentado.infra.util;

import br.com.carteiradoaposentado.infra.util.model.Constante;

public final class UtilEnum {

    private UtilEnum() { }

    public static <T extends Enum & Constante<S>, S> T getEnum(final Class<T> enumClass, final S value) {
        if (enumClass != null && value != null) {
            Enum[] var2 = (Enum[]) enumClass.getEnumConstants();
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Enum obj = var2[var4];
                if (((Constante) obj).getValor().equals(value)) {
                    return (T) obj;
                }
            }

            return null;
        } else {
            return null;
        }
    }
}
