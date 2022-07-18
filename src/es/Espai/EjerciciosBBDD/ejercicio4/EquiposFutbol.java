package es.Espai.EjerciciosBBDD.ejercicio4;

//Crea una ventana Frame que tenga un layout tipo BorderLayout formado por los siguientes paneles:
// En la zona norte colocaremos un panel que utilizaremos para agregar diferentes elementos
//que nos servirán para filtrar la consulta sql.
// En la zona centro insertaremos un panel que contendrá el JTable que nos mostrará los datos
//de las diferentes consultas que hagamos.
// En la zona sur pondremos 2 botones:
//o 1 botón “Mostrar”
//o 1 botón “Salir”.

//Al arrancar la aplicación, el JTable estará vacío. Lo rellenaremos al pulsar el botón “Mostrar” con
//los datos de la tabla equipos

import es.Espai.EjerciciosBBDD.ejercicio3.DatabaseFutbol;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EquiposFutbol extends JFrame {


        private final String [] titulos = {"Codigo","Equipo","Ciudad","Estadio","Nom.Quiniela"};
        private DefaultTableModel dtm = new DefaultTableModel();
        private DatabaseFutbol dbf = new DatabaseFutbol();
        private Container contentPane;
        private JTable jtb_Equipos;

        public EquiposFutbol(){

            setResizable(false);
            setTitle("Equipos de futbol");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100,100,628,460);
            setLocationRelativeTo(null);
            contentPane = getContentPane();
            contentPane.setLayout(new BorderLayout());

            //---------------NORTE------------------//

            JPanel panelNorte = new JPanel();
            panelNorte.setBorder(new TitledBorder(new TitledBorder(new LineBorder(
                    new Color(192,192,192)),"Filtros", TitledBorder.LEADING,
                    TitledBorder.TOP,null,null),"Filtros",
                    TitledBorder.LEADING,TitledBorder.TOP,null,null));
            panelNorte.setBounds(10,0,528,356);


            //---------------CENTRO------------------//

            JPanel panelCentro = new JPanel();
            panelCentro.setLayout(new GridLayout(1,1));
                dtm.setColumnIdentifiers(titulos);
                jtb_Equipos = new JTable();
                jtb_Equipos.setEnabled(false);
                jtb_Equipos.setBounds(38,95,519,124);
                jtb_Equipos.setBorder(null);
                jtb_Equipos.setModel(dtm);
                panelCentro.add(jtb_Equipos);


            //---------------SUR------------------//

            JPanel panelSur = new JPanel();
            JButton btnMostrar = new JButton("Mostrar");
            JButton btnSalir = new JButton("Salir");
            btnMostrar.setBounds(20,378,89,23);
            btnSalir.setBounds(503,377,89,23);
            panelSur.add(btnMostrar);
            panelSur.add(btnSalir);


            //--------------------------------------//

            contentPane.add(panelCentro, BorderLayout.CENTER);
            contentPane.add(panelSur, BorderLayout.SOUTH);
            contentPane.add(panelNorte,BorderLayout.NORTH);

            btnMostrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarConsulta();
                }
            });


            btnSalir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


        }

    public static void main(String[] args) {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new EquiposFutbol().setVisible(true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
    }

    protected void mostrarConsulta(){

            dtm.setRowCount(0);
            try {

                String sql = " Select ´CodEq´ , ´Equipo´ , ´Ciudad´ , ´Estadio´ ,  ´NomQuinLFP´  from equipos ";

                ResultSet aux = dbf.consultaSelect(sql);
                while (aux.next()) {

                    Object[] fila = { aux.getObject(1), aux.getObject(2), aux.getObject(3), aux.getObject(4),
                            aux.getObject(5)};
                    dtm.addRow(fila);
                }

                System.out.println("Acceso a sql OK");
                jtb_Equipos.setModel(dtm);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error en la conexión a SQL. Error en " + e);
            }

    }
}
