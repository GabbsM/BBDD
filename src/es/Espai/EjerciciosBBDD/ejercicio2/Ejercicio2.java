package es.Espai.EjerciciosBBDD.ejercicio2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Haz un programa en modo consola que muestre todos los equipos de la liga. Para ello primero
//debes crear un proyecto Java y agregarle el driver mysql-connector. Hay dos versiones:
// mysql-connector-java-5.1.47.jar
// mysql-connector-java-8.0.15.jar
//NOTA: La versión 8.0.15 introduce una serie de cambios en las clausulas Class.forName y en la
//cadena de conexión.
public class Ejercicio2 {

    public static void main(String[] args) {

        try {

            Connection miConexion;

            miConexion = DriverManager.getConnection("jdbc:mysql://localhost/futbol", "root", "");

            Statement miStateman = miConexion.createStatement();

            ResultSet miResulset = miStateman.executeQuery("SELECT * FROM EQUIPOS");

            while (miResulset.next()){
                System.out.println(miResulset.getString("Equipo") + "     " + miResulset.getString("Ciudad") + "     " + miResulset.getString("Estadio"));

            }





        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("No se ha podido conectar a la base de datos");
    }
}
