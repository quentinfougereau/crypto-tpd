package RSA;// -*- coding: utf-8 -*-

import ALEA.Alea;

import java.math.BigInteger;
import java.util.Random;

public class RSA_raw {
    private static BigInteger code, codeChiffré, codeDéchiffré ;
    private static BigInteger n ;      // Le module de la clef publique
    private static BigInteger e ;      // L'exposant de la clef publique
    private static BigInteger d ;      // L'exposant de la clef privée
    private static BigInteger p ;
    private static BigInteger q ;
    private static BigInteger w ;
    
    static void fabrique() {           // Fabrique d'une paire de clefs RSA (A MODIFIER)
        n = new BigInteger("196520034100071057065009920573", 10);
        e = new BigInteger("7", 10);
        d = new BigInteger("56148581171448620129544540223", 10);
        p = Alea.getProbablyPrimeNumber(1024);
        q = Alea.getProbablyPrimeNumber(1024);
        n = p.multiply(q);
        w = p.subtract(BigInteger.valueOf(1));
        w = w.multiply(q.subtract(BigInteger.valueOf(1)));
        System.out.println("n = " + n);
        System.out.println("w = " + w);

        Random alea = new Random();



        /*
        BigInteger d = new BigInteger(w.subtract(BigInteger.valueOf(1)).bitLength(), alea);
        if (d.equals(BigInteger.valueOf(0))) {
            d.add(BigInteger.valueOf(1));
        }
        d.gcd(w);
        */


    }

    public static void main(String[] args) {  
        code = new BigInteger("4b594f544f", 16);

        /* Affichage du code clair */
        System.out.println("Code clair        : " + code);
    
        fabrique(); 

        /* Affichage des clefs utilisées */
        System.out.println("Clef publique (n) : " + n);
        System.out.println("Clef publique (e) : " + e);
        System.out.println("Clef privée (d)   : " + d);

        /* On effectue d'abord le chiffrement RSA du code clair avec la clef publique */
        codeChiffré = code.modPow(e, n);
        System.out.println("Code chiffré      : " + codeChiffré);

        /* On déchiffre ensuite avec la clef privée */
        codeDéchiffré = codeChiffré.modPow(d, n);
        System.out.println("Code déchiffré    : " + codeDéchiffré);
    }
}
