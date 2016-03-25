package my_java;

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by fraps on 13.03.2016.
 */
public class uncipher {
    public static void main(String[] args) {
        char[] alphabetRus = new char[32];
        int max_sovpodenii = 0;

        for (int i = 0; i < alphabetRus.length; i++) {
            alphabetRus[i] = (char) ((int) 'а' + i);
            System.out.print(alphabetRus[i]);                      //заполнение массива - алфавит
        }

        char[] CP_mass;                                          //массив символов нашего ШТ
        String CP_string;                                           //строка вводимого ШТ
        System.out.println();
        Scanner sc = new Scanner(System.in);
        CP_string = sc.nextLine();
        CP_mass = new char[CP_string.length()];

        for (int i = 0; i < CP_mass.length; i++) {
            CP_mass[i] = CP_string.charAt(i);                              //перевод строки в массив
        }

        for(int r = 6; r < 26; r++){
            int D = 0;
            for(int i = 0; i < CP_mass.length - r - 1; i++)
                if(CP_mass[i] == CP_mass[i + r])
                    D++;
            System.out.println("r=" + r + " " + D);                               //находим длину ключа
            max_sovpodenii = Math.max(D, max_sovpodenii);
        }
        System.out.println( "MAX: " + max_sovpodenii);

        int r;
        r = sc.nextInt();
        char[] CT_Key_letter = new char[r];
        int[] letter_freq = new int[alphabetRus.length];

        for(int i = 0; i < r; i++){
            int max_temp_frq_letter = 0;
            for (int k = 0; k < alphabetRus.length; k++)
                    letter_freq[k] = 0;                                               //чистим массив для следующего блока текста

            for(int j = i; j < CP_mass.length - r - 1; j += r)
                for (int k = 0; k < alphabetRus.length; k++)
                    if (CP_mass[j] == alphabetRus[k])                                   //считаем кол-во букв блока
                        letter_freq[k]++;

            for(int j = 0; j < letter_freq.length; j++) {
                int temp_max = max_temp_frq_letter;
                max_temp_frq_letter = Math.max(max_temp_frq_letter, letter_freq[j]);               //определяем букву что чаще всего повтор и ставим ее на соотв место в нашем шифрованом ключе
                if(max_temp_frq_letter != temp_max)
                    CT_Key_letter[i] = alphabetRus[j];
            }
        }

        for(int i = 0; i < r; i++)
            System.out.print(CT_Key_letter[i]);

        int[] index_CT = new int[r];
        for(int i = 0; i < index_CT.length; i++)
            for(int j = 0; j < alphabetRus.length; j++)
                if(CT_Key_letter[i] == alphabetRus[j])                                  ////////////////////////////
                    index_CT[i] = j;

        char[] OT_Key_letter = new char[r];
        int[] index_OT = new int[r];

            for (int i = 0; i < r; i++) {
                index_OT[i] = (index_CT[i] - 14) - (((index_CT[i] - 14) / 32) * 32);
                if (index_OT[i] < 0)
                    index_OT[i] = 32 + index_OT[i];
            }

            for (int i = 0; i < index_OT.length; i++)
                for (int j = 0; j < alphabetRus.length; j++)
                    if (index_OT[i] == j)
                        OT_Key_letter[i] = alphabetRus[j];

        System.out.println();
            for (int i = 0; i < OT_Key_letter.length; i++)
                System.out.print(OT_Key_letter[i]);
    }
}
