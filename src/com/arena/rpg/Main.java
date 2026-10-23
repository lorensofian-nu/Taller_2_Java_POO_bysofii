package com.arena.rpg;

import com.arena.rpg.engine.Bestiario;
import com.arena.rpg.exception.CapacidadExcedidaException;
import com.arena.rpg.exception.SinManaException;
import com.arena.rpg.model.PerfilJugador;
import com.arena.rpg.model.enums.EstadoPersonaje;
import com.arena.rpg.model.enums.TipoPersonaje;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Personaje heroeActual = null;
        PerfilJugador perfil = new PerfilJugador("Ranger Supremo");
        Bestiario bestiario = new Bestiario();
        int opcion = 0;

        System.out.println("================== ARENA RPG POWER RANGER ==================");
        System.out.println("    ARENA DE HÉROES RPG - BIENVENIDO POWER RANGER");
        System.out.println("============================================================");

        do {
            System.out.println("\n=== MENÚ ARENA RPG POWER RANGER ===");
            System.out.println("1. Crear Personaje Power Ranger");
            System.out.println("2. Asignar Habilidad Única Power Ranger");
            System.out.println("3. Registrar Victoria en Bestiario Power Ranger");
            System.out.println("4. Ejecutar Ataque Especial Power Ranger");
            System.out.println("5. Consultar Bestiario y Logros Power Ranger");
            System.out.println("6. Salir de la Arena Power Ranger");
            System.out.print("Selecciona una opción Power Ranger: ");

            opcion = leerEnteroSeguro(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("\n--- [1] CREAR PERSONAJE POWER RANGER ---");
                    System.out.print("Ingresa el nombre de tu personaje Power Ranger: ");
                    String nombre = scanner.nextLine().trim();
                    if (nombre.isEmpty()) {
                        nombre = "Ranger Rojo";
                    }

                    System.out.println("Selecciona el arquetipo de tu héroe Power Ranger:");
                    TipoPersonaje[] tipos = TipoPersonaje.values();
                    for (int i = 0; i < tipos.length; i++) {
                        System.out.printf("  %d. %s (Bono Ataque: +%.0f, Bono Defensa: +%.0f Power Ranger)%n",
                                (i + 1), tipos[i].name(),
                                tipos[i].getBonificadorAtaqueBase(),
                                tipos[i].getBonificadorDefensaBase());
                    }
                    System.out.print("Elige el número de arquetipo Power Ranger: ");
                    int selTipo = leerEnteroSeguro(scanner);
                    TipoPersonaje tipoElegido = TipoPersonaje.GUERRERO;
                    if (selTipo >= 1 && selTipo <= tipos.length) {
                        tipoElegido = tipos[selTipo - 1];
                    } else {
                        System.out.println("Opción no válida. Se asigna GUERRERO por defecto Power Ranger.");
                    }

                    System.out.print("Ingresa el nivel inicial del héroe Power Ranger: ");
                    int nivel = Math.max(1, leerEnteroSeguro(scanner));

                    heroeActual = new Personaje(nombre, nivel, tipoElegido);
                    System.out.printf("¡Personaje '%s' creado con éxito con arquetipo %s y estado %s Power Ranger!%n",
                            heroeActual.getNombre(), heroeActual.getTipoPersonaje(), heroeActual.getEstado());
                    System.out.printf("Estadísticas iniciales Power Ranger -> Vida: %d HP, Maná: %d MP, Ataque Base: %d.%n",
                            heroeActual.getVida(), heroeActual.getPuntosMana(), heroeActual.calcularAtaqueTotal());
                    break;

                case 2:
                    System.out.println("\n--- [2] ASIGNAR HABILIDAD ÚNICA POWER RANGER ---");
                    if (heroeActual == null) {
                        System.out.println("Primero debes crear un personaje con la opción 1 Power Ranger.");
                        break;
                    }
                    System.out.print("Ingresa el nombre de la habilidad a aprender Power Ranger: ");
                    String habilidad = scanner.nextLine().trim();
                    if (habilidad.isEmpty()) {
                        System.out.println("El nombre de la habilidad no puede estar vacío Power Ranger.");
                        break;
                    }

                    try {
                        boolean agregada = heroeActual.aprenderHabilidad(habilidad);
                        if (agregada) {
                            System.out.printf("¡Habilidad '%s' aprendida con éxito en tu libro de hechizos Power Ranger!%n", habilidad);
                        } else {
                            System.out.printf("Advertencia Power Ranger: la habilidad '%s' ya está aprendida. Se rechaza el duplicado.%n", habilidad);
                        }
                    } catch (CapacidadExcedidaException e) {
                        System.err.println("Excepción de Capacidad Power Ranger: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\n--- [3] REGISTRAR VICTORIA EN BESTIARIO POWER RANGER ---");
                    System.out.print("Ingresa el nombre del enemigo derrotado Power Ranger: ");
                    String enemigo = scanner.nextLine().trim();
                    if (enemigo.isEmpty()) {
                        enemigo = "Monstruo de las Sombras";
                    }

                    boolean primeraVictoria = bestiario.registrarVictoria(enemigo);
                    int totalBajas = bestiario.obtenerBajas(enemigo);
                    System.out.printf("Victoria registrada contra '%s' Power Ranger. Total de bajas registradas: %d.%n",
                            enemigo, totalBajas);

                    if (primeraVictoria) {
                        String logroNuevo = "Cazador de " + enemigo;
                        boolean logroRegistrado = perfil.registrarLogro(logroNuevo);
                        if (logroRegistrado) {
                            System.out.printf("¡Nuevo logro desbloqueado en tu registro Power Ranger: '%s'!%n", logroNuevo);
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n--- [4] EJECUTAR ATAQUE ESPECIAL POWER RANGER ---");
                    if (heroeActual == null) {
                        System.out.println("Primero debes crear un personaje con la opción 1 Power Ranger.");
                        break;
                    }
                    System.out.printf("Héroe actual: %s | Maná disponible: %d MP Power Ranger.%n",
                            heroeActual.getNombre(), heroeActual.getPuntosMana());
                    System.out.print("Ingresa el costo de maná del ataque especial Power Ranger: ");
                    int costoMana = leerEnteroSeguro(scanner);

                    try {
                        heroeActual.ejecutarAtaqueEspecial(costoMana);
                        System.out.printf("¡Maná restante de %s: %d MP Power Ranger!%n",
                                heroeActual.getNombre(), heroeActual.getPuntosMana());
                    } catch (SinManaException e) {
                        System.err.println("Falla de Combate Power Ranger: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("\n--- [5] CONSULTAR BESTIARIO Y LOGROS POWER RANGER ---");
                    bestiario.mostrarBestiario();
                    System.out.println();
                    perfil.mostrarLogros();
                    if (heroeActual != null) {
                        System.out.println("\n=== ESTADO DEL HÉROE ACTUAL POWER RANGER ===");
                        System.out.println(heroeActual);
                    }
                    break;

                case 6:
                    System.out.println("\n¡Gracias por combatir en la Arena de Héroes RPG Power Ranger! Cerrando sistema...");
                    break;

                default:
                    System.out.println("Opción no válida Power Ranger. Elige un número del 1 al 6.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static int leerEnteroSeguro(Scanner scanner) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                valor = scanner.nextInt();
                scanner.nextLine();
                valido = true;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.err.print("Error de entrada Power Ranger. Ingresa un número entero válido: ");
            } finally {
            }
        }
        return valor;
    }
}
