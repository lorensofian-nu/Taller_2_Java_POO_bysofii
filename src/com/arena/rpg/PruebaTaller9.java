package com.arena.rpg;

import com.arena.rpg.engine.Bestiario;
import com.arena.rpg.engine.CombateEngine;
import com.arena.rpg.engine.InventarioTienda;
import com.arena.rpg.exception.CapacidadExcedidaException;
import com.arena.rpg.exception.SinManaException;
import com.arena.rpg.exception.VidaInsuficienteException;
import com.arena.rpg.model.Habilidad;
import com.arena.rpg.model.Item;
import com.arena.rpg.model.PerfilJugador;
import com.arena.rpg.model.enums.EstadoPersonaje;
import com.arena.rpg.model.enums.RaridadItem;
import com.arena.rpg.model.enums.TipoPersonaje;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PruebaTaller9 {
    public static void main(String[] args) {
        System.out.println("=========================================================================");
        System.out.println("  INICIANDO PRUEBAS DE SET, MAP, ENUMS Y EXCEPCIONES POWER RANGER       ");
        System.out.println("=========================================================================\n");

        System.out.println("--- PRUEBA 1: ENUMERACIONES CON ESTADO Y COMPORTAMIENTO POWER RANGER ---");
        TipoPersonaje guerrero = TipoPersonaje.GUERRERO;
        TipoPersonaje mago = TipoPersonaje.MAGO;
        TipoPersonaje arquero = TipoPersonaje.ARQUERO;

        assert guerrero.getBonificadorAtaqueBase() == 15.0 : "Error bono ataque guerrero Power Ranger";
        assert guerrero.getBonificadorDefensaBase() == 10.0 : "Error bono defensa guerrero Power Ranger";
        assert mago.getBonificadorAtaqueBase() == 25.0 : "Error bono ataque mago Power Ranger";
        assert arquero.getBonificadorDefensaBase() == 5.0 : "Error bono defensa arquero Power Ranger";

        Item pocionLegendaria = new Item("Elixir Sagrado", 100.0, RaridadItem.LEGENDARIO);
        assert pocionLegendaria.calcularStatTotal() == 250.0 : "Error calculo stat legendario Power Ranger";
        System.out.println("Enums y multiplicadores de ítems validados con éxito Power Ranger: " + pocionLegendaria);

        System.out.println("\n--- PRUEBA 2: HASHSET DE HABILIDADES ÚNICAS POWER RANGER ---");
        Personaje heroe = new Personaje("Tommy Oliver Power Ranger", 5, TipoPersonaje.GUERRERO);
        assert heroe.getEstado() == EstadoPersonaje.VIVO : "El estado inicial debe ser VIVO Power Ranger";

        try {
            boolean hab1 = heroe.aprenderHabilidad("Golpe Dragón");
            assert hab1 : "Debe aprender habilidad nueva Power Ranger";

            boolean habRepetida = heroe.aprenderHabilidad("Golpe Dragón");
            assert !habRepetida : "No debe permitir duplicados en HashSet Power Ranger";

            heroe.aprenderHabilidad("Escudo de Luz");
            heroe.aprenderHabilidad("Espada Fulgurante");
            heroe.aprenderHabilidad("Furia Ranger");
            heroe.aprenderHabilidad("Patada Giratoria");

            assert heroe.obtenerHabilidades().size() == 5 : "Deben haber 5 habilidades en el libro Power Ranger";
            System.out.println("Libro de habilidades únicas verificado Power Ranger: " + heroe.obtenerHabilidades());
        } catch (CapacidadExcedidaException e) {
            System.err.println("Falla inesperada Power Ranger: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA 3: EXCEPCIÓN DE CAPACIDAD EXCEDIDA POWER RANGER ---");
        boolean excepcionCapacidadDisparada = false;
        try {
            heroe.aprenderHabilidad("Poder Prohibido");
        } catch (CapacidadExcedidaException e) {
            excepcionCapacidadDisparada = true;
            System.out.println("CapacidadExcedidaException capturada correctamente Power Ranger: " + e.getMessage());
        }
        assert excepcionCapacidadDisparada : "Debió lanzarse CapacidadExcedidaException Power Ranger";

        Inventario<String> inv = new Inventario<>(2);
        boolean excepcionInv = false;
        try {
            inv.agregarConCapacidad("Ítem 1");
            inv.agregarConCapacidad("Ítem 2");
            inv.agregarConCapacidad("Ítem 3");
        } catch (CapacidadExcedidaException e) {
            excepcionInv = true;
            System.out.println("CapacidadExcedidaException en inventario verificada Power Ranger: " + e.getMessage());
        }
        assert excepcionInv : "Debió lanzarse CapacidadExcedidaException en inventario Power Ranger";

        System.out.println("\n--- PRUEBA 4: TREESET DE LOGROS ORDENADOS POWER RANGER ---");
        PerfilJugador perfil = new PerfilJugador("Jason Lee Scott Power Ranger");
        assert perfil.registrarLogro("Primera Victoria") : "Error al registrar logro Power Ranger";
        assert !perfil.registrarLogro("Primera Victoria") : "TreeSet no debe aceptar logros duplicados Power Ranger";
        perfil.registrarLogro("Maestro del Maná");
        perfil.registrarLogro("Cazador de Dragones");
        perfil.registrarLogro("Asesino de Orcos");

        Set<String> logrosOrdenados = perfil.listarLogrosOrdenados();
        List<String> listaOrdenada = new ArrayList<>(logrosOrdenados);
        assert listaOrdenada.get(0).equals("Asesino de Orcos") : "Orden alfabético incorrecto en posición 0 Power Ranger";
        assert listaOrdenada.get(1).equals("Cazador de Dragones") : "Orden alfabético incorrecto en posición 1 Power Ranger";
        assert listaOrdenada.get(2).equals("Maestro del Maná") : "Orden alfabético incorrecto en posición 2 Power Ranger";
        assert listaOrdenada.get(3).equals("Primera Victoria") : "Orden alfabético incorrecto en posición 3 Power Ranger";
        System.out.println("Logros ordenados alfabéticamente comprobados con éxito Power Ranger: " + logrosOrdenados);

        System.out.println("\n--- PRUEBA 5: HASHMAP EN BESTIARIO (GETORDEFAULT Y CONTAINSKEY) POWER RANGER ---");
        Bestiario bestiario = new Bestiario();
        assert !bestiario.contieneEnemigo("Putty Patroller") : "No debe contener al enemigo antes de registrar Power Ranger";
        assert bestiario.obtenerBajas("Putty Patroller") == 0 : "El conteo por defecto debe ser 0 con getOrDefault Power Ranger";

        boolean primeraVez = bestiario.registrarVictoria("Putty Patroller");
        assert primeraVez : "La primera baja debe retornar true Power Ranger";
        assert bestiario.contieneEnemigo("Putty Patroller") : "containsKey debe retornar true Power Ranger";

        boolean segundaVez = bestiario.registrarVictoria("Putty Patroller");
        assert !segundaVez : "La segunda baja no es primera vez Power Ranger";
        assert bestiario.obtenerBajas("Putty Patroller") == 2 : "Conteo debe ser 2 Power Ranger";

        bestiario.registrarVictoria("Goldar");
        System.out.println("Bestiario HashMap verificado con éxito Power Ranger:");
        bestiario.mostrarBestiario();

        System.out.println("\n--- PRUEBA 6: TREEMAP EN INVENTARIO TIENDA POWER RANGER ---");
        InventarioTienda tienda = new InventarioTienda();
        tienda.registrarItem("Poción Mayor", 50.0);
        tienda.registrarItem("Armadura Titán", 300.0);
        tienda.registrarItem("Daga Sombra", 120.0);

        List<String> articulosOrdenados = new ArrayList<>(tienda.getCatalogoPrecios().keySet());
        assert articulosOrdenados.get(0).equals("Armadura Titán") : "Error orden alfabético TreeMap Power Ranger";
        assert articulosOrdenados.get(1).equals("Daga Sombra") : "Error orden alfabético TreeMap Power Ranger";
        assert articulosOrdenados.get(2).equals("Poción Mayor") : "Error orden alfabético TreeMap Power Ranger";
        System.out.println("Catálogo TreeMap ordenado verificado con éxito Power Ranger:");
        tienda.mostrarCatalogo();

        System.out.println("\n--- PRUEBA 7: EXCEPCIÓN VERIFICADA SINMANAEXCEPTION POWER RANGER ---");
        boolean sinManaAtrapada = false;
        try {
            heroe.ejecutarAtaqueEspecial(heroe.getPuntosMana() + 100);
        } catch (SinManaException e) {
            sinManaAtrapada = true;
            System.out.println("SinManaException capturada con éxito Power Ranger: " + e.getMessage());
        }
        assert sinManaAtrapada : "Debió arrojarse SinManaException Power Ranger";

        System.out.println("\n--- PRUEBA 8: EXCEPCIÓN NO VERIFICADA VIDAINSUFICIENTEEXCEPTION POWER RANGER ---");
        boolean vidaInsuficienteAtrapada = false;
        try {
            heroe.consumirVida(heroe.getVida() + 50);
        } catch (VidaInsuficienteException e) {
            vidaInsuficienteAtrapada = true;
            System.out.println("VidaInsuficienteException capturada con éxito Power Ranger: " + e.getMessage());
        }
        assert vidaInsuficienteAtrapada : "Debió arrojarse VidaInsuficienteException Power Ranger";

        System.out.println("\n--- PRUEBA 9: CONTRATO EQUALS Y HASHCODE EN HABILIDAD POWER RANGER ---");
        Habilidad h1 = new Habilidad("Rayo Cósmico", 30);
        Habilidad h2 = new Habilidad("rayo cósmico", 50);
        assert h1.equals(h2) : "Habilidades con mismo nombre deben ser equals Power Ranger";
        assert h1.hashCode() == h2.hashCode() : "Hash codes deben ser iguales Power Ranger";

        Set<Habilidad> setHabilidades = new HashSet<>();
        setHabilidades.add(h1);
        boolean agregadaDuplicada = setHabilidades.add(h2);
        assert !agregadaDuplicada : "HashSet debe rechazar Habilidad duplicada por equals y hashCode Power Ranger";
        assert setHabilidades.size() == 1 : "El tamaño del set de habilidades debe ser 1 Power Ranger";
        System.out.println("Contrato equals() y hashCode() verificado con éxito Power Ranger.");

        System.out.println("\n--- PRUEBA 10: COMBATEENGINE Y EFECTOS DE ESTADO POWER RANGER ---");
        CombateEngine motor = new CombateEngine();
        Personaje objetivo = new Personaje("Rita Repulsa", 3, TipoPersonaje.MAGO);
        try {
            motor.ejecutarAtaque(heroe, objetivo, 20);
        } catch (SinManaException e) {
            System.err.println("Falla en motor Power Ranger: " + e.getMessage());
        }

        objetivo.setEstado(EstadoPersonaje.ENVENENADO);
        motor.aplicarEfectosDeEstado(objetivo);

        objetivo.setEstado(EstadoPersonaje.ATURDIDO);
        assert !motor.puedeCombatir(objetivo) : "Personaje aturdido no debe poder combatir Power Ranger";
        System.out.println("Lógica de CombateEngine y estados validada exitosamente Power Ranger.");

        System.out.println("\n=========================================================================");
        System.out.println("  ¡TODAS LAS PRUEBAS DEL TALLER 9 PASARON CON ÉXITO POWER RANGER!       ");
        System.out.println("=========================================================================");
    }
}
