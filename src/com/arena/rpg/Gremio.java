package com.arena.rpg;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un Gremio en la Arena RPG.
 * Participa en una Asociación Bidireccional (0..* a 1) con la clase Personaje:
 * El Gremio conoce la lista de todos sus miembros y cada Personaje conoce
 * el gremio al que pertenece.
 */
public class Gremio {
    private String nombre;
    private List<Personaje> miembros; // Cardinalidad: 0..*

    public Gremio(String nombre) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Gremio Anónimo" : nombre.trim();
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

    /**
     * Sincronización bidireccional cruzada con Personaje.
     * Agrega el personaje a la lista interna y actualiza la referencia
     * en el personaje para mantener la coherencia en memoria.
     */
    public void agregarMiembro(Personaje p) {
        // Validar que 'p' no sea null.
        if (p == null) {
            return;
        }

        // Verificar si la lista de miembros no contiene ya a 'p'.
        if (!this.miembros.contains(p)) {
            // Añadir 'p' a la colección interna de miembros.
            this.miembros.add(p);
        }

        // Sincronización cruzada: Si 'p.getGremio()' no es 'this', invocar p.unirseAGremio(this).
        if (p.getGremio() != this) {
            p.unirseAGremio(this);
        }
    }

    /**
     * Remueve un miembro del gremio y desvincula la relación en el personaje.
     */
    public void expulsarMiembro(Personaje p) {
        if (p == null) {
            return;
        }

        if (this.miembros.remove(p)) {
            // Si el personaje todavía apuntaba a este gremio, desvincularlo
            if (p.getGremio() == this) {
                p.setGremio(null);
            }
        }
    }

    /**
     * Retorna una copia de la lista de miembros para evitar ConcurrentModificationException
     * al ser iterada mientras se modifica externamente.
     */
    public List<Personaje> getMiembros() {
        return new ArrayList<>(this.miembros);
    }

    public int getCantidadMiembros() {
        return this.miembros.size();
    }

    /**
     * Prevención estricta de StackOverflowError (Sección 5.1):
     * Rompe la cadena de recursión circular imprimiendo únicamente los nombres
     * simples de los miembros en vez de invocar su toString() completo.
     * Utiliza StringBuilder para concatenación eficiente (Sección 4.1).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gremio{nombre='").append(nombre).append("', miembros=[");
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
