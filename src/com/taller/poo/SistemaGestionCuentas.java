package com.taller.poo;

import java.util.Scanner;

public class SistemaGestionCuentas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuentaBancaria[] cuentas = {
                new CuentaBancaria("1001", "Carlos Pérez", 1250.75, 500.0),
                new CuentaBancaria("1002", "Ana Gómez", 50.0, 100.0),
                new CuentaBancaria("1003", "Laura Martínez", 300.0, 200.0)
        };

        int opcion;
        do {
            System.out.printf("%n=== SISTEMA DE GESTIÓN DE CUENTAS By Sofii ===%n");
            System.out.printf("1. Depositar en una cuenta%n");
            System.out.printf("2. Retirar de una cuenta%n");
            System.out.printf("3. Ver reporte consolidado%n");
            System.out.printf("4. Salir%n");
            System.out.printf("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.printf("Opción no válida. Ingrese un número del 1 al 4: ");
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    procesarOperacion(scanner, cuentas, true);
                    break;
                case 2:
                    procesarOperacion(scanner, cuentas, false);
                    break;
                case 3:
                    mostrarReporte(cuentas);
                    break;
                case 4:
                    System.out.printf("Sistema finalizado.%n");
                    break;
                default:
                    System.out.printf("Opción no válida. Intente de nuevo.%n");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void procesarOperacion(Scanner scanner, CuentaBancaria[] cuentas,
                                          boolean esDeposito) {
        System.out.printf("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        CuentaBancaria cuentaEncontrada = buscarCuenta(cuentas, numeroCuenta);
        if (cuentaEncontrada == null) {
            System.out.printf("Error: no existe una cuenta con número %s.%n", numeroCuenta);
        } else {
            System.out.printf("Ingrese el monto: ");
            while (!scanner.hasNextDouble()) {
                scanner.next();
                System.out.printf("Monto no válido. Ingrese un número mayor que cero: ");
            }
            double monto = scanner.nextDouble();
            scanner.nextLine();

            if (monto <= 0) {
                System.out.printf("Error: el monto debe ser mayor que cero.%n");
            } else if (esDeposito) {
                if (cuentaEncontrada.depositar(monto)) {
                    System.out.printf("Depósito realizado. Nuevo saldo: $%.2f%n",
                            cuentaEncontrada.getSaldo());
                } else {
                    System.out.printf("No fue posible realizar el depósito.%n");
                }
            } else {
                if (cuentaEncontrada.retirar(monto)) {
                    System.out.printf("Retiro realizado. Nuevo saldo: $%.2f%n",
                            cuentaEncontrada.getSaldo());
                } else {
                    System.out.printf("Error: saldo y crédito disponible insuficientes.%n");
                }
            }
        }
    }

    private static CuentaBancaria buscarCuenta(CuentaBancaria[] cuentas, String numeroCuenta) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i].getNumeroCuenta().equals(numeroCuenta)) {
                return cuentas[i];
            }
        }
        return null;
    }

    private static void mostrarReporte(CuentaBancaria[] cuentas) {
        StringBuilder reporte = new StringBuilder();
        double saldoTotal = 0.0;

        reporte.append(String.format("%n=== REPORTE CONSOLIDADO ===%n"));
        for (int i = 0; i < cuentas.length; i++) {
            CuentaBancaria cuenta = cuentas[i];
            saldoTotal += cuenta.getSaldo();
            reporte.append(String.format("Cuenta: %s | Titular: %s | Saldo: $%.2f | Crédito: $%.2f%n",
                    cuenta.getNumeroCuenta(), cuenta.getTitular(), cuenta.getSaldo(),
                    cuenta.getLimiteCredito() == null ? 0.0 : cuenta.getLimiteCredito()));
        }
        reporte.append(String.format("Saldo total acumulado: $%.2f%n", saldoTotal));
        System.out.printf("%s", reporte.toString());
    }
}