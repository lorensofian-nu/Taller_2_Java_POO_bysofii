# Taller 2: Java POO bysofii

Repositorio del taller de Programación Orientada a Objetos en Java, enfocado en conceptos fundamentales de POO.

## Descripción

Este proyecto contiene implementaciones prácticas de conceptos de Programación Orientada a Objetos en Java, incluyendo clases de dominio, utilidades y aplicaciones de consola.

## Estructura del Proyecto

```
src/com/taller/poo/
├── Vehiculo.java              # Clase de dominio: Vehiculo con atributos y constructores
├── Estudiante.java            # Clase de dominio: Estudiante con reporte academico
├── CuentaBancaria.java        # Clase de dominio: Cuenta bancaria con deposito/retiro
├── ConversorMoneda.java       # Utilidad estática para conversion de moneda
├── Principal.java             # Clase principal con demo de todas las clases
├── SistemaGestionCuentas.java # Aplicación interactiva de gestion de cuentas
├── Case1.java                 # Caso de estudio: Variable no inicializada
├── Case2.java                 # Caso de estudio: Metodo no estatico desde contexto estatico
├── Case3.java                 # Caso de estudio: Constructor mal declarado
└── Case4.java                 # Caso de estudio: Sombreado de variables
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

## Requisitos

- Java 8 o superior
- IntelliJ IDEA (opcional, proyecto incluye configuracion .iml)

## Compilación y Ejecución

```bash
# Compilar todas las clases
javac src/com/taller/poo/*.java

# Ejecutar Principal
java -cp src com.taller.poo.Principal

# Ejecutar SistemaGestionCuentas
java -cp src com.taller.poo.SistemaGestionCuentas
```

## Autor

Sofia Carvajal - [lorensofian-nu](https://github.com/lorensofian-nu)

## Licencia

Proyecto academico - Taller de Java POO
