import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CNPDecoder {
    private static final String FILE_PATH = "resources/placeOfBirth.txt";
    private static String cnp;
    private static int no1, no2, no3, no4, no5, no6, no7, no8, no9, no10, no11, no12, no13;
    private static Map<Integer, String> mapFromFile = null;

    public static void main(String[] args) {
        do {
            enterCNP();
            if (isCNPValid() == true) {
                displayGender();
                displayDateOfBirth();
                displayPlaceOfBirth();
            } else {
                System.out.println("CNP is invalid! Please try again.");
            }
        } while (isCNPValid() == false);
    }

    public static void convertCNPToInt() {
        char[] cnpToArray = new char[cnp.length()];
        for (int i = 0; i < cnp.length(); i++) {
            cnpToArray[i] = cnp.charAt(i);
        }
        for (char c : cnpToArray) {
            no1 = Integer.parseInt(String.valueOf(cnpToArray[0]));
            no2 = Integer.parseInt(String.valueOf(cnpToArray[1]));
            no3 = Integer.parseInt(String.valueOf(cnpToArray[2]));
            no4 = Integer.parseInt(String.valueOf(cnpToArray[3]));
            no5 = Integer.parseInt(String.valueOf(cnpToArray[4]));
            no6 = Integer.parseInt(String.valueOf(cnpToArray[5]));
            no7 = Integer.parseInt(String.valueOf(cnpToArray[6]));
            no8 = Integer.parseInt(String.valueOf(cnpToArray[7]));
            no9 = Integer.parseInt(String.valueOf(cnpToArray[8]));
            no10 = Integer.parseInt(String.valueOf(cnpToArray[9]));
            no11 = Integer.parseInt(String.valueOf(cnpToArray[10]));
            no12 = Integer.parseInt(String.valueOf(cnpToArray[11]));
            no13 = Integer.parseInt(String.valueOf(cnpToArray[12]));
        }
    }

    public static void enterCNP() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("CNP: ");
            cnp = scanner.nextLine();
            if ((cnp.length() != 13) || (!cnp.matches("\\d+"))) {
                System.out.println("Wrong input, please try again!");
            }
        } while ((cnp.length() != 13) || (!cnp.matches("\\d+")));
    }


    public static boolean isCNPValid() {
        convertCNPToInt();
        int multiplicationWithConstant = (no1 * 2 + no2 * 7 + no3 * 9 + no4 + no5 * 4 + no6 * 6 + no7 * 3 + no8 * 5 + no9 * 8 + no10 * 2 + no11 * 7 + no12 * 9);
        double componentC = multiplicationWithConstant % 11;
        if (componentC == 10.00) {
            componentC = 1;
        }
        if (componentC == no13) {
            // System.out.println("CNP is valid");
            return true;
        } else {
            return false;
        }
    }

    public static void displayGender() {
        convertCNPToInt();
        if (no1 == 1 || no1 == 3 || no1 == 5 || no1 == 7) {
            System.out.println("Gender: Male");
        } else if (no1 == 2 || no1 == 4 || no1 == 6 || no1 == 8) {
            System.out.println("Gender: Female");
        }
    }

    public static void displayDateOfBirth() {
        convertCNPToInt();
        if (no1 == 1 || no1 == 2) {
            System.out.printf("Date of birth: " + "%d%d" + "." + "%d%d" + "." + "19%d%d\n", no6, no7, no4, no5, no2, no3);
        } else if (no1 == 5 || no1 == 6) {
            System.out.printf("Date of birth: " + "%d%d" + "." + "%d%d" + "." + "20%d%d\n", no6, no7, no4, no5, no2, no3);
        } else if (no1 == 3 || no1 == 4) {
            System.out.printf("Date of birth: " + "%d%d" + "." + "%d%d" + "." + "18%d%d\n", no6, no7, no4, no5, no2, no3);
        } else {
            System.out.println("Date of birth: unavailable");
        }
    }

    public static void displayPlaceOfBirth() {
        placeOfBirthRead(FILE_PATH);
        int placeOfBirthCode = Integer.parseInt(cnp.substring(7, 9));
        String placeOfBirth = mapFromFile.get(placeOfBirthCode);
        System.out.println("Place of birth: " + placeOfBirth);

    }

    public static void placeOfBirthRead(String FILE_PATH) {
        mapFromFile = HashMapFromTextFile();
        for (Map.Entry<Integer, String> entry : mapFromFile.entrySet()) {
        }
    }

    public static Map<Integer, String> HashMapFromTextFile() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_PATH));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                Integer code = Integer.valueOf(parts[0].trim());
                String place = parts[1].trim();
                if (!code.equals("") && !place.equals("")) {
                    map.put(code, place);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
                ;
            }
        }
        return map;
    }

}

