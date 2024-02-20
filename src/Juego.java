import buckshoot.Color;
import buckshoot.Escopeta;
import buckshoot.Jugador;

public class Juego {
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE

    /**
     * Muestra los nombres y las vidas de los juagadores en juego
     * @param j1 Datos del jugador 1
     * @param j2 Datos del jugador 2
     */
    public static void mostrarJugadores(Jugador j1, Jugador j2) {
        System.out.printf("%n%s%n%s%n%n%s%n%s%n",
                                GREEN+"********** JUGADORES **********"+RESET,
                                j1,
                                j2,
                                GREEN+"*******************************"+RESET);
    }
    
    /**
     * Menú principal del juego
     * @return Opciones:
     *         1: Empieza el juego |
     *         2: Sale del juego
     */
    public static int gameMenu() {
        System.out.printf(GREEN+"%n%s%n%s%n%s%n%s%n%s%n%s"+RESET,
                        "########## BUCKSHOOT ROULETTE ##########",
                                YELLOW+"1"+GREEN+". Jugar (solo)",
                                YELLOW+"2"+GREEN+". Jugar (2 jugadores)",
                                YELLOW+"3"+GREEN+". Guía del juego",
                                YELLOW+"4"+GREEN+". Salir",
                                "Elige una opción: ");
        
        System.out.print(YELLOW);
        int opcion = Integer.parseInt(System.console().readLine());
        System.out.print(RESET);
        
        while ((opcion != 1) && (opcion != 2) && (opcion != 3) && (opcion != 4)) {
            System.out.print(GREEN+"Elige una opción: "+RESET);
            System.out.print(YELLOW);
            opcion = Integer.parseInt(System.console().readLine());
            System.out.print(RESET);
        }
        
        return opcion;
    }
    
    /**
     * Crea un jugador con el nombre introducido
     * @return Jugador creado
     */
    public static Jugador crearJugador() {
        System.out.print(GREEN+"Introduce tu nombre: "+RESET);
        System.out.print(YELLOW);
        String nombre = System.console().readLine();
        System.out.print(RESET);
        
        return new Jugador(nombre);
    }
    
    /**
     * Menú de selección de disparo
     * @return Opciones:
     *         1: Disparas al otro jugador |
     *         2: Te disparas a tí mismo
     */
    public static int aQuienDispara() {
        System.out.printf(GREEN+"%n%s%n%s%n%s%n%s"+RESET,
                        "¿A quién vas a disparar?",
                                YELLOW+"1"+GREEN+". Al otro jugador",
                                YELLOW+"2"+GREEN+". A tí mismo",
                                "Elige una opción: ");
        
        System.out.print(YELLOW);
        int opcion = Integer.parseInt(System.console().readLine());
        System.out.print(RESET);
        
        while (opcion != 1 && opcion != 2) {
            System.out.print(GREEN+"Elige una opción: "+RESET);
            System.out.print(YELLOW);
            opcion = Integer.parseInt(System.console().readLine());
            System.out.print(RESET);
        }
        
        return opcion;
    }
    
    /**
     * Recarga la escopeta con balas aleatorias
     * @param escopeta Escopeta a recargar
     * @throws InterruptedException 
     */
    public static void recargarEscopeta(Escopeta escopeta) throws InterruptedException {
        escopeta.recargar();
        System.out.printf(YELLOW+"%nEstas son las balas en juego");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.println("."+RESET);
        Thread.sleep(500);
        
        System.out.printf("%n%s%n", escopeta);
        
        System.out.printf(YELLOW+"%nIntroduzco las balas en un orden aleatorio");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.println("."+RESET);
        Thread.sleep(1000);
        
        escopeta.mezclar();
    }
    
    public static void game2Players() throws InterruptedException {
        // CREACIÓN DEL JUGADOR 1
        System.out.printf(GREEN+"%n##### JUGADOR 1 #####%n"+RESET);
        Jugador j1 = crearJugador();
        
        // CREACIÓN DEL JUGADOR 2
        System.out.printf(GREEN+"%n##### JUGADOR 2 #####%n"+RESET);
        Jugador j2 = crearJugador();
        
        // MUESTRA LOS JUGADORES Y SUS VIDAS
        mostrarJugadores(j1, j2);
        
        // CREA LA ESCOPETA Y LA RECARGA EN UN ORDEN ALEATORIO
        Escopeta escopeta = new Escopeta();
        recargarEscopeta(escopeta);
        
        // ELIGE ALEATORIAMENTE QUIEN EMPIEZA EL TURNO
        Jugador jugadorEnJuego = ((int)(Math.random()*2))==0?j1:j2;
        Jugador jugadorContrario = jugadorEnJuego==j1?j2:j1;
        System.out.printf(YELLOW+"%nEl turno es de %s%n"+RESET, jugadorEnJuego.getNombre().toUpperCase());
        
        // ESTO ES LA PARTIDA COMPLETA
        while ((j1.getVidas() > 0) && (j2.getVidas() > 0)) {
            // MENÚ DE A QUIÉN QUIERE DISPARAR
            switch (aQuienDispara()) {
                // DISPARA AL JUGADOR CONTRARIO
                case 1:{
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    // SI LA BALA ES ROJA LE RESTA UNA VIDA
                    if (escopeta.disparar().getColor() == Color.ROJO) {
                        System.out.printf(RED+"%nBUUUUUUUUM!! La bala era ROJA%n"+RESET);
                        jugadorContrario.setVidas(jugadorContrario.getVidas()-1);
                    // SI LA BALA ES AZUL NO LE HACE NADA
                    } else
                        System.out.printf(BLUE+"%nClick... La bala era AZUL%n"+RESET);
                    
                    // SI EL JUGADOR HA MUERTO TERMINA LA PARTIDA
                    if (jugadorContrario.getVidas() == 0)
                        break;
                    
                    // CAMBIA EL TURNO AL JUGADOR CONTRARIO
                    Thread.sleep(1000);
                    System.out.printf(YELLOW+"%nEl turno es de %s%n"+RESET, jugadorContrario.getNombre().toUpperCase());
                    Thread.sleep(1000);
                    Jugador aux = jugadorEnJuego;
                    jugadorEnJuego = jugadorContrario;
                    jugadorContrario = aux;
                    
                    // COMPRUEBA SI LA ESCOPETA ESTÁ VACÍA Y EN ESE CASO LA RECARGA
                    if (escopeta.getNumBalas() == 0)
                        recargarEscopeta(escopeta);
                    
                    break;
                }
                
                // SE DISPARA A SÍ MISMO
                case 2:{
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    // SI LA BALA ES ROJA LE RESTA UNA VIDA
                    if (escopeta.disparar().getColor() == Color.ROJO) {
                        System.out.printf(RED+"%nBUUUUUUUUM!! La bala era ROJA%n"+RESET);
                        jugadorEnJuego.setVidas(jugadorEnJuego.getVidas()-1);
                        
                        // SI EL JUGADOR HA MUERTO TERMINA LA PARTIDA
                        if (jugadorEnJuego.getVidas() == 0)
                            break;
                        
                        Thread.sleep(1000);
                        // CAMBIA EL TURNO AL JUGADOR CONTRARIO
                        System.out.printf(YELLOW+"%nEl turno es de %s%n"+RESET, jugadorContrario.getNombre().toUpperCase());
                        Thread.sleep(1000);
                        Jugador aux = jugadorEnJuego;
                        jugadorEnJuego = jugadorContrario;
                        jugadorContrario = aux;
                    } else {
                    // SI LA BALA ES AZUL NO LE HACE NADA
                        System.out.printf(BLUE+"%nClick... La bala era AZUL%n"+RESET);
                        
                        // SI EL JUGADOR HA MUERTO TERMINA LA PARTIDA
                        if (jugadorEnJuego.getVidas() == 0)
                            break;
                        
                        Thread.sleep(1000);
                        System.out.printf(YELLOW+"%nEl turno es de %s%n"+RESET, jugadorEnJuego.getNombre().toUpperCase());
                        Thread.sleep(1000);
                    }
                    
                    // SI EL JUGADOR HA MUERTO TERMINA LA PARTIDA
                    if (jugadorEnJuego.getVidas() == 0)
                        break;
                    
                    // COMPRUEBA SI LA ESCOPETA ESTÁ VACÍA Y EN ESE CASO LA RECARGA
                    if (escopeta.getNumBalas() == 0)
                        recargarEscopeta(escopeta);
                    
                    break;
                }
            }
            // MUESTRA LOS JUGADORES Y SUS VIDAS
            mostrarJugadores(j1, j2);
        }
        // MUESTRA EL GANADOR DE LA PARTIDA
        Jugador ganador = j1.getVidas()>0?j1:j2;
        System.out.printf(YELLOW+"%nHA GANADO %s!!%n"+RESET, ganador.getNombre().toUpperCase());
    }
    
    public static void game1Player() throws InterruptedException {
        // CREACIÓN DEL JUGADOR 1
        System.out.printf(GREEN+"%n##### JUGADOR 1 #####%n"+RESET);
        Jugador j1 = crearJugador();
        
        // CREACIÓN DEL JUGADOR 2
        Jugador j2 = new Jugador("Bot");
        
        // MUESTRA LOS JUGADORES Y SUS VIDAS
        mostrarJugadores(j1, j2);
        
        // CREA LA ESCOPETA Y LA RECARGA EN UN ORDEN ALEATORIO
        Escopeta escopeta = new Escopeta();
        recargarEscopeta(escopeta);
        
        // ELIGE ALEATORIAMENTE QUIEN EMPIEZA EL TURNO
        Jugador jugadorEnJuego = j1;
        Jugador jugadorContrario = j2;
        System.out.printf(YELLOW+"%nEl turno es de %s%n"+RESET, jugadorEnJuego.getNombre().toUpperCase());
        
        // ESTO ES LA PARTIDA COMPLETA
        while ((j1.getVidas() > 0) && (j2.getVidas() > 0)) {
            int opcionBot = escopeta.getNumBalasRojas() >= escopeta.getNumBalasAzules()?1:2;
            // MENÚ DE A QUIÉN QUIERE DISPARAR
            switch (jugadorEnJuego==j1?aQuienDispara():opcionBot) {
                // DISPARA AL JUGADOR CONTRARIO
                case 1:{
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    // SI LA BALA ES ROJA LE RESTA UNA VIDA
                    if (escopeta.disparar().getColor() == Color.ROJO) {
                        System.out.printf(RED+"%nBUUUUUUUUM!! La bala era ROJA%n"+RESET);
                        jugadorContrario.setVidas(jugadorContrario.getVidas()-1);
                    // SI LA BALA ES AZUL NO LE HACE NADA
                    } else
                        System.out.printf(BLUE+"%nClick... La bala era AZUL%n"+RESET);
                    
                    // SI EL JUGADOR HA MUERTO TERMINA LA PARTIDA
                    if (jugadorContrario.getVidas() == 0)
                        break;
                    
                    // CAMBIA EL TURNO AL JUGADOR CONTRARIO
                    Thread.sleep(1000);
                    System.out.printf(YELLOW+"%nEl turno es de %s%n"+RESET, jugadorContrario.getNombre().toUpperCase());
                    Thread.sleep(1000);
                    Jugador aux = jugadorEnJuego;
                    jugadorEnJuego = jugadorContrario;
                    jugadorContrario = aux;
                    
                    // COMPRUEBA SI LA ESCOPETA ESTÁ VACÍA Y EN ESE CASO LA RECARGA
                    if (escopeta.getNumBalas() == 0)
                        recargarEscopeta(escopeta);
                    
                    break;
                }
                
                // SE DISPARA A SÍ MISMO
                case 2:{
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    System.out.print(" ");
                    Thread.sleep(500);
                    // SI LA BALA ES ROJA LE RESTA UNA VIDA
                    if (escopeta.disparar().getColor() == Color.ROJO) {
                        System.out.printf(RED+"%nBUUUUUUUUM!! La bala era ROJA%n"+RESET);
                        jugadorEnJuego.setVidas(jugadorEnJuego.getVidas()-1);
                        
                        // SI EL JUGADOR HA MUERTO TERMINA LA PARTIDA
                        if (jugadorEnJuego.getVidas() == 0)
                            break;
                        
                        Thread.sleep(1000);
                        // CAMBIA EL TURNO AL JUGADOR CONTRARIO
                        System.out.printf(YELLOW+"%nEl turno es de %s%n"+RESET, jugadorContrario.getNombre().toUpperCase());
                        Thread.sleep(1000);
                        Jugador aux = jugadorEnJuego;
                        jugadorEnJuego = jugadorContrario;
                        jugadorContrario = aux;
                    } else {
                    // SI LA BALA ES AZUL NO LE HACE NADA
                        System.out.printf(BLUE+"%nClick... La bala era AZUL%n"+RESET);
                        
                        // SI EL JUGADOR HA MUERTO TERMINA LA PARTIDA
                        if (jugadorEnJuego.getVidas() == 0)
                            break;
                        
                        Thread.sleep(1000);
                        System.out.printf(YELLOW+"%nEl turno es de %s%n"+RESET, jugadorEnJuego.getNombre().toUpperCase());
                        Thread.sleep(1000);
                    }
                    
                    // SI EL JUGADOR HA MUERTO TERMINA LA PARTIDA
                    if (jugadorEnJuego.getVidas() == 0)
                        break;
                    
                    // COMPRUEBA SI LA ESCOPETA ESTÁ VACÍA Y EN ESE CASO LA RECARGA
                    if (escopeta.getNumBalas() == 0)
                        recargarEscopeta(escopeta);
                    
                    break;
                }
            }
            // MUESTRA LOS JUGADORES Y SUS VIDAS
            mostrarJugadores(j1, j2);
        }
        // MUESTRA EL GANADOR DE LA PARTIDA
        Jugador ganador = j1.getVidas()>0?j1:j2;
        System.out.printf(YELLOW+"%nHA GANADO %s!!%n"+RESET, ganador.getNombre().toUpperCase());
    }
    
    public static void guia() {
        System.out.printf(YELLOW+"%n%s%n%s%n%n%s%n%s%n%s%n%n%s%n%s%n%n%s%n%s%n%s%n%n%s%n"+RESET,
                        "Buckshoot Roulette es un juego basado en la ruleta rusa pero",
                                "cambiando ciertos aspectos sobre las balas.",
                                
                                "La escopeta se recargará con un orden aleatorio y una cantidad ",
                                "aleatoria de balas (entre 2 y 8) habiendo siempre mínimo una ",
                                "bala "+RED+"ROJA"+YELLOW+" y una bala "+BLUE+"AZUL"+YELLOW+".",
                                
                                RED+"Las balas ROJAS quitan 1 vida."+YELLOW,
                                BLUE+"Las balas AZULES NO quitan vidas."+YELLOW,
                                
                                "En el caso de disparar al contrincante el siguiente turno será suyo.",
                                
                                "En el caso de dispararte a tí mismo el siguiente turno será tuyo",
                                "siempre y cuando la bala disparada no sea "+RED+"ROJA"+YELLOW+".",
                                
                                "Ganará el jugador que consiga que su contrincante pierda sus "+GREEN+"3 VIDAS"+YELLOW+".");
    }
    
    public static void main(String[] args) throws Exception {
        int opcion = 0;
        
        while (opcion != 4) {
            // MUESTRA EL MENÚ DEL JUEGO
            opcion = gameMenu();
            
            // EJECUTA LA OPCIÓN INDICADA
            switch (opcion) {
                // INICIA EL JUEGO
                case 1:
                    game1Player();
                    break;
                
                case 2:
                    game2Players();
                    break;
                
                // MUESTRA LA GUÍA DEL JUEGO
                case 3:
                    guia();
                    break;
                
                // SALE DEL JUEGO
                case 4:
                    System.out.printf(YELLOW+"%nGracias por jugar al juego!!%n"+RESET);
                    break;
            }
        }
    }
}