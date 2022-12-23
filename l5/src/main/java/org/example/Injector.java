package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/*
 * @author Синицин Данила
 */
public class Injector {
    private static Properties proper = new Properties (  );

    public <T> T inject(T object) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, NullPointerException {
        loading ();
        Field[] field = object.getClass ().getDeclaredFields ();
        for (Field f:field
        ) {
            if(f.getAnnotation ( AutoInjectable.class )!=null){
                f.setAccessible ( true );
                String fieldClassname=f.getType ().toString ().split ( " " )[1];
                String injectedClassName = proper.getProperty ( fieldClassname );
                try {
                    Object value = Class.forName(injectedClassName).newInstance();
                    f.set (object, value);
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Exception");
                };

            }
        }
        return object;
    }

    /**
     * We load from needed file all the properties
     */
    private void loading() throws IOException {
        FileInputStream inputStream= new FileInputStream ( "src/main/resources/data.properties" );
        proper.load ( inputStream );

    }
}
