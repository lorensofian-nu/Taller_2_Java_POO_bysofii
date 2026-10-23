package com.arena.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reto Integrador (Sección 4.1): Aplicación interactiva de consola para la Arena de Héroes RPG.
 * Demuestra en tiempo de ejecución:
 * 1. Asociación Unidireccional (Personaje -> Arma)
 * 2. Asociación Bidireccional (Personaje <-> Gremio)
 * 3. Agregación Débil (Escuadron -> Personaje)
 * 4. Composición Fuerte (Personaje -> CorazonRPG)
 */
public class AplicacionArenaRPG {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Colecciones globales para listar Gremios, Escuadrones y Personajes
        List<Personaje> personajes = new ArrayList<>();
        List<Gremio> gremios = new ArrayList<>();
        List<Escuadron> escuadrones = new ArrayList<>();

        // Datos iniciales demostrativos para facilitar las pruebas inmediatas
        precargarDatosDemostrativos(personajes, gremios, escuadrones);

        do {
            System.out.println("\n=== MENÚ ARENA DE HÉROES RPG ===");
            System.out.println("1. Crear Personaje");
            System.out.println("2. Crear Gremio");
            System.out.println("3. Reclutar Personaje a Escuadrón");
            System.out.println("4. Equipar Arma a Personaje");
            System.out.println("5. Mostrar Estado Detallado (Personaje y Corazón RPG)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    // Solicitar nombre e instanciar un nuevo Personaje
                    System.out.println("\n--- [1] CREAR PERSONAJE ---");
                    System.out.print("Ingrese el nombre del héroe: ");
                    String nombreHeroe = scanner.nextLine().trim();
                    if (nombreHeroe.isEmpty()) {
                        nombreHeroe = "Héroe-" + (personajes.size() + 1);
                    }
                    System.out.print("Ingrese el nivel del héroe (ej. 1, 5, 10): ");
                    int nivel = Math.max(1, leerEntero(scanner));

                    // Instanciación: CorazonRPG se crea internamente dentro del constructor (Composición 1..1)
                    Personaje nuevoPersonaje = new Personaje(nombreHeroe, nivel);
                    personajes.add(nuevoPersonaje);

                    System.out.printf("✅ ¡Personaje '%s' (Nivel %d) creado con éxito!%n",
                            nuevoPersonaje.getNombre(), nuevoPersonaje.getNivel());
                    System.out.printf("   [Composición 1..1] Su Corazón RPG ha sido instanciado automáticamente: %s%n",
                            nuevoPersonaje.getCorazon());
                    break;

                case 2:
                    // Solicitar nombre e instanciar un nuevo Gremio
                    System.out.println("\n--- [2] CREAR GREMIO ---");
                    System.out.print("Ingrese el nombre del Gremio: ");
                    String nombreGremio = scanner.nextLine().trim();
                    if (nombreGremio.isEmpty()) {
                        nombreGremio = "Gremio-" + (gremios.size() + 1);
                    }

                    Gremio nuevoGremio = new Gremio(nombreGremio);
                    gremios.add(nuevoGremio);
                    System.out.printf("✅ Gremio '%s' fundado exitosamente.%n", nuevoGremio.getNombre());

                    // Opcionalmente afiliar un personaje para demostrar la Asociación Bidireccional
                    if (!personajes.isEmpty()) {
                        System.out.print("¿Desea afiliar un personaje existente a este gremio ahora? (1=Sí / 2=No): ");
                        int afiliar = leerEntero(scanner);
                        if (afiliar == 1) {
                            Personaje elegido = seleccionarPersonaje(scanner, personajes);
                            if (elegido != null) {
                                // Sincronización bidireccional cruzada en doble vía
                                elegido.unirseAGremio(nuevoGremio);
                                System.out.printf("🔗 [Asociación Bidireccional] '%s' ahora pertenece al gremio '%s' " +
                                                "y el gremio lo tiene en su lista de miembros.%n",
                                        elegido.getNombre(), nuevoGremio.getNombre());
                            }
                        }
                    }
                    break;

                case 3:
                    // Seleccionar un Personaje existente y agregarlo a un Escuadron (Agregación)
                    System.out.println("\n--- [3] RECLUTAR PERSONAJE A ESCUADRÓN (AGREGACIÓN) ---");
                    if (personajes.isEmpty()) {
                        System.out.println("⚠️ No hay personajes creados. Por favor cree uno primero con la opción 1.");
                        break;
                    }

                    Escuadron escuadronDestino;
                    if (escuadrones.isEmpty()) {
                        System.out.print("No existen escuadrones activos. Ingrese el nombre del nuevo Escuadrón: ");
                        String nombreEsc = scanner.nextLine().trim();
                        if (nombreEsc.isEmpty()) {
                            nombreEsc = "Escuadrón Alfa";
                        }
                        escuadronDestino = new Escuadron(nombreEsc);
                        escuadrones.add(escuadronDestino);
                        System.out.printf("✅ Escuadrón '%s' formado.%n", escuadronDestino.getNombre());
                    } else {
                        System.out.println("Seleccione el Escuadrón al que desea reclutar:");
                        for (int i = 0; i < escuadrones.size(); i++) {
                            System.out.printf("  %d. %s (Miembros actuales: %d)%n",
                                    (i + 1), escuadrones.get(i).getNombre(), escuadrones.get(i).getCantidadMiembros());
                        }
                        System.out.printf("  %d. [Crear nuevo escuadrón]%n", (escuadrones.size() + 1));
                        System.out.print("Opción: ");
                        int selEsc = leerEntero(scanner);
                        if (selEsc == escuadrones.size() + 1) {
                            System.out.print("Ingrese nombre del nuevo Escuadrón: ");
                            String nEsc = scanner.nextLine().trim();
                            escuadronDestino = new Escuadron(nEsc.isEmpty() ? "Escuadrón Beta" : nEsc);
                            escuadrones.add(escuadronDestino);
                        } else if (selEsc >= 1 && selEsc <= escuadrones.size()) {
                            escuadronDestino = escuadrones.get(selEsc - 1);
                        } else {
                            System.out.println("Opción de escuadrón inválida.");
                            break;
                        }
                    }

                    Personaje pReclutar = seleccionarPersonaje(scanner, personajes);
                    if (pReclutar != null) {
                        escuadronDestino.reclutarPersonaje(pReclutar);
                        System.out.println("💡 [Agregación Débil]: El personaje es referenciado por el escuadrón sin modificar " +
                                "su existencia independiente en memoria.");
                    }
                    break;

                case 4:
                    // Instanciar un Arma y asignarla a un Personaje (Asociación Unidireccional)
                    System.out.println("\n--- [4] GESTIONAR EQUIPAMIENTO DE ARMA (ASOCIACIÓN UNIDIRECCIONAL) ---");
                    if (personajes.isEmpty()) {
                        System.out.println("⚠️ No hay personajes creados. Por favor cree uno primero con la opción 1.");
                        break;
                    }

                    Personaje pArma = seleccionarPersonaje(scanner, personajes);
                    if (pArma == null) {
                        break;
                    }

                    System.out.printf("Personaje seleccionado: %s | Arma actual: %s%n",
                            pArma.getNombre(),
                            pArma.getArmaEquipada() != null ? pArma.getArmaEquipada().toString() : "Ninguna (Desarmado)");
                    System.out.println("1. Equipar nueva arma");
                    System.out.println("2. Desequipar arma actual");
                    System.out.print("Elija una acción: ");
                    int accionArma = leerEntero(scanner);

                    if (accionArma == 1) {
                        System.out.print("Ingrese el nombre del arma: ");
                        String nomArma = scanner.nextLine().trim();
                        if (nomArma.isEmpty()) {
                            nomArma = "Espada de Acero";
                        }
                        System.out.print("Ingrese el daño base del arma (ej. 15, 25, 40): ");
                        int danio = Math.max(0, leerEntero(scanner));

                        Arma nuevaArma = new Arma(nomArma, danio);
                        pArma.equiparArma(nuevaArma);
                        System.out.printf("➡️ [Asociación Unidireccional 0..1]: '%s' conoce al arma '%s', " +
                                        "pero el arma no almacena referencia hacia '%s'.%n",
                                pArma.getNombre(), nuevaArma.getNombre(), pArma.getNombre());
                    } else if (accionArma == 2) {
                        pArma.desequiparArma();
                    } else {
                        System.out.println("Acción de arma no válida.");
                    }
                    break;

                case 5:
                    // Construir reporte usando StringBuilder e imprimir estadísticas con System.out.printf
                    mostrarReporteDetallado(personajes, gremios, escuadrones);
                    break;

                case 6:
                    System.out.println("\nCerrando la Arena de Héroes...");
                    break;

                default:
                    System.out.println("Opción inválida. Ingrese un número del 1 al 6.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    /**
     * Construye un reporte estructurado y completo empleando StringBuilder
     * e imprime tablas formateadas mediante System.out.printf (Requisitos 2 y 3).
     */
    private static void mostrarReporteDetallado(List<Personaje> personajes,
                                                List<Gremio> gremios,
                                                List<Escuadron> escuadrones) {
        System.out.println("\n========================================================================================================");
        System.out.println("                                ESTADO DETALLADO DE LA ARENA RPG                                        ");
        System.out.println("========================================================================================================");

        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados en la Arena.");
        } else {
            System.out.println("\n>>> TABLA DE PERSONAJES (ASOCIACIÓN, AGREGACIÓN Y COMPOSICIÓN) <<<");
            String lineaSeparadora = "+-----+----------------------+-------+--------------+---------------------------+----------------------+--------------------+";
            System.out.println(lineaSeparadora);
            System.out.printf("| %-3s | %-20s | %-5s | %-12s | %-25s | %-20s | %-18s |%n",
                    "ID", "Nombre", "Nivel", "Ataque Total", "Arma Equipada", "Gremio", "Corazón RPG");
            System.out.println(lineaSeparadora);

            for (int i = 0; i < personajes.size(); i++) {
                Personaje p = personajes.get(i);
                String armaStr = (p.getArmaEquipada() != null)
                        ? p.getArmaEquipada().getNombre() + " (+" + p.getArmaEquipada().getDanioBase() + ")"
                        : "(Desarmado)";
                String gremioStr = (p.getGremio() != null)
                        ? p.getGremio().getNombre()
                        : "(Sin Gremio)";
                String corazonStr = (p.getCorazon() != null)
                        ? "HP:" + p.getCorazon().getSaludBase() + " MP:" + p.getCorazon().getManaBase()
                        : "(N/A)";

                System.out.printf("| %-3d | %-20s | %-5d | %-12d | %-25s | %-20s | %-18s |%n",
                        (i + 1),
                        recortar(p.getNombre(), 20),
                        p.getNivel(),
                        p.calcularAtaqueTotal(),
                        recortar(armaStr, 25),
                        recortar(gremioStr, 20),
                        recortar(corazonStr, 18));
            }
            System.out.println(lineaSeparadora);
        }

        // Reporte de Gremios (Asociación Bidireccional) construido con StringBuilder
        StringBuilder sbGremios = new StringBuilder();
        sbGremios.append("\n>>> GREMIOS ACTIVOS (ASOCIACIÓN BIDIRECCIONAL - SECCIÓN 2.2) <<<\n");
        if (gremios.isEmpty()) {
            sbGremios.append("No hay gremios fundados.\n");
        } else {
            for (Gremio g : gremios) {
                sbGremios.append(" • ").append(g.toString()).append("\n");
            }
        }
        System.out.print(sbGremios.toString());

        // Reporte de Escuadrones (Agregación Débil) construido con StringBuilder
        StringBuilder sbEscuadrones = new StringBuilder();
        sbEscuadrones.append("\n>>> ESCUADRONES TÁCTICOS (AGREGACIÓN DÉBIL - SECCIÓN 3.1) <<<\n");
        if (escuadrones.isEmpty()) {
            sbEscuadrones.append("No hay escuadrones registrados.\n");
        } else {
            for (Escuadron e : escuadrones) {
                sbEscuadrones.append(e.toString()).append("\n");
            }
        }
        System.out.print(sbEscuadrones.toString());
        System.out.println("========================================================================================================\n");
    }

    /**
     * Permite seleccionar interactivamente un personaje de la lista disponible.
     */
    private static Personaje seleccionarPersonaje(Scanner scanner, List<Personaje> personajes) {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes disponibles.");
            return null;
        }

        System.out.println("Seleccione un Personaje:");
        for (int i = 0; i < personajes.size(); i++) {
            Personaje p = personajes.get(i);
            System.out.printf("  %d. %s (Nivel %d)%n", (i + 1), p.getNombre(), p.getNivel());
        }
        System.out.print("Número de personaje: ");
        int sel = leerEntero(scanner);
        if (sel >= 1 && sel <= personajes.size()) {
            return personajes.get(sel - 1);
        } else {
            System.out.println("Selección inválida.");
            return null;
        }
    }

    /**
     * Lectura segura de enteros desde consola para evitar caídas por InputMismatchException.
     */
    private static int leerEntero(Scanner scanner) {
        while (true) {
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número entero válido: ");
            }
        }
    }

    /**
     * Limita la longitud de una cadena para mantener el formato de la tabla printf.
     */
    private static String recortar(String texto, int longitud) {
        if (texto == null) return "";
        if (texto.length() <= longitud) return texto;
        return texto.substring(0, longitud - 3) + "...";
    }

    /**
     * Precarga datos iniciales para demostración y evaluación rápida del sistema.
     */
    private static void precargarDatosDemostrativos(List<Personaje> personajes,
                                                    List<Gremio> gremios,
                                                    List<Escuadron> escuadrones) {
        // 1. Instanciación de personajes (Composición automática con CorazonRPG)
        Personaje aragorn = new Personaje("Aragorn", 10);
        Personaje legolas = new Personaje("Legolas", 8);
        Personaje gimli = new Personaje("Gimli", 9);

        // 2. Asociación Unidireccional: Equipamiento de armas
        aragorn.equiparArma(new Arma("Andúril", 45));
        legolas.equiparArma(new Arma("Arco Galadhrim", 35));
        // Gimli queda inicialmente desarmado para demostrar armaEquipada == null

        // 3. Asociación Bidireccional: Gremio
        Gremio montaces = new Gremio("Montaraces del Norte");
        aragorn.unirseAGremio(montaces);
        gremios.add(montaces);

        // 4. Agregación Débil: Escuadrón
        Escuadron comunidad = new Escuadron("Comunidad del Anillo");
        comunidad.reclutarPersonaje(aragorn);
        comunidad.reclutarPersonaje(legolas);
        comunidad.reclutarPersonaje(gimli);
        escuadrones.add(comunidad);

        personajes.add(aragorn);
        personajes.add(legolas);
        personajes.add(gimli);
    }
}
