package my_java;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        String alphabetRus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String alphabetBigRus = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        double[] temp_arr = new double[33];
        int length = 0;
        double H1 = 0;
        double H2 = 0;

        try (FileReader fr = new FileReader("D:\\Android(projects)\\Crypto_lab1\\app\\src\\main\\java\\com\\example\\fraps\\crypto_lab1\\sometxt.txt")) {
            int c;
            while ((c = fr.read()) != -1)
                for (int i = 0; i < alphabetRus.length(); i++) {
                    if ((char) c == alphabetBigRus.charAt(i) || (char) c == alphabetRus.charAt(i)) {
                        temp_arr[i]++;
                        length++;
                    }
                }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }

        for (int i = 0; i < temp_arr.length; i++) {
            System.out.print(" " + alphabetRus.charAt(i) + ": ");
            System.out.print(String.format("%.8f", (double) temp_arr[i]/length));
            if ((i + 1) % 4 == 0)
                System.out.println();
        }

        for(int i = 0; i < alphabetRus.length(); i++)
            H1 += (double) temp_arr[i]/length * (Math.log((double) temp_arr[i] / length)) / Math.log(2);

        System.out.println();
        System.out.println(-1 * H1);

        String[][] bigramArr = new String[alphabetRus.length()][alphabetRus.length()];
        int[][] IntBigramArr = new int[alphabetRus.length()][alphabetRus.length()];
        String itemp, jtemp;
        for(int i = 0; i < alphabetRus.length(); i++) {
            for (int j = 0; j < alphabetRus.length(); j++) {
                itemp = alphabetRus.substring(i, i+1);
                jtemp = alphabetRus.substring(j, j+1);
                bigramArr[i][j] = itemp + jtemp;
                System.out.print(bigramArr[i][j] + " ");
            }
            System.out.println();
        }

        try (FileReader fr = new FileReader("D:\\Android(projects)\\Crypto_lab1\\app\\src\\main\\java\\com\\example\\fraps\\crypto_lab1\\sometxt.txt")) {
            int c;
            int first = -1;
            int second;
            long timer = -System.currentTimeMillis();
            while ((c = fr.read()) != -1)
                for (int i = 0; i < alphabetRus.length(); i++)
                    if ((char) c == alphabetBigRus.charAt(i) || (char) c == alphabetRus.charAt(i)) {
                        if (first == -1) {
                            first = i;
                            break;
                        } else {
                            second = i;
                            IntBigramArr[first][second]++;
                            first = second;                                //пересекающиеся
                            //first = -1;                                          //непересикающиеся
                            break;
                        }
                    }
                    else
                        if (first != -1 && i == 32)       // со знаками припинания пробелами и тд...
                            first = -1;

            long timer1 = -System.currentTimeMillis();
            System.out.println("C.m() " + (double) (timer - timer1)/1000);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }

        int summ = 0;
        for(int i = 0; i < alphabetRus.length(); i++)
            for (int j = 0; j < alphabetRus.length(); j++) {
                summ += IntBigramArr[i][j];
            }

        for(int i = 0; i < alphabetRus.length(); i++) {
            for (int j = 0; j < alphabetRus.length(); j++) {
                itemp = alphabetRus.substring(i, i+1);
                jtemp = alphabetRus.substring(j, j+1);
                bigramArr[i][j] = itemp + jtemp;
                System.out.print(bigramArr[i][j] + "->" + String.format("%.8f", (double) IntBigramArr[i][j] /summ) + "  ");
            }
            System.out.println();
            System.out.println();
        }

        for(int i = 0; i < alphabetRus.length(); i++)
            for (int j = 0; j < alphabetRus.length(); j++) {
                if(IntBigramArr[i][j] != 0)
                H2 += (double) IntBigramArr[i][j]/summ * (Math.log((double) IntBigramArr[i][j] / summ) / Math.log(2));
            }
        System.out.println(String.format("%.8f", (-0.5)*H2));


    }
}
