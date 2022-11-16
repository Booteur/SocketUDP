package com;


import java.net.*;


public class ServeurUDP {
    static boolean  finJeuChezServeur=false;
    public static void main(String[] args) {
        //verifier si le jeu atteint sa fin ou pas

        int nbSecret=(int) (Math.random()*100);
        System.out.println(nbSecret);
        String msgRenseignement;
        byte[]donnees;
        DatagramSocket dgSocketServ=null;
        DatagramPacket dgPacket;
        try{
            dgSocketServ=new DatagramSocket(123);
            System.out.println("On lance le jeu !!!! A vous de jouer");
            while (!finJeuChezServeur){
                donnees=new byte[1000];
                dgPacket=new DatagramPacket(donnees,donnees.length);
                dgSocketServ.receive(dgPacket);
                String ch_nbr=new String(donnees,0,dgPacket.getLength());
                int nbr_Essai=Integer.parseInt(ch_nbr);
                if (nbr_Essai>nbSecret){
                    msgRenseignement="Merci de donner un nombre plus petit";
                    System.out.println("On attent une nouvelle tentative");
                } else if (nbr_Essai<nbSecret) {
                    msgRenseignement="Merci de donner un nombre plus grand";
                    System.out.println("On attent une nouvelle tentative");
                }
                else {
                    finJeuChezServeur=true;
                    System.out.println(finJeuChezServeur);
                    msgRenseignement="Bravo";
                    System.out.println("On termine le jeu!!!! A la prochaine");
                }
                int portClient=dgPacket.getPort();
                InetAddress addressIpClient=dgPacket.getAddress();
                donnees=new byte[msgRenseignement.length()];
                msgRenseignement.getBytes(0,msgRenseignement.length(),donnees,0);
                //Creation du paquet  pour l'envoyer au client
                dgPacket=new DatagramPacket(donnees,msgRenseignement.length(),addressIpClient,portClient);
                dgSocketServ.send(dgPacket);
                System.out.println("Le gagnant est le client d'adresse "+addressIpClient+" et de port"+" "+portClient);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

