package co.edu.puj.rpg.modelo.enemigo;

import co.edu.puj.rpg.modelo.Personaje;

public class Enemigo {

    private String nombre;
    private int puntosVida;
    private int vidaMaxima;
    private int puntosAtaque;
    private int nivel;
    private double oro;
    private boolean estaVivo;

    public Enemigo(String nombre, int vidaMaxima, int puntosAtaque, int nivel, double oro) {
        this.nombre = (nombre == null || nombre.isBlank()) ? "Enemigo" : nombre.trim();
        this.vidaMaxima = vidaMaxima > 0 ? vidaMaxima : 1;
        this.puntosAtaque = puntosAtaque > 0 ? puntosAtaque : 1;
        this.nivel = 1;
        this.oro = 0.0;
        this.estaVivo = true;
        this.setNivel(nivel);
        this.setOro(oro);
        this.setPuntosVida(this.vidaMaxima);
    }

    public void atacar(Personaje objetivo) {
        if (!this.estaVivo || objetivo == null || !objetivo.isEstaVivo()) {
            return;
        }
        objetivo.recibirDano(this.puntosAtaque);
    }

    public void recibirDano(int cantidadDano) {
        if (cantidadDano <= 0) {
            return;
        }
        this.setPuntosVida(this.puntosVida - cantidadDano);
    }

    public final void setPuntosVida(int vida) {
        if (vida <= 0) {
            this.puntosVida = 0;
            this.estaVivo = false;
        } else if (vida > this.vidaMaxima) {
            this.puntosVida = this.vidaMaxima;
            this.estaVivo = true;
        } else {
            this.puntosVida = vida;
            this.estaVivo = true;
        }
    }

    public final boolean setOro(double oro) {
        if (oro < 0.0) {
            return false;
        }
        this.oro = oro;
        return true;
    }

    public final void setNivel(int nivel) {
        if (nivel < 1) {
            return;
        }
        this.nivel = nivel;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPuntosVida() {
        return this.puntosVida;
    }

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }

    public int getPuntosAtaque() {
        return this.puntosAtaque;
    }

    public int getNivel() {
        return this.nivel;
    }

    public double getOro() {
        return this.oro;
    }

    public boolean isEstaVivo() {
        return this.estaVivo;
    }

    public int getRecompensaExperiencia() {
        return this.nivel * 60;
    }
}
