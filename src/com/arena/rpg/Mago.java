package com.arena.rpg;

public class Mago extends Personaje {
    protected int puntosMana;

    public Mago(String nombre, int vida, int ataqueBase, int posX, int posY, int puntosMana) {
        super(nombre, vida, ataqueBase, posX, posY);
        this.puntosMana = puntosMana;
    }

    @Override
    public void defender(int danoRecibido) {
        recibirDano(danoRecibido);
        System.out.println(nombre + " recibe " + danoRecibido + " de daño sin mitigación Power Ranger.");
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        if (puntosMana >= 10) {
            puntosMana -= 10;
            int danoMagico = ataqueBase + 20;
            System.out.println(nombre + " lanza una Bola de Fuego a " + objetivo.getNombre()
                    + " consumiendo 10 de Maná (" + puntosMana + " restantes) causando " + danoMagico + " de daño Power Ranger!");
            objetivo.defender(danoMagico);
        } else {
            System.out.println(nombre + " no tiene suficiente maná para un ataque especial Power Ranger.");
        }
    }

    public int getPuntosMana() {
        return puntosMana;
    }
}
