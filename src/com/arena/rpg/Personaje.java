package com.arena.rpg;

public abstract class Personaje implements ICombatiente {
    protected String nombre;
    protected int vida;
    protected int ataqueBase;
    protected int posicionX;
    protected int posicionY;

    public Personaje(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataqueBase = ataqueBase;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public abstract void realizarAtaqueEspecial(Personaje objetivo);

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void recibirDano(int cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicion(int x, int y) {
        if (x < 0 || x >= 5 || y < 0 || y >= 5) {
            System.out.println("Posicion fuera de rango Power Ranger. Se cancela movimiento.");
            return;
        }
        this.posicionX = x;
        this.posicionY = y;
    }
}
