package buckshoot;

import java.util.ArrayList;
import java.util.Collections;

public class Escopeta {
    private ArrayList<Bala> cargador;

    public Escopeta() {
        this.cargador = new ArrayList<Bala>();
    }

    public ArrayList<Bala> getCargador() {
        return cargador;
    }

    public int getNumBalas() {
        return cargador.size();
    }

    public int getNumBalasRojas() {
        int contador = 0;
        
        for (Bala bala : cargador) {
            if (bala.getColor()==Color.ROJO)
                contador++;
        }
        
        return contador;
    }

    public int getNumBalasAzules() {
        int contador = 0;
        
        for (Bala bala : cargador) {
            if (bala.getColor()==Color.AZUL)
                contador++;
        }
        
        return contador;
    }

    public void recargar() {
        cargador.add(new Bala(Color.ROJO));
        cargador.add(new Bala(Color.AZUL));

        for (int i = 0; i < (int)(Math.random()*7); i++)
            cargador.add(new Bala());
    }

    public void mezclar() {
        Collections.shuffle(cargador);
    }

    public Bala disparar() {
        return cargador.removeFirst();
    }

    public void vaciar() {
        cargador.clear();
    }

    @Override
    public String toString() {
        String ret = "";

        for (Bala bala : cargador)
            ret += bala+" ";

        return String.format("%s", ret);
    }
}