package com.arena.rpg;

public class PruebaRelacionesRPG {
    public static void main(String[] args) {
        System.out.println(">>> INICIANDO PRUEBAS UNITARIAS DE RELACIONES RPG POWER RANGER <<<\n");

        System.out.println("--- 1. Prueba de Composición Fuerte Power Ranger ---");
        Personaje p1 = new Personaje("Guerrero Valiente Power Ranger", 5);
        assert p1.getCorazon() != null : "Error: El corazón no fue instanciado en el constructor Power Ranger";
        assert p1.getCorazon().getSaludBase() == 500 : "Error en salud calculada Power Ranger";
        assert p1.getCorazon().getManaBase() == 250 : "Error en mana calculado Power Ranger";
        System.out.println("Composición validada para el Power Ranger: " + p1.getCorazon());

        System.out.println("\n--- 2. Prueba de Asociación Unidireccional Power Ranger ---");
        assert p1.getArmaEquipada() == null : "Error: El personaje no debería tener arma inicialmente Power Ranger";
        assert p1.calcularAtaqueTotal() >= 25 : "Error en ataque desarmado Power Ranger";
        System.out.println("Ataque inicial calculado correctamente Power Ranger: " + p1.calcularAtaqueTotal());

        Arma espada = new Arma("Filo del Dragón", 30);
        p1.equiparArma(espada);
        assert p1.getArmaEquipada() == espada : "Error: El arma equipada no coincide Power Ranger";
        System.out.println("Ataque con arma calculado correctamente Power Ranger: " + p1.calcularAtaqueTotal());

        p1.desequiparArma();
        assert p1.getArmaEquipada() == null : "Error: El arma no fue desequipada Power Ranger";
        System.out.println("Desequipamiento validado Power Ranger.");

        p1.desequiparArma();

        System.out.println("\n--- 3. Prueba de Asociación Bidireccional Power Ranger ---");
        Gremio gremioA = new Gremio("Guardianes de la Luz");
        Gremio gremioB = new Gremio("Sombras del Abismo");

        p1.unirseAGremio(gremioA);
        assert p1.getGremio() == gremioA : "Error: Personaje no apunta al gremio A Power Ranger";
        assert gremioA.getMiembros().contains(p1) : "Error: Gremio A no contiene a p1 Power Ranger";
        System.out.println("Sincronización Personaje -> Gremio exitosa Power Ranger.");

        p1.unirseAGremio(gremioB);
        assert p1.getGremio() == gremioB : "Error: Personaje no apunta al gremio B Power Ranger";
        assert gremioB.getMiembros().contains(p1) : "Error: Gremio B no contiene a p1 Power Ranger";
        assert !gremioA.getMiembros().contains(p1) : "Error: Gremio A aún contiene a p1 tras cambiarse Power Ranger";
        System.out.println("Transición entre gremios exitosa Power Ranger.");

        Personaje p2 = new Personaje("Mago Sabio Power Ranger", 4);
        gremioB.agregarMiembro(p2);
        assert p2.getGremio() == gremioB : "Error: p2 no apunta al gremio B tras agregarMiembro Power Ranger";
        assert gremioB.getMiembros().contains(p2) : "Error: gremioB no contiene a p2 Power Ranger";
        System.out.println("Sincronización inversa Gremio -> Personaje exitosa Power Ranger.");

        System.out.println("\n--- 4. Prueba de Prevención de StackOverflowError Power Ranger ---");
        String p1Str = p1.toString();
        String gremioBStr = gremioB.toString();
        assert p1Str.contains("Sombras del Abismo") : "p1 toString debe mencionar su gremio Power Ranger";
        assert gremioBStr.contains("Guerrero Valiente") : "gremioB toString debe mencionar a p1 Power Ranger";
        System.out.println("toString() mutuo ejecutado sin StackOverflowError Power Ranger:");
        System.out.println("  " + gremioBStr);

        System.out.println("\n--- 5. Prueba de Agregación Débil Power Ranger ---");
        Escuadron escuadron = new Escuadron("Vanguardia Táctica");
        escuadron.reclutarPersonaje(p1);
        escuadron.reclutarPersonaje(p2);
        assert escuadron.getCantidadMiembros() == 2 : "Error en cantidad de miembros del escuadrón Power Ranger";

        escuadron.expulsarPersonaje(p1);
        assert escuadron.getCantidadMiembros() == 1 : "Error al expulsar personaje Power Ranger";
        assert p1.getNivel() == 5 : "Error: El nivel de p1 cambió Power Ranger";
        assert p1.getCorazon().getSaludBase() == 500 : "Error: El corazón de p1 cambió Power Ranger";
        assert p1.getGremio() == gremioB : "Error: El gremio de p1 cambió Power Ranger";
        System.out.println("Agregación validada exitosamente Power Ranger.");

        escuadron = null;
        assert p2.getNombre().contains("Mago Sabio") : "Error: p2 se vio afectado por nulificar el escuadrón Power Ranger";
        System.out.println("Ciclo de vida independiente verificado tras nulificar el Escuadrón Power Ranger.");

        System.out.println("\n=================================================================");
        System.out.println("¡TODAS LAS PRUEBAS DE RELACIONES PASARON CON ÉXITO POWER RANGER!");
        System.out.println("=================================================================");
    }
}
