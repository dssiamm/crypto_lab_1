package com.example.fraps.crypto_lab1;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by fraps on 29.02.2016.
 */
public class cipher {
    public static void main(String[] args) {
        String alphabetRus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        int[] alph_mass = new int[alphabetRus.length()];
        for(int i = 0; i < alph_mass.length; i++)
            alph_mass[i] = i;

        String PT;
        Scanner in = new Scanner(System.in);
        PT = in.nextLine();
        char[] CT = new char[PT.length()];
        int[] PT_mass = new int[PT.length()];
        int[] CT_mass = new int[PT.length()];
        int k = 6;
        String[] K = {"вг", "абв", "конь", "ветвь", "отчегосама", "никогдатеперь", "тебевсёпопорядку", "духомнемымиглухим", "досихпороживлением", "вдругрезковспомнилон"};
        int[] K_mass = new int[K[k].length()];

        for(int i = 0; i < K[k].length(); i++)
            for(int j = 0; j < alphabetRus.length(); j++)
                if(K[k].charAt(i) == alphabetRus.charAt(j)) {
                    K_mass[i] = j;
                    break;
                }

        for(int i = 0; i < PT.length(); i++)
            for(int j = 0; j < alphabetRus.length(); j++)
                if(PT.charAt(i) == alphabetRus.charAt(j)) {
                    PT_mass[i] = j;
                    break;
                }

        System.out.println();
        for(int j = 0; j < PT.length(); j++)
        System.out.print(PT_mass[j] + " ");

        System.out.println();
        for(int j = 0; j < K_mass.length; j++)
            System.out.print(K_mass[j] + " ");

        int l ;
        for(int i = 0; i < PT.length(); i++) {
            int x, y, m ,n;
            x = i / K_mass.length;
            y = x * K_mass.length;
            l = i - y;
            m = (PT_mass[i] + K_mass[l]) / alphabetRus.length();
            n = m * alphabetRus.length();
            CT_mass[i] = PT_mass[i] + K_mass[l] - n;
        }

        System.out.println();
        for(int j = 0; j < PT.length(); j++)
            System.out.print(CT_mass[j] + " ");

        for(int i = 0; i < CT_mass.length; i++)
            for(int j = 0; j < alphabetRus.length(); j++)
                if(CT_mass[i] == alph_mass[j]){
                    CT[i] = alphabetRus.charAt(j);
                    break;
                }

        System.out.println();
        for(int i = 0; i < CT.length; i++)
            System.out.print(CT[i]);


    }
}
