/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author user
 */
public class InputFromKeyboard {
    
    public static int inputNumberFromRange(Integer min, Integer max) {
        
        Scanner scanner = new Scanner(System.in);
        int number = min - 1;
        boolean isNumber = true;
        boolean repeat;
        do{
            try {
                number = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                isNumber = false;
            }
            if(max == null ){
               if((number >= min) && isNumber){
                repeat = false; 
               }else{
                    System.out.printf("Enter number from next range: %d .. %d: ",min,max);
                    isNumber = true;
                    repeat = true;
                }
            }else{
                if((number >= min && number <= max) && isNumber){
                    repeat = false;
                }else{
                    System.out.printf("Enter number from next range: %d .. %d: ",min,max);
                    isNumber = true;
                    repeat = true;
                }
            }
        }while(repeat);
        return number;
    }

        public static String inputSymbolYesOrNo() {
        Scanner scanner = new Scanner(System.in);
        String symbol = "n";
        do{
            symbol = scanner.nextLine();
            if (symbol.equals("n") || symbol.equals("y")){
                return symbol;
            }
            System.out.println("For continue press \"y\", to exsit press \"n\"");
        }while(true);
    }
        public static boolean askForCustomerUpdate() {
        System.out.println("Do you want to update customers? (y/n)");
        String сhoice = inputSymbolYesOrNo();
        return сhoice.equals("y");
    }
        public static boolean askForProductUpdate() {
        System.out.println("Do you want to update products? (y/n)");
        String сhoice = inputSymbolYesOrNo();
        return сhoice.equals("y");
    }
            public static int inputNumberFromRange(List<Integer> listId){
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        do{
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                if(!listId.contains(number)){
                    continue;
                }
                return number;
            } catch (Exception e) {
                scanner.nextLine();
            }
        }while(true);
        
    }
    
    
}



