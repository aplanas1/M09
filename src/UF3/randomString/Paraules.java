package UF3.randomString;

import java.util.ArrayList;
import java.util.List;

public class Paraules {
    List<String> paraules = new ArrayList<>();

    public Paraules() {
        paraules.add("Hola");
        paraules.add("Adios");
        paraules.add("Pepe");
        paraules.add("Cola");
        paraules.add("Caracola");
        paraules.add("Nariz");
        paraules.add("Jota");
    }

    public String getParaula(){
        int paraula = (int) (Math.random()*7);
        System.out.println(paraules.get(paraula));
        return paraules.get(paraula);
    }
}
