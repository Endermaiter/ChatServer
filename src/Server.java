import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

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

            System.out.println("Conexión recibida!!\n");

            //Instancia del objeto de entrada
            InputStream is = newSocket.getInputStream();
            DataInputStream daIn = new DataInputStream(is);
            //Metemos en una variable la opcion leida que determina cómo el servidor debe tratar el dato
            String mensaje = daIn.readUTF();
            System.out.println("Mensaje recibido: " + mensaje);
            devolverDatos(mensaje);

            //Metemos en una variable el numero de digitos que tiene el dato recibido
/*
            switch (opcion) {

                case 1 -> {

                    //Metemos en una variable el dato recibido por el cliente (El tamaño del array será exactamente igual numero de cifras del dato)
                    byte[] arrayDatos = new byte[digitos];
                    is.read(arrayDatos);
                    int datoRecibido = Integer.parseInt(new String(arrayDatos));

                    //Hacemos los cálculos necesarios
                    float eqCamposFlo = datoRecibido / 4050f; //1 campo de fútbol = 4050m2
                    String eqCampos = String.valueOf(eqCamposFlo);

                    //le mandamos el dato modificado al método devolverDatos que mandará el dato al cliente
                    devolverDatos(eqCampos);
                    System.out.println(eqCampos);

                } //**EL RESTO DE OPCIONES FUNCIONAN EXACTAMENTE IGUAL QUE LA PRIMERA PERO CAMBIANDO LAS OPERACIONES

                case 2 -> {

                    byte[] arrayDatos2 = new byte[digitos];
                    is.read(arrayDatos2);
                    int datoRecibido2 = Integer.parseInt(new String(arrayDatos2));

                    float eqMesesFlo = (datoRecibido2 * 12); //Meses que quedan para la jubilación
                    String eqMeses = String.valueOf(eqMesesFlo);

                    devolverDatos(eqMeses);
                }

                case 3 -> {

                    byte[] arrayDatos3 = new byte[digitos];
                    is.read(arrayDatos3);
                    int datoRecibido3 = Integer.parseInt(new String(arrayDatos3));

                    float eqLibrosFlo = datoRecibido3 / 47f; //Pérez Reverte tiene 47 obras literarias
                    String eqLibros = String.valueOf(eqLibrosFlo);

                    devolverDatos(eqLibros);
                }

                case 4 -> {

                    byte[] arrayDatos4 = new byte[digitos];
                    is.read(arrayDatos4);
                    int datoRecibido4 = Integer.parseInt(new String(arrayDatos4));

                    float difGasolinaFlo = datoRecibido4 - 1.517f; //gasolinera más barata -> 1,517€/L
                    String difGasolina = String.valueOf(difGasolinaFlo);

                    devolverDatos(difGasolina);
                }

                case 5 -> {

                    //Cerrando el socket
                    newSocket.close();

                    //Cerrando el socket del servdor
                    serverSocket.close();

                    //Cerrando servidor
                    System.out.println("Cerrando servidor...");
                    System.out.println("**SERVIDOR CERRADO**");
                    System.exit(0);
                }
            }*/
        } while (true);

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
                System.out.println("Devolviendo dato...\n");
                System.out.println(mensaje);
                String mensajeDev = "Adios";
                daOu.writeUTF(mensajeDev);
                System.out.println("Dato devuelto:" + mensajeDev);
            }
            case "Que" -> {
                //Devolvemos el dato
                System.out.println("Devolviendo dato...\n");
                String mensajeDev2 = "So";
                daOu.writeUTF(mensajeDev2);
                System.out.println("Dato devuelto\n");
            }
        }


        //Cerramos el socket
        clienteSocket.close();
    }
}