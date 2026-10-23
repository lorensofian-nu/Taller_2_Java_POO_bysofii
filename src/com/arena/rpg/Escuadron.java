package com.arena.rpg;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un Escuadrón táctico en la Arena RPG.
 * Modela una relación de Agregación Débil (0..*) con la clase Personaje:
 * El escuadrón agrupa combatientes, pero los personajes tienen un ciclo
 * de vida independiente. Si el escuadrón se disuelve o se elimina,
 * los objetos Personaje permanecen intactos en la memoria Heap.
 */
public class Escuadron {
    private String nombre;
    private List<Personaje> miembros; // Agregación (0..*)

    public Escuadron(String nombre) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Escuadrón Anónimo" : nombre.trim();
        this.miembros = new ArrayList<>(); // Inicialización de la colección
    }

    /**
     * Recluta a un personaje existente dentro del escuadrón.
     */
    public void reclutarPersonaje(Personaje p) {
        // Validar que 'p' no sea null y que no esté ya dentro de 'miembros'
        if (p == null) {
            System.out.println("[ALERTA] No se puede reclutar un personaje con referencia nula.");
            return;
        }

        if (this.miembros.contains(p)) {
            System.out.printf("ℹ️ El personaje '%s' ya forma parte del escuadrón '%s'.%n",
                    p.getNombre(), this.nombre);
            return;
        }

        // Agregar 'p' a la lista 'miembros' sin alterar la instanciación original de 'p'
        this.miembros.add(p);
        System.out.printf("🚩 '%s' ha sido reclutado con éxito en el escuadrón '%s'.%n",
                p.getNombre(), this.nombre);
    }

    /**
     * Expulsa a un personaje del escuadrón.
     * Nota de Ciclo de Vida: El personaje removido mantiene su estado,
     * nivel, corazón RPG y armas intactos en memoria (Agregación Débil).
     */
    public void expulsarPersonaje(Personaje p) {
        if (p == null) {
            return;
        }

        // Remover 'p' de la lista 'miembros' si existe
        if (this.miembros.remove(p)) {
            System.out.printf("🚪 '%s' ha sido retirado del escuadrón '%s' (su instancia sigue viva en memoria).%n",
                    p.getNombre(), this.nombre);
        } else {
            System.out.printf("ℹ️ El personaje '%s' no se encuentra en el escuadrón '%s'.%n",
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

    /**
     * Retorna una copia defensiva de la lista de miembros para prevenir
     * ConcurrentModificationException durante iteraciones concurrentes.
     */
    public List<Personaje> getMiembros() {
        return new ArrayList<>(this.miembros);
    }

    public int getCantidadMiembros() {
        return this.miembros.size();
    }

    /**
     * Reporte formateado utilizando StringBuilder (Requisito 3).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Escuadrón: '").append(nombre).append("' (").append(miembros.size()).append(" integrantes)\n");
        if (miembros.isEmpty()) {
            sb.append("   (Sin integrantes reclutados actualmente)");
        } else {
            for (int i = 0; i < miembros.size(); i++) {
                Personaje p = miembros.get(i);
                sb.append("   ").append(i + 1).append(". ")
                  .append(p.getNombre())
                  .append(" (Nivel ").append(p.getNivel())
                  .append(", Ataque Total: ").append(p.calcularAtaqueTotal()).append(")");
                if (i < miembros.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}
