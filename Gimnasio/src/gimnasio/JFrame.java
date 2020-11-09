package gimnasio;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;

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
    
    public void llenarTabla(){
        String tabla = getSelectedTable();
        try{
            ResultSet resultado = null;
            String consultaSQL = "SELECT * FROM Gimnasio." + tabla;
            resultado = sentencia.executeQuery(consultaSQL);
            datagrid.setModel(getTableModel(resultado));

        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void limpiarPantalla(){
        String tabla = getSelectedTable();
        switch(tabla){
            case "Articulo":
                textfield_articulo_nombre.setText("");
                textfield_articulo_precio.setText("");
                textfield_articulo_existencia.setText("");
                break;
        }
    }
    
    public void actualizaFormulario(int index){
        String tabla = getSelectedTable();
        switch(tabla){
            case "Articulo":
                String articulo_nombre = datagrid.getValueAt(index,1).toString();
                String articulo_precio = datagrid.getValueAt(index,2).toString();
                String articulo_existencia = datagrid.getValueAt(index,3).toString();
                textfield_articulo_nombre.setText(articulo_nombre);
                textfield_articulo_precio.setText(articulo_precio);
                textfield_articulo_existencia.setText(articulo_existencia);
                break;
        }
    }
    
    private String getInsertSentencia(String selected_table){
        String sentencia = "INSERT INTO gimnasio." + selected_table;
        switch(selected_table){
            case "Articulo":
                sentencia += " (Nombre, Precio, Existencia) "
                        + "VALUES "
                        + "('"+ textfield_articulo_nombre.getText() +"', "
                        + "'"+ textfield_articulo_precio.getText() +"', "
                        + "'"+ textfield_articulo_existencia.getText() +"')";
                break;
        }
        
        return sentencia;
    }
    
    private String getUpdateSentencia(String selectedTable, int index){
        int pk = Integer.parseInt(datagrid.getValueAt(index, 0).toString());
        String sentencia = "UPDATE gimnasio." + selectedTable + " SET ";
        switch(selectedTable){
            case "Articulo":
                sentencia += "Nombre = '"+ textfield_articulo_nombre.getText() +"', "
                        + "Precio = '"+ textfield_articulo_precio.getText() +"', "
                        + "Existencia='"+ textfield_articulo_existencia.getText() +"'";
                break;
        }
        
        sentencia += " WHERE Id" + selectedTable +"=" + pk;
        return sentencia;
    }
    
    private String getDeleteSentencia(String selectedTable, int index){
        int pk = Integer.parseInt(datagrid.getValueAt(index, 0).toString());
        String sentencia =  "DELETE FROM gimnasio." + selectedTable + 
                " WHERE Id" + selectedTable + "=" + pk;
        return sentencia;
    }
    
    private String getSelectedTable(){
        return tabs.getTitleAt(tabs.getSelectedIndex());
    }
    
    public DefaultTableModel getTableModel(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
    }

    
    //              Código generado por NetBeans ↓
    // ---------------------------------------------------------
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        panel_empleado = new javax.swing.JPanel();
        panel_cliente = new javax.swing.JPanel();
        panel_suscripcion = new javax.swing.JPanel();
        panel_clase = new javax.swing.JPanel();
        panel_inscripcion = new javax.swing.JPanel();
        panel_pago = new javax.swing.JPanel();
        panel_horario = new javax.swing.JPanel();
        panel_venta = new javax.swing.JPanel();
        panel_detalleventa = new javax.swing.JPanel();
        panel_compra = new javax.swing.JPanel();
        panel_detallecompra = new javax.swing.JPanel();
        panel_articulo = new javax.swing.JPanel();
        label_articulo1 = new javax.swing.JLabel();
        textfield_articulo_nombre = new javax.swing.JTextField();
        label_articulo2 = new javax.swing.JLabel();
        textfield_articulo_precio = new javax.swing.JTextField();
        label_articulo3 = new javax.swing.JLabel();
        textfield_articulo_existencia = new javax.swing.JTextField();
        menu = new javax.swing.JScrollPane();
        datagrid = new javax.swing.JTable();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gimnasio DB");
        setForeground(java.awt.Color.lightGray);
        setResizable(false);

        tabs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabs.setToolTipText("");
        tabs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabsStateChanged(evt);
            }
        });

        panel_empleado.setToolTipText("Empleado");

        javax.swing.GroupLayout panel_empleadoLayout = new javax.swing.GroupLayout(panel_empleado);
        panel_empleado.setLayout(panel_empleadoLayout);
        panel_empleadoLayout.setHorizontalGroup(
            panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_empleadoLayout.setVerticalGroup(
            panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Empleado", panel_empleado);

        javax.swing.GroupLayout panel_clienteLayout = new javax.swing.GroupLayout(panel_cliente);
        panel_cliente.setLayout(panel_clienteLayout);
        panel_clienteLayout.setHorizontalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_clienteLayout.setVerticalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Cliente", panel_cliente);

        javax.swing.GroupLayout panel_suscripcionLayout = new javax.swing.GroupLayout(panel_suscripcion);
        panel_suscripcion.setLayout(panel_suscripcionLayout);
        panel_suscripcionLayout.setHorizontalGroup(
            panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_suscripcionLayout.setVerticalGroup(
            panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Suscripcion", panel_suscripcion);

        javax.swing.GroupLayout panel_claseLayout = new javax.swing.GroupLayout(panel_clase);
        panel_clase.setLayout(panel_claseLayout);
        panel_claseLayout.setHorizontalGroup(
            panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_claseLayout.setVerticalGroup(
            panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Clase", panel_clase);

        javax.swing.GroupLayout panel_inscripcionLayout = new javax.swing.GroupLayout(panel_inscripcion);
        panel_inscripcion.setLayout(panel_inscripcionLayout);
        panel_inscripcionLayout.setHorizontalGroup(
            panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_inscripcionLayout.setVerticalGroup(
            panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Inscripcion", panel_inscripcion);

        javax.swing.GroupLayout panel_pagoLayout = new javax.swing.GroupLayout(panel_pago);
        panel_pago.setLayout(panel_pagoLayout);
        panel_pagoLayout.setHorizontalGroup(
            panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_pagoLayout.setVerticalGroup(
            panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Pago", panel_pago);

        javax.swing.GroupLayout panel_horarioLayout = new javax.swing.GroupLayout(panel_horario);
        panel_horario.setLayout(panel_horarioLayout);
        panel_horarioLayout.setHorizontalGroup(
            panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_horarioLayout.setVerticalGroup(
            panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Horario", panel_horario);

        javax.swing.GroupLayout panel_ventaLayout = new javax.swing.GroupLayout(panel_venta);
        panel_venta.setLayout(panel_ventaLayout);
        panel_ventaLayout.setHorizontalGroup(
            panel_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_ventaLayout.setVerticalGroup(
            panel_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Venta", panel_venta);

        javax.swing.GroupLayout panel_detalleventaLayout = new javax.swing.GroupLayout(panel_detalleventa);
        panel_detalleventa.setLayout(panel_detalleventaLayout);
        panel_detalleventaLayout.setHorizontalGroup(
            panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_detalleventaLayout.setVerticalGroup(
            panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("DetalleVenta", panel_detalleventa);

        javax.swing.GroupLayout panel_compraLayout = new javax.swing.GroupLayout(panel_compra);
        panel_compra.setLayout(panel_compraLayout);
        panel_compraLayout.setHorizontalGroup(
            panel_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_compraLayout.setVerticalGroup(
            panel_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("Compra", panel_compra);

        javax.swing.GroupLayout panel_detallecompraLayout = new javax.swing.GroupLayout(panel_detallecompra);
        panel_detallecompra.setLayout(panel_detallecompraLayout);
        panel_detallecompraLayout.setHorizontalGroup(
            panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        panel_detallecompraLayout.setVerticalGroup(
            panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabs.addTab("DetalleCompra", panel_detallecompra);

        label_articulo1.setText("Nombre:");

        label_articulo2.setText("Precio:");

        label_articulo3.setText("Existencia:");

        javax.swing.GroupLayout panel_articuloLayout = new javax.swing.GroupLayout(panel_articulo);
        panel_articulo.setLayout(panel_articuloLayout);
        panel_articuloLayout.setHorizontalGroup(
            panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_articuloLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(label_articulo1)
                .addGap(29, 29, 29)
                .addComponent(textfield_articulo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(label_articulo2)
                .addGap(29, 29, 29)
                .addComponent(textfield_articulo_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(label_articulo3)
                .addGap(18, 18, 18)
                .addComponent(textfield_articulo_existencia, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        panel_articuloLayout.setVerticalGroup(
            panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_articuloLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_articulo2)
                    .addComponent(textfield_articulo_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_articulo1)
                    .addComponent(textfield_articulo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_articulo3)
                        .addComponent(textfield_articulo_existencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        tabs.addTab("Articulo", panel_articulo);

        datagrid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        datagrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datagridMouseClicked(evt);
            }
        });
        menu.setViewportView(datagrid);

        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar))
                .addGap(49, 49, 49)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabsStateChanged
        llenarTabla();
    }//GEN-LAST:event_tabsStateChanged

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        try{
            String sentenciaSQL = getInsertSentencia(getSelectedTable());
            sentencia.execute(sentenciaSQL);
            JOptionPane.showMessageDialog(this, "Agregado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        llenarTabla();
        limpiarPantalla();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void datagridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datagridMouseClicked
        int idx = datagrid.getSelectedRow();
           
        if(idx != -1){
            actualizaFormulario(idx);
        }
    }//GEN-LAST:event_datagridMouseClicked

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int idx = datagrid.getSelectedRow();
        
        if(idx != -1){
            try{
                String sentenciaSQL = getUpdateSentencia(getSelectedTable(), idx);
                sentencia.execute(sentenciaSQL);
                JOptionPane.showMessageDialog(this, "Modificado correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            llenarTabla();
            limpiarPantalla();
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int idx = datagrid.getSelectedRow();
        
        if(idx != -1){
            try{
                String sentenciaSQL = getDeleteSentencia(getSelectedTable(), idx);

                sentencia.execute(sentenciaSQL);
                JOptionPane.showMessageDialog(this, "Eliminado correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            llenarTabla();
            limpiarPantalla();
        }        
    }//GEN-LAST:event_btn_eliminarActionPerformed

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
    private javax.swing.JTable datagrid;
    private javax.swing.JLabel label_articulo1;
    private javax.swing.JLabel label_articulo2;
    private javax.swing.JLabel label_articulo3;
    private javax.swing.JScrollPane menu;
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
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField textfield_articulo_existencia;
    private javax.swing.JTextField textfield_articulo_nombre;
    private javax.swing.JTextField textfield_articulo_precio;
    // End of variables declaration//GEN-END:variables
}
