package co.edu.puj.rpg.modelo;

import co.edu.puj.rpg.modelo.enemigo.Enemigo;

public class Personaje {

    private String nombre;
    private int puntosVida;
    private int vidaMaxima;
    private int puntosAtaque;
    private int nivel;
    private double oro;
    private boolean estaVivo;

    private int experiencia;
    private int pocionesBasicas;
    private int pocionesGrandes;
    private int ultimoDano;
    private boolean ultimoGolpeCritico;

    private static final double PROBABILIDAD_CRITICO = 0.15;
    private static final int EXPERIENCIA_BASE_POR_NIVEL = 100;

    public Personaje() {
        this("Héroe Anónimo", 100, 10, 0.0);
    }

    public Personaje(String nombre, int vidaMaxima, int puntosAtaque, double oro) {
        this.nombre = (nombre == null || nombre.isBlank()) ? "Héroe Anónimo" : nombre.trim();
        this.vidaMaxima = vidaMaxima > 0 ? vidaMaxima : 1;
        this.puntosAtaque = puntosAtaque > 0 ? puntosAtaque : 1;
        this.nivel = 1;
        this.experiencia = 0;
        this.pocionesBasicas = 3;
        this.pocionesGrandes = 0;
        this.oro = 0.0;
        this.estaVivo = true;
        this.setOro(oro);
        this.setPuntosVida(this.vidaMaxima);
    }

    public void atacar(Enemigo enemigo) {
        this.ultimoDano = 0;
        this.ultimoGolpeCritico = false;
        if (!this.estaVivo || enemigo == null || !enemigo.isEstaVivo()) {
            return;
        }
        int dano = this.puntosAtaque;
        if (Math.random() < PROBABILIDAD_CRITICO) {
            dano = dano * 2;
            this.ultimoGolpeCritico = true;
        }
        this.ultimoDano = dano;
        enemigo.recibirDano(dano);
    }

    public void usarPocion(int cantidadRestaurada) {
        if (!this.estaVivo || cantidadRestaurada <= 0) {
            return;
        }
        this.setPuntosVida(this.puntosVida + cantidadRestaurada);
    }

    public void recibirDano(int cantidadDano) {
        if (cantidadDano <= 0) {
            return;
        }
        this.setPuntosVida(this.puntosVida - cantidadDano);
    }

    public int ganarExperiencia(int puntos) {
        if (puntos <= 0 || !this.estaVivo) {
            return 0;
        }
        this.experiencia += puntos;
        int nivelesGanados = 0;
        while (this.experiencia >= this.getExperienciaRequerida()) {
            this.experiencia -= this.getExperienciaRequerida();
            this.setNivel(this.nivel + 1);
            this.vidaMaxima += 10;
            this.puntosAtaque += 2;
            this.setPuntosVida(this.vidaMaxima);
            nivelesGanados++;
        }
        return nivelesGanados;
    }

    public void agregarPocionGrande() {
        this.pocionesGrandes++;
    }

    public boolean consumirPocionBasica() {
        if (this.pocionesBasicas > 0) {
            this.pocionesBasicas--;
            return true;
        }
        return false;
    }

    public boolean consumirPocionGrande() {
        if (this.pocionesGrandes > 0) {
            this.pocionesGrandes--;
            return true;
        }
        return false;
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
            System.out.println(">> Saldo insuficiente: la operación fue rechazada y el oro no cambió.");
            return false;
        }
        this.oro = oro;
        return true;
    }

    protected final void setNivel(int nivel) {
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

    public int getExperiencia() {
        return this.experiencia;
    }

    public int getExperienciaRequerida() {
        return this.nivel * EXPERIENCIA_BASE_POR_NIVEL;
    }

    public int getPocionesBasicas() {
        return this.pocionesBasicas;
    }

    public int getPocionesGrandes() {
        return this.pocionesGrandes;
    }

    public int getUltimoDano() {
        return this.ultimoDano;
    }

    public boolean isUltimoGolpeCritico() {
        return this.ultimoGolpeCritico;
    }
}
