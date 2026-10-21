package com.arena.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArenaRPGMain {
    private static char[][] tablero = new char[5][5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        inicializarTablero();

        List<Personaje> heroes = new ArrayList<>();
        Guerrero guerrero = new Guerrero("Thorin", 120, 15, 0, 0, 10);
        Mago mago = new Mago("Gandalf", 80, 25, 1, 1, 50);

        heroes.add(guerrero);
        heroes.add(mago);

        List<Personaje> enemigos = new ArrayList<>();
        Guerrero orco1 = new Guerrero("Orco Básico", 60, 10, 0, 4, 2);
        Guerrero orco2 = new Guerrero("Orco Jefe", 90, 12, 4, 4, 5);

        enemigos.add(orco1);
        enemigos.add(orco2);

        Inventario<Pocion> inventarioPociones = new Inventario<>();
        inventarioPociones.agregar(new Pocion("Poción de Vida Menor", 30));
        inventarioPociones.agregar(new Pocion("Poción de Vida Mayor", 50));

    
        actualizarTablero(heroes, enemigos);

        System.out.println("=== ¡BIENVENIDO A ARENA By Sofii! ===");

        
        boolean victoria = false;
        boolean derrota = false;

        while (!victoria && !derrota) {
            actualizarTablero(heroes, enemigos);
            mostrarTablero();
            mostrarEstado(heroes, enemigos);

         
            for (Personaje heroe : new ArrayList<>(heroes)) {
                if (!heroe.estaVivo()) continue;

                System.out.println("\nTurno de: " + heroe.getNombre());
                System.out.println("1. Atacar");
                System.out.println("2. Usar Inventario");
                System.out.println("3. Mover en Tablero");
                System.out.print("Seleccione opción: ");
                
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        if (!enemigos.isEmpty()) {
                            System.out.println("Seleccione enemigo a atacar Power Ranger:");
                            for (int i = 0; i < enemigos.size(); i++) {
                                System.out.println(i + ". " + enemigos.get(i).getNombre() + " (Vida: " + enemigos.get(i).getVida() + ")");
                            }
                            int targetIdx = scanner.nextInt();
                            if (targetIdx >= 0 && targetIdx < enemigos.size()) {
                                heroe.realizarAtaqueEspecial(enemigos.get(targetIdx));
                            } else {
                                System.out.println("Objetivo no válido Power Ranger.");
                            }
                        } else {
                            System.out.println("No hay enemigos para atacar Power Ranger.");
                        }
                        break;
                    case 2:
                        if (!inventarioPociones.estaVacio()) {
                            Pocion p = inventarioPociones.remover(0);
                            System.out.println(heroe.getNombre() + " usa " + p.getNombre() + " y recupera " + p.getCuracion() + " de vida!");
                            heroe.recibirDano(-p.getCuracion()); // Curación
                        } else {
                            System.out.println("El inventario está vacío Power Ranger.");
                        }
                        break;
                    case 3:
                        System.out.print("Ingrese nueva fila power ranger (0-4): ");
                        int nuevaX = scanner.nextInt();
                        System.out.print("Ingrese nueva columna power ranger (0-4): ");
                        int nuevaY = scanner.nextInt();

                        if (nuevaX < 0 || nuevaX >= 5 || nuevaY < 0 || nuevaY >= 5) {
                            System.out.println("Posicion fuera de rango Power Ranger. Se cancela movimiento.");
                        } else {
                            heroe.setPosicion(nuevaX, nuevaY);
                        }
                        break;
                    default:
                        System.out.println("Opción inválida Power Ranger.");
                }
            }

            System.out.println("\n--- TURNO ENEMIGO ---");
            for (Personaje enemigo : enemigos) {
                if (enemigo.estaVivo() && !heroes.isEmpty()) {
                    Personaje heroeObjetivo = heroes.get(0);
                    enemigo.realizarAtaqueEspecial(heroeObjetivo);
                }
            }

            
            heroes.removeIf(p -> !p.estaVivo());
            enemigos.removeIf(p -> !p.estaVivo());

            
            boolean enCofre = false;
            for (Personaje h : heroes) {
                if (h.getPosicionX() == 2 && h.getPosicionY() == 2) {
                    enCofre = true;
                    break;
                }
            }

            if (enemigos.isEmpty() || enCofre) {
                victoria = true;
            } else if (heroes.isEmpty()) {
                derrota = true;
            }
        }

        if (victoria) {
            System.out.println("\n==========================================");
            System.out.println("¡VICTORIA Power Ranger! Has derrotado a los enemigos o alcanzado el cofre.");
            System.out.println("==========================================");
        } else {
            System.out.println("\n==========================================");
            System.out.println("¡DERROTA Power Ranger! Tu escuadrón ha sido eliminado.");
            System.out.println("==========================================");
        }
        scanner.close();
    }

    private static void inicializarTablero() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tablero[i][j] = '.';
            }
        }
        tablero[2][2] = 'C'; 
    }

    private static void actualizarTablero(List<Personaje> heroes, List<Personaje> enemigos) {
        inicializarTablero();
        for (Personaje h : heroes) {
            if (h.estaVivo() && h.getPosicionX() >= 0 && h.getPosicionX() < 5 && h.getPosicionY() >= 0 && h.getPosicionY() < 5) {
                tablero[h.getPosicionX()][h.getPosicionY()] = h instanceof Guerrero ? 'G' : 'M';
            }
        }
        for (Personaje e : enemigos) {
            if (e.estaVivo() && e.getPosicionX() >= 0 && e.getPosicionX() < 5 && e.getPosicionY() >= 0 && e.getPosicionY() < 5) {
                tablero[e.getPosicionX()][e.getPosicionY()] = 'E';
            }
        }
    }

    private static void mostrarTablero() {
        System.out.println("\n--- TABLERO DE BATALLA Power Ranger (5x5) ---");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print("[" + tablero[i][j] + "]");
            }
            System.out.println();
        }
    }

    private static void mostrarEstado(List<Personaje> heroes, List<Personaje> enemigos) {
        System.out.println("\n--- ESTADO HÉROES ---");
        for (Personaje h : heroes) {
            System.out.println(h.getNombre() + " | Vida: " + h.getVida() + " | Pos: (" + h.getPosicionX() + "," + h.getPosicionY() + ")");
        }
        System.out.println("--- ESTADO ENEMIGOS ---");
        for (Personaje e : enemigos) {
            System.out.println(e.getNombre() + " | Vida: " + e.getVida() + " | Pos: (" + e.getPosicionX() + "," + e.getPosicionY() + ")");
        }
    }
}