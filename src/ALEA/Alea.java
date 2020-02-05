package ALEA;// -*- coding: utf-8 -*-

import EPP.EPP;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.Random;

public class Alea
{

    static int nbTentative;
    static int nbTentativeMoyenne = 0;

    public static void main(String[] args)
    {
        long start = System.nanoTime();

        for (int i = 0; i < 10; i++) {
            long iStart = System.nanoTime();

            getProbablyPrimeNumber(512);

            long iEnd = System.nanoTime();
            double time = (double) (iEnd - iStart) / 1000000000;
            nbTentativeMoyenne += nbTentative;
            System.out.println("Temps boucle n°" + i + " : " + time + " seconde");
        }

        long end = System.nanoTime();

        double tempstotal = (double) (end - start) / 1000000000;
        System.out.println("Temps d'execution total : " + tempstotal + " seconde(s)");
        System.out.println("Temps d'execution moyen : " + tempstotal/10 + " seconde(s)");
        System.out.println("Nombre de tentative moyenne : " + nbTentativeMoyenne / 10);

    }

    public static BigInteger getProbablyPrimeNumber(int numBits) {
        Random alea = new Random();
        //int c = (int) Math.round(15 * (Math.log(10) / Math.log(2)));
        //BigInteger x = new BigInteger(512, c, alea);
        BigInteger x = new BigInteger(numBits, alea).setBit(511);

        boolean isProbablyPrime = EPP.est_probablement_premier(x);
        nbTentative = 1;

        while(!isProbablyPrime) {
            x = new BigInteger(512, alea).setBit(511);
            isProbablyPrime = EPP.est_probablement_premier(x);
            nbTentative++;
        }


        /*
        System.out.println ("Valeur de x : " + x);
        System.out.println("Nombre de bit de x : " + x.bitLength());
        System.out.println("Nombre de tentative : " + nbTentative);
        */

        return x;
    }

}

/*
  $ make
  javac *.java 
  $ java Alea
  Valeur de x : 83298061311752912319685844512597155212
  $ java Alea
  Valeur de x : 285871252058034402763697089368152008986
  $ java Alea
  Valeur de x : 197542704085834536850620075483034476692
  $ java Alea
  Valeur de x : 118481379124562405626844429693602702101
  $
*/

