package com.arena.rpg;

public class PruebaRelacionesRPG {
    public static void main(String[] args) {
        System.out.println(">>> INICIANDO PRUEBAS UNITARIAS DE RELACIONES RPG <<<\n");

        // 1. PRUEBA DE COMPOSICIÓN (Personaje -> CorazonRPG)
        System.out.println("--- 1. Prueba de Composición Fuerte ---");
        Personaje p1 = new Personaje("Guerrero Valiente", 5);
        assert p1.getCorazon() != null : "Error: El corazón no fue instanciado en el constructor";
        assert p1.getCorazon().getSaludBase() == 500 : "Error en salud calculada";
        assert p1.getCorazon().getManaBase() == 250 : "Error en mana calculado";
        System.out.println("✔ Composición validada: El corazón se crea internamente dentro del constructor.");
        System.out.println("  Detalle del Corazón: " + p1.getCorazon());

        // 2. PRUEBA DE ASOCIACIÓN UNIDIRECCIONAL (Personaje -> Arma)
        System.out.println("\n--- 2. Prueba de Asociación Unidireccional ---");
        // Desarmado
        assert p1.getArmaEquipada() == null : "Error: El personaje no debería tener arma inicialmente";
        assert p1.calcularAtaqueTotal() == 25 : "Error en ataque desarmado (5 * 5 = 25)";
        System.out.println("✔ Ataque desarmado calculado correctamente: " + p1.calcularAtaqueTotal());

        // Equipar arma
        Arma espada = new Arma("Filo del Dragón", 30);
        p1.equiparArma(espada);
        assert p1.getArmaEquipada() == espada : "Error: El arma equipada no coincide";
        assert p1.calcularAtaqueTotal() == 55 : "Error en ataque armado (25 + 30 = 55)";
        System.out.println("✔ Ataque con arma calculado correctamente: " + p1.calcularAtaqueTotal());

        // Desequipar arma
        p1.desequiparArma();
        assert p1.getArmaEquipada() == null : "Error: El arma no fue desequipada";
        assert p1.calcularAtaqueTotal() == 25 : "Error tras desequipar arma";
        System.out.println("✔ Desequipamiento validado.");

        // Desequipar cuando ya no tiene arma (debe manejarlo sin fallar)
        p1.desequiparArma();

        // 3. PRUEBA DE ASOCIACIÓN BIDIRECCIONAL (Personaje <-> Gremio)
        System.out.println("\n--- 3. Prueba de Asociación Bidireccional y Sincronización Doble Vía ---");
        Gremio gremioA = new Gremio("Guardianes de la Luz");
        Gremio gremioB = new Gremio("Sombras del Abismo");

        // Caso A: personaje.unirseAGremio(gremio)
        p1.unirseAGremio(gremioA);
        assert p1.getGremio() == gremioA : "Error: Personaje no apunta al gremio A";
        assert gremioA.getMiembros().contains(p1) : "Error: Gremio A no contiene a p1 en su lista";
        System.out.println("✔ Sincronización Personaje -> Gremio exitosa.");

        // Caso B: Cambio de gremio (debe desvincular del gremioA automáticamente)
        p1.unirseAGremio(gremioB);
        assert p1.getGremio() == gremioB : "Error: Personaje no apunta al gremio B";
        assert gremioB.getMiembros().contains(p1) : "Error: Gremio B no contiene a p1";
        assert !gremioA.getMiembros().contains(p1) : "Error: Gremio A aún contiene a p1 tras cambiarse de gremio";
        System.out.println("✔ Transición entre gremios exitosa (desvinculación previa automática).");

        // Caso C: gremio.agregarMiembro(otroPersonaje)
        Personaje p2 = new Personaje("Mago Sabio", 4);
        gremioB.agregarMiembro(p2);
        assert p2.getGremio() == gremioB : "Error: p2 no apunta al gremio B tras agregarMiembro";
        assert gremioB.getMiembros().contains(p2) : "Error: gremioB no contiene a p2";
        System.out.println("✔ Sincronización inversa Gremio -> Personaje exitosa.");

        // 4. PRUEBA DE PREVENCIÓN DE STACKOVERFLOWERROR
        System.out.println("\n--- 4. Prueba de Prevención de StackOverflowError en toString() ---");
        String p1Str = p1.toString();
        String gremioBStr = gremioB.toString();
        assert p1Str.contains("Sombras del Abismo") : "p1 toString debe mencionar su gremio";
        assert gremioBStr.contains("Guerrero Valiente") : "gremioB toString debe mencionar a p1";
        System.out.println("✔ toString() mutuo ejecutado sin StackOverflowError:");
        System.out.println("  " + gremioBStr);

        // 5. PRUEBA DE AGREGACIÓN DÉBIL (Escuadron -> Personaje)
        System.out.println("\n--- 5. Prueba de Agregación Débil ---");
        Escuadron escuadron = new Escuadron("Vanguardia Táctica");
        escuadron.reclutarPersonaje(p1);
        escuadron.reclutarPersonaje(p2);
        assert escuadron.getCantidadMiembros() == 2 : "Error en cantidad de miembros del escuadrón";

        // Expulsar personaje
        escuadron.expulsarPersonaje(p1);
        assert escuadron.getCantidadMiembros() == 1 : "Error al expulsar personaje";
        assert p1.getNivel() == 5 : "Error: El nivel de p1 cambió";
        assert p1.getCorazon().getSaludBase() == 500 : "Error: El corazón de p1 cambió";
        assert p1.getGremio() == gremioB : "Error: El gremio de p1 cambió";
        System.out.println("✔ Agregación validada: La expulsión de escuadrón no altera el objeto Personaje.");

        // Disolución del escuadrón (referencia a null)
        escuadron = null;
        assert p2.getNombre().equals("Mago Sabio") : "Error: p2 se vio afectado por nulificar el escuadrón";
        System.out.println("✔ Ciclo de vida independiente verificado tras nulificar el Escuadrón.");

        System.out.println("\n=======================================================");
        System.out.println("🎉 ¡TODAS LAS PRUEBAS DE RELACIONES PASARON CON ÉXITO! 🎉");
        System.out.println("=======================================================");
    }
}
