package com.arena.rpg.model;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class PerfilJugador {
    private String nombreJugador;
    private Set<String> logros;

    public PerfilJugador(String nombreJugador) {
        this.nombreJugador = (nombreJugador == null || nombreJugador.trim().isEmpty()) ? "Power Ranger Hero" : nombreJugador.trim();
        this.logros = new TreeSet<>();
    }

    public boolean registrarLogro(String logro) {
        if (logro == null || logro.trim().isEmpty()) {
            return false;
        }
        return this.logros.add(logro.trim());
    }

    public Set<String> listarLogrosOrdenados() {
        return Collections.unmodifiableSet(this.logros);
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        if (nombreJugador != null && !nombreJugador.trim().isEmpty()) {
            this.nombreJugador = nombreJugador.trim();
        }
    }

    public int getCantidadLogros() {
        return this.logros.size();
    }

    public void mostrarLogros() {
        System.out.println("=== REGISTRO DE LOGROS ORDENADOS POWER RANGER ===");
        if (this.logros.isEmpty()) {
            System.out.println("Aún no has desbloqueado logros en la arena Power Ranger.");
            return;
        }
        int indice = 1;
        for (String logro : this.logros) {
            System.out.println(indice + ". " + logro + " (Desbloqueado por el Power Ranger " + this.nombreJugador + ")");
            indice++;
        }
    }
}
