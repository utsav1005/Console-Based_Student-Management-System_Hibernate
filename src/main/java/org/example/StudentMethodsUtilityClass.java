package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class StudentMethodsUtilityClass {
    //1.Add Student
    //2.Update Student
    //3.Display Student
    //4.Delete Student
    static Scanner sc = new Scanner(System.in);

    //Add Student
    public static void addStudent() {

        System.out.println("Please Enter Student Name:");
        String name = sc.nextLine();
        sc.nextLine();
        System.out.println("Please Enter Student Age:");
        int age = sc.nextInt();
        sc.nextLine();
        Student student  = new Student(name, age);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.persist(student); //save Student
            tx.commit();
            System.out.println("Student Added Successfully With ID:"+student.getId());
        } catch (Exception e) {
            if(tx!= null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    //Update Student
    public static void updateStudent() {
        System.out.println("For Update your data please enter your Student id");
        int id = sc.nextInt();
        sc.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            //Fetch Student from DB
            Student foundStudent = session.get(Student.class,id);
            if(foundStudent == null){
                System.err.println("Student Not Found:");
                return;
            }
            System.out.println("Student Found Successfully!!"+foundStudent);

            System.out.println("Which Details You want To update Select Any One\n" +
                    "1.Update name\n" +
                    "2.Update Age\n" +
                    "3. Update Both\n" +
                    "4.Cancel Update\n"
            );

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter Updated Name:");
                    foundStudent.setName(sc.nextLine());
                    System.out.println("Update name Successfully!!!");
                    break;

                case 2:
                    System.out.println("Enter Updated Age:");
                    foundStudent.setAge(sc.nextInt());
                    System.out.println("Update age Successfully!!!");
                    break;
                case 3:
                    System.out.println("Enter Updated Name:");
                    String name = sc.nextLine();
                    foundStudent.setName(name);
                    System.out.println("Enter Updated Age:");
                    int age = sc.nextInt();
                    foundStudent.setName(name);
                    foundStudent.setAge(age);
                    System.out.println("Update name and age Successfully!!!");
                    break;

                case 4:
                    System.out.println("Update Cancelled Successfully!!!");
                    break;

                default:
                    System.err.println("Invalid Operation");
                    break;
        }

        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    //Display Student
    public static void displayStudents() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> student = session.createQuery("from Student", Student.class).list();
        if(student.isEmpty()){
            System.out.println("No Students Found");
        }else{
            for(Student s : student){
                System.out.println(s);
            }
        }
    session.close();
    }

    //Delete Student
    public static void deleteStudent() {
        System.out.println("Enter Student id which You want to delete it");
        int id = sc.nextInt();
        sc.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Student studentDelete = session.get(Student.class , id);

            if(studentDelete == null){
                System.out.println("Student Not Found");

            }
            System.out.println("Student Found Successfully"+studentDelete);
            System.out.println("Are You sure You want to Delete it (Yes/No):");
            String confirms = sc.nextLine();
            if (confirms.equalsIgnoreCase("Yes")) {
                session.remove(studentDelete);
                tx.commit();
                System.out.println("Student Deleted Successfully");
            } else {
                System.out.println("Delete Operation is failed");
                tx.rollback();
            }

        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        }
    }

