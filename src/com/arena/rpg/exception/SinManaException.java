package com.arena.rpg.exception;

public class SinManaException extends JuegoException {
    public SinManaException(String mensaje) {
        super(mensaje);
    }

    public SinManaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
