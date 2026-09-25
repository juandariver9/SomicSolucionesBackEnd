package com.pruebatecnica.kardexone.Exception;

/**
 * Se lanza cuando un recurso solicitado no existe. Se traduce en HTTP 404.
 */
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String recurso, Object id) {
        super(recurso + " con ID " + id + " no existe.");
    }

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
