package com.example.higherlower;

/**
 * This is a new class that has been created for the throw
 */

public class Throw {

    //Create variable
    private int throwNumber;

    //Empty constructor
    public Throw() {
    }

    //Constructor with the variable that has been created above
    public Throw(int throwNumber) {
        this.throwNumber = throwNumber;
    }

    // Getter
    public int getThrowNumber() {
        return throwNumber;
    }

    //Setter
    public void setThrowNumber(int throwNumber) {
        this.throwNumber = throwNumber;
    }

    //To string to return the following string: "Throw is "throwNumber""
    @Override
    public String toString() {
        return "Throw is " + throwNumber;
    }
}
