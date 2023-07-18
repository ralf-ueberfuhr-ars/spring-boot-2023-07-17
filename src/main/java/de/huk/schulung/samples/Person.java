package de.huk.schulung.samples;


import java.util.Date;

public class Person {

    public static void main(String[] args) throws Exception {
        GreetingFramework.handleGreetings(Person.class);
    }

    void doSth() {

    }

    @Greet("Tim")
    public String sayHello() {
        return "Cheers";
    }

    @Greet
    public String julia() {
        return "Hallöchen";
    }

    @Greet("Der Timestamper")
    public String blablubb() {
        return "Es ist " + new Date();
    }

    // -> Konsolenausgabe: "Tim grüßt: Cheers"

}
