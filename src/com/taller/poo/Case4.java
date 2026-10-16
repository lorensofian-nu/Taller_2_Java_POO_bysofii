/* package com.taller.poo;
public class Vehiculo {
private int velocidadMaxima;
private boolean encendido;
public Vehiculo(int velocidadMaxima, boolean encendido) {
// Asignación de variables omitiendo la referencia 'this'
velocidadMaxima = velocidadMaxima;
encendido = encendido;
}
public void imprimirEstado() {
System.out.printf("Velocidad Máxima: %d | Encendido: %b%n",
velocidadMaxima, encendido);
}
}

1. Se mostraría `Velocidad Máxima: 0 | Encendido: false`, porque los atributos conservan
sus valores por defecto. Las instrucciones `velocidadMaxima = velocidadMaxima;` y
 `encendido = encendido;` asignan los parámetros a sí mismos.
2. El sombreado ocurre cuando un parámetro tiene el mismo nombre que un atributo.
Dentro del constructor, el nombre sin `this` hace referencia al parámetro, así que
la asignación no modifica el atributo del objeto.
3. El constructor corregido es:


public Vehiculo(int velocidadMaxima, boolean encendido) {
    this.velocidadMaxima = velocidadMaxima;
    this.encendido = encendido;
}

 */
