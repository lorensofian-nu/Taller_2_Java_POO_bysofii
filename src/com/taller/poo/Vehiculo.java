package com.taller.poo;

/*
 * Se eligieron tipos primitivos para kilometraje, velocidadMaxima y encendido
 * porque estos datos siempre deben tener un valor y no se necesita representar
 * la ausencia de información con null. Los tipos primitivos suelen consumir
 * menos memoria que sus clases Wrapper. En cambio, Double, Integer y Boolean
 * pueden representar null, pero usan referencias a objetos y pueden tener un
 * mayor consumo de memoria.
 */
public class Vehiculo {
    private String marca;
    private String modelo;
    private double kilometraje;
    private int velocidadMaxima;
    private boolean encendido;

    public Vehiculo() {
        this.marca = "Genérico";
        this.modelo = "Sin Modelo";
        this.kilometraje = 0.0;
        this.velocidadMaxima = 120;
        this.encendido = false;
    }

    public Vehiculo(String marca, String modelo, double kilometraje,
                    int velocidadMaxima, boolean encendido) {
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.velocidadMaxima = velocidadMaxima;
        this.encendido = encendido;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }
}