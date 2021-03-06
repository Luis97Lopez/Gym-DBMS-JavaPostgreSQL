package gimnasio;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;

public class JFrame extends javax.swing.JFrame {
    Statement sentencia = null;
    String login_message_failed = "No has iniciado sesión.";
    String login_message_ok = "Inicio de sesión para el Usuario: ";
    String logged_in = "Hola, ";
    String not_logged_in = "No has iniciado sesión.";
    
    public JFrame(Statement sentencia) {
        initComponents();
        this.sentencia = sentencia;
    }
    
    //              Métodos Auxiliares para la GridView y Componentes ↓
    // ---------------------------------------------------------
    
    public Boolean llenarTabla(){
        String tabla = getSelectedTable();
        try{
            if(sentencia != null){
                ResultSet resultado = null;
                String consultaSQL = getSelectSentencia(tabla);
                resultado = sentencia.executeQuery(consultaSQL);
                datagrid.setModel(getTableModel(resultado));
            }
        }catch(Exception e){
            datagrid.setModel(new DefaultTableModel());
            if(!tabla.equals("Login") && !tabla.equals("Reporte1") && !tabla.equals("Reporte2"))
                JOptionPane.showMessageDialog(this, e.getMessage());
            return false;
        }
        return true;
    }
    
    public void limpiarPantalla(){
        String tabla = getSelectedTable();
        switch(tabla){
            case "Articulo":
                textfield_articulo_nombre.setText("");
                textfield_articulo_precio.setText("");
                textfield_articulo_existencia.setText("");
                break;
            case "Horario":
                textfield_horario_horainicio.setText("");
                textfield_horario_horafin.setText("");
            break;
            case "Empleado":
                combobox_empleado_idhorario.setSelectedIndex(0);
                textfield_empleado_nombre.setText("");
                textfield_empleado_celular.setText("");
                textfield_empleado_sueldo.setText("");
                textfield_empleado_dias.setText("");
            break;
            case "Cliente":
                textfield_cliente_nombre.setText("");
                textfield_cliente_direccion.setText("");
                combobox_cliente_idempleado.setSelectedIndex(0);
            break;
            case "Suscripcion":
                combobox_suscripcion_idempleado.setSelectedIndex(0);
                combobox_suscripcion_idcliente.setSelectedIndex(0);
                textfield_suscripcion_precio.setText("");
                textfield_suscripcion_duracion.setText("");
                textfield_suscripcion_tipo.setText("");
                textfield_suscripcion_fecha.setText("");
                textfield_suscripcion_estado.setText("");
            break;
            case "Clase":
                combobox_clase_idempleado.setSelectedIndex(0);
                combobox_clase_idhorario.setSelectedIndex(0);
                textfield_clase_nombre.setText("");
                textfield_clase_cupo.setText("");
            break;
            case "Inscripcion":
                combobox_inscripcion_idclase.setSelectedIndex(0);
                combobox_inscripcion_idcliente.setSelectedIndex(0);
            break;
            case "Pago":
                combobox_pago_idsuscripcion.setSelectedIndex(0);
                combobox_pago_idcliente.setSelectedIndex(0);
                textfield_pago_total.setText("");
                textfield_pago_fecha.setText("");
            break;
            case "Venta":
                combobox_venta_idempleado.setSelectedIndex(0);
                textfield_venta_fecha.setText("");
            break;
            case "DetalleVenta":
                combobox_detalleventa_idarticulo.setSelectedIndex(0);
                combobox_detalleventa_idventa.setSelectedIndex(0);
                textfield_detalleventa_cantidad.setText("");
            break;
            case "Compra":
                combobox_compra_idempleado.setSelectedIndex(0);
                textfield_compra_fecha.setText("");
            break;
            case "DetalleCompra":
                combobox_detallecompra_idarticulo.setSelectedIndex(0);
                combobox_detallecompra_idcompra.setSelectedIndex(0);
                textfield_detallecompra_cantidad.setText("");
            break;
            case "Reporte1":
                textfield_reporte1_ventasmin.setText("");
                textfield_reporte1_ventasmax.setText("");
                break;
            case "Reporte2":
                textfield_reporte2_horainicio.setText("");
                textfield_reporte2_horafin.setText("");
                break;
        }
    }
    
    public void actualizaComponentes(){
        String tabla = getSelectedTable();
        String query;
        int index_info_column = 0;
        
        switch(tabla){
            case "Cliente":
                query = getQueryOfComboBox("Empleado");
                combobox_cliente_idempleado.
                        setModel(convertQueryToComboBoxModel(query));
                break;
            case "Suscripcion":
                query = getQueryOfComboBox("Empleado");
                combobox_suscripcion_idempleado.
                        setModel(convertQueryToComboBoxModel(query));
                
                query = getQueryOfComboBox("Cliente");
                combobox_suscripcion_idcliente.
                        setModel(convertQueryToComboBoxModel(query));
            case "Clase":
                query = getQueryOfComboBox("Empleado");
                combobox_clase_idempleado.
                        setModel(convertQueryToComboBoxModel(query));
                
                query = getQueryOfComboBox("Horario");
                combobox_clase_idhorario.
                        setModel(convertQueryToComboBoxModel(query));
                break;
            case "Inscripcion":
                query = getQueryOfComboBox("Clase");
                combobox_inscripcion_idclase.
                        setModel(convertQueryToComboBoxModel(query));
                
                query = getQueryOfComboBox("Cliente");
                combobox_inscripcion_idcliente.
                        setModel(convertQueryToComboBoxModel(query));
                break;
            case "Pago":
                query = getQueryOfComboBox("Suscripcion");
                combobox_pago_idsuscripcion.
                        setModel(convertQueryToComboBoxModel(query));
                
                query = getQueryOfComboBox("Cliente");
                combobox_pago_idcliente.
                        setModel(convertQueryToComboBoxModel(query));
                break;
            case "Empleado":
                query = getQueryOfComboBox("Horario");
                combobox_empleado_idhorario.
                        setModel(convertQueryToComboBoxModel(query));
                break;
            case "Venta":
                query = getQueryOfComboBox("Empleado");
                combobox_venta_idempleado.
                        setModel(convertQueryToComboBoxModel(query));
                break;
            case "Compra":
                query = getQueryOfComboBox("Empleado");
                combobox_compra_idempleado.
                        setModel(convertQueryToComboBoxModel(query));
                break;
            case "DetalleVenta":
                query = getQueryOfComboBox("Articulo");
                combobox_detalleventa_idarticulo.
                        setModel(convertQueryToComboBoxModel(query));  
                
                query = getQueryOfComboBox("Venta");
                combobox_detalleventa_idventa.
                        setModel(convertQueryToComboBoxModel(query));
                break;
            case "DetalleCompra":
                query = getQueryOfComboBox("Articulo");
                combobox_detallecompra_idarticulo.
                        setModel(convertQueryToComboBoxModel(query)); 
                
                query = getQueryOfComboBox("Compra");
                combobox_detallecompra_idcompra.
                        setModel(convertQueryToComboBoxModel(query));
                break;
        }
    }
    
    public void actualizaBotones(){
        String tabla = getSelectedTable();
        Boolean value = tabla.equals("Reporte1") || tabla.equals("Reporte2");
        
        btn_agregar.setVisible(!value);
        btn_modificar.setVisible(!value);
        btn_eliminar.setVisible(!value);
        btn_consultar.setVisible(value);
    }
    
    public void actualizaFormulario(int index){
        String tabla = getSelectedTable();
        switch(tabla){
            case "Horario":
                textfield_horario_horainicio.setText(
                        datagrid.getValueAt(index, 1).toString());
                textfield_horario_horafin.setText(
                        datagrid.getValueAt(index, 2).toString());
                break;
            case "Articulo":
                String articulo_nombre = datagrid.getValueAt(index,1).toString();
                String articulo_precio = datagrid.getValueAt(index,2).toString();
                String articulo_existencia = datagrid.getValueAt(index,3).toString();
                textfield_articulo_nombre.setText(articulo_nombre);
                textfield_articulo_precio.setText(articulo_precio);
                textfield_articulo_existencia.setText(articulo_existencia);
                break;
            case "Cliente":
                String cliente_nombre = datagrid.getValueAt(index, 1).toString();
                String cliente_direccion = datagrid.getValueAt(index, 2).toString();;
                String cliente_idempleado = datagrid.getValueAt(index, 3).toString();;
                
                
                textfield_cliente_nombre.setText(cliente_nombre);
                textfield_cliente_direccion.setText(cliente_direccion);
                combobox_cliente_idempleado.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_cliente_idempleado, cliente_idempleado ));
                break;
            case "Suscripcion":
                combobox_suscripcion_idempleado.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_suscripcion_idempleado, 
                                datagrid.getValueAt(index, 1).toString()));
                combobox_suscripcion_idcliente.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_suscripcion_idcliente, 
                                datagrid.getValueAt(index, 2).toString()));
                textfield_suscripcion_precio.setText(
                        datagrid.getValueAt(index, 3).toString());
                textfield_suscripcion_duracion.setText(
                        datagrid.getValueAt(index, 4).toString());
                textfield_suscripcion_tipo.setText(
                        datagrid.getValueAt(index, 5).toString());
                textfield_suscripcion_fecha.setText(
                        datagrid.getValueAt(index, 6).toString());
                textfield_suscripcion_estado.setText(
                        datagrid.getValueAt(index, 7).toString());
                break;
            case "Clase":
                combobox_clase_idempleado.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_clase_idempleado, 
                                datagrid.getValueAt(index, 3).toString()));
                
                combobox_clase_idhorario.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_clase_idhorario, 
                                datagrid.getValueAt(index, 4).toString()));
                
                textfield_clase_nombre.setText(
                        datagrid.getValueAt(index, 1).toString());
                textfield_clase_cupo.setText(
                        datagrid.getValueAt(index, 2).toString());
                
                break;
            case "Inscripcion":
                combobox_inscripcion_idcliente.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_inscripcion_idcliente, 
                                datagrid.getValueAt(index, 2).toString()));
                
                combobox_inscripcion_idclase.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_inscripcion_idclase, 
                                datagrid.getValueAt(index, 1).toString()));
                break;
            case "Pago":
                combobox_pago_idsuscripcion.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_pago_idsuscripcion, 
                                datagrid.getValueAt(index, 1).toString()));
                
                combobox_pago_idcliente.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_pago_idcliente, 
                                datagrid.getValueAt(index, 2).toString()));
                
                textfield_pago_total.setText(
                        datagrid.getValueAt(index, 3).toString());
                textfield_pago_fecha.setText(
                        datagrid.getValueAt(index, 4).toString());
                break;
            case "Empleado":
                combobox_empleado_idhorario.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_empleado_idhorario, 
                                datagrid.getValueAt(index, 2).toString()));
                
                textfield_empleado_nombre.setText(
                        datagrid.getValueAt(index, 1).toString());
                textfield_empleado_sueldo.setText(
                        datagrid.getValueAt(index, 4).toString());
                textfield_empleado_celular.setText(
                        datagrid.getValueAt(index, 3).toString());
                textfield_empleado_dias.setText(
                        datagrid.getValueAt(index, 5).toString());
                break;
            case "Venta":
                combobox_venta_idempleado.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_venta_idempleado, 
                                datagrid.getValueAt(index, 1).toString()));
                
                textfield_venta_fecha.setText(
                        datagrid.getValueAt(index, 2).toString());
                break;
            case "DetalleVenta":
                combobox_detalleventa_idarticulo.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_detalleventa_idarticulo, 
                                datagrid.getValueAt(index, 2).toString()));
                
                combobox_detalleventa_idventa.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_detalleventa_idventa, 
                                datagrid.getValueAt(index, 1).toString()));
                
                textfield_detalleventa_cantidad.setText(
                        datagrid.getValueAt(index, 3).toString());
                 
                break;
            case "Compra":
                combobox_compra_idempleado.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_compra_idempleado, 
                                datagrid.getValueAt(index, 1).toString()));
                
                textfield_compra_fecha.setText(
                        datagrid.getValueAt(index, 2).toString());
                break;
            case "DetalleCompra":
                combobox_detallecompra_idarticulo.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_detallecompra_idarticulo, 
                                datagrid.getValueAt(index, 2).toString()));
                
                combobox_detallecompra_idcompra.setSelectedIndex(
                        getIndexOfIDInComboBox(combobox_detallecompra_idcompra, 
                                datagrid.getValueAt(index, 1).toString()));
                
                textfield_detallecompra_cantidad.setText(
                        datagrid.getValueAt(index, 3).toString());
                 
                break;
        }
    }
    
    private String getSelectedTable(){
        return tabs.getTitleAt(tabs.getSelectedIndex());
    }
    
    public DefaultTableModel getTableModel(ResultSet rs) throws SQLException{
        // Método genérico que devuelve la tabla con sus respectivos nombres
        // de sus columnas.
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
    
    public Boolean checkTabla(String tabla, int idx){
        if(tabla.equals("Venta") || tabla.equals("Compra")){
            return datagrid.getValueAt(idx, 3) == null;
        }else{
           return true;
        }
    }
    
    public void consultarReportes(){
        String tabla = getSelectedTable();
        String query = "";
        
        switch(tabla){
            case "Reporte1":
                query += "SELECT ar.Nombre, COUNT(v.IdArticulo) "
                        + "FROM gimnasio.Articulo ar INNER JOIN "
                            + "(SELECT * FROM gimnasio.Venta tv "
                            + "INNER JOIN gimnasio.DetalleVenta td "
                            + "ON tv.IdVenta = td.IdVenta) v "
                        + "ON v.IdArticulo = ar.IdArticulo "
                        + "GROUP BY ar.Nombre "
                        + "HAVING (COUNT(v.IdArticulo) >= "+ 
                        textfield_reporte1_ventasmin.getText() +" "
                        + "AND COUNT(v.IdArticulo) <= "+
                        textfield_reporte1_ventasmax.getText()+")";
                break;
            case "Reporte2":
                query += "SELECT IdClase, Nombre, HoraInicio, HoraFin FROM gimnasio.Clase c "
                        + "INNER JOIN gimnasio.Horario h "
                        + "ON c.IdHorario = h.IdHorario "
                        + "WHERE c.IdHorario IN "
                        + "(SELECT IdHorario FROM gimnasio.Horario "
                        + "WHERE (HoraInicio >= '" 
                        + textfield_reporte2_horainicio.getText() + "' "
                        + "AND HoraFin <= '" 
                        + textfield_reporte2_horafin.getText() + "'))";
        }
        if(!query.equals("")){
            hazConsultaReporte(query);
        }
    }
    
    public void hazConsultaReporte(String query){
        try{
            if(sentencia != null){
                ResultSet resultado = null;
                resultado = sentencia.executeQuery(query);
                datagrid.setModel(getTableModel(resultado));
            }
        }catch(Exception e){
            datagrid.setModel(new DefaultTableModel());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    //              Métodos get Sentencias ↓
    // ---------------------------------------------------------
    
    private String getSelectSentencia(String selected_table){
        String sentencia = "SELECT * FROM gimnasio." + selected_table;
        
        switch(selected_table){
            case "Cliente":
                sentencia = "SELECT c.IdCliente, c.Nombre, c.Direccion, "
                        + "CONCAT(c.IdEmpleado, ' ',e.Nombre) AS Empleado "
                        + "FROM gimnasio.Cliente c "
                        + "INNER JOIN gimnasio.Empleado e "
                        + "ON c.IdEmpleado = e.IdEmpleado";
                break;
            case "Suscripcion":
                sentencia = "SELECT s.IdSuscripcion, "
                        + "CONCAT(s.IdEmpleado, ' ',e.Nombre) AS Empleado, "
                        + "CONCAT(s.IdCliente, ' ',c.Nombre) AS Cliente, "
                        + "s.Precio, s.Duracion, s.Tipo, s.Fecha, s.Estado "
                        + "FROM gimnasio.Suscripcion s "
                        + "INNER JOIN gimnasio.Empleado e "
                        + "ON s.IdEmpleado = e.IdEmpleado "
                        + "INNER JOIN gimnasio.Cliente c "
                        + "ON s.IdCliente = c.IdCliente";
                break;
            case "Clase":
                sentencia = "SELECT c.IdClase, c.Nombre, c.Cupo, "
                        + "CONCAT(c.IdEmpleado, ' ',e.Nombre) AS Empleado, "
                        + "CONCAT(c.IdHorario,' ',h.HoraInicio,'-', h.HoraFin) "
                        + "as Horario "
                        + "FROM gimnasio.Clase c "
                        + "INNER JOIN gimnasio.Horario h "
                        + "ON c.IdHorario = h.IdHorario "
                        + "INNER JOIN gimnasio.Empleado e "
                        + "ON c.IdEmpleado = e.IdEmpleado";
                break;
            case "Inscripcion":
                sentencia = "SELECT i.IdInscripcion, "
                        + "CONCAT(i.IdClase,' ',cs.Nombre, ' ', h.HoraInicio, "
                        + "'-', h.HoraFin) AS Clase, "
                        + "CONCAT(i.IdCliente, ' ', ct.Nombre) AS Cliente "
                        + "FROM gimnasio.Inscripcion i "
                        + "INNER JOIN gimnasio.Clase cs "
                        + "ON i.IdClase = cs.IdClase "
                        + "INNER JOIN gimnasio.Cliente ct "
                        + "ON i.IdCliente = ct.IdCliente "
                        + "INNER JOIN gimnasio.Horario h "
                        + "ON h.IdHorario = cs.IdHorario";
                break;
            case "Pago":
                sentencia = "SELECT p.IdPago, p.IdSuscripcion, "
                        + "CONCAT(p.IdCliente, ' ', c.Nombre) AS Cliente,  "
                        + "p.Total, p.Fecha "
                        + "FROM gimnasio.Pago p "
                        + "INNER JOIN gimnasio.Cliente c "
                        + "ON p.IdCliente = c.IdCliente";
                break;
            case "Empleado":
                sentencia = "SELECT e.IdEmpleado, e.Nombre, "
                        + "CONCAT(e.IdHorario, ' ', h.HoraInicio, "
                        + "'-' ,h.HoraFin) AS Horario, "
                        + "e.Celular, e.Sueldo, e.Dias "
                        + "FROM gimnasio.Empleado e "
                        + "INNER JOIN gimnasio.Horario h "
                        + "ON e.IdHorario = h.IdHorario";
                break;
            case "Venta":
                sentencia = "SELECT v.IdVenta, "
                        + "CONCAT(v.IdEmpleado, ' ' ,e.Nombre) AS Empleado, "
                        + "v.Fecha, v.Total "
                        + "FROM gimnasio.Venta v "
                        + "INNER JOIN gimnasio.Empleado e "
                        + "ON v.IdEmpleado = e.IdEmpleado";
                break;
            case "DetalleVenta":
                sentencia = "SELECT dv.IdVenta, "
                        + "CONCAT(dv.IdArticulo, ' ' ,a.Nombre ) AS Articulo, "
                        + "dv.Cantidad, dv.Subtotal "
                        + "FROM gimnasio.DetalleVenta dv "
                        + "INNER JOIN gimnasio.Articulo a "
                        + "ON dv.IdArticulo = a.IdArticulo";
                break;
            case "Compra":
                sentencia = "SELECT c.IdCompra, "
                         + "CONCAT(c.IdEmpleado, ' ' ,e.Nombre) AS Empleado, "
                        + "c.Fecha, c.Total "
                        + "FROM gimnasio.Compra c "
                        + "INNER JOIN gimnasio.Empleado e "
                        + "ON c.IdEmpleado = e.IdEmpleado";
                break;
            case "DetalleCompra":
                sentencia = "SELECT dc.IdCompra, "
                        + "CONCAT(dc.IdArticulo, ' ' ,a.Nombre ) AS Articulo, "
                        + "dc.Cantidad, dc.Subtotal "
                        + "FROM gimnasio.DetalleCompra dc "
                        + "INNER JOIN gimnasio.Articulo a "
                        + "ON dc.IdArticulo = a.IdArticulo";
                break;
        }
        
        return sentencia;
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
            
            case "Horario":
                    sentencia += "(horainicio, horafin) "
                        + "VALUES"
                        + "('"+ textfield_horario_horainicio.getText() +"', "
                        + "'"+ textfield_horario_horafin.getText() +"')";
                break;
            case "Empleado":
                sentencia += "(idhorario, nombre, celular, sueldo, dias) "
                    + "VALUES"
                    + "('"+ getIDOfCombobox(combobox_empleado_idhorario) +"', "
                    + "'"+ textfield_empleado_nombre.getText() +"', "
                    + "'"+ textfield_empleado_celular.getText() +"', "
                    + "'"+ textfield_empleado_sueldo.getText() +"', "
                    + "'"+ textfield_empleado_dias.getText() +"')";
            break;
            case "Cliente":
                sentencia += "(nombre, direccion, idempleado) "
                + "VALUES"
                + "('"+ textfield_cliente_nombre.getText() +"', "
                + "'"+ textfield_cliente_direccion.getText() +"', "
                + "'"+ getIDOfCombobox(combobox_cliente_idempleado) +"')";
            break;
            case "Suscripcion":
                sentencia += "(idempleado, idcliente, precio, duracion, tipo, fecha, estado) "
                + "VALUES"
                + "('"+ getIDOfCombobox(combobox_suscripcion_idempleado) +"', "
                + "'"+ getIDOfCombobox(combobox_suscripcion_idcliente) +"', "
                + "'"+ textfield_suscripcion_precio.getText() +"', "
                + "'"+ textfield_suscripcion_duracion.getText() +"', "
                + "'"+ textfield_suscripcion_tipo.getText() +"', "
                + "'"+ textfield_suscripcion_fecha.getText() +"', "
                + "'"+ textfield_suscripcion_estado.getText() +"')";
            break;
            case "Clase":
                sentencia += "(idempleado, idhorario, nombre, cupo) "
                + "VALUES"
                + "('"+ getIDOfCombobox(combobox_clase_idempleado) +"', "
                + "'"+ getIDOfCombobox(combobox_clase_idhorario) +"', "
                + "'"+ textfield_clase_nombre.getText() +"', "
                + "'"+ textfield_clase_cupo.getText() +"')";
            break;
            case "Inscripcion":
                sentencia += "(idclase, idcliente) "
                + "VALUES"
                + "('"+ getIDOfCombobox(combobox_inscripcion_idclase) +"', "
                + "'"+ getIDOfCombobox(combobox_inscripcion_idcliente) +"')";
            break;
            case "Pago":
                sentencia += "(idsuscripcion, idcliente, total, fecha) "
                + "VALUES"
                + "('"+ getIDOfCombobox(combobox_pago_idsuscripcion) +"', "
                + "'"+ getIDOfCombobox(combobox_pago_idcliente) +"', "
                + "'"+ textfield_pago_total.getText() +"', "
                + "'"+ textfield_pago_fecha.getText() +"')";
            break;
            case "Venta":
                sentencia += "(idempleado, fecha) "
                + "VALUES"
                + "('"+ getIDOfCombobox(combobox_venta_idempleado) +"', "
                + "'"+ textfield_venta_fecha.getText() +"')";
            break;
            case "DetalleVenta":
                sentencia += "(idventa, idarticulo, cantidad) "
                + "VALUES"
                + "('"+ getIDOfCombobox(combobox_detalleventa_idventa) +"', "
                + "'"+ getIDOfCombobox(combobox_detalleventa_idarticulo) +"', "
                + "'"+ textfield_detalleventa_cantidad.getText() +"')";
            break;
            case "Compra":
                sentencia += "(idempleado, fecha) "
                + "VALUES"
                + "('"+ getIDOfCombobox(combobox_compra_idempleado) +"', "
                + "'"+ textfield_compra_fecha.getText() +"')";
            break;
            case "DetalleCompra":
                sentencia += "(idcompra, idarticulo, cantidad) "
                + "VALUES"
                + "('"+ getIDOfCombobox(combobox_detallecompra_idcompra) +"', "
                + "'"+ getIDOfCombobox(combobox_detallecompra_idarticulo) +"', "
                + "'"+ textfield_detallecompra_cantidad.getText() +"')";
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
            case "Horario":
                sentencia += "horainicio = '"+ textfield_horario_horainicio.getText() +"', "
                        + "horafin='"+ textfield_horario_horafin.getText() +"'";
                break;
            case "Empleado":
                sentencia += "idhorario = '"+ getIDOfCombobox(combobox_empleado_idhorario) +"', "
                        + "nombre = '"+ textfield_empleado_nombre.getText() +"', "
                        + "celular = '"+ textfield_empleado_celular.getText() +"', "
                        + "sueldo = '"+ textfield_empleado_sueldo.getText() +"', "
                        + "dias='"+ textfield_empleado_dias.getText() +"'";
                break;
            case "Cliente":
                sentencia += "Nombre = '"+ textfield_cliente_nombre.getText() +"', "
                + "Direccion = '"+ textfield_cliente_direccion.getText() +"', "
                + "idempleado = '"+ getIDOfCombobox(combobox_cliente_idempleado) +"'";
                        ;
            break;
            case "Suscripcion":
                sentencia += "idempleado = '"+ getIDOfCombobox(combobox_suscripcion_idempleado) +"', "
                + "idcliente = '"+ getIDOfCombobox(combobox_suscripcion_idcliente) +"', "
                + "Precio = '"+ textfield_suscripcion_precio.getText() +"', "
                + "Duracion = '"+ textfield_suscripcion_duracion.getText() +"', "
                + "tipo = '"+ textfield_suscripcion_tipo.getText() +"', "
                + "fecha = '"+ textfield_suscripcion_fecha.getText() +"', "
                + "estado = '"+ textfield_suscripcion_estado.getText() +"'";
            break;
            case "Clase":
                sentencia += "idempleado = '"+ getIDOfCombobox(combobox_clase_idempleado) +"', "
                + "idhorario = '"+getIDOfCombobox(combobox_clase_idhorario) +"', "
                + "nombre = '"+textfield_clase_nombre.getText() +"', "
                + "cupo = '"+textfield_clase_cupo.getText() +"'";
            break;
            case "Inscripcion":
                sentencia += "idclase = '"+ getIDOfCombobox(combobox_inscripcion_idclase) +"', "
                + "idcliente = '"+getIDOfCombobox(combobox_inscripcion_idcliente) +"'";
            break;
            case "Pago":
                sentencia += "idsuscripcion = '"+ getIDOfCombobox(combobox_pago_idsuscripcion) +"', "
                + "idcliente = '"+getIDOfCombobox(combobox_pago_idcliente) +"', "
                + "total = '"+textfield_pago_total.getText() +"', "
                + "fecha = '"+textfield_pago_fecha.getText() +"'";
            break;
            case "Venta":
                sentencia += "idempleado = '"+ getIDOfCombobox(combobox_venta_idempleado) +"', "
                + "fecha = '"+textfield_venta_fecha.getText() +"'";
            break;
            case "DetalleVenta":
                sentencia += "idarticulo = '"+ getIDOfCombobox(combobox_detalleventa_idarticulo) +"', "
                + "cantidad = '"+textfield_detalleventa_cantidad.getText() +"', " 
                + "idventa = '"+ getIDOfCombobox(combobox_detalleventa_idventa) + "'";
            break;
            case "Compra":
                sentencia += "idempleado = '"+ getIDOfCombobox(combobox_compra_idempleado) +"', "
                + "fecha = '"+textfield_compra_fecha.getText() +"'";
            break;
            case "DetalleCompra":
                sentencia += "idarticulo = '"+ getIDOfCombobox(combobox_detallecompra_idarticulo) +"', "
                + "cantidad = '"+textfield_detallecompra_cantidad.getText() +"', "
                + "idcompra = '"+ getIDOfCombobox(combobox_detallecompra_idcompra) + "'";
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
    
    //              Métodos para manejo de ComboBox ↓
    // ---------------------------------------------------------
    
    public String getQueryOfComboBox(String tabla){
        String query = "";
        switch(tabla){
            case "Empleado":
                query = "SELECT IdEmpleado, Nombre FROM gimnasio.Empleado";
                break;
            case "Cliente":
                query = "SELECT IdCliente, Nombre FROM gimnasio.Cliente";
                break;
            case "Horario":
                query = "SELECT IdHorario, "
                        + "CONCAT(HoraInicio, ' - ' ,HoraFin) as Horario  "
                        + "FROM gimnasio.Horario";
                break;
            case "Clase":
                query = "SELECT IdClase, Nombre, "
                        + "CONCAT(h.HoraInicio, '-', h.HoraFin) AS Horario "
                        + "FROM gimnasio.Clase c "
                        + "INNER JOIN gimnasio.Horario h "
                        + "ON c.IdHorario=h.IdHorario";
                break;
            case "Suscripcion":
                query = "SELECT IdSuscripcion FROM gimnasio.Suscripcion";
                break;
            case "Venta":
                query = "SELECT IdVenta FROM gimnasio.Venta WHERE Total IS NULL";
                break;
            case "Compra":
                query = "SELECT IdCompra FROM gimnasio.Compra WHERE Total IS NULL";
                break;
            case "Articulo":
                query = "SELECT IdArticulo, Nombre FROM gimnasio.Articulo";
                break;
        }
        return query;
    }
  
    public DefaultComboBoxModel convertQueryToComboBoxModel(String query){
        Vector<String> data = new Vector<String>();
        data.add(" ");
        try{
            if(sentencia != null){
                ResultSet rs = null;
                rs = sentencia.executeQuery(query);
                
                ResultSetMetaData metaData = rs.getMetaData();
                
                int columnCount = metaData.getColumnCount();
                
                while (rs.next()) {
                    String s = "";
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        s += rs.getObject(columnIndex) + " ";
                    }
                    data.add(s);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return new DefaultComboBoxModel(data.toArray());
    }
    
    public String getIDOfCombobox(JComboBox c){
        try{
            return c.getSelectedItem().toString().split("\\s+")[0];
        }
        catch(Exception e){
            return "";
        }
    }
    
    public String getIDOfComboboxElement(JComboBox c, int i){
        try{
            return c.getItemAt(i).toString().split("\\s+")[0];
        }
        catch(Exception e){
            return "";
        }
    }
    
    public int getIndexOfIDInComboBox(JComboBox c, String all){
        String id = all.split("\\s+")[0];
        int n = c.getItemCount();
        for(int i = 0; i < n; i++){
            String temp = getIDOfComboboxElement(c, i);
            if(temp.equals(id))
                return i;
        }
        return -1;
    }
    

    //              Código generado por NetBeans ↓
    // ---------------------------------------------------------
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        panel_horario = new javax.swing.JPanel();
        label_horario = new javax.swing.JLabel();
        textfield_horario_horainicio = new javax.swing.JTextField();
        label_horario1 = new javax.swing.JLabel();
        textfield_horario_horafin = new javax.swing.JTextField();
        label_hora = new javax.swing.JLabel();
        label_hora1 = new javax.swing.JLabel();
        panel_empleado = new javax.swing.JPanel();
        label_empleado1 = new javax.swing.JLabel();
        textfield_empleado_celular = new javax.swing.JTextField();
        label_empleado2 = new javax.swing.JLabel();
        label_empleado3 = new javax.swing.JLabel();
        textfield_empleado_sueldo = new javax.swing.JTextField();
        label_empleado4 = new javax.swing.JLabel();
        textfield_empleado_dias = new javax.swing.JTextField();
        textfield_empleado_nombre = new javax.swing.JTextField();
        label_empleado5 = new javax.swing.JLabel();
        combobox_empleado_idhorario = new javax.swing.JComboBox<>();
        panel_cliente = new javax.swing.JPanel();
        textfield_cliente_nombre = new javax.swing.JTextField();
        label_cliente = new javax.swing.JLabel();
        label_cliente1 = new javax.swing.JLabel();
        label_cliente2 = new javax.swing.JLabel();
        textfield_cliente_direccion = new javax.swing.JTextField();
        combobox_cliente_idempleado = new javax.swing.JComboBox<>();
        panel_suscripcion = new javax.swing.JPanel();
        label_suscripcion = new javax.swing.JLabel();
        label_suscripcion1 = new javax.swing.JLabel();
        label_suscripcion2 = new javax.swing.JLabel();
        textfield_suscripcion_precio = new javax.swing.JTextField();
        textfield_suscripcion_duracion = new javax.swing.JTextField();
        label_suscripcion3 = new javax.swing.JLabel();
        textfield_suscripcion_tipo = new javax.swing.JTextField();
        label_suscripcion4 = new javax.swing.JLabel();
        textfield_suscripcion_fecha = new javax.swing.JTextField();
        label_suscripcion5 = new javax.swing.JLabel();
        textfield_suscripcion_estado = new javax.swing.JTextField();
        label_suscripcion6 = new javax.swing.JLabel();
        combobox_suscripcion_idempleado = new javax.swing.JComboBox<>();
        combobox_suscripcion_idcliente = new javax.swing.JComboBox<>();
        panel_clase = new javax.swing.JPanel();
        label_clase = new javax.swing.JLabel();
        label_clase1 = new javax.swing.JLabel();
        textfield_clase_nombre = new javax.swing.JTextField();
        label_clase2 = new javax.swing.JLabel();
        label_clase3 = new javax.swing.JLabel();
        textfield_clase_cupo = new javax.swing.JTextField();
        combobox_clase_idempleado = new javax.swing.JComboBox<>();
        combobox_clase_idhorario = new javax.swing.JComboBox<>();
        panel_inscripcion = new javax.swing.JPanel();
        label_inscripcion = new javax.swing.JLabel();
        label_inscripcion1 = new javax.swing.JLabel();
        combobox_inscripcion_idcliente = new javax.swing.JComboBox<>();
        combobox_inscripcion_idclase = new javax.swing.JComboBox<>();
        panel_pago = new javax.swing.JPanel();
        label_pago = new javax.swing.JLabel();
        label_pago1 = new javax.swing.JLabel();
        label_pago2 = new javax.swing.JLabel();
        textfield_pago_total = new javax.swing.JTextField();
        label_pago3 = new javax.swing.JLabel();
        textfield_pago_fecha = new javax.swing.JTextField();
        combobox_pago_idcliente = new javax.swing.JComboBox<>();
        combobox_pago_idsuscripcion = new javax.swing.JComboBox<>();
        panel_articulo = new javax.swing.JPanel();
        label_articulo1 = new javax.swing.JLabel();
        textfield_articulo_nombre = new javax.swing.JTextField();
        label_articulo2 = new javax.swing.JLabel();
        textfield_articulo_precio = new javax.swing.JTextField();
        label_articulo3 = new javax.swing.JLabel();
        textfield_articulo_existencia = new javax.swing.JTextField();
        panel_venta = new javax.swing.JPanel();
        label_venta = new javax.swing.JLabel();
        label_venta2 = new javax.swing.JLabel();
        textfield_venta_fecha = new javax.swing.JTextField();
        combobox_venta_idempleado = new javax.swing.JComboBox<>();
        panel_detalleventa = new javax.swing.JPanel();
        label_detalleventa = new javax.swing.JLabel();
        label_detalleventa1 = new javax.swing.JLabel();
        textfield_detalleventa_cantidad = new javax.swing.JTextField();
        combobox_detalleventa_idarticulo = new javax.swing.JComboBox<>();
        label_detalleventa2 = new javax.swing.JLabel();
        combobox_detalleventa_idventa = new javax.swing.JComboBox<>();
        panel_compra = new javax.swing.JPanel();
        label_compra = new javax.swing.JLabel();
        textfield_compra_fecha = new javax.swing.JTextField();
        label_compra2 = new javax.swing.JLabel();
        combobox_compra_idempleado = new javax.swing.JComboBox<>();
        panel_detallecompra = new javax.swing.JPanel();
        label_detallecompra = new javax.swing.JLabel();
        label_detallecompra1 = new javax.swing.JLabel();
        textfield_detallecompra_cantidad = new javax.swing.JTextField();
        combobox_detallecompra_idarticulo = new javax.swing.JComboBox<>();
        combobox_detallecompra_idcompra = new javax.swing.JComboBox<>();
        label_detallecompra2 = new javax.swing.JLabel();
        panel_reporte1 = new javax.swing.JPanel();
        label_horario2 = new javax.swing.JLabel();
        textfield_reporte1_ventasmin = new javax.swing.JTextField();
        label_horario3 = new javax.swing.JLabel();
        textfield_reporte1_ventasmax = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        panel_reporte2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        label_hora4 = new javax.swing.JLabel();
        textfield_reporte2_horainicio = new javax.swing.JTextField();
        label_horario6 = new javax.swing.JLabel();
        textfield_reporte2_horafin = new javax.swing.JTextField();
        label_horario7 = new javax.swing.JLabel();
        label_hora5 = new javax.swing.JLabel();
        menu = new javax.swing.JScrollPane();
        datagrid = new javax.swing.JTable();
        btn_agregar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_consultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gimnasio DB");
        setForeground(java.awt.Color.lightGray);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tabs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabs.setToolTipText("");
        tabs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabsStateChanged(evt);
            }
        });

        label_horario.setText("HoraInicio:");

        label_horario1.setText("HoraFin:");

        label_hora.setText("HH:MM");

        label_hora1.setText("HH:MM");

        javax.swing.GroupLayout panel_horarioLayout = new javax.swing.GroupLayout(panel_horario);
        panel_horario.setLayout(panel_horarioLayout);
        panel_horarioLayout.setHorizontalGroup(
            panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_horarioLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(label_horario)
                .addGap(30, 30, 30)
                .addComponent(textfield_horario_horainicio, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(label_horario1)
                .addGap(30, 30, 30)
                .addComponent(textfield_horario_horafin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(panel_horarioLayout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(label_hora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_hora1)
                .addGap(185, 185, 185))
        );
        panel_horarioLayout.setVerticalGroup(
            panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_horarioLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_hora)
                    .addComponent(label_hora1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_horario1)
                        .addComponent(textfield_horario_horafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_horarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_horario)
                        .addComponent(textfield_horario_horainicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );

        tabs.addTab("Horario", panel_horario);

        panel_empleado.setToolTipText("Empleado");

        label_empleado1.setText("IdHorario:");

        label_empleado2.setText("Celular:");

        label_empleado3.setText("Sueldo:");

        label_empleado4.setText("Días:");

        label_empleado5.setText("Nombre:");

        javax.swing.GroupLayout panel_empleadoLayout = new javax.swing.GroupLayout(panel_empleado);
        panel_empleado.setLayout(panel_empleadoLayout);
        panel_empleadoLayout.setHorizontalGroup(
            panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empleadoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_empleadoLayout.createSequentialGroup()
                        .addComponent(label_empleado1)
                        .addGap(29, 29, 29)
                        .addComponent(combobox_empleado_idhorario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_empleadoLayout.createSequentialGroup()
                        .addComponent(label_empleado5)
                        .addGap(29, 29, 29)
                        .addComponent(textfield_empleado_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addGroup(panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_empleado3)
                    .addComponent(label_empleado2))
                .addGap(43, 43, 43)
                .addGroup(panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_empleadoLayout.createSequentialGroup()
                        .addComponent(textfield_empleado_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(label_empleado4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(textfield_empleado_dias, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(panel_empleadoLayout.createSequentialGroup()
                        .addComponent(textfield_empleado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panel_empleadoLayout.setVerticalGroup(
            panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empleadoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_empleado3)
                    .addComponent(textfield_empleado_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_empleado4)
                    .addComponent(textfield_empleado_dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_empleado5)
                    .addComponent(textfield_empleado_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_empleado1)
                        .addComponent(combobox_empleado_idhorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_empleado2)
                        .addComponent(textfield_empleado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tabs.addTab("Empleado", panel_empleado);

        label_cliente.setText("Nombre:");

        label_cliente1.setText("IdEmpleado:");

        label_cliente2.setText("Direccion:");

        javax.swing.GroupLayout panel_clienteLayout = new javax.swing.GroupLayout(panel_cliente);
        panel_cliente.setLayout(panel_clienteLayout);
        panel_clienteLayout.setHorizontalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_clienteLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(label_cliente)
                .addGap(29, 29, 29)
                .addComponent(textfield_cliente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(label_cliente2)
                .addGap(29, 29, 29)
                .addComponent(textfield_cliente_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(label_cliente1)
                .addGap(33, 33, 33)
                .addComponent(combobox_cliente_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        panel_clienteLayout.setVerticalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clienteLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_cliente1)
                        .addComponent(combobox_cliente_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_cliente2)
                        .addComponent(textfield_cliente_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_cliente)
                        .addComponent(textfield_cliente_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        tabs.addTab("Cliente", panel_cliente);

        label_suscripcion.setText("IdCliente:");

        label_suscripcion1.setText("IdEmpleado:");

        label_suscripcion2.setText("Precio:");

        label_suscripcion3.setText("Duracion:");

        label_suscripcion4.setText("Tipo:");

        label_suscripcion5.setText("Fecha:");

        label_suscripcion6.setText("Estado:");

        javax.swing.GroupLayout panel_suscripcionLayout = new javax.swing.GroupLayout(panel_suscripcion);
        panel_suscripcion.setLayout(panel_suscripcionLayout);
        panel_suscripcionLayout.setHorizontalGroup(
            panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_suscripcionLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_suscripcionLayout.createSequentialGroup()
                        .addComponent(label_suscripcion1)
                        .addGap(18, 18, 18)
                        .addComponent(combobox_suscripcion_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_suscripcionLayout.createSequentialGroup()
                        .addGroup(panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_suscripcionLayout.createSequentialGroup()
                                .addComponent(label_suscripcion2)
                                .addGap(45, 45, 45))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_suscripcionLayout.createSequentialGroup()
                                .addComponent(label_suscripcion)
                                .addGap(30, 30, 30)))
                        .addGroup(panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfield_suscripcion_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combobox_suscripcion_idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(67, 67, 67)
                .addGroup(panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_suscripcionLayout.createSequentialGroup()
                        .addComponent(label_suscripcion3)
                        .addGap(30, 30, 30)
                        .addComponent(textfield_suscripcion_duracion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_suscripcionLayout.createSequentialGroup()
                        .addComponent(label_suscripcion4)
                        .addGap(30, 30, 30)
                        .addComponent(textfield_suscripcion_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_suscripcionLayout.createSequentialGroup()
                        .addComponent(label_suscripcion5)
                        .addGap(30, 30, 30)
                        .addComponent(textfield_suscripcion_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(label_suscripcion6)
                .addGap(30, 30, 30)
                .addComponent(textfield_suscripcion_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        panel_suscripcionLayout.setVerticalGroup(
            panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_suscripcionLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_suscripcion)
                    .addComponent(label_suscripcion3)
                    .addComponent(textfield_suscripcion_duracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_suscripcion_idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_suscripcion1)
                    .addComponent(label_suscripcion4)
                    .addComponent(textfield_suscripcion_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_suscripcion6)
                    .addComponent(textfield_suscripcion_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_suscripcion_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_suscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_suscripcion2)
                    .addComponent(textfield_suscripcion_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_suscripcion5)
                    .addComponent(textfield_suscripcion_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tabs.addTab("Suscripcion", panel_suscripcion);

        label_clase.setText("IdEmpleado:");

        label_clase1.setText("IdHorario:");

        label_clase2.setText("Nombre:");

        label_clase3.setText("Cupo:");

        javax.swing.GroupLayout panel_claseLayout = new javax.swing.GroupLayout(panel_clase);
        panel_clase.setLayout(panel_claseLayout);
        panel_claseLayout.setHorizontalGroup(
            panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_claseLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_clase, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_clase1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(27, 27, 27)
                .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combobox_clase_idhorario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_clase_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_claseLayout.createSequentialGroup()
                        .addComponent(label_clase2)
                        .addGap(18, 18, 18)
                        .addComponent(textfield_clase_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_claseLayout.createSequentialGroup()
                        .addComponent(label_clase3)
                        .addGap(18, 18, 18)
                        .addComponent(textfield_clase_cupo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        panel_claseLayout.setVerticalGroup(
            panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_claseLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_clase2)
                            .addComponent(textfield_clase_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(label_clase))
                    .addGroup(panel_claseLayout.createSequentialGroup()
                        .addComponent(combobox_clase_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(12, 12, 12)
                .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_clase3)
                        .addComponent(textfield_clase_cupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_claseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_clase1)
                        .addComponent(combobox_clase_idhorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        tabs.addTab("Clase", panel_clase);

        label_inscripcion.setText("IdCliente:");

        label_inscripcion1.setText("IdClase:");

        javax.swing.GroupLayout panel_inscripcionLayout = new javax.swing.GroupLayout(panel_inscripcion);
        panel_inscripcion.setLayout(panel_inscripcionLayout);
        panel_inscripcionLayout.setHorizontalGroup(
            panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inscripcionLayout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(label_inscripcion1)
                .addGap(36, 36, 36)
                .addComponent(combobox_inscripcion_idclase, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(label_inscripcion)
                .addGap(35, 35, 35)
                .addComponent(combobox_inscripcion_idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        panel_inscripcionLayout.setVerticalGroup(
            panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inscripcionLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_inscripcion1)
                        .addComponent(combobox_inscripcion_idclase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_inscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_inscripcion)
                        .addComponent(combobox_inscripcion_idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
        );

        tabs.addTab("Inscripcion", panel_inscripcion);

        label_pago.setText("IdSuscripcion:");

        label_pago1.setText("IdCliente:");

        label_pago2.setText("Total:");

        label_pago3.setText("Fecha:");

        javax.swing.GroupLayout panel_pagoLayout = new javax.swing.GroupLayout(panel_pago);
        panel_pago.setLayout(panel_pagoLayout);
        panel_pagoLayout.setHorizontalGroup(
            panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pagoLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_pagoLayout.createSequentialGroup()
                        .addComponent(label_pago1)
                        .addGap(30, 30, 30)
                        .addComponent(combobox_pago_idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_pagoLayout.createSequentialGroup()
                        .addComponent(label_pago)
                        .addGap(30, 30, 30)
                        .addComponent(combobox_pago_idsuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(81, 81, 81)
                .addGroup(panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_pagoLayout.createSequentialGroup()
                        .addComponent(label_pago2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textfield_pago_total, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_pagoLayout.createSequentialGroup()
                        .addComponent(label_pago3)
                        .addGap(30, 30, 30)
                        .addComponent(textfield_pago_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        panel_pagoLayout.setVerticalGroup(
            panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pagoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_pago2)
                        .addComponent(textfield_pago_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_pago)
                        .addComponent(combobox_pago_idsuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_pago3)
                        .addComponent(textfield_pago_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_pago1)
                        .addComponent(combobox_pago_idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tabs.addTab("Pago", panel_pago);

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
                .addGroup(panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_articulo3)
                        .addComponent(textfield_articulo_existencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_articuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_articulo2)
                        .addComponent(textfield_articulo_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_articulo1)
                        .addComponent(textfield_articulo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        tabs.addTab("Articulo", panel_articulo);

        label_venta.setText("IdEmpleado:");

        label_venta2.setText("Fecha:");

        javax.swing.GroupLayout panel_ventaLayout = new javax.swing.GroupLayout(panel_venta);
        panel_venta.setLayout(panel_ventaLayout);
        panel_ventaLayout.setHorizontalGroup(
            panel_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ventaLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(label_venta)
                .addGap(30, 30, 30)
                .addComponent(combobox_venta_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(label_venta2)
                .addGap(30, 30, 30)
                .addComponent(textfield_venta_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        panel_ventaLayout.setVerticalGroup(
            panel_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ventaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panel_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_venta)
                    .addComponent(label_venta2)
                    .addComponent(textfield_venta_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_venta_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        tabs.addTab("Venta", panel_venta);

        label_detalleventa.setText("IdArticulo:");

        label_detalleventa1.setText("Cantidad:");

        label_detalleventa2.setText("IdVenta:");

        javax.swing.GroupLayout panel_detalleventaLayout = new javax.swing.GroupLayout(panel_detalleventa);
        panel_detalleventa.setLayout(panel_detalleventaLayout);
        panel_detalleventaLayout.setHorizontalGroup(
            panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detalleventaLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_detalleventaLayout.createSequentialGroup()
                        .addComponent(label_detalleventa2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(combobox_detalleventa_idventa, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_detalleventaLayout.createSequentialGroup()
                        .addComponent(label_detalleventa)
                        .addGap(30, 30, 30)
                        .addComponent(combobox_detalleventa_idarticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addComponent(label_detalleventa1)
                .addGap(42, 42, 42)
                .addComponent(textfield_detalleventa_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );
        panel_detalleventaLayout.setVerticalGroup(
            panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detalleventaLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_detalleventa)
                    .addComponent(combobox_detalleventa_idarticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfield_detalleventa_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_detalleventa1))
                .addGap(18, 18, 18)
                .addGroup(panel_detalleventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_detalleventa2)
                    .addComponent(combobox_detalleventa_idventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        tabs.addTab("DetalleVenta", panel_detalleventa);

        label_compra.setText("IdEmpleado:");

        label_compra2.setText("Fecha:");

        javax.swing.GroupLayout panel_compraLayout = new javax.swing.GroupLayout(panel_compra);
        panel_compra.setLayout(panel_compraLayout);
        panel_compraLayout.setHorizontalGroup(
            panel_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_compraLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(label_compra)
                .addGap(18, 18, 18)
                .addComponent(combobox_compra_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(label_compra2)
                .addGap(30, 30, 30)
                .addComponent(textfield_compra_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        panel_compraLayout.setVerticalGroup(
            panel_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_compraLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panel_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combobox_compra_idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_compra2)
                        .addComponent(textfield_compra_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_compra))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        tabs.addTab("Compra", panel_compra);

        label_detallecompra.setText("IdArticulo:");

        label_detallecompra1.setText("Cantidad:");

        label_detallecompra2.setText("IdCompra:");

        javax.swing.GroupLayout panel_detallecompraLayout = new javax.swing.GroupLayout(panel_detallecompra);
        panel_detallecompra.setLayout(panel_detallecompraLayout);
        panel_detallecompraLayout.setHorizontalGroup(
            panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detallecompraLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detallecompraLayout.createSequentialGroup()
                        .addComponent(label_detallecompra2)
                        .addGap(30, 30, 30)
                        .addComponent(combobox_detallecompra_idcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_detallecompraLayout.createSequentialGroup()
                        .addComponent(label_detallecompra)
                        .addGap(30, 30, 30)
                        .addComponent(combobox_detallecompra_idarticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(label_detallecompra1)
                        .addGap(31, 31, 31)
                        .addComponent(textfield_detallecompra_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        panel_detallecompraLayout.setVerticalGroup(
            panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detallecompraLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_detallecompra)
                    .addComponent(combobox_detallecompra_idarticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfield_detallecompra_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_detallecompra1))
                .addGap(18, 18, 18)
                .addGroup(panel_detallecompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_detallecompra2)
                    .addComponent(combobox_detallecompra_idcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        tabs.addTab("DetalleCompra", panel_detallecompra);

        label_horario2.setText("Ventas Mín:");

        label_horario3.setText("Ventas Max:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Artículos con mínimo y máximo de Ventas");

        javax.swing.GroupLayout panel_reporte1Layout = new javax.swing.GroupLayout(panel_reporte1);
        panel_reporte1.setLayout(panel_reporte1Layout);
        panel_reporte1Layout.setHorizontalGroup(
            panel_reporte1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_reporte1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(label_horario2)
                .addGap(30, 30, 30)
                .addComponent(textfield_reporte1_ventasmin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(label_horario3)
                .addGap(30, 30, 30)
                .addComponent(textfield_reporte1_ventasmax, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_reporte1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
        panel_reporte1Layout.setVerticalGroup(
            panel_reporte1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_reporte1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(panel_reporte1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_reporte1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_horario3)
                        .addComponent(textfield_reporte1_ventasmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_reporte1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label_horario2)
                        .addComponent(textfield_reporte1_ventasmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        tabs.addTab("Reporte1", panel_reporte1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Clases entre el Rango de Horarios");

        label_hora4.setText("HH:MM");

        label_horario6.setText("HoraInicio:");

        label_horario7.setText("HoraFin:");

        label_hora5.setText("HH:MM");

        javax.swing.GroupLayout panel_reporte2Layout = new javax.swing.GroupLayout(panel_reporte2);
        panel_reporte2.setLayout(panel_reporte2Layout);
        panel_reporte2Layout.setHorizontalGroup(
            panel_reporte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_reporte2Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(panel_reporte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_reporte2Layout.createSequentialGroup()
                        .addComponent(label_horario6)
                        .addGap(30, 30, 30)
                        .addComponent(textfield_reporte2_horainicio, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_reporte2Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(label_hora4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(panel_reporte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_reporte2Layout.createSequentialGroup()
                        .addComponent(label_horario7)
                        .addGap(30, 30, 30)
                        .addComponent(textfield_reporte2_horafin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_reporte2Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(label_hora5)))
                .addGap(180, 180, 180))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_reporte2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
        panel_reporte2Layout.setVerticalGroup(
            panel_reporte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_reporte2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_reporte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_reporte2Layout.createSequentialGroup()
                        .addComponent(label_hora5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_reporte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_horario7)
                            .addComponent(textfield_reporte2_horafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_reporte2Layout.createSequentialGroup()
                        .addComponent(label_hora4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_reporte2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_horario6)
                            .addComponent(textfield_reporte2_horainicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        tabs.addTab("Reporte2", panel_reporte2);

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

        btn_logout.setText("Cerrar Sesión");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        btn_consultar.setText("Consultar");
        btn_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar))
                .addGap(15, 15, 15)
                .addComponent(btn_consultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        btn_consultar.getAccessibleContext().setAccessibleName("Consultar");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabsStateChanged
       if(sentencia != null){
            actualizaBotones();
            if(llenarTabla())
                actualizaComponentes();
            limpiarPantalla();
       }else{
           if(tabs.getSelectedIndex() != 0){
                tabs.setSelectedIndex(0);
                JOptionPane.showMessageDialog(this, login_message_failed);
                limpiarPantalla();
           }
       }
    }//GEN-LAST:event_tabsStateChanged

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        if(sentencia != null){
            try{
                String tabla = getSelectedTable();
                int dialogResult;
                
                if(tabla.equals("DetalleCompra") || tabla.equals("DetalleVenta")){
                    dialogResult = JOptionPane.showConfirmDialog(null, 
                            "Después de insertar el registro ya no se podrá modificar ni eliminar. ¿Seguro que quieres continuar?");
                }else{
                    dialogResult = JOptionPane.YES_OPTION;
                }
                
                if(dialogResult == JOptionPane.YES_OPTION){
                    String sentenciaSQL = getInsertSentencia(tabla);
                    sentencia.execute(sentenciaSQL);
                    JOptionPane.showMessageDialog(this, "Agregado correctamente");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            llenarTabla();
            limpiarPantalla();
        }else{
            JOptionPane.showMessageDialog(this, login_message_failed);
        }
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void datagridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datagridMouseClicked
        int idx = datagrid.getSelectedRow();
           
        if(idx != -1){
            actualizaFormulario(idx);
        }
    }//GEN-LAST:event_datagridMouseClicked

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        if(sentencia != null){
            int idx = datagrid.getSelectedRow();

            if(idx != -1){
                try{
                    String tabla = getSelectedTable();
                    
                    if(!tabla.equals("DetalleCompra") && 
                            !tabla.equals("DetalleVenta"))
                    {
                        String sentenciaSQL = getUpdateSentencia(tabla, idx);
                        sentencia.execute(sentenciaSQL);
                        JOptionPane.showMessageDialog(this, "Modificado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(this, "No se puede modificar el registro.");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    return;
                }
                llenarTabla();
                limpiarPantalla();
            }
        }else{
            JOptionPane.showMessageDialog(this, login_message_failed);
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        if(sentencia != null){
            int idx = datagrid.getSelectedRow();
            if(idx != -1){
                try{
                    String tabla = getSelectedTable();
                    
                    if(!tabla.equals("DetalleCompra") && !tabla.equals("DetalleVenta") && 
                            checkTabla(tabla, idx))
                    {
                        String sentenciaSQL = getDeleteSentencia(tabla, idx);
                        sentencia.execute(sentenciaSQL);
                        JOptionPane.showMessageDialog(this, "Eliminado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(this, "No se puede eliminar el registro.");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    return;
                }
                llenarTabla();
                limpiarPantalla();
            }
        }else{
            JOptionPane.showMessageDialog(this, login_message_failed);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        llenarTabla();
        actualizaComponentes();
        actualizaBotones();
    }//GEN-LAST:event_formWindowOpened

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        if(sentencia != null){
            this.setVisible(false);
            new login().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultarActionPerformed
        consultarReportes();
    }//GEN-LAST:event_btn_consultarActionPerformed

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
                //new JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_consultar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JComboBox<String> combobox_clase_idempleado;
    private javax.swing.JComboBox<String> combobox_clase_idhorario;
    private javax.swing.JComboBox<String> combobox_cliente_idempleado;
    private javax.swing.JComboBox<String> combobox_compra_idempleado;
    private javax.swing.JComboBox<String> combobox_detallecompra_idarticulo;
    private javax.swing.JComboBox<String> combobox_detallecompra_idcompra;
    private javax.swing.JComboBox<String> combobox_detalleventa_idarticulo;
    private javax.swing.JComboBox<String> combobox_detalleventa_idventa;
    private javax.swing.JComboBox<String> combobox_empleado_idhorario;
    private javax.swing.JComboBox<String> combobox_inscripcion_idclase;
    private javax.swing.JComboBox<String> combobox_inscripcion_idcliente;
    private javax.swing.JComboBox<String> combobox_pago_idcliente;
    private javax.swing.JComboBox<String> combobox_pago_idsuscripcion;
    private javax.swing.JComboBox<String> combobox_suscripcion_idcliente;
    private javax.swing.JComboBox<String> combobox_suscripcion_idempleado;
    private javax.swing.JComboBox<String> combobox_venta_idempleado;
    private javax.swing.JTable datagrid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel label_articulo1;
    private javax.swing.JLabel label_articulo2;
    private javax.swing.JLabel label_articulo3;
    private javax.swing.JLabel label_clase;
    private javax.swing.JLabel label_clase1;
    private javax.swing.JLabel label_clase2;
    private javax.swing.JLabel label_clase3;
    private javax.swing.JLabel label_cliente;
    private javax.swing.JLabel label_cliente1;
    private javax.swing.JLabel label_cliente2;
    private javax.swing.JLabel label_compra;
    private javax.swing.JLabel label_compra2;
    private javax.swing.JLabel label_detallecompra;
    private javax.swing.JLabel label_detallecompra1;
    private javax.swing.JLabel label_detallecompra2;
    private javax.swing.JLabel label_detalleventa;
    private javax.swing.JLabel label_detalleventa1;
    private javax.swing.JLabel label_detalleventa2;
    private javax.swing.JLabel label_empleado1;
    private javax.swing.JLabel label_empleado2;
    private javax.swing.JLabel label_empleado3;
    private javax.swing.JLabel label_empleado4;
    private javax.swing.JLabel label_empleado5;
    private javax.swing.JLabel label_hora;
    private javax.swing.JLabel label_hora1;
    private javax.swing.JLabel label_hora4;
    private javax.swing.JLabel label_hora5;
    private javax.swing.JLabel label_horario;
    private javax.swing.JLabel label_horario1;
    private javax.swing.JLabel label_horario2;
    private javax.swing.JLabel label_horario3;
    private javax.swing.JLabel label_horario6;
    private javax.swing.JLabel label_horario7;
    private javax.swing.JLabel label_inscripcion;
    private javax.swing.JLabel label_inscripcion1;
    private javax.swing.JLabel label_pago;
    private javax.swing.JLabel label_pago1;
    private javax.swing.JLabel label_pago2;
    private javax.swing.JLabel label_pago3;
    private javax.swing.JLabel label_suscripcion;
    private javax.swing.JLabel label_suscripcion1;
    private javax.swing.JLabel label_suscripcion2;
    private javax.swing.JLabel label_suscripcion3;
    private javax.swing.JLabel label_suscripcion4;
    private javax.swing.JLabel label_suscripcion5;
    private javax.swing.JLabel label_suscripcion6;
    private javax.swing.JLabel label_venta;
    private javax.swing.JLabel label_venta2;
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
    private javax.swing.JPanel panel_reporte1;
    private javax.swing.JPanel panel_reporte2;
    private javax.swing.JPanel panel_suscripcion;
    private javax.swing.JPanel panel_venta;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField textfield_articulo_existencia;
    private javax.swing.JTextField textfield_articulo_nombre;
    private javax.swing.JTextField textfield_articulo_precio;
    private javax.swing.JTextField textfield_clase_cupo;
    private javax.swing.JTextField textfield_clase_nombre;
    private javax.swing.JTextField textfield_cliente_direccion;
    private javax.swing.JTextField textfield_cliente_nombre;
    private javax.swing.JTextField textfield_compra_fecha;
    private javax.swing.JTextField textfield_detallecompra_cantidad;
    private javax.swing.JTextField textfield_detalleventa_cantidad;
    private javax.swing.JTextField textfield_empleado_celular;
    private javax.swing.JTextField textfield_empleado_dias;
    private javax.swing.JTextField textfield_empleado_nombre;
    private javax.swing.JTextField textfield_empleado_sueldo;
    private javax.swing.JTextField textfield_horario_horafin;
    private javax.swing.JTextField textfield_horario_horainicio;
    private javax.swing.JTextField textfield_pago_fecha;
    private javax.swing.JTextField textfield_pago_total;
    private javax.swing.JTextField textfield_reporte1_ventasmax;
    private javax.swing.JTextField textfield_reporte1_ventasmin;
    private javax.swing.JTextField textfield_reporte2_horafin;
    private javax.swing.JTextField textfield_reporte2_horainicio;
    private javax.swing.JTextField textfield_suscripcion_duracion;
    private javax.swing.JTextField textfield_suscripcion_estado;
    private javax.swing.JTextField textfield_suscripcion_fecha;
    private javax.swing.JTextField textfield_suscripcion_precio;
    private javax.swing.JTextField textfield_suscripcion_tipo;
    private javax.swing.JTextField textfield_venta_fecha;
    // End of variables declaration//GEN-END:variables
}
