package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.Personaje;
import co.edu.puj.rpg.modelo.enemigo.Enemigo;

import java.util.Scanner;


public class Main {

    private static final int CURA_POCION_BASICA = 30;
    private static final int CURA_POCION_GRANDE = 60;
    private static final double COSTO_POCION_GRANDE = 30.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("      SIMULADOR DE BATALLA - ARENA By SOFII");      
        System.out.println("==========================================");
        System.out.print("Nombre de tu héroe power rangerr(Enter para el predeterminado): ");
        String nombre = sc.hasNextLine() ? sc.nextLine().trim() : "";

        Personaje heroe = nombre.isEmpty() ? new Personaje() : new Personaje(nombre, 100, 15, 50.0);
        if (nombre.isEmpty()) {
            heroe.setOro(50.0);
        }
        Enemigo enemigo = new Enemigo("Orco de la Arena", 80, 12, 2, 40.0);

        System.out.printf("%n¡%s entra a la arena power ranger y se enfrenta a %s (nivel %d)!%n",
                heroe.getNombre(), enemigo.getNombre(), enemigo.getNivel());

        StringBuilder historial = new StringBuilder();
        historial.append(String.format("%s entra a la arena contra %s.%n", heroe.getNombre(), enemigo.getNombre()));
        int ronda = 0;

        combate: do {
            mostrarMenu();
            int opcion = leerOpcion(sc);

            if (opcion < 1 || opcion > 5) {
                System.out.println(">> Opción inválida power ranger . Elige un número entre 1 y 5.");
            } else {
                ronda++;
                boolean consumeTurno = false;

                switch (opcion) {
                    case 1: 
                        heroe.atacar(enemigo);
                        historial.append(String.format("[R%d] %s ataca%s: %d de daño a %s (vida %d/%d).%n",
                                ronda, heroe.getNombre(),
                                heroe.isUltimoGolpeCritico() ? " con GOLPE CRÍTICO" : "",
                                heroe.getUltimoDano(), enemigo.getNombre(),
                                enemigo.getPuntosVida(), enemigo.getVidaMaxima()));
                        consumeTurno = true;
                        break;

                    case 2: 
                        consumeTurno = usarPocion(heroe, historial, ronda);
                        break;

                    case 3: 
                        mostrarEstado(heroe, enemigo);
                        historial.append(String.format("[R%d] %s consulta el estado del combate.%n",
                                ronda, heroe.getNombre()));
                        break;

                    case 4: 
                        historial.append(String.format("[R%d] %s huye de la arena.%n", ronda, heroe.getNombre()));
                        imprimirHistorial(historial, ronda);
                        break combate;

                    case 5: 
                        tienda(sc, heroe, historial, ronda);
                        break;

                    default:
                        break;
                }

                if (consumeTurno && enemigo.isEstaVivo()) {
                    int vidaPrevia = heroe.getPuntosVida();
                    enemigo.atacar(heroe);
                    historial.append(String.format("[R%d] %s ataca: %d de daño a %s (vida %d/%d).%n",
                            ronda, enemigo.getNombre(), vidaPrevia - heroe.getPuntosVida(),
                            heroe.getNombre(), heroe.getPuntosVida(), heroe.getVidaMaxima()));
                }
                if (!enemigo.isEstaVivo()) {
                    historial.append(String.format("[R%d] %s ha sido derrotado.%n", ronda, enemigo.getNombre()));
                }
                if (!heroe.isEstaVivo()) {
                    historial.append(String.format("[R%d] %s ha caído en combate.%n", ronda, heroe.getNombre()));
                }

                imprimirHistorial(historial, ronda);
            }
        } while (heroe.isEstaVivo() && enemigo.isEstaVivo());


        System.out.println("\n===== FIN DEL COMBATE POWER RANGER =====");
        if (!heroe.isEstaVivo()) {
            System.out.printf("%s ha sido derrotado por %s. ¡Derrota!%n", heroe.getNombre(), enemigo.getNombre());
        } else if (!enemigo.isEstaVivo()) {
            int xp = enemigo.getRecompensaExperiencia();
            int niveles = heroe.ganarExperiencia(xp);
            heroe.setOro(heroe.getOro() + enemigo.getOro());
            System.out.printf("¡Victoria power ranger! %s derrotó a %s.%n", heroe.getNombre(), enemigo.getNombre());
            System.out.printf("Recompensa: %d de experiencia y %.2f de oro.%n", xp, enemigo.getOro());
            if (niveles > 0) {
                System.out.printf("¡SUBIDA DE NIVEL POWER RANGER! Ahora eres nivel %d (vida máx. %d, ataque %d).%n",
                        heroe.getNivel(), heroe.getVidaMaxima(), heroe.getPuntosAtaque());
            }
            mostrarEstado(heroe, enemigo);
        } else {
            System.out.printf("%s huyó del combate con %d/%d de vida.%n",
                    heroe.getNombre(), heroe.getPuntosVida(), heroe.getVidaMaxima());
        }
        sc.close();
    }


    private static void mostrarMenu() {
        System.out.println("\n----------- MENÚ DE COMBATE POWER RANGER -----------");
        System.out.println("1. Atacar");
        System.out.println("2. Usar Poción");
        System.out.println("3. Ver Estado");
        System.out.println("4. Huir");
        System.out.println("5. Tienda");
        System.out.print("Elige una opción: ");
    }

    
    private static int leerOpcion(Scanner sc) {
        if (!sc.hasNextLine()) {
            System.out.println("\nEntrada finalizada Power Ranger. Cerrando el simulador.");
            System.exit(0);
        }
        String linea = sc.nextLine().trim();
        if (linea.matches("-?\\d+")) {
            return Integer.parseInt(linea);
        } else {
            return -1;
        }
    }

    private static void mostrarEstado(Personaje heroe, Enemigo enemigo) {
        System.out.println("\n============== ESTADO ==============");
        System.out.printf("Héroe   : %s%n", heroe.getNombre());
        System.out.printf("Nivel   : %d%n", heroe.getNivel());
        System.out.printf("Vida    : %d / %d%n", heroe.getPuntosVida(), heroe.getVidaMaxima());
        System.out.printf("Ataque  : %d%n", heroe.getPuntosAtaque());
        System.out.printf("Oro     : %.2f%n", heroe.getOro());
        System.out.printf("XP      : %d / %d%n", heroe.getExperiencia(), heroe.getExperienciaRequerida());
        System.out.printf("Pociones: %d básicas, %d grandes%n", heroe.getPocionesBasicas(), heroe.getPocionesGrandes());
        System.out.printf("Estado  : %s%n", heroe.isEstaVivo() ? "Vivo" : "Derrotado");
        System.out.println("------------------------------------");
        System.out.printf("Enemigo : %s (nivel %d)%n", enemigo.getNombre(), enemigo.getNivel());
        System.out.printf("Vida    : %d / %d%n", enemigo.getPuntosVida(), enemigo.getVidaMaxima());
        System.out.printf("Estado  : %s%n", enemigo.isEstaVivo() ? "Vivo" : "Derrotado");
        System.out.println("====================================");
    }


    private static boolean usarPocion(Personaje heroe, StringBuilder historial, int ronda) {
        if (heroe.getPuntosVida() == heroe.getVidaMaxima()) {
            System.out.println(">> Tu vida ya está al máximo Power Rangers; no es necesario usar una poción.");
            historial.append(String.format("[R%d] %s intentó usar una poción con la vida llena.%n",
                    ronda, heroe.getNombre()));
            return false;
        }
        int antes = heroe.getPuntosVida();
        if (heroe.consumirPocionGrande()) {
            heroe.usarPocion(CURA_POCION_GRANDE);
            historial.append(String.format("[R%d] %s usa una Poción Grande: +%d vida (%d/%d).%n",
                    ronda, heroe.getNombre(), heroe.getPuntosVida() - antes,
                    heroe.getPuntosVida(), heroe.getVidaMaxima()));
            return true;
        } else if (heroe.consumirPocionBasica()) {
            heroe.usarPocion(CURA_POCION_BASICA);
            historial.append(String.format("[R%d] %s usa una Poción básica: +%d vida (%d/%d).%n",
                    ronda, heroe.getNombre(), heroe.getPuntosVida() - antes,
                    heroe.getPuntosVida(), heroe.getVidaMaxima()));
            return true;
        } else {
            System.out.println(">> No tienes pociones Power Ranger. Visita la tienda (opción 5).");
            historial.append(String.format("[R%d] %s no tiene pociones disponibles.%n", ronda, heroe.getNombre()));
            return false;
        }
    }

    
    private static void tienda(Scanner sc, Personaje heroe, StringBuilder historial, int ronda) {
        int op;
        do {
            System.out.println("\n--------------- TIENDA POWER RANGER ---------------");
            System.out.printf("Tu oro: %.2f%n", heroe.getOro());
            System.out.printf("1. Poción Grande de Salud (+%d vida) - costo %.2f%n", CURA_POCION_GRANDE,
                    COSTO_POCION_GRANDE);
            System.out.println("2. Salir de la tienda");
            System.out.print("Elige una opción: ");
            op = leerOpcion(sc);

            if (op == 1) {
                double nuevoSaldo = heroe.getOro() - COSTO_POCION_GRANDE;
                if (heroe.setOro(nuevoSaldo)) {
                    heroe.agregarPocionGrande();
                    System.out.printf(">> Compra exitosa. Oro restante: %.2f%n", heroe.getOro());
                    historial.append(
                            String.format("[R%d] %s compra una Poción Grande de Salud.%n", ronda, heroe.getNombre()));
                } else {
                    System.out.println(">> Compra rechazada: no se entregó el ítem.");
                    historial.append(String.format("[R%d] %s intentó comprar una Poción Grande sin oro suficiente.%n",
                            ronda, heroe.getNombre()));
                }
            } else if (op == 2) {
                System.out.println(">> Saliendo de la tienda.");
            } else {
                System.out.println(">> Opción inválida . Elige 1 o 2.");
            }
        } while (op != 2);
    }

    private static void imprimirHistorial(StringBuilder historial, int ronda) {
        System.out.printf("%n----- HISTORIAL DE BATALLA POWER RANGER(hasta la ronda %d) -----%n", ronda);
        System.out.print(historial);
        System.out.println("---------------------------------------------------");
    }
}
