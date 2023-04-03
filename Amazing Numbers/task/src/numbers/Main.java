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
        number.setSquare();
        number.setSunny();
        number.setJumping();

        if (choice == 1) {
            System.out.printf("Properties of %d\n", number.digit);
            System.out.printf("even: %b\n", number.getEven());
            System.out.printf("odd: %b\n", number.getOdd());
            System.out.printf("duck: %b\n", number.getDuck());
            System.out.printf("buzz: %b\n", number.getBuzz());
            System.out.printf("palindromic: %b\n", number.getPalindromic());
            System.out.printf("gapful: %b\n", number.getGapful());
            System.out.printf("spy: %b\n", number.getSpy());
            System.out.printf("sunny: %b\n", number.getSunny());
            System.out.printf("square: %b\n", number.getSquare());
            System.out.printf("jumping: %b\n", number.getJumping());
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

    public static List<String> attribute_adder(List<String> attributes) {
        attributes.add("EVEN");
        attributes.add("ODD");
        attributes.add("BUZZ");
        attributes.add("DUCK");
        attributes.add("PALINDROMIC");
        attributes.add("GAPFUL");
        attributes.add("SPY");
        attributes.add("SUNNY");
        attributes.add("SQUARE");
        attributes.add("JUMPING");
        return attributes;
    }

    public static void three_param_routine(String[] digit) {
        List<String> attributes = new ArrayList<String>();
        attribute_adder(attributes);

        long param1 = Long.valueOf(digit[0]);
        long param2 = Long.valueOf(digit[1]);

        String param3 = digit[2].toLowerCase();

        if (!attributes.contains(param3.toUpperCase())) {
            System.out.printf("The property [%s] is wrong.\n", param3.toUpperCase());
            System.out.printf("Available properties: %s\n", attributes);
            System.out.println("Enter a request: ");
        } else {
            match_finder(param1, param2, param3, attributes);
            System.out.println("Enter a request: ");
        }
    }

    public static void match_finder(long param1, long param2, String param3, List<String> attributes) {
        long matches = 0;
        long counter = 0;

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
    }

    public static void match_finder(long param1, long param2, List<String> params, List<String> attributes) {
        long total_matches = 0;
        long increment_digit = 0;
        long hits = 0;
        int index_of_params = 0;

        while (total_matches < param2) {
            amazing_number temp_number = new amazing_number(param1 + increment_digit);
            number_generator(temp_number, 0);
            hits = 0;
            index_of_params = 0;
            while (index_of_params < params.size()) {
                if (temp_number.getAttributes().contains(params.get(index_of_params))) {
                    hits++;
                }
                index_of_params++;
            }
            if (hits == params.size()) {
                attributes = temp_number.getAttributes();
                print_attributes(attributes, temp_number.digit);
                total_matches++;
            }
            increment_digit++;
        }
    }

    public static List<String> contradiction_checker(List<String> contradictions, List<String> params, List<String> attributes) {
        for (String param : params) {
            if (param.equals("odd")) {
                if (params.contains("even")) {
                    contradictions.add(param);
                    contradictions.add("even");
                }
            }
            if (param.equals("sunny")) {
                if (params.contains("square")) {
                    contradictions.add(param);
                    contradictions.add("square");
                }
            }
            if (param.equals("spy")) {
                if (params.contains("duck")) {
                    contradictions.add(param);
                    contradictions.add("duck");
                }
            }
        }
        return contradictions;
    }

    public static void four_param_routine(String[] digit) {
        List<String> attributes = new ArrayList<String>();
        List<String> params = new ArrayList<>();
        List<String> error = new ArrayList<String>();
        List<String> opposites = new ArrayList<String>();
        List<String> contradictions = new ArrayList<String>();

        opposites.add("[odd, even]");
        opposites.add("[even, odd]");
        opposites.add("[sunny, square]");
        opposites.add("[square, sunny]");
        opposites.add("[spy, duck]");
        opposites.add("[duck, spy]");

        attribute_adder(attributes);

        long param1 = Long.valueOf(digit[0]);
        long param2 = Long.valueOf(digit[1]);

        for (int i = 2; i < digit.length; i++) {
            params.add(digit[i].toLowerCase().trim());
        }

        for (String param : params) {
            if (!attributes.contains(param.toUpperCase())) {
                error.add(param);
            }
        }

        if (error.size() > 0) {
            if (error.size() == 1) {
                System.out.printf("The property %s is wrong.\n", error.toString());
            } else {
                System.out.printf("The properties %s are wrong.\n", error.toString());
            }
            System.out.printf("Available properties: %s\n", attributes);
            System.out.println("Enter a request: ");
        } else {
            contradictions = contradiction_checker(contradictions, params, attributes);
            if (contradictions.size() > 0) {
                System.out.printf("The request contains mutually exclusive properties: %s\n" +
                        "There are no numbers with these properties.\n", contradictions.toString());
                System.out.println("Enter a request: ");
            } else {
                match_finder(param1, param2, params, attributes);
                System.out.println("Enter a request: ");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n");

        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
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
            switch (digit.length) {
                case 1:
                    one_digit_routine(success);
                    break;
                case 2:
                    two_param_routine(digit);
                    break;
                case 3:
                    three_param_routine(digit);
                    break;
                default:
                    four_param_routine(digit);
                    break;
            }
        }
    }
}
