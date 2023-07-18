package de.huk.schulung.samples;

import java.lang.reflect.Method;

public class GreetingFramework {

    public static void handleGreetings(Class<?> c) throws Exception {
        // Klasse nach Methoden durchsuchen, die mit @Greet annotiert sind
        Method[] methods = c.getDeclaredMethods();
        for(Method m : methods) {
            Greet greet = m.getAnnotation(Greet.class);
            if(null != greet) {
                // für jede Methode:
                //  - Erstelle eine Instanz der Klasse (Standardkonstruktor!)
                Object o = c.getDeclaredConstructor().newInstance();
                //  - Rufe die Methode auf (keine Parameter)
                String message = m.invoke(o).toString();
                //  - Ermittle den Namen des Grüßers
                String name = greet.value();
                if(name.isEmpty()) {
                    name = m.getName();
                }
                //  - Logge den Gruß
                System.out.println(name + " grüßt: " + message);
            }

        }
    }

}
