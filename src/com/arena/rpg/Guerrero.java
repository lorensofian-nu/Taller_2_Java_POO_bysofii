package com.arena.rpg;

public class Guerrero extends Personaje {
    protected int fuerzaEscudo;

    public Guerrero(String nombre, int vida, int ataqueBase, int posX, int posY, int fuerzaEscudo) {
        super(nombre, vida, ataqueBase, posX, posY);
        this.fuerzaEscudo = fuerzaEscudo;
    }

    @Override
    public void defender(int danoRecibido) {
        int danoMitigado = danoRecibido - fuerzaEscudo;
        if (danoMitigado < 0)
            danoMitigado = 0;
        recibirDano(danoMitigado);
        System.out.println(nombre + " bloquea con su escudo reduciendo " + fuerzaEscudo + " de daño Power Ranger.");
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        int danoEspecial = ataqueBase + 15;
        System.out.println(nombre + " ejecuta un Golpe Devastador a " + objetivo.getNombre() + " causando "
                + danoEspecial + " de daño Power Ranger!");
        objetivo.defender(danoEspecial);
    }

    public void atacar(Personaje p) {
        realizarAtaqueEspecial(p);
    }

    public void atacar(Personaje p, boolean modoFuria) {
        if (modoFuria) {
            int danoFuria = (ataqueBase + 15) * 2;
            System.out.println(nombre + " entra en MODO FURIA y ataca a " + p.getNombre() + " causando " + danoFuria
                    + " de daño Power Ranger!");
            p.defender(danoFuria);
        } else {
            atacar(p);
        }
    }
}
