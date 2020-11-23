import java.sql.*;

public class MensajesDAO {

    public static void crearMensajeDB(Mensaje mensaje){
        Conexion db_connect = new Conexion();

        try(Connection conexion = db_connect.get_connection()) {

            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO mensajes (mensaje, autor_mensaje) VALUES (?,?)";
                ps = conexion.prepareStatement(query);
                ps.setString(1, mensaje.getMensaje());
                ps.setString(2, mensaje.getAutor_mensaje());
                //para ejecutar consulta
                ps.executeUpdate();
                System.out.println("mensaje creado");
            }catch (SQLException ex){
                System.out.println(ex);
            }

        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public static void leerMensajeDB(){
        Conexion db_connect = new Conexion();
        //preparar la sentencia
        PreparedStatement ps = null;
        //traer datos en fila para procesarlos y mostrarlo
        ResultSet rs = null;

        try(Connection conexion = db_connect.get_connection()) {
            String query = "SELECT * FROM mensajes";
            ps = conexion.prepareStatement(query);
            //no tiene transaccion es solo para pedir datos
            rs = ps.executeQuery();
            //mientras q tenga datos, mostrarlos
            while (rs.next()){
                System.out.println("ID: "+rs.getInt("id_mensaje"));
                System.out.println("Mensaje: "+rs.getString(2));
                System.out.println("Autor: "+rs.getString("autor_mensaje"));
                System.out.println("Fecha: "+rs.getString("fecha_mensaje"));
                System.out.println("");
            }
        }catch (SQLException e){
            System.out.println("no se pudo encontrar mensajes");
            System.out.println(e);
        }
    }

    public static void borrarMensajeDB(int id_mensaje){
        Conexion db_connect = new Conexion();

        try(Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "DELETE FROM mensajes WHERE id_mensaje = ?";
                //? parametro q se envia a la consulta
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id_mensaje);
                //para ejecutar transaccion
                //ps.executeUpdate(); retorna la cantidad de filas affectadas por la consulta
                //System.out.println("el mensaje se ha borrado");
                int countRowsUpdated = ps.executeUpdate();
                if (countRowsUpdated != 0) {
                    System.out.println(" has been deleted successfully.");
                } else {
                    System.out.println(" was not found.");
                }

            }catch (SQLException ex){
                System.out.println("no se pudo borrar el mensaje");
                System.out.println(ex);
            }
        }catch (SQLException e){
            System.out.println(e);
        }

    }

    public static void actualizarMensajeDB(Mensaje mensaje){
        Conexion db_connect = new Conexion();

        try(Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "UPDATE mensajes SET mensaje = ? WHERE id_mensaje = ?";
                //? parametro q se envia a la consulta
                ps = conexion.prepareStatement(query);
                ps.setString(1, mensaje.getMensaje());
                ps.setInt(2, mensaje.getId_mensaje());

                //ps.setString(3, mensaje.getAutor_mensaje());
                //para ejecutar transaccion
                //ps.executeUpdate(); retorna la cantidad de filas affectadas por la consulta
                //System.out.println("el mensaje se ha borrado");
                int countRowsUpdated = ps.executeUpdate();
                if (countRowsUpdated != 0) {
                    System.out.println(" has been edited.");
                } else {
                    System.out.println(" was not found.");
                }

            }catch (SQLException ex){
                System.out.println("no se pudo actualizar el mensaje");
                System.out.println(ex);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
