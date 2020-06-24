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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class contact {
    File itemm=new File("D:\\java\\Contact_Application\\contacts.txt");
    private String salutation,first_name,last_name,home_address,email,notes,dob,mobile_no,office;

    public contact(String mobile_no, String dob, String salutation, String first_name, String last_name, String home_address, String office, String email, String notes) throws FileNotFoundException, IOException {
        this.mobile_no=mobile_no;
        this.dob = dob;
        this.salutation = salutation;
        this.first_name = first_name;
        this.last_name = last_name;
        this.home_address = home_address;
        this.office=office;
        this.email = email;
        this.notes = notes;
        System.out.println("\t\t>>>>>Contact Created.<<<<<\n");
        
        BufferedReader br = null;
        ArrayList<String> array=new ArrayList<>();
            br = new BufferedReader(new FileReader(itemm));
        String line;
            while((line=br.readLine())!=null){
                array.add(line);
            }
        FileWriter fw=new FileWriter(itemm);
        BufferedWriter bw=new BufferedWriter(fw);
        for(int i=0;i<array.size();i++){
            bw.write(array.get(i));
            bw.newLine();
        }
        bw.write(this.first_name);
        bw.newLine();
        bw.write(this.last_name);
        bw.newLine();
        bw.write(this.salutation);
        bw.newLine();
        
        bw.write(this.mobile_no);
        bw.newLine();
        bw.write(this.home_address);
        bw.newLine();
        bw.write(this.office);
        bw.newLine();
        bw.write(this.email);
        bw.newLine();
        bw.write(this.dob);
        bw.newLine();
        bw.write(this.notes);
        bw.close();
        
        
    }
    public void create_contact(){
        
    }
    
    
}
