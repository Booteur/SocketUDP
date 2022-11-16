package com;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {


        try {
           String msgRecuDuServeur;

            InetAddress serverAddressClient=InetAddress.getByName("localhost");
            DatagramSocket ClientUDP=new DatagramSocket();
            DatagramPacket dgPaquetClient;
            byte[]donneesClient;
            boolean finJeuChezClient=false;
            Scanner Clavier = new Scanner(System.in);
            while (!finJeuChezClient){
                System.out.println(finJeuChezClient);
                System.out.println("Veuillez saisir un nombre de 0 à 100");
                String recup = Clavier.nextLine();
                donneesClient=recup.getBytes();
                dgPaquetClient = new DatagramPacket(donneesClient, recup.getBytes().length, serverAddressClient, 123);
                ClientUDP.send(dgPaquetClient);

                donneesClient=new byte[1000];
                dgPaquetClient=new DatagramPacket(donneesClient, donneesClient.length);
                ClientUDP.receive(dgPaquetClient);
                msgRecuDuServeur=new String(donneesClient,0,dgPaquetClient.getLength());
               if(msgRecuDuServeur.equals("Bravo")==true){
                   finJeuChezClient=true;
                   System.out.println("fin de Jeu le nombre trouvé est : "+Integer.parseInt(recup));
               }


            }
            ClientUDP.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
