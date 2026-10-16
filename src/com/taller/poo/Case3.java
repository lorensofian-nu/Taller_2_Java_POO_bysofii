/* package com.taller.poo;
public class CuentaBancaria {
private String numeroCuenta;
private double saldo;
// Declaración errónea del constructor
public void CuentaBancaria(String numeroCuenta, double saldoInicial) {
this.numeroCuenta = numeroCuenta;
       this.saldo = saldoInicial;
}
}

1. Al escribir `void`, Java interpreta `CuentaBancaria(...)` como un método que no
   devuelve un valor, no como un constructor. Un constructor no lleva tipo de retorno.

2. Como no existe un constructor válido con los parámetros `String` y `double`, la llamada
`new CuentaBancaria("A-101", 500.0)` no encuentra una firma compatible. Además, al no declarar
un constructor propio, Java solo podría proporcionar el constructor vacío por defecto, que no
acepta esos dos argumentos.

3. La declaración correcta es:
java
public CuentaBancaria(String numeroCuenta, double saldoInicial) {
    this.numeroCuenta = numeroCuenta;
    this.saldo = saldoInicial;
}
*/