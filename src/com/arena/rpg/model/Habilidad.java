package com.arena.rpg.model;

import java.util.Objects;

public class Habilidad implements Comparable<Habilidad> {
    private String nombre;
    private int costoMana;

    public Habilidad(String nombre, int costoMana) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Habilidad Power Ranger" : nombre.trim();
        this.costoMana = Math.max(0, costoMana);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }

    public int getCostoMana() {
        return costoMana;
    }

    public void setCostoMana(int costoMana) {
        this.costoMana = Math.max(0, costoMana);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habilidad habilidad = (Habilidad) o;
        return Objects.equals(nombre.toLowerCase(), habilidad.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }

    @Override
    public int compareTo(Habilidad otra) {
        if (otra == null) return 1;
        return this.nombre.compareToIgnoreCase(otra.nombre);
    }

    @Override
    public String toString() {
        return nombre + " (Costo: " + costoMana + " MP Power Ranger)";
    }
}
