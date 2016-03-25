package com.example.fraps.crypto_lab1;

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by fraps on 13.03.2016.
 */
public class uncipher {
    public static void main(String[] args) {
        char[] alphabetRus = new char[32];

        for (int i = 0; i < alphabetRus.length; i++) {
            alphabetRus[i] = (char) ((int) 'а' + i);
            System.out.print(alphabetRus[i]);                      //заполнение массива - алфавит
        }

        int[] int_letter_freq = new int[alphabetRus.length];

        char[] CP_mass;                                          //массив символов нашего ШТ
        String CP_string;                                           //строка вводимого ШТ
        System.out.println();
        Scanner sc = new Scanner(System.in);
        CP_string = sc.nextLine();
        CP_mass = new char[CP_string.length()];

        for (int i = 0; i < CP_mass.length; i++) {
            if (CP_string.charAt(i) == ' ')
                continue;                                   //перевод введенной строки в масив символов
            CP_mass[i] = CP_string.charAt(i);
            System.out.print(CP_mass[i]);
        }

        for (int i = 0; i < CP_mass.length; i++)
            for (int j = 0; j < alphabetRus.length; j++)
                if (CP_mass[i] == alphabetRus[j])                   //подсчет кол-ва появления буквы
                    int_letter_freq[j]++;

        System.out.println();

        for (int j = 0; j < alphabetRus.length; j++) {                        //буква - кол-во появлений в ШТ
            System.out.print(alphabetRus[j] + " " + int_letter_freq[j] + " ");
            if ((j + 1) % 4 == 0)
                System.out.println();
        }

        String[][] bigramArr = new String[alphabetRus.length][alphabetRus.length];
        int[][] IntBigramArr = new int[alphabetRus.length][alphabetRus.length];
        String itemp, jtemp;

        for (int i = 0; i < alphabetRus.length; i++) {
            for (int j = 0; j < alphabetRus.length; j++) {
                itemp = "" + alphabetRus[i];
                jtemp = "" + alphabetRus[j];
                bigramArr[i][j] = itemp + jtemp;
            }
        }

        int first = -1;
        int second = -1;

        for (int i = 0; i < CP_mass.length; i++)
            for (int j = 0; j < alphabetRus.length; j++)
                if (CP_mass[i] == alphabetRus[j]) {
                    if (first == -1)
                        first = j;
                    else {
                        second = j;
                        IntBigramArr[first][second]++;
                        first = second;
                    }
                }

        for (int i = 0; i < alphabetRus.length; i++) {
            for (int j = 0; j < alphabetRus.length; j++) {
                System.out.print(bigramArr[i][j] + " " + IntBigramArr[i][j] + " ");
            }
            System.out.println();
        }

        int max = 0;
        for (int i = 0; i < alphabetRus.length; i++)
            for (int j = 0; j < alphabetRus.length; j++)
                max = Math.max(max, IntBigramArr[i][j]);

        int[] length_between_bigrams = new int[max - 1];

        boolean tocount = false;
        int k = 0;
        int temp = 0;

        for (int i = 0; i < CP_mass.length; i++) {
            for (int j = 0; j < alphabetRus.length; j++)
                if (CP_mass[i] == alphabetRus[j]) {
                    if (first == -1)
                        first = j;
                    else {
                        second = j;
                        if (max == IntBigramArr[first][second]) {
                            if (tocount) {
                                length_between_bigrams[k] = i - temp - 2;
                                temp = i;
                                k++;
                            } else
                                tocount = true;
                        }
                        first = second;
                    }
                }
        }

        length_between_bigrams[0] -= 2;
        System.out.println();

        for (int i = 0; i < length_between_bigrams.length; i++)
            System.out.print(length_between_bigrams[i] + " ");



    }
}
