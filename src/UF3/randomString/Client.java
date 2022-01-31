package UF3.randomString;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class Client {
    /* Client afegit al grup multicast SrvVelocitats.java que representa un velocímetre */

    private boolean continueRunning = true;
    private MulticastSocket socket;
    private InetAddress multicastIP;
    private int port;
    NetworkInterface netIf;
    InetSocketAddress group;
    int hola = 0;
    int adios = 0;
    int pepe = 0;
    int cola = 0;
    int caracola = 0;
    int nariz = 0;
    int jota = 0;


    public Client(int portValue, String strIp) throws IOException {
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        socket = new MulticastSocket(port);
        netIf = socket.getNetworkInterface();
        group = new InetSocketAddress(strIp,portValue);
    }

    public void runClient() throws IOException{
        DatagramPacket packet;
        byte [] receivedData = new byte[10];

        socket.joinGroup(group,netIf);
        System.out.printf("Connectat a %s:%d%n",group.getAddress(),group.getPort());

        while(continueRunning){
            packet = new DatagramPacket(receivedData, 10);
            socket.setSoTimeout(5000);
            try{
                socket.receive(packet);
                continueRunning = getData(packet.getData(), packet.getLength());
            }catch(SocketTimeoutException e){
                System.out.println("S'ha perdut la connexió amb el servidor.");
                continueRunning = false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        socket.leaveGroup(group,netIf);
        socket.close();
    }

    protected  boolean getData(byte[] data, int length) {
        boolean ret=true;

        String rebut = new String(data,0,length);
        int numero = 0;

        if (rebut.equals("Hola")){
            hola++;
            numero=hola;
        }
        if (rebut.equals("Adios")){
            adios++;
            numero=adios;
        }
        if (rebut.equals("Pepe")){
            pepe++;
            numero=pepe;
        }
        if (rebut.equals("Cola")){
            cola++;
            numero=cola;
        }
        if (rebut.equals("Caracola")){
            caracola++;
            numero=caracola;
        }
        if (rebut.equals("Nariz")){
            nariz++;
            numero=nariz;
        }
        if (rebut.equals("Jota")){
            jota++;
            numero=jota;
        }

        System.out.println("Numero de veces: (" + numero + "), Palabra: " + rebut);

        return ret;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(5557, "224.0.10.20");
        client.runClient();
        System.out.println("Parat!");

    }

}