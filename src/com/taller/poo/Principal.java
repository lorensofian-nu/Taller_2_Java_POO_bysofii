package com.taller.poo;

public class Principal {
    public static void main(String[] args) {
        Vehiculo vehiculo1 = new Vehiculo("Toyota", "Corolla", 15000.50, 180, true);
        Vehiculo vehiculo2 = new Vehiculo();
        Vehiculo vehiculo3 = new Vehiculo("Mazda", "CX-30", 8200.75, 200, false);

        Estudiante estudiante1 = new Estudiante("1001", "Sofía Rodríguez", 4.5, true);
        Estudiante estudiante2 = new Estudiante("1002", "Andres Lopez", 3.8, true);
        Estudiante estudiante3 = new Estudiante("1003", "Atropos", 4.2, false);

        CuentaBancaria cuenta1 = new CuentaBancaria();
        CuentaBancaria cuenta2 = new CuentaBancaria("1002", "Ana Gómez");
        CuentaBancaria cuenta3 = new CuentaBancaria("1003", "Carlos Pérez", 1000.75, 500.0);

        cuenta1.depositar(250.0);
        cuenta1.retirar(50.0);
        cuenta2.depositar(150.0);
        cuenta2.retirar(100.0);
        cuenta3.depositar(300.0);
        cuenta3.retirar(50.0);

        double euros = ConversorMoneda.convertirDolaresAEuros(100.0, 0.92);

        System.out.printf("=== REPORTE DE VEHÍCULOS ===%n");
        System.out.printf("[1] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                vehiculo1.getMarca(), vehiculo1.getModelo(), vehiculo1.getKilometraje(),
                vehiculo1.getVelocidadMaxima(), vehiculo1.isEncendido());
        System.out.printf("[2] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                vehiculo2.getMarca(), vehiculo2.getModelo(), vehiculo2.getKilometraje(),
                vehiculo2.getVelocidadMaxima(), vehiculo2.isEncendido());
        System.out.printf("[3] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                vehiculo3.getMarca(), vehiculo3.getModelo(), vehiculo3.getKilometraje(),
                vehiculo3.getVelocidadMaxima(), vehiculo3.isEncendido());

        System.out.printf("%n=== REPORTE DE ESTUDIANTES ===%n");
        System.out.printf("%s%n", estudiante1.obtenerReporteAcademicos());
        System.out.printf("%s%n", estudiante2.obtenerReporteAcademicos());
        System.out.printf("%s%n", estudiante3.obtenerReporteAcademicos());

        System.out.printf("%n=== REPORTE DE CUENTAS BANCARIAS ===%n");
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: %s | Crédito: %s%n",
                cuenta1.getNumeroCuenta(), cuenta1.getTitular(),
                ConversorMoneda.formatearMoneda(cuenta1.getSaldo()),
                ConversorMoneda.formatearMoneda(cuenta1.getLimiteCredito()));
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: %s | Crédito: %s%n",
                cuenta2.getNumeroCuenta(), cuenta2.getTitular(),
                ConversorMoneda.formatearMoneda(cuenta2.getSaldo()),
                ConversorMoneda.formatearMoneda(cuenta2.getLimiteCredito()));
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: %s | Crédito: %s%n",
                cuenta3.getNumeroCuenta(), cuenta3.getTitular(),
                ConversorMoneda.formatearMoneda(cuenta3.getSaldo()),
                ConversorMoneda.formatearMoneda(cuenta3.getLimiteCredito()));

        System.out.printf("%nConversión de $100.00 a euros: %s%n",
                ConversorMoneda.formatearMoneda(euros));
    }
}