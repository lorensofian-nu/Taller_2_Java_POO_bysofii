package com.taller.poo;

public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private Double limiteCredito;

    public CuentaBancaria() {
        this.numeroCuenta = "000000";
        this.titular = "Sin Nombre";
        this.saldo = 0.0;
        this.limiteCredito = 0.0;
    }

    public CuentaBancaria(String numeroCuenta, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0.0;
        this.limiteCredito = 100.0;
    }

    public CuentaBancaria(String numeroCuenta, String titular, double saldo, Double limiteCredito) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.limiteCredito = limiteCredito;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            return true;
        } else {
            return false;
        }
    }

    public boolean retirar(double monto) {
        if (monto <= 0) {
            return false;
        } else {
            double creditoDisponible = 0.0;
            if (this.limiteCredito != null) {
                creditoDisponible = this.limiteCredito;
            }

            if (monto <= this.saldo + creditoDisponible) {
                this.saldo -= monto;
                return true;
            } else {
                return false;
            }
        }
    }
}
/*

1. ¿Qué diferencia hay entre `double` y `Double` en memoria y valor por defecto?**

`double` es un tipo primitivo y, cuando se declara como atributo de una clase, empieza en `0.0`.
 `Double` es la clase Wrapper del tipo `double`; guarda una referencia a un objeto y, si no se
  inicializa, su valor por defecto es `null`. Por eso `Double`
permite representar que todavía no hay un valor asignado, mientras que `double` siempre tiene un valor numérico.


2. ¿Por qué operar con un `Double` no inicializado puede lanzar `NullPointerException`?**

Si `limiteCredito` todavía está en `null`, Java no tiene un número que pueda usar en la operación. Al intentar
convertir automáticamente el `Double` a `double` (un proceso llamado *unboxing*), se produce una `
NullPointerException`. En cambio, un atributo `double` empieza en `0.0`, así que se puede usar
en una operación aritmética sin ese error.
 */