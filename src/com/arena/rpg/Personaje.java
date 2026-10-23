package com.arena.rpg;

/**
 * Clase que modela un combatiente en la Arena de Héroes RPG.
 * Integra las tres relaciones fundamentales de la POO (Unidad 2.5):
 * 1. Asociación Unidireccional con Arma (0..1)
 * 2. Asociación Bidireccional con Gremio (0..* a 1)
 * 3. Composición Fuerte con CorazonRPG (1..1)
 *
 * Implementa ICombatiente para máxima compatibilidad con el ecosistema de la Arena RPG.
 */
public class Personaje implements ICombatiente {
    protected String nombre;
    protected int nivel;
    protected Arma armaEquipada;   // Asociación Unidireccional (0..1)
    protected Gremio gremio;       // Asociación Bidireccional (0..* a 1)
    protected CorazonRPG corazon;  // Composición Fuerte (1..1)

    // Atributos de combate y posición
    protected int vida;
    protected int ataqueBase;
    protected int posicionX;
    protected int posicionY;

    /**
     * Constructor principal según especificación del taller Unidad 2.5.
     * El CorazonRPG es instanciado estrictamente dentro del constructor,
     * garantizando la composición y el ciclo de vida dependiente.
     */
    public Personaje(String nombre, int nivel) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Héroe Sin Nombre" : nombre.trim();
        this.nivel = Math.max(1, nivel);
        this.armaEquipada = null; // Inicialmente desarmado (0..1)
        this.gremio = null;       // Inicialmente sin gremio

        // Composición Fuerte (1..1): Instanciación directa en el constructor
        // No existe personaje sin corazón, ni corazón sin personaje.
        this.corazon = new CorazonRPG(this.nivel * 100, this.nivel * 50);
        this.vida = this.corazon.getSaludBase();
        this.ataqueBase = this.nivel * 5;
        this.posicionX = 0;
        this.posicionY = 0;
    }

    /**
     * Constructor sobrecargado con atributos vitales base explícitos.
     */
    public Personaje(String nombre, int nivel, int saludBase, int manaBase) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Héroe Sin Nombre" : nombre.trim();
        this.nivel = Math.max(1, nivel);
        this.armaEquipada = null;
        this.gremio = null;

        this.corazon = new CorazonRPG(saludBase, manaBase);
        this.vida = this.corazon.getSaludBase();
        this.ataqueBase = this.nivel * 5;
        this.posicionX = 0;
        this.posicionY = 0;
    }

    /**
     * Constructor de combate compatible con subclases tácticas (ej. Guerrero, Mago).
     */
    public Personaje(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Héroe Sin Nombre" : nombre.trim();
        this.vida = Math.max(0, vida);
        this.ataqueBase = Math.max(1, ataqueBase);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.nivel = Math.max(1, ataqueBase / 5);
        this.armaEquipada = null;
        this.gremio = null;
        this.corazon = new CorazonRPG(this.vida, this.nivel * 50);
    }

    // ==============================================================
    // SECCIÓN 2.1: ASOCIACIÓN UNIDIRECCIONAL (Personaje -> Arma)
    // ==============================================================

    /**
     * Asigna un arma al personaje e imprime confirmación.
     */
    public void equiparArma(Arma nuevaArma) {
        if (nuevaArma == null) {
            System.out.println("[ALERTA] No se puede equipar una referencia de arma nula.");
            return;
        }
        // Asignar 'nuevaArma' al atributo 'armaEquipada'
        this.armaEquipada = nuevaArma;
        // Imprimir mensaje confirmando el equipamiento del arma
        System.out.printf("⚔️ %s ha equipado con éxito: %s (Daño base: +%d)%n",
                this.nombre, nuevaArma.getNombre(), nuevaArma.getDanioBase());
    }

    /**
     * Desequipa el arma actual validando si existe previamente.
     */
    public void desequiparArma() {
        // Validar si 'armaEquipada' no es null
        if (this.armaEquipada != null) {
            String armaAnterior = this.armaEquipada.getNombre();
            // Reestablecer 'armaEquipada' a null e informar al usuario
            this.armaEquipada = null;
            System.out.printf("🛡️ %s ha desequipado '%s'. Ahora combate desarmado.%n",
                    this.nombre, armaAnterior);
        } else {
            // Si no tiene arma, emitir mensaje indicando que no hay arma para retirar
            System.out.printf("ℹ️ %s no tiene ningún arma equipada para retirar.%n", this.nombre);
        }
    }

    /**
     * Calcula el poder de ataque total.
     * ¡Advertencia de seguridad en memoria!: Verifica que 'armaEquipada' no sea null
     * antes de invocar 'getDanioBase()' para evitar NullPointerException.
     */
    public int calcularAtaqueTotal() {
        int ataque = (this.ataqueBase > 0) ? this.ataqueBase : (this.nivel * 5);
        // Si 'armaEquipada' no es null, sumar su daño base al 'ataqueBase'
        if (this.armaEquipada != null) {
            ataque += this.armaEquipada.getDanioBase();
        }
        // Retornar el valor final calculado
        return ataque;
    }

    // ==============================================================
    // SECCIÓN 2.2: ASOCIACIÓN BIDIRECCIONAL (Personaje <-> Gremio)
    // ==============================================================

    /**
     * Algoritmo de Sincronización en Doble Vía:
     * 1. Validación de Permanencia Previa: desvincularse si pertenecía a otro gremio.
     * 2. Actualización de Referencia Propia: asignar el nuevo gremio.
     * 3. Sincronización Cruzada Inversa: invocar nuevoGremio.agregarMiembro(this).
     */
    public void unirseAGremio(Gremio nuevoGremio) {
        // Evitar recursión redundante si ya pertenece a ese gremio
        if (this.gremio == nuevoGremio) {
            return;
        }

        // Si ya está inscrito en un gremio diferente, desvincularse de él primero
        if (this.gremio != null) {
            Gremio gremioAnterior = this.gremio;
            this.gremio = null; // Romper enlace local para evitar ciclos
            gremioAnterior.expulsarMiembro(this);
        }

        // Asignar el nuevo gremio al atributo local 'this.gremio'
        this.gremio = nuevoGremio;

        // Invocación cruzada: Asegurar que 'nuevoGremio' registre este personaje en su lista
        if (nuevoGremio != null && !nuevoGremio.getMiembros().contains(this)) {
            nuevoGremio.agregarMiembro(this);
        }
    }

    /**
     * Permite al personaje abandonar su gremio actual de forma coherente.
     */
    public void abandonarGremio() {
        if (this.gremio != null) {
            Gremio antiguo = this.gremio;
            this.gremio = null;
            antiguo.expulsarMiembro(this);
            System.out.printf("🚪 %s ha abandonado el gremio '%s'.%n", this.nombre, antiguo.getNombre());
        } else {
            System.out.printf("ℹ️ %s no pertenece a ningún gremio.%n", this.nombre);
        }
    }

    // Setter con visibilidad de paquete para ser invocado por Gremio
    void setGremio(Gremio gremio) {
        this.gremio = gremio;
    }

    // ==============================================================
    // COMBATE E INTERFAZ ICOMBATIENTE
    // ==============================================================

    @Override
    public void defender(int danoRecibido) {
        recibirDano(danoRecibido);
    }

    public void realizarAtaqueEspecial(Personaje objetivo) {
        if (objetivo == null) return;
        int dano = calcularAtaqueTotal();
        System.out.println(this.nombre + " realiza un ataque a " + objetivo.getNombre() + " causando " + dano + " de daño.");
        objetivo.defender(dano);
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void recibirDano(int cantidad) {
        this.vida -= cantidad;
        if (this.vida < 0) {
            this.vida = 0;
        }
        if (this.corazon != null) {
            this.corazon.setSaludBase(this.vida);
        }
    }

    public int getVida() {
        return vida;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicion(int x, int y) {
        if (x < 0 || x >= 5 || y < 0 || y >= 5) {
            System.out.println("Posicion fuera de rango Power Ranger. Se cancela movimiento.");
            return;
        }
        this.posicionX = x;
        this.posicionY = y;
    }

    // ==============================================================
    // GETTERS Y CONTROL DE ACCESO
    // ==============================================================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = Math.max(1, nivel);
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public Gremio getGremio() {
        return gremio;
    }

    /**
     * Acceso al Corazón RPG (Composición).
     * Nota de Arquitectura: NO existe un método 'setCorazon(CorazonRPG c)',
     * pues la Composición prohíbe el reemplazo externo del componente vital.
     */
    public CorazonRPG getCorazon() {
        return corazon;
    }

    // ==============================================================
    // REPORTE Y DEPURACIÓN (Sección 4.1 y 5.1)
    // ==============================================================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Personaje: '").append(nombre).append("' (Nivel ").append(nivel).append(")\n");
        sb.append("  • Ataque Total: ").append(calcularAtaqueTotal()).append(" (Base: ").append(nivel * 5);
        if (armaEquipada != null) {
            sb.append(" + Arma '").append(armaEquipada.getNombre()).append("': ").append(armaEquipada.getDanioBase());
        } else {
            sb.append(" + Desarmado: 0");
        }
        sb.append(")\n");
        sb.append("  • Corazón RPG: ").append(corazon).append("\n");
        sb.append("  • Gremio: ").append(gremio != null ? gremio.getNombre() : "Sin Gremio");
        return sb.toString();
    }
}
