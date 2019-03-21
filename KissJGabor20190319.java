package kissjgabor20190319;

import java.io.*;
import java.util.*;

/**
 *
 * @author KjG
 */
public class KissJGabor20190319 {

    private static RandomAccessFile f;
    private static ArrayList<CarRefueling> carRefuelings = new ArrayList<>();
    private static double fullfuel;
    private static int na = 0;

    private static void f00(int hossz, char jel, String name) {
        int start = 0;
        if (name.length() != 0) {
            start = name.length() + 5;
            System.out.print(jel + "| ");
            System.out.print(name + " |");
        }
        for (int ii = start; ii < hossz; ii++) {
            System.out.print(jel);
        }
        System.out.println("");
    }

    // --- textdekoráció metódus vége
    // --- fájl metódusok
    private static void inputFile(String fileName, String mode) {
        try {
            f = new RandomAccessFile(fileName, mode);
            String sor;
            while ((sor = f.readLine()) != null) {
                carRefuelings.add(new CarRefueling(sor));
            }
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("\t" + fileName + "... beolvasva!");
    }

    private static void appendFile(String fileName, String mode, String str) {
        try {
            f = new RandomAccessFile(fileName, mode);
            f.seek(f.length());     //a fájlmutatót a fájl végére mozgatja
            f.writeBytes(str + "\n");
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    // --- fájl metódusok vége
    // --- a feladat metódusai
    private static void f1(String label, String fileName, String mode) {
        f00(60, '-', label);
        inputFile(fileName, mode);
        System.out.println("");
    }

    private static void f2(String label, String before, String behind) {
        f00(60, '-', label);
        double max = carRefuelings.get(0).getRange();
        for (CarRefueling carRefueling : carRefuelings) {
            if (carRefueling.getRange() > max) {
                max = carRefueling.getRange();
            }
        }
        String str = before;
        str += String.format("%.1f", max);
        str += behind;
        System.out.println(str + "\n");
    }

    private static void f3(String label, String before, String behind) {
        f00(60, '-', label);
        for (CarRefueling carRefueling : carRefuelings) {
            fullfuel += carRefueling.getQuantity();
        }
        String str = before;
        str += String.format("%.2f", fullfuel);
        str += behind;
        System.out.println(str + "\n");
    }

    private static void f4a(String label, String before, String behind) {
        f00(60, '-', label);
        double fullrange = 0;
        for (CarRefueling carRefueling : carRefuelings) {
            fullrange += carRefueling.getRange();
        }
        String str = before;
        str += String.format("%.2f", 100 * fullfuel / fullrange);
        str += behind;
        System.out.println(str + "\n");
    }

    private static void f4b(String label, String before, String behind) {
        f00(60, '-', label);
        double sum = 0;
        for (CarRefueling carRefueling : carRefuelings) {
            sum += carRefueling.getFuelAt100();
        }
        String str = before;
        str += String.format("%.2f", sum / carRefuelings.size());
        str += behind;
        System.out.println(str + "\n");
    }

    private static void f5a(String label, String before, String behind) {
        f00(60, '-', label);
        for (CarRefueling carRefueling : carRefuelings) {
            if (carRefueling.getId().equals("NA")) {
                na++;
            }
        }
        String str = before;
        str += na;
        str += behind;
        System.out.println(str + "\n");
    }

    private static void f5b(String label, String before, String behind) {
        f00(60, '-', label);
        int ntype = 0;
        for (CarRefueling carRefueling : carRefuelings) {
            if (carRefueling.getId().substring(0, 1).equals("N")) {
                ntype++;
            }
        }
        String str = before;
        str += ntype - na;
        str += behind;
        System.out.println(str + "\n");
    }

    private static String toStringYearFuel(String year, double yearFuel) {
        String str = String.format("%6s", year);
        str += String.format("%10.1f", yearFuel);
        return str;
    }

    private static void f6(String label, String fileName, String mode, String behind) {
        f00(60, '-', label);
        String lastYear = carRefuelings.get(0).getYear();
        String str;
        double yearFuel = 0;
        for (CarRefueling carRefueling : carRefuelings) {
            if (carRefueling.getYear().equals(lastYear)) {
                yearFuel += carRefueling.getQuantity();;
            } else {
                str = toStringYearFuel(carRefueling.getYear(), yearFuel) + behind;
                System.out.println(str);
                appendFile(fileName, mode, str);
                lastYear = carRefueling.getYear();
                yearFuel = carRefueling.getQuantity();
            }
        }
        str = toStringYearFuel(lastYear, yearFuel) + behind;
        System.out.println(str);
        appendFile(fileName, mode, str);
        System.out.println("");
    }
    // --- a feladat metódusai vége
    // --- fő program

    public static void main(String[] args) {
        f00(60, '*', "STARTofJAVA 2019mar19 HF");
        f00(60, '-', "");

        f1("1. ", "sources/autoadatok.txt", "r");
        f2("2. ", "\tA leghosszabb út: ", " km volt.");
        f3("3. ", "\tÖsszesen ", " liter benzint tankolt.");
        f4a("4.a", "\tÁtlagfogyasztás: ", " liter/100km.");
        f4b("4.b", "\tÁtlagfogyasztás: ", " liter/100km.");
        f5a("5.a", "\tNem jegyezte fel a benzinkút nevét:", " alkalommal.");
        f5b("5.b", "\tN-típusú benzinkútnál ", " alkalommal tankolt.");
        f6("6. ", "target/evenkent.txt", "rw", " l");

        f00(60, '-', "");
        f00(60, '*', "ENDofJAVA   2019mar19");

    }
    // --- fő program vége
}
