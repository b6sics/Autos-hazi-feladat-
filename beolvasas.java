package kissjgabor20190319;

// importáljuk a fájlműveletekhez szükséges osztályt
import java.io.*;

public class KissJGabor20190319 {

      // a fájlműveletekhez deklarálunk egy RandomAccessFile osztályú 'f' nevű változót
    private static RandomAccessFile f;
    
      //Az autó tankolás adatait egy CarRefueling tipusú elemeket tartalmazó 
      //ArrayList tipusú tömbbe fogjuk beolvasni aminek a neve carRefuelings
    private static ArrayList<CarRefueling> carRefuelings = new ArrayList<>();

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
      // --- fő program
    public static void main(String[] args) {
    
        //Az f1 metódus helyett itt is közvetlenül meghívhatjuk a beolvasó rutint
        inputFile("sources/autoadatok.txt", "r");
    }
    // --- fő program vége
}
