import java.util.stream.IntStream;

public class Kata8 {
    public static void main(String[] args) {
        System.out.println(validateCard("1234567890123456"));

    }

    public static boolean validateCard(String cardNumber) {
        int[] digits = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            digits[i] = cardNumber.charAt(i) - '0';
        }

        int checkDigit = digits[cardNumber.length()-1];
        digits = remove(digits, cardNumber.length()-1);

        digits = reverse(digits);

        digits = doubleOdds(digits);

        int sum = addDigits(digits);

        String[] sumNumbers = Integer.toString(sum).split("");
        int compare = 10-Integer.parseInt(sumNumbers[1]);

        if (compare == checkDigit) {
            if (cardNumber.length() < 14) {
                System.out.println("Passes Luhn test, but too short.");
                return false;
            } else if (cardNumber.length() > 19) {
                System.out.println("Passes Luhn test, but too long.");
                return false;
            }

            return true;
        }

        return false;
    }

    public static int[] remove(int[] digits, int index) {
        if (digits == null || index < 0 || index >= digits.length) {
            return digits;
        }

        return IntStream.range(0, digits.length)
                .filter(i -> i != index)
                .map(i -> digits[i])
                .toArray();
    }

    public static int[] reverse(int[] digits) {
        for(int i = 0; i < digits.length / 2; i++)
        {
            int temp = digits[i];
            digits[i] = digits[digits.length - i - 1];
            digits[digits.length - i - 1] = temp;
        }

        return digits;
    }

    public static int[] doubleOdds(int[] digits) {
        for (int i = 0; i < digits.length; i++) {
            if (i%2 == 0) {
                int newValue = digits[i]*2;
                if (newValue >= 10) {
                    String[] values = Integer.toString(newValue).split("");
                    newValue = Integer.parseInt(values[0]) + Integer.parseInt(values[1]);
                    digits[i] = newValue;
                } else {
                    digits[i] = newValue;
                }
            }
        }

        return digits;
    }

    public static int addDigits(int[] digits) {
        int sum = 0;
        for (int digit : digits) {
            sum += digit;
        }

        return sum;
    }
}
