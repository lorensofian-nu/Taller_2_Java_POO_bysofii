package com.arena.rpg.engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Bestiario {
    private Map<String, Integer> derrotasPorEnemigo;

    public Bestiario() {
        this.derrotasPorEnemigo = new HashMap<>();
    }

    public boolean registrarVictoria(String nombreEnemigo) {
        if (nombreEnemigo == null || nombreEnemigo.trim().isEmpty()) {
            return false;
        }
        String clave = nombreEnemigo.trim();
        boolean primeraVez = !this.derrotasPorEnemigo.containsKey(clave);
        int conteoActual = this.derrotasPorEnemigo.getOrDefault(clave, 0);
        this.derrotasPorEnemigo.put(clave, conteoActual + 1);
        return primeraVez;
    }

    public boolean contieneEnemigo(String nombreEnemigo) {
        if (nombreEnemigo == null) {
            return false;
        }
        return this.derrotasPorEnemigo.containsKey(nombreEnemigo.trim());
    }

    public int obtenerBajas(String nombreEnemigo) {
        if (nombreEnemigo == null) {
            return 0;
        }
        return this.derrotasPorEnemigo.getOrDefault(nombreEnemigo.trim(), 0);
    }

    public Map<String, Integer> getDerrotas() {
        return Collections.unmodifiableMap(this.derrotasPorEnemigo);
    }

    public void mostrarBestiario() {
        System.out.println("=== BESTIARIO DE LA ARENA POWER RANGER ===");
        if (this.derrotasPorEnemigo.isEmpty()) {
            System.out.println("No has registrado enemigos derrotados en el bestiario Power Ranger.");
            return;
        }
        System.out.printf("| %-25s | %-15s |%n", "Enemigo Power Ranger", "Bajas Power Ranger");
        System.out.println("-----------------------------------------------------------------");
        for (Map.Entry<String, Integer> entry : this.derrotasPorEnemigo.entrySet()) {
            System.out.printf("| %-25s | %-15d | Power Ranger%n", entry.getKey(), entry.getValue());
        }
    }
}
