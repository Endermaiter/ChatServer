import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HiloCliente extends Thread{

    private final Socket cliente;

    //Constructor del hilo con parametro de tipo socket para diferenciar el hilo de otros clientes
    public HiloCliente(Socket cliente){
        this.cliente = cliente;
    }

    //Ejecucion del hilo
    public void run() {
        while (true) {
            //CONEXION RECIBIDA
            try {
                //Instancia del objeto de entrada
                InputStream is = cliente.getInputStream();
                //Instanciamos un DataInputStream para facilitar la lectura
                DataInputStream daIn = new DataInputStream(is);
                //Leemos el mensaje y lo metemos en una variable
                String mensaje = daIn.readUTF();
                System.out.println("Mensaje recibido: " + mensaje);
                //LLamamos al metodo devolverMensaje() para devolver un mensaje al cliente
                devolverMensaje(mensaje);
            } catch (IOException e) {

            }
        }
    }

    public static void devolverMensaje(String mensaje) throws IOException {
        //Creamos un nuevo socket
        Socket clienteSocket = new Socket();
        System.out.println("Estableciendo la conexión...\n");

        //Asignamos ip y puerto al nuevo socket
        InetSocketAddress addr = new InetSocketAddress("localhost", 6666);
        clienteSocket.connect(addr);

        //Instanciamos el objeto de salida
        OutputStream os = clienteSocket.getOutputStream();
        //Instanciamos un DataOutputStream para facilitar la escritura
        DataOutputStream daOu = new DataOutputStream(os);

        //Segun el mensaje reicibido...
        switch (mensaje) {
            case "Hola" -> {
                //Devolvemos el dato
                System.out.println("Devolviendo mensaje...\n");
                System.out.println(mensaje);
                String mensajeDev = "Adios";
                daOu.writeUTF(mensajeDev);
                System.out.println("Mensaje devuelto:" + mensajeDev);
            }
            case "Que" -> {
                //Devolvemos el dato
                System.out.println("Devolviendo mensaje...\n");
                String mensajeDev2 = "So";
                daOu.writeUTF(mensajeDev2);
                System.out.println("Mensaje devuelto\n");
            }
            case "Marcos" -> {
                //Devolvemos el dato
                System.out.println("Devolviendo mensaje...\n");
                String mensajeDev3 = "Bienvenido " + mensaje + "!!";
                daOu.writeUTF(mensajeDev3);
                System.out.println("Mensaje devuelto\n");
            }
            case "Juan" -> {
                //Devolvemos el dato
                System.out.println("Devolviendo mensaje...\n");
                String mensajeDev3 = "Es un gran profesor";
                daOu.writeUTF(mensajeDev3);
                System.out.println("Mensaje devuelto\n");
            }

            //Casos especiales

            //Si no recibe nada(el jTextField quedó en blanco)
            case "" -> {
                //Devolvemos el dato
                System.out.println("Devolviendo mensaje...\n");
                String mensajeDev3 = "**Debes rellenar el campo de texto**";
                daOu.writeUTF(mensajeDev3);
                System.out.println("Mensaje devuelto\n");
            }
            //Si recibe la instruccion de cerrar el servidor
            case "Exit" -> {
                System.exit(0);
            }
            //Si recibe cualquier otra cosa que el servidor no entienda
            default -> {
                //Devolvemos el dato
                System.out.println("Devolviendo mensaje...\n");
                String mensajeDev4 = "**Perdona, no te he entendido, ¿Puedes repetirlo?**";
                daOu.writeUTF(mensajeDev4);
                System.out.println("Mensaje devuelto:\n" + mensajeDev4);
            }
        }

        //Cerramos el socket
        clienteSocket.close();
    }
}
