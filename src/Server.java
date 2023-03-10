import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    public static void main(String[] args) throws IOException {

        System.out.println("**SERVIDOR**\n");

        //Creando socket servidor
        ServerSocket serverSocket = new ServerSocket();

        //Realizamos el bind
        InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
        serverSocket.bind(addr);

        do {
            //Esperando a que llegue una conexión
            System.out.println("Aceptando conexiones...\n");
            Socket newSocket = serverSocket.accept();
            HiloCliente thread = new HiloCliente(newSocket);
            thread.start();
        } while (true);
    }
}