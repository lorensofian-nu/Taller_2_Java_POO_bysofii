package com.arena.rpg.model.enums;

public enum RaridadItem {
    COMUN(1.0),
    RARO(1.5),
    LEGENDARIO(2.5);

    private final double multiplicadorStat;

    private RaridadItem(double multiplicadorStat) {
        this.multiplicadorStat = multiplicadorStat;
    }

    public double getMultiplicadorStat() {
        return multiplicadorStat;
    }
}
