import java.util.Scanner;

public class MensajeServicio {

    public static void crearMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu mensaje");
        String mensaje = sc.nextLine();

        System.out.println("Escribe tu nombre");
        String nombre = sc.nextLine();

        Mensaje registro = new Mensaje();
        registro.setMensaje(mensaje);
        registro.setAutor_mensaje(nombre);
        //Enviar a la capa DAO
        MensajesDAO.crearMensajeDB(registro);
    }

    public static void listarMensajes(){
        MensajesDAO.leerMensajeDB();
    }

    public static void borrarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el codigo del mensaje a borrar");
        int codigo = sc.nextInt();
        MensajesDAO.borrarMensajeDB(codigo);
    }

    public static void editarMensaje(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Edita tu mensaje");
        String mensajeEdit = sc.nextLine();

        System.out.println("Indica el Id del mensaje a editar");
        int idMensajeEdit = sc.nextInt();

        Mensaje registroEdit = new Mensaje();
        registroEdit.setId_mensaje(idMensajeEdit);
        registroEdit.setMensaje(mensajeEdit);
        //Enviar a la capa DAO
        MensajesDAO.actualizarMensajeDB(registroEdit);
    }

}
