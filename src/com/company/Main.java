package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] sizeNTotal = sizeChoice();
        String[] orderDetails = orderToppings(Double.parseDouble(sizeNTotal[1]));
        System.out.println("You have ordered a " + sizeNTotal[0] + " pizza with " + orderDetails[0] + " that costs £" + orderDetails[1]);
    }
    static String strInput() {
        Scanner input = new Scanner(System.in);
        String output = input.nextLine();
        return output;
    }
    static String[] sizeChoice(){
        double total = 0.00;
        String size = null;
        String[] orderDetails = {"",""};
        do {
            if (total > 0) {
                break;
            }
            System.out.println("Choose between a small, medium or large pizza:");
            size = strInput();
            size = size.toLowerCase();
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
        } while (true);
        orderDetails[0] = size;
        orderDetails[1] = Double.toString(total);
        return orderDetails;
    }
    static String[] orderToppings(double total) {
        int freeToppings = 2;
        boolean errorRun;
        boolean end = false;
        String[] toppings = {"cheese","ham","pepperoni","egg","olives","chicken","bacon"};
        String[] toppingsNTotal = {"",""};
        System.out.println("\nYou have 2 free toppings\nHere are the toppings we offer:\n Cheese\n Ham\n Pepperoni\n Egg\n Olives\n Chicken\n Bacon\nEach additional topping is £1.25\nWhen you have chosen all the toppings you'd like, enter 'end' to stop.");
        while (true) {
            errorRun = true;
            String topping = strInput();
            topping = topping.toLowerCase();
            for (int i = 0; i < toppings.length; i++) {
                if (topping.equals(toppings[i])) {
                    if (freeToppings > 0) {
                        freeToppings -= 1;
                        toppingsNTotal[0] += topping.toLowerCase() + " ";
                        errorRun = false;
                    }
                    else {
                        toppingsNTotal[0] += topping.toLowerCase() + " ";
                        total += 1.25;
                        errorRun = false;
                    }
                }
                else if (topping.equalsIgnoreCase("end")) {
                    end = true;
                    errorRun = false;
                }
            }
            if (end == true) {
                break;
            }
            else if (errorRun == true) {
                System.out.println("We do not offer that topping\nPlease enter a topping we do offer");
            }
        }
        toppingsNTotal[1] = Double.toString(total);
        return toppingsNTotal;
    }
}
