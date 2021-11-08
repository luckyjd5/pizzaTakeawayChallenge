package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        String[] sizeNTotal = sizeChoice(); // Calls method which asks for size of pizza.
        String[] orderDetails = orderToppings(Double.parseDouble(sizeNTotal[1])); // Calls method which asks for pizza toppings, inputting order total
        if (orderDetails[0].length() > 0) { // If they order toppings it will display the toppings they ordered
            System.out.println("\nYou have ordered a " + sizeNTotal[0] + " pizza with:" + orderDetails[0] + "\nYour order costs £" + orderDetails[1]);
        } else { // If they didn't order toppings, this will be displayed
            System.out.println("\nYou have ordered a " + sizeNTotal[0] + " pizza that costs £" + orderDetails[1]);
        }
    }

    static String strInput() { // An input scanner method that allows inputs from methods without a scanner object, saves on space
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    static String[] sizeChoice() {
        double total = 0.00;
        String size = null;
        String[] orderDetails = {"", ""};
        while (total == 0) { // While loop stops invalid inputs from being processed
            System.out.println("Choose between a small, medium or large pizza:");
            size = strInput().toLowerCase();
            switch (size) {
                case ("small"):
                    total += 7.99;
                    break;
                case ("medium"):
                    total += 10.99;
                    break;
                case ("large"):
                    total += 13.99;
                    break;
                default:
                    System.out.println("Please enter a valid size.\n");
            }
        }
        orderDetails[0] = size;
        orderDetails[1] = Double.toString(total);
        return orderDetails; // An array is returned because two variables need to be used when printing the order details
    }

    static String[] orderToppings(double total) {
        int freeToppings = 2; // The user has 2 free toppings, when this reaches 0 they must pay for toppings
        boolean errorRun;
        boolean end = false;
        String[] toppings = {"cheese", "ham", "pepperoni", "egg", "olives", "chicken", "bacon"};
        String[] toppingsNTotal = {"\n", ""};
        System.out.println("\nYou have 2 free toppings\nHere are the toppings we offer:\n Cheese\n Ham\n Pepperoni\n Egg\n Olives\n Chicken\n Bacon\nEach additional topping is £1.25\nWhen you have chosen all the toppings you'd like, enter 'end' to stop.");
        while (true) {
            total *= 100;
            total = Math.round(total);
            total /= 100; // Prevents precision errors in doubles and keeps the double to 2dp
            errorRun = true;
            System.out.println("\nYour order is £" + total + "\nEnter a topping:");
            String topping = strInput();
            topping = topping.toLowerCase();
            for (String i : toppings) { // If the inputted topping exists in the topping array, the topping is added to a list of toppings and if needed money is added to the total
                if (topping.equals(toppings[Arrays.asList(toppings).indexOf(i)])) {
                    errorRun = false;
                    toppingsNTotal[0] += "• " + topping.toLowerCase() + "\n";
                    if (freeToppings > 0) {
                        freeToppings -= 1;
                    } else {
                        total += 1.25;
                    }
                }
                else if (topping.equalsIgnoreCase("end")) { // If the user enters 'end', they will end the while and for loops, taking them to the order details printing
                    end = true;
                    break;
                }
            }
            if (end) {
                break;
            } else if (errorRun) { // If the user's input does not match 'end' or any of the toppings in the toppings array, they go back to the beginning of the while loop
                System.out.println("We do not offer that topping\nPlease enter a topping we do offer");
            }
        }
        toppingsNTotal[1] = Double.toString(total);
        return toppingsNTotal; // An array is returned because 2 variables are needed for the order details, toppings ordered and total price.
    }
}