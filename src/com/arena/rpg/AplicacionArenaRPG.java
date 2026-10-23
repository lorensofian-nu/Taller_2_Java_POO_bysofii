package com.arena.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AplicacionArenaRPG {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        List<Personaje> personajes = new ArrayList<>();
        List<Gremio> gremios = new ArrayList<>();
        List<Escuadron> escuadrones = new ArrayList<>();

        precargarDatosDemostrativos(personajes, gremios, escuadrones);

        do {
            System.out.println("\n=== MENÚ ARENA DE HÉROES RPG POWER RANGER ===");
            System.out.println("1. Crear Personaje Power Ranger");
            System.out.println("2. Crear Gremio Power Ranger");
            System.out.println("3. Reclutar Personaje a Escuadrón Power Ranger");
            System.out.println("4. Equipar Arma a Personaje Power Ranger");
            System.out.println("5. Mostrar Estado Detallado Power Ranger");
            System.out.println("6. Salir Power Ranger");
            System.out.print("Seleccione una opción Power Ranger: ");

            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("\n--- [1] CREAR PERSONAJE POWER RANGER ---");
                    System.out.print("Ingrese el nombre del héroe Power Ranger: ");
                    String nombreHeroe = scanner.nextLine().trim();
                    if (nombreHeroe.isEmpty()) {
                        nombreHeroe = "Héroe-" + (personajes.size() + 1);
                    }
                    System.out.print("Ingrese el nivel del héroe Power Ranger: ");
                    int nivel = Math.max(1, leerEntero(scanner));

                    Personaje nuevoPersonaje = new Personaje(nombreHeroe, nivel);
                    personajes.add(nuevoPersonaje);

                    System.out.printf("¡Personaje '%s' (Nivel %d) creado con éxito Power Ranger!%n",
                            nuevoPersonaje.getNombre(), nuevoPersonaje.getNivel());
                    System.out.printf("Su Corazón RPG ha sido instanciado automáticamente Power Ranger: %s%n",
                            nuevoPersonaje.getCorazon());
                    break;

                case 2:
                    System.out.println("\n--- [2] CREAR GREMIO POWER RANGER ---");
                    System.out.print("Ingrese el nombre del Gremio Power Ranger: ");
                    String nombreGremio = scanner.nextLine().trim();
                    if (nombreGremio.isEmpty()) {
                        nombreGremio = "Gremio-" + (gremios.size() + 1);
                    }

                    Gremio nuevoGremio = new Gremio(nombreGremio);
                    gremios.add(nuevoGremio);
                    System.out.printf("Gremio '%s' fundado exitosamente Power Ranger.%n", nuevoGremio.getNombre());

                    if (!personajes.isEmpty()) {
                        System.out.print("¿Desea afiliar un personaje existente a este gremio ahora Power Ranger? (1=Sí / 2=No): ");
                        int afiliar = leerEntero(scanner);
                        if (afiliar == 1) {
                            Personaje elegido = seleccionarPersonaje(scanner, personajes);
                            if (elegido != null) {
                                elegido.unirseAGremio(nuevoGremio);
                                System.out.printf("'%s' ahora pertenece al gremio '%s' Power Ranger.%n",
                                        elegido.getNombre(), nuevoGremio.getNombre());
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- [3] RECLUTAR PERSONAJE A ESCUADRÓN POWER RANGER ---");
                    if (personajes.isEmpty()) {
                        System.out.println("No hay personajes creados Power Ranger.");
                        break;
                    }

                    Escuadron escuadronDestino;
                    if (escuadrones.isEmpty()) {
                        System.out.print("No existen escuadrones activos. Ingrese el nombre del nuevo Escuadrón Power Ranger: ");
                        String nombreEsc = scanner.nextLine().trim();
                        if (nombreEsc.isEmpty()) {
                            nombreEsc = "Escuadrón Alfa";
                        }
                        escuadronDestino = new Escuadron(nombreEsc);
                        escuadrones.add(escuadronDestino);
                        System.out.printf("Escuadrón '%s' formado Power Ranger.%n", escuadronDestino.getNombre());
                    } else {
                        System.out.println("Seleccione el Escuadrón al que desea reclutar Power Ranger:");
                        for (int i = 0; i < escuadrones.size(); i++) {
                            System.out.printf("  %d. %s (Miembros actuales: %d Power Ranger)%n",
                                    (i + 1), escuadrones.get(i).getNombre(), escuadrones.get(i).getCantidadMiembros());
                        }
                        System.out.printf("  %d. [Crear nuevo escuadrón Power Ranger]%n", (escuadrones.size() + 1));
                        System.out.print("Opción Power Ranger: ");
                        int selEsc = leerEntero(scanner);
                        if (selEsc == escuadrones.size() + 1) {
                            System.out.print("Ingrese nombre del nuevo Escuadrón Power Ranger: ");
                            String nEsc = scanner.nextLine().trim();
                            escuadronDestino = new Escuadron(nEsc.isEmpty() ? "Escuadrón Beta" : nEsc);
                            escuadrones.add(escuadronDestino);
                        } else if (selEsc >= 1 && selEsc <= escuadrones.size()) {
                            escuadronDestino = escuadrones.get(selEsc - 1);
                        } else {
                            System.out.println("Opción de escuadrón inválida Power Ranger.");
                            break;
                        }
                    }

                    Personaje pReclutar = seleccionarPersonaje(scanner, personajes);
                    if (pReclutar != null) {
                        escuadronDestino.reclutarPersonaje(pReclutar);
                    }
                    break;

                case 4:
                    System.out.println("\n--- [4] GESTIONAR EQUIPAMIENTO DE ARMA POWER RANGER ---");
                    if (personajes.isEmpty()) {
                        System.out.println("No hay personajes creados Power Ranger.");
                        break;
                    }

                    Personaje pArma = seleccionarPersonaje(scanner, personajes);
                    if (pArma == null) {
                        break;
                    }

                    System.out.printf("Personaje seleccionado: %s | Arma actual: %s Power Ranger%n",
                            pArma.getNombre(),
                            pArma.getArmaEquipada() != null ? pArma.getArmaEquipada().toString() : "Ninguna");
                    System.out.println("1. Equipar nueva arma Power Ranger");
                    System.out.println("2. Desequipar arma actual Power Ranger");
                    System.out.print("Elija una acción Power Ranger: ");
                    int accionArma = leerEntero(scanner);

                    if (accionArma == 1) {
                        System.out.print("Ingrese el nombre del arma Power Ranger: ");
                        String nomArma = scanner.nextLine().trim();
                        if (nomArma.isEmpty()) {
                            nomArma = "Espada Power Ranger";
                        }
                        System.out.print("Ingrese el daño base del arma Power Ranger: ");
                        int danio = Math.max(0, leerEntero(scanner));

                        Arma nuevaArma = new Arma(nomArma, danio);
                        pArma.equiparArma(nuevaArma);
                    } else if (accionArma == 2) {
                        pArma.desequiparArma();
                    } else {
                        System.out.println("Acción de arma no válida Power Ranger.");
                    }
                    break;

                case 5:
                    mostrarReporteDetallado(personajes, gremios, escuadrones);
                    break;

                case 6:
                    System.out.println("\nCerrando la Arena de Héroes Power Ranger...");
                    break;

                default:
                    System.out.println("Opción inválida Power Ranger. Ingrese un número del 1 al 6.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void mostrarReporteDetallado(List<Personaje> personajes,
                                                List<Gremio> gremios,
                                                List<Escuadron> escuadrones) {
        System.out.println("\n========================================================================================================");
        System.out.println("                           ESTADO DETALLADO DE LA ARENA RPG POWER RANGER                                ");
        System.out.println("========================================================================================================");

        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados en la Arena Power Ranger.");
        } else {
            System.out.println("\n>>> TABLA DE PERSONAJES POWER RANGER <<<");
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

        StringBuilder sbGremios = new StringBuilder();
        sbGremios.append("\n>>> GREMIOS ACTIVOS POWER RANGER <<<\n");
        if (gremios.isEmpty()) {
            sbGremios.append("No hay gremios fundados Power Ranger.\n");
        } else {
            for (Gremio g : gremios) {
                sbGremios.append(" • ").append(g.toString()).append("\n");
            }
        }
        System.out.print(sbGremios.toString());

        StringBuilder sbEscuadrones = new StringBuilder();
        sbEscuadrones.append("\n>>> ESCUADRONES TÁCTICOS POWER RANGER <<<\n");
        if (escuadrones.isEmpty()) {
            sbEscuadrones.append("No hay escuadrones registrados Power Ranger.\n");
        } else {
            for (Escuadron e : escuadrones) {
                sbEscuadrones.append(e.toString()).append("\n");
            }
        }
        System.out.print(sbEscuadrones.toString());
        System.out.println("========================================================================================================\n");
    }

    private static Personaje seleccionarPersonaje(Scanner scanner, List<Personaje> personajes) {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes disponibles Power Ranger.");
            return null;
        }

        System.out.println("Seleccione un Personaje Power Ranger:");
        for (int i = 0; i < personajes.size(); i++) {
            Personaje p = personajes.get(i);
            System.out.printf("  %d. %s (Nivel %d Power Ranger)%n", (i + 1), p.getNombre(), p.getNivel());
        }
        System.out.print("Número de personaje Power Ranger: ");
        int sel = leerEntero(scanner);
        if (sel >= 1 && sel <= personajes.size()) {
            return personajes.get(sel - 1);
        } else {
            System.out.println("Selección inválida Power Ranger.");
            return null;
        }
    }

    private static int leerEntero(Scanner scanner) {
        while (true) {
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida Power Ranger. Ingrese un número entero válido: ");
            }
        }
    }

    private static String recortar(String texto, int longitud) {
        if (texto == null) return "";
        if (texto.length() <= longitud) return texto;
        return texto.substring(0, longitud - 3) + "...";
    }

    private static void precargarDatosDemostrativos(List<Personaje> personajes,
                                                    List<Gremio> gremios,
                                                    List<Escuadron> escuadrones) {
        Personaje aragorn = new Personaje("Aragorn", 10);
        Personaje legolas = new Personaje("Legolas", 8);
        Personaje gimli = new Personaje("Gimli", 9);

        aragorn.equiparArma(new Arma("Andúril", 45));
        legolas.equiparArma(new Arma("Arco Galadhrim", 35));

        Gremio montaces = new Gremio("Montaraces del Norte");
        aragorn.unirseAGremio(montaces);
        gremios.add(montaces);

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
