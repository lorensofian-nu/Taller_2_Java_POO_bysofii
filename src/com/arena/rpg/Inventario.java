package com.arena.rpg;

import com.arena.rpg.exception.CapacidadExcedidaException;

import java.util.ArrayList;
import java.util.List;

public class Inventario<T> {
    private List<T> items = new ArrayList<>();
    private int capacidadMaxima = 10;

    public Inventario() {
        this.capacidadMaxima = 10;
    }

    public Inventario(int capacidadMaxima) {
        this.capacidadMaxima = Math.max(1, capacidadMaxima);
    }

    public void agregar(T item) {
        if (items.size() >= capacidadMaxima) {
            System.out.println("Inventario lleno Power Ranger. No se puede agregar más ítems.");
            return;
        }
        items.add(item);
    }

    public void agregarConCapacidad(T item) throws CapacidadExcedidaException {
        if (items.size() >= capacidadMaxima) {
            throw new CapacidadExcedidaException("Capacidad máxima del inventario excedida Power Ranger.");
        }
        items.add(item);
    }

    public T obtener(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public T remover(int index) {
        if (index >= 0 && index < items.size()) {
            return items.remove(index);
        }
        return null;
    }

    public List<T> getItems() {
        return items;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }
}
