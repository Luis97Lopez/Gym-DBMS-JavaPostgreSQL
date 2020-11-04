package gimnasio;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class JFrame extends javax.swing.JFrame {
    String bd = "jdbc:postgresql://localhost:5432/Gimnasio";
    String user = "postgres";
    String password = "postgres";
    Connection conexion = null;
    Statement sentencia = null;
    
    public JFrame() {
        initComponents();
        conectarBD();
    }
    
    public void conectarBD(){
        try{
            conexion = DriverManager.getConnection(bd, user, password);
            sentencia = conexion.createStatement();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    
    //              Código generado por NetBeans ↓
    // ---------------------------------------------------------
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        panel_empleado = new javax.swing.JPanel();
        panel_cliente = new javax.swing.JPanel();
        panel_suscripcion = new javax.swing.JPanel();
        panel_clase = new javax.swing.JPanel();
        panel_inscripcion = new javax.swing.JPanel();
        panel_pago = new javax.swing.JPanel();
        panel_horario = new javax.swing.JPanel();
        panel_articulo = new javax.swing.JPanel();
        panel_venta = new javax.swing.JPanel();
        panel_detalleventa = new javax.swing.JPanel();
        panel_compra = new javax.swing.JPanel();
        panel_detallecompra = new javax.swing.JPanel();
        datagrid = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gimnasio DB");
        setForeground(java.awt.Color.lightGray);
        setResizable(false);

        tab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tab.setToolTipText("");

        panel_empleado.setToolTipText("Empleado");

        javax.swing.GroupLayout panel_empleadoLayout = new javax.swing.GroupLayout(panel_empleado);
        panel_empleado.setLayout(panel_empleadoLayout);
        panel_empleadoLayout.setHorizontalGroup(
            panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_empleadoLayout.setVerticalGroup(
            panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Empleado", panel_empleado);

        javax.swing.GroupLayout panel_clienteLayout = new javax.swing.GroupLayout(panel_cliente);
        panel_cliente.setLayout(panel_clienteLayout);
        panel_clienteLayout.setHorizontalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_clienteLayout.setVerticalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Cliente", panel_cliente);

        javax.swing.GroupLayout panel_suscripcionLayout = new javax.swing.GroupLayout(panel_suscripcion);
        panel_suscripcion.setLayout(panel_suscripcionLayout);
        panel_suscripcionLayout.setHorizontalGroup(
            panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_suscripcionLayout.setVerticalGroup(
            panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Suscripcion", panel_suscripcion);

        javax.swing.GroupLayout panel_claseLayout = new javax.swing.GroupLayout(panel_clase);
        panel_clase.setLayout(panel_claseLayout);
        panel_claseLayout.setHorizontalGroup(
            panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_claseLayout.setVerticalGroup(
            panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Clase", panel_clase);

        javax.swing.GroupLayout panel_inscripcionLayout = new javax.swing.GroupLayout(panel_inscripcion);
        panel_inscripcion.setLayout(panel_inscripcionLayout);
        panel_inscripcionLayout.setHorizontalGroup(
            panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_inscripcionLayout.setVerticalGroup(
            panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Inscripcion", panel_inscripcion);

        javax.swing.GroupLayout panel_pagoLayout = new javax.swing.GroupLayout(panel_pago);
        panel_pago.setLayout(panel_pagoLayout);
        panel_pagoLayout.setHorizontalGroup(
            panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_pagoLayout.setVerticalGroup(
            panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Pago", panel_pago);

        javax.swing.GroupLayout panel_horarioLayout = new javax.swing.GroupLayout(panel_horario);
        panel_horario.setLayout(panel_horarioLayout);
        panel_horarioLayout.setHorizontalGroup(
            panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_horarioLayout.setVerticalGroup(
            panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Horario", panel_horario);

        javax.swing.GroupLayout panel_articuloLayout = new javax.swing.GroupLayout(panel_articulo);
        panel_articulo.setLayout(panel_articuloLayout);
        panel_articuloLayout.setHorizontalGroup(
            panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_articuloLayout.setVerticalGroup(
            panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Articulo", panel_articulo);

        javax.swing.GroupLayout panel_ventaLayout = new javax.swing.GroupLayout(panel_venta);
        panel_venta.setLayout(panel_ventaLayout);
        panel_ventaLayout.setHorizontalGroup(
            panel_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_ventaLayout.setVerticalGroup(
            panel_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Venta", panel_venta);

        javax.swing.GroupLayout panel_detalleventaLayout = new javax.swing.GroupLayout(panel_detalleventa);
        panel_detalleventa.setLayout(panel_detalleventaLayout);
        panel_detalleventaLayout.setHorizontalGroup(
            panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_detalleventaLayout.setVerticalGroup(
            panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("DetalleVenta", panel_detalleventa);

        javax.swing.GroupLayout panel_compraLayout = new javax.swing.GroupLayout(panel_compra);
        panel_compra.setLayout(panel_compraLayout);
        panel_compraLayout.setHorizontalGroup(
            panel_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_compraLayout.setVerticalGroup(
            panel_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("Compra", panel_compra);

        javax.swing.GroupLayout panel_detallecompraLayout = new javax.swing.GroupLayout(panel_detallecompra);
        panel_detallecompra.setLayout(panel_detallecompraLayout);
        panel_detallecompraLayout.setHorizontalGroup(
            panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_detallecompraLayout.setVerticalGroup(
            panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );

        tab.addTab("DetalleCompra", panel_detallecompra);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        datagrid.setViewportView(tabla);

        btn_agregar.setText("Agregar");

        btn_modificar.setText("Modificar");

        btn_eliminar.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datagrid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_agregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(datagrid, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JScrollPane datagrid;
    private javax.swing.JPanel panel_articulo;
    private javax.swing.JPanel panel_clase;
    private javax.swing.JPanel panel_cliente;
    private javax.swing.JPanel panel_compra;
    private javax.swing.JPanel panel_detallecompra;
    private javax.swing.JPanel panel_detalleventa;
    private javax.swing.JPanel panel_empleado;
    private javax.swing.JPanel panel_horario;
    private javax.swing.JPanel panel_inscripcion;
    private javax.swing.JPanel panel_pago;
    private javax.swing.JPanel panel_suscripcion;
    private javax.swing.JPanel panel_venta;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
