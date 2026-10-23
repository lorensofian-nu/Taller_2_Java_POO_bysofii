package com.arena.rpg.engine;

import com.arena.rpg.Personaje;
import com.arena.rpg.exception.SinManaException;
import com.arena.rpg.model.enums.EstadoPersonaje;

public class CombateEngine {

    public void ejecutarAtaque(Personaje atacante, Personaje objetivo, int costoMana) throws SinManaException {
        if (atacante == null || objetivo == null) {
            System.out.println("Combatientes no válidos en la arena Power Ranger.");
            return;
        }

        if (atacante.getEstado() == EstadoPersonaje.DERROTADO) {
            System.out.println("El atacante ya ha sido derrotado Power Ranger.");
            return;
        }

        if (atacante.getEstado() == EstadoPersonaje.ATURDIDO) {
            System.out.println("El atacante está aturdido y no puede realizar acciones Power Ranger.");
            return;
        }

        if (costoMana > atacante.getPuntosMana()) {
            throw new SinManaException("Maná insuficiente Power Ranger: tienes " + atacante.getPuntosMana() + " MP y la habilidad requiere " + costoMana + " MP.");
        }

        atacante.setPuntosMana(atacante.getPuntosMana() - costoMana);
        int danio = atacante.calcularAtaqueTotal();
        System.out.printf("¡%s ataca a %s causando %d de daño consumiendo %d de maná Power Ranger!%n",
                atacante.getNombre(), objetivo.getNombre(), danio, costoMana);
        objetivo.recibirDano(danio);

        if (!objetivo.estaVivo()) {
            System.out.printf("¡El objetivo %s ha sido derrotado en combate Power Ranger!%n", objetivo.getNombre());
        }
    }

    public void aplicarEfectosDeEstado(Personaje personaje) {
        if (personaje == null || !personaje.estaVivo()) {
            return;
        }

        if (personaje.getEstado() == EstadoPersonaje.ENVENENADO) {
            int danioVeneno = Math.max(5, personaje.getNivel() * 3);
            System.out.printf("El veneno inflige %d de daño a %s este turno Power Ranger.%n",
                    danioVeneno, personaje.getNombre());
            personaje.recibirDano(danioVeneno);
        } else if (personaje.getEstado() == EstadoPersonaje.ATURDIDO) {
            System.out.printf("%s sigue aturdido y no podrá actuar este turno Power Ranger.%n",
                    personaje.getNombre());
        }
    }

    public boolean puedeCombatir(Personaje personaje) {
        return personaje != null && personaje.estaVivo() && personaje.getEstado() != EstadoPersonaje.ATURDIDO;
    }
}
