package sg.edu.nus.iss.app.server;

public class Calculator {
    
    public static int[] getCalculator(String data, String data2){
        
      
        // String[] numbers = data.split(" ");
        // String str = String.join("", numbers);
        // return str;
        String[] numbers = data.split(" ");
        String[] numbers2 = data2.split(" ");
        int[] arrayNumbers = new int[5];
        int[] arrayNumbers2 = new int[5];
        int[] arrayNumbersAdd = new int[5];
        for (int i = 0; i < numbers.length; i++ ) {
            arrayNumbers[i] = Integer.parseInt(numbers[i]);
            arrayNumbers2[i] = Integer.parseInt(numbers2[i]);
            arrayNumbersAdd[i] = arrayNumbers[i]+arrayNumbers2[i];
        }
       return arrayNumbersAdd;
    }
}
