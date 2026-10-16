/* package ;
public class PruebaEstudiante {
    public static void main(String[] args) {
        Estudiante estudiante1;
// Intento de invocación sobre una variable no instanciada
        String reporte = estudiante1.obtenerReporteAcademicos();
        System.out.println(reporte);
    }
}
1. El código no compila porque la variable local `estudiante1` podría no haber sido inicializada.
 El compilador informa que la variable podría no estar inicializada.
2. La variable solo fue declarada, pero no se creó ningún objeto con `new`.
Por eso no tiene una instancia desde la cual llamar al método.
3. Una instrucción válida es:
java
Estudiante estudiante1 = new Estudiante();
Después de esa línea ya se puede llamar a estudiante1.obtenerReporteAcademicos()`.
 */