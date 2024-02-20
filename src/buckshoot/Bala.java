package buckshoot;

public class Bala {
    public static final String RED_BACKGROUND = "\033[41m";  // RED
    public static final String BLUE_BACKGROUND = "\033[44m"; // BLUE
    public static final String RESET = "\033[0m";            // RESET
    private Color color;
    private int damage;

    public Bala() {
        color = ((int)(Math.random()*2))==0?Color.AZUL:Color.ROJO;
        damage = color.equals(Color.AZUL)?0:1;
    }

    public Bala(Color color) {
        this.color = color;
        damage = color.equals(Color.AZUL)?0:1;
    }

    public Color getColor() {
        return color;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        String colorBala = color.equals(Color.ROJO)?RED_BACKGROUND:BLUE_BACKGROUND;
        return String.format("%s", colorBala+" "+RESET);
    }
}