package main;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Anagram {

    public static void main(String[] args) throws Exception{

        Anagram a = new Anagram();

        List<String> lista = new ArrayList<String>();
        List<String> listaAnagramow = new ArrayList<String>();

        lista = a.wczytajPlik();

        /***
         *
         */

        if (lista.size() == 0 ) {
            System.out.println("Nie wczytano żądanych słów!");
        } else {
            System.out.println(String.format("Wczytano %,d unikalnych słów.", lista.size()));
            listaAnagramow = a.znajdzAnagramy(lista);
            System.out.println(String.format("Znaleziono %,d anagramów.", listaAnagramow.size()));

            for (String s : listaAnagramow) {
                System.out.println(s);
            }
        }
    }

    public List<String> wczytajPlik() throws Exception{
        List<String> listaSlow = new ArrayList<String>();
        Set<String> slowaSet = new HashSet<String>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("anagram.txt"));
        try{
            String linia;
            while ((linia = bufferedReader.readLine()) !=null) {
                if (!linia.equals("")) {
                    slowaSet.add(linia);
                }
            }
            bufferedReader.close();
            listaSlow.addAll(slowaSet);
        }catch (Exception e) {
            System.out.println("Błąd odczytu linii");
        }
        return listaSlow;
    }



    public List<String> znajdzAnagramy (List<String> listaSlow) {
        Date dStart = new Date();

        String s = "Wyszukiwanie anagramy";

        long totalComp = 0;

        List<String> list = new ArrayList<String>();

        System.out.print(s);

        for(int x = 0; x < listaSlow.size(); x++) {
            for(int y = x +1; y < listaSlow.size(); x++) {
                String str1 = listaSlow.get(x).toString();
                String str2 = listaSlow.get(y).toString();

                totalComp++;

                if (porownajSlowa(str2, str2)) {
                    if ((x % 20) == 0) {
                        System.out.print(".");
                    }
                    list.add(str1 + " - " + str2);
                }
            }
        }
        System.out.println(String.format("\n\nLiczba wykonanych porównań %,d", totalComp));
        Date dEnd = new Date();
        long czas = (dEnd.getTime() - dStart.getTime()) / 1000;
        System.out.println(String.format("Porównanie trwało %,d sekund", czas));

        return list;
    }

    public boolean porownajSlowa (String str1, String str2) {
        String pStr1 = str1.toLowerCase();
        String pStr2 = str2.toLowerCase();

        char[] c1 = pStr1.toCharArray();
        char[] c2 = pStr2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        String sorted1 = new String(c1);
        String sorted2 = new String(c2);

        return sorted1.equals(sorted2);

    }


}
