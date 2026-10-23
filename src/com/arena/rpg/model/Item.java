package com.arena.rpg.model;

import com.arena.rpg.model.enums.RaridadItem;

public class Item {
    private String nombre;
    private double statBase;
    private RaridadItem raridad;

    public Item(String nombre, double statBase, RaridadItem raridad) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Objeto Genérico" : nombre.trim();
        this.statBase = Math.max(0.0, statBase);
        this.raridad = (raridad == null) ? RaridadItem.COMUN : raridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }

    public double getStatBase() {
        return statBase;
    }

    public void setStatBase(double statBase) {
        this.statBase = Math.max(0.0, statBase);
    }

    public RaridadItem getRaridad() {
        return raridad;
    }

    public void setRaridad(RaridadItem raridad) {
        if (raridad != null) {
            this.raridad = raridad;
        }
    }

    public double calcularStatTotal() {
        return this.statBase * this.raridad.getMultiplicadorStat();
    }

    @Override
    public String toString() {
        return nombre + " [" + raridad + "] - Efecto Total Power Ranger: " + calcularStatTotal();
    }
}
