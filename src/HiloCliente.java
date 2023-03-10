import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HiloCliente extends Thread{

    private final Socket cliente;

    public HiloCliente(Socket cliente){
        this.cliente = cliente;
    }

    public void run() {
        while (true) {
            try {
                //System.out.println("Conexión recibida!!\n");
                //Instancia del objeto de entrada
                InputStream is = cliente.getInputStream();
                DataInputStream daIn = new DataInputStream(is);
                //Metemos en una variable la opcion leida que determina cómo el servidor debe tratar el dato
                String mensaje = daIn.readUTF();
                System.out.println("Mensaje recibido: " + mensaje);
                devolverDatos(mensaje);
            } catch (IOException e) {

            }
        }
    }

    public static void devolverDatos(String mensaje) throws IOException {
        //Creamos un nuevo socket
        Socket clienteSocket = new Socket();
        System.out.println("Estableciendo la conexión...\n");

        //Asignamos ip y puerto al nuevo socket
        InetSocketAddress addr = new InetSocketAddress("localhost", 6666);
        clienteSocket.connect(addr);

        //Instanciamos el objeto de salida
        OutputStream os = clienteSocket.getOutputStream();
        DataOutputStream daOu = new DataOutputStream(os);
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
            case "" -> {
                //Devolvemos el dato
                System.out.println("Devolviendo mensaje...\n");
                String mensajeDev3 = "**Debes rellenar el campo de texto**";
                daOu.writeUTF(mensajeDev3);
                System.out.println("Mensaje devuelto\n");
            }
            case "Exit" -> {
                System.exit(0);
            }
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
