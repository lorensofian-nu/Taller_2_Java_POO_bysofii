package com.arena.rpg;

import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private String nombre;
    private List<Personaje> miembros;

    public Escuadron(String nombre) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Escuadrón Anónimo Power Ranger" : nombre.trim();
        this.miembros = new ArrayList<>();
    }

    public void reclutarPersonaje(Personaje p) {
        if (p == null) {
            System.out.println("No se puede reclutar un personaje nulo Power Ranger.");
            return;
        }

        if (this.miembros.contains(p)) {
            System.out.printf("El personaje '%s' ya forma parte del escuadrón '%s' Power Ranger.%n",
                    p.getNombre(), this.nombre);
            return;
        }

        this.miembros.add(p);
        System.out.printf("'%s' ha sido reclutado con éxito en el escuadrón '%s' Power Ranger.%n",
                p.getNombre(), this.nombre);
    }

    public void expulsarPersonaje(Personaje p) {
        if (p == null) {
            return;
        }

        if (this.miembros.remove(p)) {
            System.out.printf("'%s' ha sido retirado del escuadrón '%s' Power Ranger.%n",
                    p.getNombre(), this.nombre);
        } else {
            System.out.printf("El personaje '%s' no se encuentra en el escuadrón '%s' Power Ranger.%n",
                    p.getNombre(), this.nombre);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }

    public List<Personaje> getMiembros() {
        return new ArrayList<>(this.miembros);
    }

    public int getCantidadMiembros() {
        return this.miembros.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Escuadrón Power Ranger: '").append(nombre).append("' (").append(miembros.size()).append(" integrantes)\n");
        if (miembros.isEmpty()) {
            sb.append("   (Sin integrantes reclutados actualmente Power Ranger)");
        } else {
            for (int i = 0; i < miembros.size(); i++) {
                Personaje p = miembros.get(i);
                sb.append("   ").append(i + 1).append(". ")
                  .append(p.getNombre())
                  .append(" (Nivel ").append(p.getNivel())
                  .append(", Ataque Total: ").append(p.calcularAtaqueTotal()).append(" Power Ranger)");
                if (i < miembros.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}
