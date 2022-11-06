import java.util.Scanner;
import java.util.TreeMap;

public class RomanicArabicCalculus {
    public static void main(String[] args) {
        while (true) {
            Converse converter = new Converse();
            String[] signs = {"+", "-", "/", "*"};
            String[] signsCheck = {"\\+", "-", "/", "\\*"};
            Scanner scn = new Scanner(System.in);
            System.out.print("Enter a expression: ");
            String exp = scn.nextLine();
            int signIndex = -1;
            for (int i = 0; i < signs.length; i++) {
                if (exp.contains(signs[i])) {
                    signIndex = i;
                    break;
                }
            }
            if (signIndex == -1) {
                System.out.println("Incorrect expression!");
                return;
            }
            String[] pillar = exp.split(signsCheck[signIndex]);
            if (converter.romanic(pillar[0]) == converter.romanic(pillar[1])) {
                int a, b;
                boolean romanic = converter.romanic(pillar[0]);
                if (romanic) {
                    a = converter.romanicToInt(pillar[0]);
                    b = converter.romanicToInt(pillar[1]);
                    } else {
                        a = Integer.parseInt(pillar[0]);
                        b = Integer.parseInt(pillar[1]);
                    }
                if (a>10||b>10){
                    System.out.println("Numbers must be less than 10!");
                    break;
                }
                    int result;
                    switch (signs[signIndex]) {
                        case "+":
                            result = a + b;
                            break;
                        case "-":
                            result = a - b;
                            break;
                        case "*":
                            result = a * b;
                            break;
                        default:
                            result = a / b;
                            break;
                    }
                    if (romanic) {
                        if (result==0){
                            System.out.println("N");
                        }else if(result<0){
                            System.out.println("In Roman numerals no negative numbers!");
                        }else{
                        System.out.println(converter.intToRomanic(result));}
                    } else {
                        System.out.println(result);
                    }
                } else {
                    System.out.println("Numbers must be from the same notation!");
                }


            }
        }
    }


class Converse {
        TreeMap<Character, Integer> rTM = new TreeMap<>();
        TreeMap<Integer, String> aTM = new TreeMap<>();

        Converse() {
            rTM.put('I', 1);
            rTM.put('V', 5);
            rTM.put('X', 10);
            rTM.put('L', 50);
            rTM.put('C', 100);



            aTM.put(100, "C");
            aTM.put(90, "XC");
            aTM.put(50, "L");
            aTM.put(40, "XL");
            aTM.put(10, "X");
            aTM.put(9, "IX");
            aTM.put(5, "V");
            aTM.put(4, "IV");
            aTM.put(1, "I");

        }


         boolean romanic(String number) {
            return rTM.containsKey(number.charAt(0));
        }

        //15
        String intToRomanic(int number) {
            String romanic = "";
            int arabicKey;
            do {
                arabicKey = aTM.floorKey(number);
                romanic += aTM.get(arabicKey);
                number -= arabicKey;
            } while (number != 0);
            return romanic;


        }

        int romanicToInt(String str) {
            int end = str.length() - 1;
            char[] arr = str.toCharArray();
            int arabic;
            int result = rTM.get(arr[end]);
            for (int i = end - 1; i >= 0; i--) {
                arabic = rTM.get(arr[i]);

                if (arabic < rTM.get(arr[i + 1])) {
                    result -= arabic;
                } else {
                    result += arabic;
                }


            }
            return result;

        }
    }
