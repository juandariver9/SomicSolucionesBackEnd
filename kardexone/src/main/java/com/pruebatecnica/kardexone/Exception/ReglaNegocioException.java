package com.pruebatecnica.kardexone.Exception;

/**
 * Se lanza cuando una operación viola una regla de negocio
 * (saldo insuficiente, cupo excedido, datos inválidos, etc.). Se traduce en HTTP 400.
 */
public class ReglaNegocioException extends RuntimeException {

    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
