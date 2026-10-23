# Taller 2: Java POO bysofii

Repositorio integral del taller de Programación Orientada a Objetos en Java, enfocado en conceptos fundamentales, relaciones entre clases (UML a código), colecciones avanzadas (`Set`, `Map`), enumeraciones fuertemente tipadas y jerarquía de excepciones de dominio.

---

## Estructura del Proyecto

```
src/
├── com/arena/rpg/                         # Arena RPG: Núcleo y Arquitectura Modular
│   ├── model/                             # Clases de dominio y modelos de datos
│   │   ├── enums/                         # Enumeraciones fuertemente tipadas con estado y comportamiento
│   │   │   ├── TipoPersonaje.java         # Roles: GUERRERO, MAGO, ARQUERO, PICARO (stats y multiplicadores)
│   │   │   ├── EstadoPersonaje.java       # Estados: VIVO, DERROTADO, ENVENENADO, PARALIZADO (lógica de acción)
│   │   │   └── RaridadItem.java           # Niveles: COMUN, RARO, EPICO, LEGENDARIO (colores y multiplicador)
│   │   ├── Item.java                      # Modelo con equals/hashCode por id y Comparable por nombre/precio
│   │   ├── PerfilJugador.java             # Perfil con TreeSet<String> para logros y HashMap para estadísticas
│   │   └── Habilidad.java                 # Modelo de técnica con equals/hashCode case-insensitive
│   ├── exception/                         # Jerarquía de Excepciones del Dominio
│   │   ├── JuegoException.java            # Excepción verificada base (Checked Exception)
│   │   ├── SinManaException.java          # Excepción verificada con trazabilidad de maná actual vs requerido
│   │   ├── CapacidadExcedidaException.java# Excepción verificada para límites de inventario y habilidades
│   │   └── VidaInsuficienteException.java # Excepción no verificada (Unchecked RuntimeException)
│   ├── engine/                            # Motores de Lógica y Colecciones Especializadas
│   │   ├── Bestiario.java                 # Gestión de enemigos y bajas con HashMap<String, Integer> O(1)
│   │   ├── InventarioTienda.java          # Catálogo ordenado alfabéticamente con TreeMap<String, Double>
│   │   └── CombateEngine.java             # Orquestador de turnos, consumo de maná, daño y estados
│   ├── Main.java                          # Aplicación interactiva de consola con Scanner resiliente
│   ├── PruebaTaller9.java                 # Suite de 10 pruebas unitarias con aserciones (-ea) para Taller 9
│   ├── Personaje.java                     # Entidad central con Set de habilidades únicas, vida y maná
│   ├── Arma.java                          # Asociación Unidireccional (0..1) con Personaje
│   ├── CorazonRPG.java                    # Composición Fuerte (1..1) con ciclo de vida atado a Personaje
│   ├── Gremio.java                        # Asociación Bidireccional sincronizada con Personaje
│   ├── Escuadron.java                     # Agregación Débil (0..*) con ciclo de vida independiente
│   ├── AplicacionArenaRPG.java            # Consola interactiva para relaciones POO (Unidad 2.5)
│   ├── PruebaRelacionesRPG.java           # Pruebas automatizadas de relaciones y memoria (Unidad 2.5)
│   ├── Guerrero.java                      # Subclase táctica de combate
│   ├── Mago.java                          # Subclase táctica de combate
│   ├── ICombatiente.java                  # Interfaz de combate
│   ├── Inventario.java                    # Inventario genérico con control de capacidad
│   ├── Pocion.java                        # Ítem consumible de recuperación
│   └── ArenaRPGMain.java                  # Simulador de combate en tablero 5x5
├── com/taller/poo/                        # Taller 1: Conceptos Fundamentales y Casos de Estudio
│   ├── Vehiculo.java                      # Encapsulamiento, constructores y getters/setters
│   ├── Estudiante.java                    # Modelado de entidad con métodos de negocio
│   ├── CuentaBancaria.java                # Operaciones de saldo y límite de crédito
│   ├── ConversorMoneda.java               # Métodos utilitarios estáticos
│   ├── Principal.java                     # Demostración en consola de clases de dominio
│   ├── SistemaGestionCuentas.java         # Interfaz interactiva para cuentas bancarias
│   └── Case1-4.java                       # Casos de estudio y errores comunes en Java POO
└── co/edu/puj/rpg/                        # Simulador de Batalla Inicial
    ├── modelo/                            # Personaje y Enemigo iniciales
    └── principal/                         # Main de simulación
```

---

## Unidad 2.6: Set, Map, Enumeraciones y Excepciones (Taller 9)

Esta unidad expande el proyecto **Arena RPG** integrando estructuras del Java Collections Framework (`Set` y `Map`), enumeraciones avanzadas con comportamiento polimórfico, una jerarquía formal de excepciones de negocio (checked y unchecked), y una interfaz de consola con manejo robusto de excepciones.

### 1. Enumeraciones Fuertemente Tipadas (`model.enums`)

A diferencia de simples constantes enteras o de texto, los enums en Java son tipos seguros orientados a objetos que pueden almacenar estado interno y definir métodos de negocio:

* **`TipoPersonaje`**: Define los arquetipos jugables (`GUERRERO`, `MAGO`, `ARQUERO`, `PICARO`).
  - Atributos finales: `vidaBase` (int), `manaBase` (int), `multiplicadorAtaque` (double), `multiplicadorDefensa` (double) y `descripcion` (String).
  - Constructor privado para inicializar la matriz de atributos de cada clase.
  - Métodos consultores para aplicar bonificaciones a las estadísticas del personaje.
* **`EstadoPersonaje`**: Modela el ciclo vital y los estados alterados (`VIVO`, `DERROTADO`, `ENVENENADO`, `PARALIZADO`).
  - Método `puedeActuar()`: Devuelve `false` si el estado es `DERROTADO` o `PARALIZADO`.
  - Método `getFactorDanio()`: Aplica penalizaciones según el estado (por ejemplo, daño reducido al 75% si se encuentra envenenado).
* **`RaridadItem`**: Califica los artefactos del juego (`COMUN`, `RARO`, `EPICO`, `LEGENDARIO`).
  - Atributos: `colorTerminal` (códigos de escape ANSI para resaltar la consola) y `multiplicadorPrecio` (factor económico de $1.0\times$ a $5.0\times$).
  - Método `getPrecioFinal(double precioBase)`: Calcula dinámicamente el valor en el mercado aplicando el factor de rareza.

---

### 2. Conjuntos (`Set`) y Contratos de Identidad

Se integran colecciones que prohíben elementos duplicados para garantizar la integridad de las reglas de juego:

* **`HashSet<String>` para Habilidades Únicas (`Personaje`)**:
  - Almacena los nombres de las habilidades aprendidas por el personaje.
  - Ofrece búsquedas e inserciones en tiempo constante promedio $O(1)$.
  - El método `aprenderHabilidad(String habilidad)` verifica si la habilidad ya existe; en caso afirmativo, no se duplica en el conjunto.
  - Valida el límite máximo de habilidades mediante `CapacidadExcedidaException`.
* **`TreeSet<String>` para Logros Alfabéticos (`PerfilJugador`)**:
  - Mantiene la lista de títulos y condecoraciones del jugador siempre ordenada según el orden natural lexicográfico.
  - Inserciones y búsquedas en tiempo logarítmico $O(\log n)$ basadas en árboles rojinegros.
* **Contratos `equals()` y `hashCode()`**:
  - **`Item`**: Compara unicidad exclusivamente mediante su identificador único `id`. Implementa `Comparable<Item>` ordenando por nombre alfabético y desempatando por precio.
  - **`Habilidad`**: Implementa `equals()` y `hashCode()` normalizando el nombre a minúsculas (`toLowerCase().trim()`), evitando que variaciones tipográficas ("Golpe" vs "golpe") creen duplicados en colecciones basadas en hash.

---

### 3. Diccionarios y Mapas (`Map`)

Se estructuran repositorios clave-valor orientados a consultas de alta velocidad y catálogos ordenados:

* **`HashMap<String, Integer>` en `Bestiario`**:
  - Registra las bajas de monstruos derrotados indexadas por el nombre de la criatura.
  - Acceso directo y actualización en $O(1)$.
  - Uso de `containsKey(nombre)` para verificar registros previos y `getOrDefault(nombre, 0)` para acumular derrotas de manera segura y concisa.
  - Iteración eficiente de pares clave-valor a través del método `entrySet()` para la visualización del reporte del bestiario.
* **`TreeMap<String, Double>` en `InventarioTienda`**:
  - Estructura el catálogo de la tienda de la Arena ordenado alfabéticamente por el nombre del artículo.
  - Permite búsquedas ordenadas y navegación mediante métodos de la interfaz `NavigableMap`:
    - `catalogo.subMap(fromKey, toKey)` para filtrar artículos por rangos de nombres.
    - `higherKey(clave)` y `lowerKey(clave)` para localizar el siguiente o anterior artículo en el inventario.

---

### 4. Jerarquía de Excepciones del Dominio (`exception`)

El proyecto implementa una arquitectura defensiva separando condiciones de negocio recuperables de fallos fatales de ejecución:

```
java.lang.Throwable
├── java.lang.Exception
│   └── JuegoException (Checked Exception Base)
│       ├── SinManaException (Checked: manaActual vs manaRequerido)
│       └── CapacidadExcedidaException (Checked: capacidadMaxima)
└── java.lang.RuntimeException
    └── VidaInsuficienteException (Unchecked Exception: vidaActual vs danio)
```

* **Excepciones Verificadas (*Checked Exceptions*)**:
  - `JuegoException`: Clase base que obliga a los métodos consumidores a declarar `throws` o a resolver la contingencia dentro de bloques `try-catch`.
  - `SinManaException`: Se dispara cuando un personaje intenta ejecutar una habilidad sin disponer del maná suficiente. Transporta el maná actual y el requerido para contextualizar el error.
  - `CapacidadExcedidaException`: Se lanza cuando un inventario o libro de habilidades rebasa el número máximo de elementos configurados.
* **Excepciones No Verificadas (*Unchecked Exceptions*)**:
  - `VidaInsuficienteException`: Hereda de `RuntimeException`. Se dispara en el motor de combate ante sacrificios letales o agresiones a personajes derrotados, representando violaciones a las precondiciones del estado del combate.

---

### 5. Motor de Combate (`CombateEngine`)

Orquesta las interacciones tácticas entre combatientes:
* Valida el estado de los personajes con `estado.puedeActuar()`.
* Evalúa la disponibilidad de maná del atacante, lanzando `SinManaException` si es insuficiente.
* Descuenta el maná gastado, calcula el daño final mitigado por defensas y reduce la salud del objetivo.
* Si los puntos de vida llegan a 0 o menos, actualiza automáticamente el estado a `EstadoPersonaje.DERROTADO` y registra la baja en el `Bestiario`.
* Aplica efectos de estados periódicos (como el daño pasivo de veneno).

---

### 6. Aplicación de Consola Interactiva y Resiliencia (`Main.java`)

Punto de entrada interactivo para el usuario ("Power Ranger"):
* **Arquitectura de menú cíclico**: Opciones para administrar perfil, consultar bestiario, explorar la tienda con `TreeMap`, entrenar habilidades (`Set`) y librar batallas con `CombateEngine`.
* **Resiliencia ante entradas inválidas**:
  - Captura explícita de `InputMismatchException` para prevenir caídas catastróficas si el usuario introduce texto en lugar de un número.
  - Limpieza sistemática del búfer mediante `scanner.nextLine()` en bloques de manejo de errores.
  - Estructuración `try-catch-finally` para la liberación adecuada del recurso `Scanner`.
  - Mensajes claros y guiados orientados al Power Ranger.

---

### 7. Suite de Pruebas Automatizadas (`PruebaTaller9.java`)

Contiene una suite con 10 pruebas unitarias con aserciones activas (`assert` / `-ea`):
1. **Prueba de Enums**: Inicialización de atributos, multiplicadores y colores ANSI.
2. **Prueba de `HashSet`**: Unicidad estricta y eliminación de habilidades duplicadas.
3. **Prueba de `CapacidadExcedidaException`**: Lanzamiento y captura ante desbordamiento.
4. **Prueba de `TreeSet`**: Ordenamiento alfabético garantizado en logros del jugador.
5. **Prueba de `HashMap`**: Registro $O(1)$, consultas con `containsKey`, `getOrDefault` e iteración con `entrySet()`.
6. **Prueba de `TreeMap`**: Catálogo ordenado alfabéticamente y navegación de claves.
7. **Prueba de `SinManaException`**: Validación checked al consumir más maná del disponible.
8. **Prueba de `VidaInsuficienteException`**: Lanzamiento unchecked ante daños letales inconsistentes.
9. **Prueba de Contrato `equals()` y `hashCode()`**: Identidad semántica de `Habilidad` e `Item`.
10. **Prueba de `CombateEngine`**: Consumo de maná, daño, estados alterados y transición a derrotado.

---

## Unidad 2.5: Relaciones entre Clases en Java (Arena RPG)

Implementación del taller práctico enfocado en modelado UML y relaciones en Java:

1. **Asociación Unidireccional (`Personaje -> Arma`, 0..1)**:
   - `Personaje` almacena un atributo `armaEquipada`. Si es `null`, combate desarmado.
   - Métodos: `equiparArma(Arma)`, `desequiparArma()`, `calcularAtaqueTotal()`.
   - Protección con validación de nulos previa para evitar `NullPointerException`.

2. **Asociación Bidireccional (`Personaje <-> Gremio`, 0..* a 1)**:
   - Sincronización cruzada en doble vía manteniendo consistencia en memoria sin recursión infinita.
   - Desvinculación automática del gremio anterior al cambiarse.
   - Prevención de `StackOverflowError` en `toString()` imprimiendo únicamente identificadores simples de miembros.

3. **Agregación Débil (`Escuadron -> Personaje`, 0..*)**:
   - `Escuadron` agrupa personajes con ciclo de vida independiente.
   - La expulsión o disolución del escuadrón no altera el estado de los personajes en la memoria Heap.

4. **Composición Fuerte (`Personaje -> CorazonRPG`, 1..1)**:
   - `CorazonRPG` se instancia internamente dentro del constructor de `Personaje`.
   - No existen setters públicos para reemplazar la instancia, protegiendo el encapsulamiento y el ciclo de vida dependiente.

5. **Aplicación de Consola (`AplicacionArenaRPG`)**:
   - Menú interactivo con ciclo `do-while` y `switch`.
   - Formateo de reportes y tablas tabulares con `System.out.printf`.
   - Concatenación eficiente de cadenas mediante `StringBuilder`.

---

## Taller 1: Conceptos Fundamentales y Casos de Estudio

### Clases de Dominio
- **`Vehiculo`**: Atributos (`marca`, `modelo`, `kilometraje`, `velocidadMaxima`, `encendido`), constructores sobrecargados y métodos de acceso.
- **`Estudiante`**: Métodos de negocio para cálculo de estado académico y reporte formateado con `obtenerReporteAcademicos()`.
- **`CuentaBancaria`**: Validación de operaciones financieras de depósito, retiro y control de saldo y crédito disponible.
- **`ConversorMoneda`**: Métodos estáticos utilitarios para conversión y formateo de divisas.

### Casos de Estudio (`Case1-4.java`)
- **`Case1.java`**: Error de variable local no inicializada.
- **`Case2.java`**: Invocación inválida de método de instancia desde contexto estático.
- **`Case3.java`**: Confusión sintáctica entre declaración de constructor y métodos con retorno.
- **`Case4.java`**: Sombreado de variables (*variable shadowing*) y uso de `this`.

---

## Requisitos del Sistema

- **Java Development Kit (JDK)**: Versión 8 o superior (probado en OpenJDK 17 y 21).
- **IDE Recomendado**: IntelliJ IDEA / VS Code / Eclipse (incluye configuración `.iml`).
- **Terminal Bash / Zsh**: Compatible con secuencias de escape ANSI.

---

## Compilación y Ejecución

### 1. Compilación de todo el proyecto
Para compilar todas las clases del repositorio en el directorio `src`:

```bash
# Compilar todos los paquetes del proyecto
javac -cp src $(find src -name "*.java")
```

O compilar específicamente el módulo de **Arena RPG**:

```bash
javac -cp src \
  src/com/arena/rpg/*.java \
  src/com/arena/rpg/model/enums/*.java \
  src/com/arena/rpg/model/*.java \
  src/com/arena/rpg/exception/*.java \
  src/com/arena/rpg/engine/*.java
```

### 2. Ejecutar Aplicaciones de la Unidad 2.6 (Taller 9)

```bash
# Ejecutar la aplicación interactiva principal (Menú Power Ranger resiliente)
java -cp src com.arena.rpg.Main

# Ejecutar la suite de pruebas automatizadas con aserciones activas
java -ea -cp src com.arena.rpg.PruebaTaller9
```

### 3. Ejecutar Aplicaciones de la Unidad 2.5 (Relaciones POO)

```bash
# Menú interactivo de relaciones POO (Asociación, Composición, Agregación)
java -cp src com.arena.rpg.AplicacionArenaRPG

# Suite de pruebas unitarias y de memoria (Unidad 2.5)
java -ea -cp src com.arena.rpg.PruebaRelacionesRPG

# Simulador de batalla en tablero
java -cp src com.arena.rpg.ArenaRPGMain
```

### 4. Ejecutar Aplicaciones del Taller 1

```bash
# Demostración general de clases de dominio
java -cp src com.taller.poo.Principal

# Menú interactivo de gestión bancaria
java -cp src com.taller.poo.SistemaGestionCuentas
```

---

## Autor

Sofia Carvajal - [lorensofian-nu](https://github.com/lorensofian-nu)

---

## Licencia

Proyecto académico - Taller de Programación Orientada a Objetos en Java.
