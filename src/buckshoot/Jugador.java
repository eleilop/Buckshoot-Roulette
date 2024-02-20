package buckshoot;

public class Jugador {
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String GREEN_BACKGROUND = "\033[42m"; // GREEN
    public static final String RESET = "\033[0m";             // RESET
    private String nombre;
    private int vidas;

    public Jugador (String nombre) {
        this.nombre = nombre;
        this.vidas = 3;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    @Override
    public String toString() {
        String vidas = "";

        for (int i = 0; i < getVidas(); i++)
            vidas += GREEN_BACKGROUND+" "+RESET+" ";

        return String.format(GREEN+"%-24s%s"+RESET,
                            getNombre().toUpperCase(),
                            vidas);
    }
}