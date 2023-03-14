import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    public static void main(String[] args) throws IOException {

        System.out.println("**SERVIDOR**\n");

        //Creando socket servidor
        ServerSocket serverSocket = new ServerSocket();

        //Asignamos ip y puerto
        InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
        //Realizamos el bind
        serverSocket.bind(addr);

        //Iniciamos un bucli infinito para que siempre esté abierto a nuevas conexiones
        do {
            //Esperando a que llegue una conexión
            System.out.println("Aceptando conexiones...\n");
            Socket newSocket = serverSocket.accept();
            //Instanciamos un nuevo hilo para el nuevo cliente
            HiloCliente thread = new HiloCliente(newSocket);
            //Iniciamos un hilo exclusivo para ese cliente
            thread.start();
        } while (true);
    }
}