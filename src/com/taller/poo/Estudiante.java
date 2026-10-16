package com.taller.poo;

public class Estudiante {
    private String identificacion;
    private String nombreCompleto;
    private double promedioCalificaciones;
    private boolean estaMatriculado;

    public Estudiante() {
        this.identificacion = "";
        this.nombreCompleto = "";
        this.promedioCalificaciones = 0.0;
        this.estaMatriculado = false;
    }

    public Estudiante(String identificacion, String nombreCompleto,
                      double promedioCalificaciones, boolean estaMatriculado) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.promedioCalificaciones = promedioCalificaciones;
        this.estaMatriculado = estaMatriculado;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public double getPromedioCalificaciones() {
        return promedioCalificaciones;
    }

    public boolean isEstaMatriculado() {
        return estaMatriculado;
    }

    public String obtenerReporteAcademicos() {
        StringBuilder reporte = new StringBuilder();
        reporte.append(String.format(
                "Identificación: %s | Nombre: %s | Promedio: %.2f | Matriculado: %b",
                identificacion, nombreCompleto, promedioCalificaciones, estaMatriculado));
        return reporte.toString();
    }
}

