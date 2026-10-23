package com.arena.rpg.exception;

public class VidaInsuficienteException extends RuntimeException {
    public VidaInsuficienteException(String mensaje) {
        super(mensaje);
    }

    public VidaInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
