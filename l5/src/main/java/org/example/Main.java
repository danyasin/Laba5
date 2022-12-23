package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException, NullPointerException {
        try {
            SomeBean sb = (new Injector()).inject(new SomeBean());
            sb.foo();
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
            System.out.println("Exception");
        };
    }
}

