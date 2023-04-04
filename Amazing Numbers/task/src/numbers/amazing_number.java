package numbers;

import java.util.ArrayList;
import java.util.List;

public class amazing_number {
    Boolean even;
    Boolean odd;
    Boolean buzz;
    Boolean duck;
    Boolean palindromic;
    Boolean gapful;
    Boolean spy;
    Boolean sunny;
    Boolean square;
    Boolean jumping;
    Boolean happy;
    boolean sad;
    long digit;

    public amazing_number(long digit) {
        this.buzz = false;
        this.duck = false;
        this.even = false;
        this.odd = false;
        this.digit = digit;
        this.palindromic = false;
        this.gapful = false;
        this.spy = false;
        this.sunny = false;
        this.square = false;
        this.jumping = false;
        this.sad = true;
        this.happy = false;
    }

    public boolean getSad() {
        return sad;
    }

    public Boolean getHappy() {
        return happy;
    }

    public void setHappy() {
        int exit_condition = 0;
        long product = 0;
        long digit_copy = this.digit;
        String digit_copy_string = Long.toString(digit_copy);
        int copy_of_value = 0;

        while (exit_condition == 0) {
            for (int i = 0; i < digit_copy_string.length(); i++) {
                long temp = Integer.parseInt(String.valueOf(digit_copy_string.charAt(i)));
                product += Math.pow(temp, 2);
                if (product == Math.pow(this.digit, 2)) {
                    copy_of_value++;
                }
            }

            if (product == 1) {
                this.happy = true;
                this.sad = false;
                exit_condition = 1;
                break;
            } else if (product == digit_copy || copy_of_value == 2 || product == 145) {
                exit_condition = 1;
                break;
            }
            digit_copy_string = String.valueOf(product);
            product = 0;
        }
    }

    public Boolean getJumping() {
        return jumping;
    }

    public void setJumping() {
        String digit_string = Long.toString(this.digit);
        int counter = 0;
        int length = digit_string.length();

        for (int i = 0; i < length - 1; i++) {
            int calculation = (int) Math.abs(Long.valueOf(digit_string.charAt(i)) - Long.valueOf(digit_string.charAt(i + 1)));
            if (calculation == 1) {
                counter += 1;
            }
        }
        if (counter == length - 1 || length == 1) {
            this.jumping = true;
        }
    }

    public Boolean getSunny() {
        return sunny;
    }

    public void setSunny() {
        long squared = (long) Math.sqrt(this.digit + 1);
        if ((long) ((Math.floor(squared) * Math.floor(squared))) == this.digit + 1) {
            this.sunny = true;
        }
    }

    public Boolean getSquare() {
        return square;
    }

    public void setSquare() {
        long squared = (long) Math.sqrt(this.digit);
        if ((long) ((Math.floor(squared) * Math.floor(squared))) == this.digit || squared == digit) {
            this.square = true;
        }
    }

    public long getDigit() {
        return digit;
    }

    public Boolean getSpy() {
        return spy;
    }

    public void setSpy() {
        String composition = Long.toString(this.digit);
        long sum = 0;
        long product = 1;
        for (int i = 0; i < composition.length(); i++) {
            int number = Integer.parseInt(String.valueOf(composition.charAt(i)));
            sum += number;
            product *= number;
        }
        if (sum == product) {
            this.spy = true;
        }
    }

    public Boolean getPalindromic() {
        return palindromic;
    }

    public void setPalindromic() {
        char[] digit_string = Long.toString(this.digit).toCharArray();
        if (digit_string.length == 1) {
            this.palindromic = true;
        } else {
            String digit_stringified = new StringBuilder(Long.toString(this.digit)).reverse().toString();
            if (digit_stringified.equals(Long.toString(this.digit))) {
                this.palindromic = true;
            }
        }
    }

    public List<String> getAttributes() {
        List<String> attributes = new ArrayList<String>();
        if (this.buzz) {
            attributes.add("buzz");
        }
        if (this.duck) {
            attributes.add("duck");
        }
        if (this.palindromic) {
            attributes.add("palindromic");
        }
        if (this.gapful) {
            attributes.add("gapful");
        }
        if (this.even) {
            attributes.add("even");
        }
        if (this.odd) {
            attributes.add("odd");
        }
        if (this.spy) {
            attributes.add("spy");
        }
        if (this.sunny) {
            attributes.add("sunny");
        }
        if (this.square) {
            attributes.add("square");
        }
        if (this.jumping) {
            attributes.add("jumping");
        }
        if (this.happy) {
            attributes.add("happy");
        }
        if (this.sad) {
            attributes.add("sad");
        }

        return attributes;
    }

    public Boolean getGapful() {
        return gapful;
    }

    public void setGapful() {
        String digit_string = Long.toString(this.digit);
        if (digit_string.length() > 2) {
            char first = digit_string.charAt(0);
            char last = digit_string.charAt(digit_string.length() - 1);

            String combined = String.valueOf(first) + last;
            int combined_int = Integer.valueOf(combined);

            if (this.digit % combined_int == 0) {
                this.gapful = true;
            }
        }
    }

    public Boolean getDuck() {
        return duck;
    }

    public void setDuck() {
        int counter = 0;
        int starts = 0;
        char[] digit_string = Long.toString(this.digit).toCharArray();

        if (digit_string[0] == '0') {
            starts++;
        }
        for (char letter : digit_string) {
            if (letter == '0') {
                counter++;
            }
        }
        if (starts == 1 && counter > 1) {
            this.duck = true;
        } else {
            if (starts == 0 && counter > 0) {
                this.duck = true;
            }
        }
    }

    public int divisible_by_7(long number) {
        if (number % 7 == 0) {
            return 1;
        }
        return 0;
    }

    public int end_checker(long number) {
        String number_string = Long.toString(number);
        if (number_string.charAt(number_string.length() - 1) == '7' || number == 7) {
            return 2;
        }
        return 0;
    }

    public Boolean getBuzz() {
        return buzz;
    }

    public void setBuzz() {
        if (this.digit == 7) {
            this.buzz = true;
            return;
        }
        int status_divisible = divisible_by_7(this.digit);
        int status_end = end_checker(this.digit);
        int status = status_divisible + status_end;

        this.buzz = status != 0;
    }

    public Boolean getOdd() {
        return odd;
    }

    public Boolean getEven() {
        return even;
    }

    public void setParity() {
        if (this.digit % 2 == 0) {
            this.even = true;
            this.odd = false;
        } else {
            this.even = false;
            this.odd = true;
        }
    }
}
