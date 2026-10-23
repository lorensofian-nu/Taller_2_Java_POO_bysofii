package com.arena.rpg;

public class CorazonRPG {
    private int saludBase;
    private int manaBase;

    public CorazonRPG(int saludBase, int manaBase) {
        this.saludBase = Math.max(1, saludBase);
        this.manaBase = Math.max(0, manaBase);
    }

    public int getSaludBase() {
        return saludBase;
    }

    public void setSaludBase(int saludBase) {
        this.saludBase = Math.max(0, saludBase);
    }

    public int getManaBase() {
        return manaBase;
    }

    public void setManaBase(int manaBase) {
        this.manaBase = Math.max(0, manaBase);
    }

    public void curar(int cantidad) {
        if (cantidad > 0) {
            this.saludBase += cantidad;
        }
    }

    public void recibirDanio(int cantidad) {
        if (cantidad > 0) {
            this.saludBase = Math.max(0, this.saludBase - cantidad);
        }
    }

    public boolean gastarMana(int cantidad) {
        if (cantidad > 0 && this.manaBase >= cantidad) {
            this.manaBase -= cantidad;
            return true;
        }
        return false;
    }

    public void recuperarMana(int cantidad) {
        if (cantidad > 0) {
            this.manaBase += cantidad;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Salud: ").append(saludBase).append(", Maná: ").append(manaBase).append(" Power Ranger]");
        return sb.toString();
    }
}
