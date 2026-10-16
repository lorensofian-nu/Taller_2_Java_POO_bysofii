package com.taller.poo;

public class ConversorMoneda {

    private ConversorMoneda() {
    }

    public static double convertirDolaresAEuros(double montoDolares, double tasaCambio) {
        if (montoDolares <= 0 || tasaCambio <= 0) {
            return 0.0;
        } else {
            return montoDolares * tasaCambio;
        }
    }

    public static String formatearMoneda(double monto) {
        return String.format("$%.2f", monto);
    }
}
