package com.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Verify {

    private static final List<AssertionError> errors = new ArrayList<>();

    public static void verify(Runnable assertion){
        try{
            System.out.println("Verify");
            assertion.run();
        }catch (AssertionError e){
            System.err.println("Verify failed:" +e.getMessage());
            errors.add(e);//Consolido los errores
        }
    }

    public static void verifyAll(){
        if(!errors.isEmpty()){
            AssertionError combined = new AssertionError("Se encontraron los siguiente errores");
            errors.forEach(combined::addSuppressed);
            errors.clear();//Limpia cada test
            throw combined;
        }
    }

}
