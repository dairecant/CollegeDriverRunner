/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.collegedriver;
import com.mycompany.college.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
/**
 *
 * @author Dáire
 */
public class Driver {
    public static void main(String args[]){
    ArrayList<Integer> IDs= new ArrayList(); //array List for IDs which will be a list of random numbers
    int mult = 11111111;
    while(IDs.size()<10){
                IDs.add((int)(Math.random()*mult)); //random number generator   
            }
    String[] names = {"Jack","Dáire","John","Aidan","Donnchadh","Niamh","Bronagh","Jordan","Patrick","Padraig"};
    DateTime[] DOBs={ //Array of DateTime Objects
        new DateTime("1996-09-23"),
        new DateTime("1996-08-04"),
        new DateTime("1996-07-19"),
        new DateTime("1996-06-22"),
        new DateTime("1996-05-05"),
        new DateTime("1995-04-17"),
        new DateTime("1996-03-18"),
        new DateTime("1996-02-02"),
        new DateTime("1994-01-09"),
        new DateTime("1996-11-10"),
    };
     ArrayList<Student> students_eng=new ArrayList();
          ArrayList<Student> students_csit=new ArrayList();

     int i=0;
     while(students_eng.size()<IDs.size()/2){
         students_eng.add(new Student(names[i],IDs.get(i),DOBs[i])); //creates a list of students
         i++;
     }
 while(students_csit.size()<IDs.size()/2){
         students_csit.add(new Student(names[i],IDs.get(i),DOBs[i])); //creates a list of students
         i++;
     }
     //Creating Module Objects
    Module SE=new Module("Software Engineering","CT417");
    Module DSP=new Module("Digital Signal Processing","EE445");   
    Module SoC = new Module("System On Chip Design","EE451");
    Module ML =new Module("Machine Learning","EE451");
    Module RTS = new Module ("Real Time Systems","CT420");  
    Module comms = new Module("Communications & Signal Processing","EE444");
  
    //Creating Course Programe objects
    CourseProgramme eng = new CourseProgramme("4th Year Engineering",new DateTime("2017-09-04"),new DateTime("2018-05-15"));
    CourseProgramme csit = new CourseProgramme("4th Year Computer Science",new DateTime("2017-09-04"),new DateTime("2018-05-15"));

    //Adding modules to courses
    eng.addModule(ML);
    eng.addModule(RTS);
    eng.addModule(SoC);
    csit.addModule(comms);
    csit.addModule(SE);
    csit.addModule(DSP);
   
    //matches student to course programme
    HashMap<Student,CourseProgramme> hm = new HashMap(); 
    
        //adding 1st half students to modules engineering course automatically
        eng.getModules().forEach((a) -> {
            for(int j=0; j<students_eng.size();j++){
                a.addStudent(students_eng.get(j));
                hm.put(students_eng.get(j), eng);
            } });
    
        //adding 2nd half students to modules engineering course automatically
        csit.getModules().forEach((a) -> {
            for(int j=0; j<students_csit.size();j++){
                a.addStudent(students_csit.get(j));
                hm.put(students_csit.get(j), csit);
            } });

      //iterates through each student in program,
      hm.keySet().stream().map((s) -> {         
          DateTimeFormatter dtfb = DateTimeFormat.forPattern("dd-MM-yyyy"); //leaves out time and time zone
          System.out.println("\n******************************************************"); //Separates different students
          System.out.println("********************Student Details*******************");

          //printing out student details
          System.out.println("\nStudent - Name: "+s.getName()
                  +"\nID: "+s.getID()
                  +"\nUsername: "+s.getUsername()
                  +"\nAge: "+s.getAge()
                  +"\nDate Of Birth: "+s.getDOB().toString(dtfb));
            return s;
        }).map((s) -> {
            printCourseDetails(hm.get(s)); //prints out course details corresponding to student
            return s;
        }).forEachOrdered((s) -> {
            //runs though each module in course
             //if student is in the student list for module, print the module information
             //this handles if there are optional modules in the course
            hm.get(s).getModules().stream().filter((a) -> (a.getStudents().contains(s))).forEachOrdered((a) -> {
                System.out.println("\nModule: "+a.getModuleName()+
                        "\nCode: "+a.getModuleCode());
            }); 
        });
  
 
 
    
    } //public static void main
     public static void printCourseDetails(CourseProgramme course){
     
      DateTimeFormatter dtfa = DateTimeFormat.forPattern("dd-MM-yyyy");
      System.out.println("\nCourse Name: "+course.getCourseName());
      System.out.println("Start Date: "+course.getStartDate().toString(dtfa)
                        +"\nFinish Date: "+course.getFinishDate().toString(dtfa));  
    }

    

}
