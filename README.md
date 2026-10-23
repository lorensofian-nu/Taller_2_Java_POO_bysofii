# Taller 2: Java POO bysofii

Repositorio del taller de Programación Orientada a Objetos en Java, enfocado en conceptos fundamentales de POO.

## Descripción

Este proyecto contiene implementaciones prácticas de conceptos de Programación Orientada a Objetos en Java, incluyendo clases de dominio, utilidades y aplicaciones de consola.

## Estructura del Proyecto

```
src/
├── com/arena/rpg/             # Arena RPG (Unidad 2.5: Relaciones POO y combate)
│   ├── Arma.java              # Asociación Unidireccional (0..1) con Personaje
│   ├── CorazonRPG.java        # Composición Fuerte (1..1) con Personaje
│   ├── Personaje.java         # Entidad central integradora de relaciones POO
│   ├── Gremio.java            # Asociación Bidireccional (0..* a 1) sincronizada
│   ├── Escuadron.java         # Agregación Débil (0..*) de personajes
│   ├── AplicacionArenaRPG.java# Menú interactivo en consola con printf y StringBuilder
│   ├── PruebaRelacionesRPG.java# Suite de pruebas unitarias y validación en memoria
│   ├── Guerrero.java          # Subclase táctica de combate
│   ├── Mago.java              # Subclase táctica de combate
│   ├── ICombatiente.java      # Interfaz de combate
│   ├── Inventario.java        # Inventario genérico
│   ├── Pocion.java            # Ítem consumible
│   └── ArenaRPGMain.java      # Simulador de batalla en tablero 5x5
├── com/taller/poo/            # Taller 1: Conceptos iniciales y casos de estudio
└── co/edu/puj/rpg/            # Simulador de batalla inicial Javeriana
```

## Clases de Dominio

### Vehiculo
Clase que modela un vehiculo con:
- Atributos: marca, modelo, kilometraje, velocidadMaxima, encendido
- Constructores: vacío y completo
- Getters para todos los atributos
- Setter para encendido

### Estudiante
Clase que modela un estudiante con:
- Atributos: identificacion, nombreCompleto, promedioCalificaciones, estaMatriculado
- Metodo: obtenerReporteAcademicos() para generar informe formateado

### CuentaBancaria
Clase que modela una cuenta bancaria con:
- Atributos: numeroCuenta, titular, saldo, limiteCredito
- Metodos: depositar(), retirar()
- Manejo de credito disponible

### ConversorMoneda
Clase utilitaria estática con:
- Metodos estáticos: convertirDolaresAEuros(), formatearMoneda()

## Aplicaciones

### Principal
Clase con metodo main que demuestra el uso de todas las clases de dominio, mostrando reportes de vehiculos, estudiantes y cuentas bancarias.

### SistemaGestionCuentas
Aplicación de consola interactiva que permite:
- Depositar en una cuenta
- Retirar de una cuenta
- Ver reporte consolidado de todas las cuentas

## Casos de Estudio

Archivos Case1-4 contienen ejemplos de errores comunes en Java POO con sus explicaciones:

- **Case1.java**: Error de variable no inicializada
- **Case2.java**: Error de metodo no estatico llamado desde contexto estatico
- **Case3.java**: Error en declaracion de constructor
- **Case4.java**: Problema de sombreado de variables

## Unidad 2.5: Relaciones entre Clases en Java (Arena RPG)

Implementación del taller práctico enfocado en modelado UML y relaciones en Java:

1. **Asociación Unidireccional (`Personaje -> Arma`, 0..1)**:
   - `Personaje` almacena un atributo `armaEquipada`. Si es `null`, combate desarmado.
   - Métodos: `equiparArma(Arma)`, `desequiparArma()`, `calcularAtaqueTotal()`.
   - Protección con validación de nulos previa para evitar `NullPointerException`.

2. **Asociación Bidireccional (`Personaje <-> Gremio`, 0..* a 1)**:
   - Sincronización cruzada en doble vía manteniendo consistencia en memoria sin recursión infinita.
   - Desvinculación automática de gremio anterior al cambiarse.
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

## Requisitos

- Java 8 o superior
- IntelliJ IDEA (opcional, proyecto incluye configuracion .iml)

## Compilación y Ejecución

```bash
# Compilar clases de Arena RPG (Unidad 2.5: Relaciones entre Clases)
javac -cp src src/com/arena/rpg/*.java

# Ejecutar la aplicación interactiva de la Arena RPG
java -cp src com.arena.rpg.AplicacionArenaRPG

# Ejecutar las pruebas unitarias y de memoria
java -ea -cp src com.arena.rpg.PruebaRelacionesRPG

# Compilar clases del Taller 1
javac src/com/taller/poo/*.java

# Ejecutar Principal del Taller 1
java -cp src com.taller.poo.Principal

# Ejecutar SistemaGestionCuentas del Taller 1
java -cp src com.taller.poo.SistemaGestionCuentas
```

## Autor

Sofia Carvajal - [lorensofian-nu](https://github.com/lorensofian-nu)

## Licencia

Proyecto academico - Taller de Java POO
