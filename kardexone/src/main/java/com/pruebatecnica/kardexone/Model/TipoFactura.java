package com.pruebatecnica.kardexone.Model;

public enum TipoFactura {
    COMPRA("+"),
    VENTA("-");

    private final String simbolo;

    TipoFactura(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public static TipoFactura fromSimbolo(String simbolo) {
        for (TipoFactura tipo : TipoFactura.values()) {
            if (tipo.simbolo.equals(simbolo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Símbolo no válido para TipoFactura: " + simbolo);
    }
}
