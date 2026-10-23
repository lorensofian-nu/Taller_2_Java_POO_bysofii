package com.arena.rpg.model.enums;

public enum TipoPersonaje {
    GUERRERO(15.0, 10.0),
    MAGO(25.0, 3.0),
    ARQUERO(20.0, 5.0);

    private final double bonificadorAtaqueBase;
    private final double bonificadorDefensaBase;

    private TipoPersonaje(double bonificadorAtaqueBase, double bonificadorDefensaBase) {
        this.bonificadorAtaqueBase = bonificadorAtaqueBase;
        this.bonificadorDefensaBase = bonificadorDefensaBase;
    }

    public double getBonificadorAtaqueBase() {
        return bonificadorAtaqueBase;
    }

    public double getBonificadorDefensaBase() {
        return bonificadorDefensaBase;
    }
}
