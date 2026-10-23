package com.arena.rpg;

import java.util.ArrayList;
import java.util.List;

public class Gremio {
    private String nombre;
    private List<Personaje> miembros;

    public Gremio(String nombre) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Gremio Anónimo Power Ranger" : nombre.trim();
        this.miembros = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }

    public void agregarMiembro(Personaje p) {
        if (p == null) {
            return;
        }

        if (!this.miembros.contains(p)) {
            this.miembros.add(p);
        }

        if (p.getGremio() != this) {
            p.unirseAGremio(this);
        }
    }

    public void expulsarMiembro(Personaje p) {
        if (p == null) {
            return;
        }

        if (this.miembros.remove(p)) {
            if (p.getGremio() == this) {
                p.setGremio(null);
            }
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
        sb.append("Gremio Power Ranger{nombre='").append(nombre).append("', miembros=[");
        for (int i = 0; i < miembros.size(); i++) {
            sb.append(miembros.get(i).getNombre());
            if (i < miembros.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
