package com.arena.rpg;

import com.arena.rpg.exception.CapacidadExcedidaException;
import com.arena.rpg.exception.SinManaException;
import com.arena.rpg.exception.VidaInsuficienteException;
import com.arena.rpg.model.enums.EstadoPersonaje;
import com.arena.rpg.model.enums.TipoPersonaje;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Personaje implements ICombatiente {
    protected String nombre;
    protected int nivel;
    protected TipoPersonaje tipoPersonaje;
    protected EstadoPersonaje estado;
    protected Set<String> habilidades;
    protected int capacidadMaximaHabilidades;

    protected Arma armaEquipada;
    protected Gremio gremio;
    protected CorazonRPG corazon;

    protected int vida;
    protected int ataqueBase;
    protected int puntosMana;
    protected int posicionX;
    protected int posicionY;

    public Personaje() {
        this("Héroe Anónimo Power Ranger", 1, TipoPersonaje.GUERRERO);
    }

    public Personaje(String nombre, TipoPersonaje tipoPersonaje) {
        this(nombre, 1, tipoPersonaje);
    }

    public Personaje(String nombre, int nivel) {
        this(nombre, nivel, TipoPersonaje.GUERRERO);
    }

    public Personaje(String nombre, int nivel, TipoPersonaje tipoPersonaje) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Héroe Power Ranger" : nombre.trim();
        this.nivel = Math.max(1, nivel);
        this.tipoPersonaje = (tipoPersonaje == null) ? TipoPersonaje.GUERRERO : tipoPersonaje;
        this.estado = EstadoPersonaje.VIVO;
        this.habilidades = new HashSet<>();
        this.capacidadMaximaHabilidades = 5;

        this.armaEquipada = null;
        this.gremio = null;

        int saludCalculada = this.nivel * 100;
        int manaCalculado = this.nivel * 50;
        this.corazon = new CorazonRPG(saludCalculada, manaCalculado);

        this.vida = this.corazon.getSaludBase();
        this.puntosMana = this.corazon.getManaBase();
        this.ataqueBase = this.nivel * 5 + (int) this.tipoPersonaje.getBonificadorAtaqueBase();
        this.posicionX = 0;
        this.posicionY = 0;
    }

    public Personaje(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Héroe Power Ranger" : nombre.trim();
        this.vida = Math.max(0, vida);
        this.ataqueBase = Math.max(1, ataqueBase);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.nivel = Math.max(1, ataqueBase / 5);
        this.tipoPersonaje = TipoPersonaje.GUERRERO;
        this.estado = this.vida > 0 ? EstadoPersonaje.VIVO : EstadoPersonaje.DERROTADO;
        this.habilidades = new HashSet<>();
        this.capacidadMaximaHabilidades = 5;

        this.armaEquipada = null;
        this.gremio = null;
        this.puntosMana = this.nivel * 50;
        this.corazon = new CorazonRPG(this.vida, this.puntosMana);
    }

    public boolean aprenderHabilidad(String habilidad) throws CapacidadExcedidaException {
        if (habilidad == null || habilidad.trim().isEmpty()) {
            return false;
        }
        String habLimpia = habilidad.trim();
        if (this.habilidades.contains(habLimpia)) {
            return false;
        }
        if (this.habilidades.size() >= this.capacidadMaximaHabilidades) {
            throw new CapacidadExcedidaException("El libro de habilidades ha alcanzado su capacidad máxima (" + this.capacidadMaximaHabilidades + ") Power Ranger.");
        }
        return this.habilidades.add(habLimpia);
    }

    public Set<String> obtenerHabilidades() {
        return Collections.unmodifiableSet(this.habilidades);
    }

    public void ejecutarAtaqueEspecial(int costoMana) throws SinManaException {
        lanzarHechizo(costoMana);
    }

    public void lanzarHechizo(int costoMana) throws SinManaException {
        if (this.estado == EstadoPersonaje.DERROTADO) {
            System.out.println("El personaje está derrotado y no puede combatir Power Ranger.");
            return;
        }
        if (this.estado == EstadoPersonaje.ATURDIDO) {
            System.out.println("El personaje está aturdido y pierde su turno Power Ranger.");
            return;
        }
        if (costoMana > this.puntosMana) {
            throw new SinManaException("Maná insuficiente Power Ranger: dispones de " + this.puntosMana + " MP y requieres " + costoMana + " MP.");
        }
        this.puntosMana -= costoMana;
        if (this.corazon != null) {
            this.corazon.gastarMana(costoMana);
        }
        int danioTotal = calcularAtaqueTotal();
        System.out.printf("¡%s ejecuta su ataque especial consumiendo %d de maná con poder total de %d Power Ranger!%n",
                this.nombre, costoMana, danioTotal);
    }

    public void consumirPuntosVida(int costoVida) {
        consumirVida(costoVida);
    }

    public void consumirVida(int costoVida) {
        if (costoVida >= this.vida) {
            throw new VidaInsuficienteException("Vida insuficiente Power Ranger: tienes " + this.vida + " HP y la acción requiere consumir " + costoVida + " HP.");
        }
        this.vida -= costoVida;
        if (this.corazon != null) {
            this.corazon.setSaludBase(this.vida);
        }
        System.out.printf("Se han consumido %d puntos de vida de %s. Vida restante: %d HP Power Ranger.%n",
                costoVida, this.nombre, this.vida);
    }

    public void equiparArma(Arma nuevaArma) {
        if (nuevaArma == null) {
            System.out.println("No se puede equipar un arma nula Power Ranger.");
            return;
        }
        this.armaEquipada = nuevaArma;
        System.out.printf("⚔️ %s ha equipado con éxito: %s (Daño base: +%d) Power Ranger.%n",
                this.nombre, nuevaArma.getNombre(), nuevaArma.getDanioBase());
    }

    public void desequiparArma() {
        if (this.armaEquipada != null) {
            String armaAnterior = this.armaEquipada.getNombre();
            this.armaEquipada = null;
            System.out.printf("🛡️ %s ha desequipado '%s'. Ahora combate desarmado Power Ranger.%n",
                    this.nombre, armaAnterior);
        } else {
            System.out.printf("ℹ️ %s no tiene ningún arma equipada para retirar Power Ranger.%n", this.nombre);
        }
    }

    public int calcularAtaqueTotal() {
        double bonoTipo = (this.tipoPersonaje != null) ? this.tipoPersonaje.getBonificadorAtaqueBase() : 0.0;
        int ataque = this.nivel * 5 + (int) bonoTipo;
        if (this.armaEquipada != null) {
            ataque += this.armaEquipada.getDanioBase();
        }
        return ataque;
    }

    public void unirseAGremio(Gremio nuevoGremio) {
        if (this.gremio == nuevoGremio) {
            return;
        }
        if (this.gremio != null) {
            Gremio gremioAnterior = this.gremio;
            this.gremio = null;
            gremioAnterior.expulsarMiembro(this);
        }
        this.gremio = nuevoGremio;
        if (nuevoGremio != null && !nuevoGremio.getMiembros().contains(this)) {
            nuevoGremio.agregarMiembro(this);
        }
    }

    public void abandonarGremio() {
        if (this.gremio != null) {
            Gremio antiguo = this.gremio;
            this.gremio = null;
            antiguo.expulsarMiembro(this);
            System.out.printf("🚪 %s ha abandonado el gremio '%s' Power Ranger.%n", this.nombre, antiguo.getNombre());
        } else {
            System.out.printf("ℹ️ %s no pertenece a ningún gremio Power Ranger.%n", this.nombre);
        }
    }

    void setGremio(Gremio gremio) {
        this.gremio = gremio;
    }

    @Override
    public void defender(int danoRecibido) {
        recibirDano(danoRecibido);
    }

    public void realizarAtaqueEspecial(Personaje objetivo) {
        if (objetivo == null) return;
        int dano = calcularAtaqueTotal();
        System.out.printf("%s realiza un ataque especial a %s causando %d de daño Power Ranger.%n",
                this.nombre, objetivo.getNombre(), dano);
        objetivo.defender(dano);
    }

    public boolean estaVivo() {
        return this.estado != EstadoPersonaje.DERROTADO && this.vida > 0;
    }

    public void recibirDano(int cantidad) {
        double defensa = (this.tipoPersonaje != null) ? this.tipoPersonaje.getBonificadorDefensaBase() : 0.0;
        int danoFinal = Math.max(0, cantidad - (int) defensa);
        this.vida -= danoFinal;
        if (this.vida <= 0) {
            this.vida = 0;
            this.estado = EstadoPersonaje.DERROTADO;
        }
        if (this.corazon != null) {
            this.corazon.setSaludBase(this.vida);
        }
    }

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

    public TipoPersonaje getTipoPersonaje() {
        return tipoPersonaje;
    }

    public void setTipoPersonaje(TipoPersonaje tipoPersonaje) {
        if (tipoPersonaje != null) {
            this.tipoPersonaje = tipoPersonaje;
        }
    }

    public EstadoPersonaje getEstado() {
        return estado;
    }

    public void setEstado(EstadoPersonaje estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }

    public int getVida() {
        return vida;
    }

    public int getPuntosMana() {
        return puntosMana;
    }

    public void setPuntosMana(int puntosMana) {
        this.puntosMana = Math.max(0, puntosMana);
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicion(int x, int y) {
        if (x < 0 || x >= 5 || y < 0 || y >= 5) {
            System.out.println("Posición fuera de rango Power Ranger. Se cancela el movimiento.");
            return;
        }
        this.posicionX = x;
        this.posicionY = y;
    }

    public Arma getArmaEquipada() {
        return armaEquipada;
    }

    public Gremio getGremio() {
        return gremio;
    }

    public CorazonRPG getCorazon() {
        return corazon;
    }

    public int getCapacidadMaximaHabilidades() {
        return capacidadMaximaHabilidades;
    }

    public void setCapacidadMaximaHabilidades(int capacidadMaximaHabilidades) {
        this.capacidadMaximaHabilidades = Math.max(1, capacidadMaximaHabilidades);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Personaje Power Ranger: '").append(nombre).append("' (Arquetipo: ").append(tipoPersonaje)
          .append(", Estado: ").append(estado).append(", Nivel ").append(nivel).append(")\n");
        sb.append("  • Vida: ").append(vida).append(" HP | Maná: ").append(puntosMana).append(" MP\n");
        sb.append("  • Ataque Total: ").append(calcularAtaqueTotal()).append(" | Arma: ")
          .append(armaEquipada != null ? armaEquipada.getNombre() : "Desarmado").append("\n");
        sb.append("  • Habilidades Aprendidas Power Ranger: ").append(habilidades).append("\n");
        sb.append("  • Gremio: ").append(gremio != null ? gremio.getNombre() : "Sin Gremio");
        return sb.toString();
    }
}
