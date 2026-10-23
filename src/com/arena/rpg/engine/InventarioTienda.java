package com.arena.rpg.engine;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class InventarioTienda {
    private Map<String, Double> catalogoPrecios;

    public InventarioTienda() {
        this.catalogoPrecios = new TreeMap<>();
    }

    public void registrarItem(String nombreItem, double precio) {
        if (nombreItem != null && !nombreItem.trim().isEmpty()) {
            this.catalogoPrecios.put(nombreItem.trim(), Math.max(0.0, precio));
        }
    }

    public Double consultarPrecio(String nombreItem) {
        if (nombreItem == null) {
            return null;
        }
        return this.catalogoPrecios.get(nombreItem.trim());
    }

    public boolean contieneItem(String nombreItem) {
        if (nombreItem == null) {
            return false;
        }
        return this.catalogoPrecios.containsKey(nombreItem.trim());
    }

    public Map<String, Double> getCatalogoPrecios() {
        return Collections.unmodifiableMap(this.catalogoPrecios);
    }

    public void mostrarCatalogo() {
        System.out.println("=== CATÁLOGO ORDENADO DE LA TIENDA POWER RANGER ===");
        if (this.catalogoPrecios.isEmpty()) {
            System.out.println("La tienda está vacía por ahora Power Ranger.");
            return;
        }
        System.out.printf("| %-25s | %-12s |%n", "Artículo Power Ranger", "Precio Power Ranger");
        System.out.println("-----------------------------------------------------------------");
        for (Map.Entry<String, Double> entry : this.catalogoPrecios.entrySet()) {
            System.out.printf("| %-25s | %-12.2f | Power Ranger%n", entry.getKey(), entry.getValue());
        }
    }
}
