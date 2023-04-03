package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static long number_checker(String[] digit) throws NumberFormatException {
        if (digit.length == 1) {
            try {
                long value = Long.parseLong(digit[0]);
                if (value < 0) {
                    return -1;
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public static void error(int choice) {
        if (choice == 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            System.out.println("Enter a request: ");
        } else {
            System.out.println("The second parameter should be a natural number.");
            System.out.println("Enter a request: ");
        }
    }

    public static void exit_program() {
        System.out.println("Goodbye!");
        exit(0);
    }

    public static void number_generator(amazing_number number, int choice) {
        number.setParity();
        number.setDuck();
        number.setBuzz();
        number.setPalindromic();
        number.setGapful();
        number.setSpy();

        if (choice == 1) {
            System.out.printf("Properties of %d\n", number.digit);
            System.out.printf("even: %b\n", number.getEven());
            System.out.printf("odd: %b\n", number.getOdd());
            System.out.printf("duck: %b\n", number.getDuck());
            System.out.printf("buzz: %b\n", number.getBuzz());
            System.out.printf("palindromic: %b\n", number.getPalindromic());
            System.out.printf("gapful: %b\n", number.getGapful());
            System.out.printf("spy: %b\n", number.getSpy());
        }
    }

    public static void one_digit_routine(long success) {
        if (success == 0) {
            exit_program();
        }
        if (success > 0) {
            amazing_number number = new amazing_number(success);
            number_generator(number, 1);
            System.out.println("Enter a request: ");
        } else {
            error(0);
        }
    }

    public static void print_attributes(List<String> attributes, long number) {
        String attr = attributes.toString();
        System.out.printf("%d is %s\n ", number, attr);
    }

    public static void two_param_routine(String[] digit) {
        List<String> attributes = new ArrayList<String>();
        long param1 = Long.valueOf(digit[0]);
        long param2 = Long.valueOf(digit[1]);

        if (param1 < 0) {
            error(0);
        } else if (param2 < 0) {
            error(1);
        }
        for (int i = 0; i < param2; i++) {
            amazing_number number2 = new amazing_number(param1);
            number_generator(number2, 0);
            attributes = number2.getAttributes();
            print_attributes(attributes, number2.digit);
            param1++;
        }
        System.out.println("Enter a request: ");
    }

    public static void three_param_routine(String[] digit) {
        List<String> attributes = new ArrayList<String>();
        attributes.add("EVEN");
        attributes.add("ODD");
        attributes.add("BUZZ");
        attributes.add("DUCK");
        attributes.add("PALINDROMIC");
        attributes.add("GAPFUL");
        attributes.add("SPY");

        long param1 = Long.valueOf(digit[0]);
        long param2 = Long.valueOf(digit[1]);
        long matches = 0;
        long counter = 0;

        String param3 = digit[2].toLowerCase();

        if (!attributes.contains(param3.toUpperCase())) {
            System.out.printf("The property [%s] is wrong.\n", param3.toUpperCase());
            System.out.printf("Available properties: %s\n", attributes);
            System.out.println("Enter a request: ");
        } else {
            while (matches < param2) {
                amazing_number temp_number = new amazing_number(param1 + counter);
                number_generator(temp_number, 0);
                if (temp_number.getAttributes().contains(param3)) {
                    attributes = temp_number.getAttributes();
                    print_attributes(attributes, temp_number.digit);
                    matches++;
                }
                counter++;
            }
            System.out.println("Enter a request: ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n");

        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a request: ");

        while (scanner.hasNext()) {
            String[] digit = scanner.nextLine().split(" ");
            long success = number_checker(digit);

            if (success == -1) {
                error(0);
                continue;
            }
            if (digit.length == 1) {
                one_digit_routine(success);
            } else {
                if (digit.length == 2) {
                    two_param_routine(digit);
                } else {
                    three_param_routine(digit);
                }

            }
        }
    }
}
