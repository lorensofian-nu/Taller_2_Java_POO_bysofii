/* package ;
public class GestorVehiculos {
    public void encenderMotor() {
        System.out.println("El motor del vehículo ha sido encendido.");
    }
    public static void main(String[] args) {
 Intento de llamada directa a método de instancia desde el método

        estático main

        encenderMotor();
    }
}
1. El compilador informa un error similar a `non-static method encenderMotor() cannot be referenced
from a static context`.

2. `main` es estático y pertenece a la clase; `encenderMotor()` es un método de instancia que necesita
un objeto concreto. Sin una instancia, Java no sabe sobre qué vehículo ejecutar el método.

3. Hay dos alternativas:
   - Crear un objeto y llamarlo: `GestorVehiculos gestor = new GestorVehiculos(); gestor.encenderMotor();`
   - Declarar el método como `static` si realmente no necesita datos de una instancia: `public static void
    encenderMotor() { ... }`.

 */