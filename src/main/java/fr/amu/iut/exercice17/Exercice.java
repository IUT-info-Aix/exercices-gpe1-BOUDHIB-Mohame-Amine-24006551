package fr.amu.iut.exercice17;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.Random;

public class Exercice {
    private final String enonce;
    private final IntegerProperty solution = new SimpleIntegerProperty();

    public Exercice() {
        Random rand = new Random();
        int a = rand.nextInt(40) + 10;
        int b = rand.nextInt(1, 10);
        char[] ops = {'+', '-', '*', '/'};
        char op = ops[rand.nextInt(ops.length)];

        switch (op) {
            case '+':
                enonce = a + " + " + b + " =";
                solution.set(a + b);
                break;
            case '-':
                enonce = a + " - " + b + " =";
                solution.set(a - b);
                break;
            case '*':
                enonce = a + " * " + b + " =";
                solution.set(a * b);
                break;
            case '/':
                int divisible = a - (a % b);
                enonce = divisible + " / " + b + " =";
                solution.set(divisible / b);
                break;
            default:
                enonce = "";
        }
    }

    public String getEnonce() {
        return enonce;
    }

    public int getSolution() {
        return solution.get();
    }

    public IntegerProperty solutionProperty() {
        return solution;
    }
}
