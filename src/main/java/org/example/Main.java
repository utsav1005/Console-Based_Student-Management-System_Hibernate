package org.example;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> StudentMethodsUtilityClass.addStudent();
                case 2 -> StudentMethodsUtilityClass.updateStudent();
                case 3 -> StudentMethodsUtilityClass.displayStudents();
                case 4 -> StudentMethodsUtilityClass.deleteStudent();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid Choice");
            }
        }
    }



