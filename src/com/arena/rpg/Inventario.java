package com.arena.rpg;

import java.util.ArrayList;
import java.util.List;

public class Inventario<T> {
    private List<T> items = new ArrayList<>();

    public void agregar(T item) {
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

    public boolean estaVacio() {
        return items.isEmpty();
    }
}
