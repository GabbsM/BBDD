package es.Espai.EjerciciosBBDD.ejercicio3;

//Crea una clase DatabaseFutbol, que contenga:
// un constructor que inicialice un objeto de la clase Connection
// un método ConsultaSelect(String sql), el cual dado una cadena sql, realice la consulta
//pedida

import java.sql.*;

public class DatabaseFutbol {
    private Connection conexion;

    public DatabaseFutbol() {

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/futbol", "root", "");


        } catch (Exception e) {

        }
    }

    public ResultSet consultaSelect(String sql) {

        ResultSet rs = null;
        try {

            DatabaseMetaData bbdd = conexion.getMetaData();
            Statement statement = conexion.createStatement();
            statement.execute(sql);
            rs = statement.getResultSet();


        } catch (Exception e) {

        }
        return rs;
    }


    public boolean Elimina(String value) {
        return false;
    }
}
