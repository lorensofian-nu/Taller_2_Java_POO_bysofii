package com.arena.rpg.exception;

public class CapacidadExcedidaException extends JuegoException {
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }

    public CapacidadExcedidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
