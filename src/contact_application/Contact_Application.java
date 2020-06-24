/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contact_application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Contact_Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        File file = new File("D:\\java\\Contact_Application\\contacts.txt");
        Scanner s = new Scanner(System.in);
        System.out.println("\t\t>>>>>Welcome to Contact Application.<<<<<");
        int choice = 1;
        int menu_chocie = 0;
        do {
            System.out.println("\t\tPress 1 to create new contact.\n\t\tPress 2 to search contact by name.\n\t\tPress 3 to delete a contact:");
            System.out.println("\t\tPress 4 to view details of contact:\n\t\tPress 5 to edit contact details:\n\t\tPress 6 to end application.\n");
            menu_chocie = s.nextInt();
            switch (menu_chocie) {
                case 1: {
                    System.out.println("Enter your salutation(Mr. Mrs. Dr. Prof. Ms. etc):");
                    String sal = s.next();
                    System.out.println("Enter first name:");
                    String first_name = s.next();
                    System.out.println("Enter last name:");
                    String last_name = s.next();
                    System.out.println("Enter date of birth in mentioned format dd/MM/yyyy");
                    String dob = s.next();
                    int i = 0;
                    while (i == 0) {
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date testDate = null;
                        String date = dob;
                        testDate = df.parse(date);

                        if (!df.format(testDate).equals(date)) {
                            System.out.println("invalid date!!. Enter date of birth in mentioned format dd/MM/yyyy");
                            dob = s.next();
                        } else {
                            i = 1;
                        }
                    }
                    String ch = "y";

                    String mobile_no = " ";
                    while (ch.equals("y") || ch.equals("Y")) {
                        System.out.println("Enter contact number after +92:");
                        String numm = s.next();
                        while (numm.length() != 16) {
                            if (numm.length() == 10) {
                                String ss = "-->+92";
                                numm = ss + numm;
                                mobile_no = numm + mobile_no;

                            } else {
                                System.out.println("Wrong input.Enter again contact number after +92:");

                                numm = s.next();
                            }
                        }
                        System.out.println("Do you want to enter another contact number:\nPress Y for yes or N to continue");
                        ch = s.next();
                    }
                    System.out.println("Enter home city:");
                    String home = s.next();
                    String chh = "y";
                    String office = "";
                    while (chh.equals("y") || chh.equals("Y")) {
                        System.out.println("Enter Office city:");
                        String office_ = s.next();
                        String a = "-->";
                        office_ = a + office_;
                        office = office + office_;
                        System.out.println("Do you want to enter another office:\nPress Y for yes or N to continue");
                        chh = s.next();
                    }
                    String testString;
                    String emailaddress;
                    boolean b = false;
                    do {
                        System.out.println("Please enter your email address ex:xyz@gmail.com");
                        Scanner name = new Scanner(System.in);
                        emailaddress = name.nextLine();

                        String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                        testString = emailaddress;
                        b = testString.matches(email_regex);
                        System.out.println("Invalid email address");
                    } while (!b);
                    System.out.println("Enter notes if any otherwise type none");
                    String notes = s.next();
                    contact c = new contact(mobile_no, dob, sal, first_name, last_name, home, office, emailaddress, notes);
                    break;
                }
                case 2: {
                    System.out.println("Enter first name of contact to search details:");
                    String name = s.next();
                    BufferedReader br = null;

                    br = new BufferedReader(new FileReader(file));

                    int count = 0;
                    String line;
                    ArrayList<String> contact_data = new ArrayList<>();
                    while ((line = br.readLine()) != null) {
                        if (line.equals(name)) {
                            break;
                        }
                    }
                    contact_data.add(line);
                    while (count <= 7) {
                        count++;
                        contact_data.add(line = br.readLine());
                    }
                    if (contact_data.get(0) != null) {
                        //System.out.println(contact_data);
                        System.out.println("Name of Contact " + contact_data.get(2) + " " + contact_data.get(0) + " " + contact_data.get(1));
                        System.out.println("Date of birth is: " + contact_data.get(7));
                        System.out.println("Contact number is: " + contact_data.get(3) + ".\nHome city is: " + contact_data.get(4));
                        System.out.println("Office city is: " + contact_data.get(5) + ".\nEmail is: " + contact_data.get(6));
                        System.out.println("Notes are: " + contact_data.get(8));
                    } else {
                        System.out.println("No record found.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter first name of contact to delete details:");
                    String name = s.next();
                    BufferedReader br = null;

                    br = new BufferedReader(new FileReader(file));

                    int count = 0;
                    int found = 0;
                    String line;
                    ArrayList<String> contact_data = new ArrayList<>();
                    while ((line = br.readLine()) != null) {

                        if (line.equals(name)) {
                            found = 1;
                            break;
                        }
                        contact_data.add(line);
                    }

                    while (count <= 8) {
                        count++;
                        line = br.readLine();
                    }
                    if (line != null) {
                        contact_data.add(line);
                    }
                    while ((line = br.readLine()) != null) {

                        if (line.equals(name)) {
                            System.out.println("same record found twice.");
                            break;
                        }
                        contact_data.add(line);

                    }
                    if (found == 1) {
                        //System.out.println(contact_data);
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);
                        for (int i = 0; i < contact_data.size(); i++) {
                            bw.write(contact_data.get(i));
                            bw.newLine();
                        }
                        bw.close();
                        System.out.println("Record deleted.");
                    } else {
                        System.out.println("Sorry, Record not found. Try Again!");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Enter first name of contact to search details:");
                    String name = s.next();
                    BufferedReader br = null;

                    br = new BufferedReader(new FileReader(file));

                    int count = 0;
                    String line;
                    ArrayList<String> contact_data = new ArrayList<>();
                    while ((line = br.readLine()) != null) {
                        if (line.equals(name)) {
                            break;
                        }
                    }
                    contact_data.add(line);
                    while (count <= 7) {
                        count++;
                        contact_data.add(line = br.readLine());
                    }
                    if (contact_data.get(0) != null) {
                        //System.out.println(contact_data);
                        System.out.println("Name of Contact " + contact_data.get(2) + " " + contact_data.get(0) + " " + contact_data.get(1));
                        System.out.println("Date of birth is: " + contact_data.get(7));
                        System.out.println("Contact number is: " + contact_data.get(3) + ".\nHome city is: " + contact_data.get(4));
                        System.out.println("Office city is: " + contact_data.get(5) + ".\nEmail is: " + contact_data.get(6));
                        System.out.println("Notes are: " + contact_data.get(8));
                    } else {
                        System.out.println("No record found.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("\t\tPress 1 to update Salutation.");
                    System.out.println("\t\tPress 2 to update First name.");
                    System.out.println("\t\tPress 3 to update last name.");
                    System.out.println("\t\tPress 4 to update mobile number.");
                    System.out.println("\t\tPress 5 to update home city.");
                    System.out.println("\t\tPress 6 to update office.");
                    System.out.println("\t\tPress 7 to update email.");
                    System.out.println("\t\tPress 8 to update dob.");
                    System.out.println("\t\tPress 9 to update notes.");
                    int update_chocie = s.nextInt();
                    switch (update_chocie) {
                        case 1: {
                            System.out.println("Enter first name of contact to update details:");
                            String name = s.next();
                            System.out.println("Enter new Salutation(Mr. Mrs. etc):");
                            String updating = s.next();
                            update(name, updating, 2);

                            break;
                        }
                        case 2: {
                            System.out.println("Enter first name of contact to update details:");
                            String name = s.next();
                            System.out.println("Enter new First Name:");
                            String updating = s.next();
                            update(name, updating, 0);

                            break;
                        }
                        case 3: {
                            System.out.println("Enter first name of contact to update details:");
                            String name = s.next();
                            System.out.println("Enter new Last Name:");
                            String updating = s.next();
                            update(name, updating, 1);

                            break;
                        }
                        case 4: {
                            System.out.println("Enter first name of contact to update details:");
                            String name = s.next();
                            String ch = "y";

                            String mobile_no = " ";
                            while (ch.equals("y") || ch.equals("Y")) {
                                System.out.println("Enter new contact number after +92:");
                                String numm = s.next();
                                while (numm.length() != 16) {
                                    if (numm.length() == 10) {
                                        String ss = "-->+92";
                                        numm = ss + numm;
                                        mobile_no = numm + mobile_no;

                                    } else {
                                        System.out.println("Wrong input.Enter again contact number after +92:");

                                        numm = s.next();
                                    }
                                }
                                System.out.println("Do you want to enter another contact number:\nPress Y for yes or N to continue");
                                ch = s.next();
                            }
                            update(name, mobile_no, 3);

                            break;
                        }
                        case 5: {
                            System.out.println("Enter first name of contact to update details:");
                            String name = s.next();
                            System.out.println("Enter new home city:");
                            String updating = s.next();
                            update(name, updating, 4);
                            break;
                        }
                        case 6: {
                            System.out.println("Enter first name of contact to update details:");
                            String name = s.next();
                            String chh = "y";
                            String office = "";
                            while (chh.equals("y") || chh.equals("Y")) {
                                System.out.println("Enter new Office city:");
                                String office_ = s.next();
                                String a = "-->";
                                office_ = a + office_;
                                office = office + office_;
                                System.out.println("Do you want to enter another office:\nPress Y for yes or N to continue");
                                chh = s.next();
                            }
                            update(name, office, 5);
                            break;
                        }
                        case 7: {
                            System.out.println("Enter first name of contact to update details:");
                            String namee = s.next();
                            String testString;
                            String emailaddress;
                            boolean b = false;
                            do {
                                System.out.println("Please enter your new email address ex:xyz@gmail.com");
                                Scanner name = new Scanner(System.in);
                                emailaddress = name.nextLine();

                                String email_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                                testString = emailaddress;
                                b = testString.matches(email_regex);
                                System.out.println("Invalid email address");
                            } while (!b);
                            update(namee, emailaddress, 6);

                            break;
                        }
                        case 8: {
                            System.out.println("Enter first name of contact to update details:");
                            String name = s.next();
                            System.out.println("Enter new date of birth in mentioned format dd/MM/yyyy");
                            String dob = s.next();
                            int i = 0;
                            while (i == 0) {
                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                Date testDate = null;
                                String date = dob;
                                testDate = df.parse(date);

                                if (!df.format(testDate).equals(date)) {
                                    System.out.println("invalid date!!. Enter date of birth in mentioned format dd/MM/yyyy");
                                    dob = s.next();
                                } else {
                                    i = 1;
                                }
                            }
                            update(name, dob, 7);
                            break;
                        }
                        case 9: {
                            System.out.println("Enter first name of contact to update details:");
                            String name = s.next();
                            System.out.println("Enter new notes otherwise type none:");
                            String updating = s.next();
                            update(name, updating, 8);
                            break;
                        }

                    }
                    break;
                }
                case 6: {
                    choice = 2;
                    break;
                }

            }
        } while (choice == 1);
    }

    public static void update(String name, String new_name, int count_) throws FileNotFoundException, IOException {
        File file = new File("D:\\java\\Contact_Application\\contacts.txt");
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));

        int count = 0;
        int found = 0;
        String line;
        ArrayList<String> contact_data = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (line.equals(name)) {
                found = 1;
                break;
            }
            contact_data.add(line);
        }
        for (count = 0; count <= 8; count++) {
            if (count == count_) {
                contact_data.add(new_name);
                line = (br.readLine());
            } else {
                contact_data.add(line);
                line = (br.readLine());

            }
        }
        while ((line = br.readLine()) != null) {
            if (line.equals(name)) {
                System.out.println("same record found twice.");
                break;
            }
            contact_data.add(line);

        }
        if (found == 1) {
            System.out.println(contact_data);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < contact_data.size(); i++) {
                bw.write(contact_data.get(i));
                bw.newLine();
            }
            bw.close();
            System.out.println("Updated");
        } else {
            System.out.println("Sorry, Record not found. Try Again!");
        }
    }

}
