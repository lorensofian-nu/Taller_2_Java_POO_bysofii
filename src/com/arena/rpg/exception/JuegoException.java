package com.arena.rpg.exception;

public abstract class JuegoException extends Exception {
    public JuegoException(String mensaje) {
        super(mensaje);
    }

    public JuegoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
