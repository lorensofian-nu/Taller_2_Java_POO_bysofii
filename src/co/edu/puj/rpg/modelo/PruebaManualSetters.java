package co.edu.puj.rpg.modelo;

public class PruebaManualSetters {

    public static void main(String[] args) {
        int[] limites = { -10, 0, 100000 };

        System.out.println("=== setPuntosVida (vidaMaxima = 100) ===");
        for (int valor : limites) {
            Personaje p = new Personaje("Prueba", 100, 15, 50.0);
            p.setPuntosVida(valor);
            boolean coherente = (p.getPuntosVida() == 0 && !p.isEstaVivo())
                    || (p.getPuntosVida() > 0 && p.getPuntosVida() <= p.getVidaMaxima() && p.isEstaVivo());
            System.out.printf("setPuntosVida(%d) -> vida=%d, estaVivo=%s, coherente=%s%n",
                    valor, p.getPuntosVida(), p.isEstaVivo(), coherente);
        }

        System.out.println("\n=== setOro (oro inicial = 50.00) ===");
        for (int valor : limites) {
            Personaje p = new Personaje("Prueba", 100, 15, 50.0);
            boolean aceptado = p.setOro(valor);
            System.out.printf("setOro(%d) -> aceptado=%s, oro=%.2f%n", valor, aceptado, p.getOro());
        }

        System.out.println("\n=== setNivel (nivel inicial = 1) ===");
        for (int valor : limites) {
            Personaje p = new Personaje("Prueba", 100, 15, 50.0);
            p.setNivel(valor);
            System.out.printf("setNivel(%d) -> nivel=%d%n", valor, p.getNivel());
        }
    }
}
