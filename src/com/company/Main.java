package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        String[] sizeNTotal = sizeChoice();
        String[] orderDetails = orderToppings(Double.parseDouble(sizeNTotal[1]));
        if (orderDetails[0].length() > 0) {
            System.out.println("\nYou have ordered a " + sizeNTotal[0] + " pizza with:" + orderDetails[0] + "\nYour order costs £" + orderDetails[1]);
        } else {
            System.out.println("\nYou have ordered a " + sizeNTotal[0] + " pizza that costs £" + orderDetails[1]);
        }
    }

    static String strInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    static String[] sizeChoice() {
        double total = 0.00;
        String size = null;
        String[] orderDetails = {"", ""};
        while (total == 0) {
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
        return orderDetails;
    }

    static String[] orderToppings(double total) {
        int freeToppings = 2;
        boolean errorRun;
        boolean end = false;
        String[] toppings = {"cheese", "ham", "pepperoni", "egg", "olives", "chicken", "bacon"};
        String[] toppingsNTotal = {"\n", ""};
        System.out.println("\nYou have 2 free toppings\nHere are the toppings we offer:\n Cheese\n Ham\n Pepperoni\n Egg\n Olives\n Chicken\n Bacon\nEach additional topping is £1.25\nWhen you have chosen all the toppings you'd like, enter 'end' to stop.");
        while (true) {
            total *= 100;
            total = Math.round(total);
            total /= 100;
            errorRun = true;
            System.out.println("\nYour order is £" + total + "\nEnter a topping:");
            String topping = strInput();
            topping = topping.toLowerCase();
            for (String i : toppings) {
                if (topping.equals(toppings[Arrays.asList(toppings).indexOf(i)])) {
                    errorRun = false;
                    toppingsNTotal[0] += "• " + topping.toLowerCase() + "\n";
                    if (freeToppings > 0) {
                        freeToppings -= 1;
                    } else {
                        total += 1.25;
                    }
                }
                else if (topping.equalsIgnoreCase("end")) {
                    end = true;
                    break;
                }
            }
            if (end) {
                break;
            } else if (errorRun) {
                System.out.println("We do not offer that topping\nPlease enter a topping we do offer");
            }
        }
        toppingsNTotal[1] = Double.toString(total);
        return toppingsNTotal;
    }
}