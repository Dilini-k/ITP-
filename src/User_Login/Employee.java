/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Login;

/**
 *
 * @author Administrator
 */
public class Employee {
    
    private int id;
    private String name;
    private String sname;
    private String addDate;
    private int age;
    private String gender;
    private String department;
    private String division;
    private float salary;
    private int contact;
    private byte[] picture;
    
    
           
    
    
    
    

 public Employee(int pid,String pname,String pSname,String pAddDate,int pAge,String pGender,String pDepartment,String pDivi,float pSalary,int pContact,byte[] pImg){
     
     this.id=pid;
     this.name=pname;
     this.sname=pSname;
     this.addDate=pAddDate;
     this.age=pAge;
     this.gender=pGender;
     this.department=pDepartment;
     this.division=pDivi;
     this.salary=pSalary;
     this.contact=pContact;
     this.picture=pImg;
        
     
 
 
 }   





    
    public int getId(){
        return id;
    
    
    }
    public String getName(){
        return name;
    
    }
    public String getSname(){
        return sname;
    
    }
    public String getDate(){
        return addDate;
    
    }
    public int getAge(){
        return age;
    
    }
    public String getGender(){
        return gender;
    
    }
    public String getDepartment(){
        return department;
    
    }
    public String getDivision(){
        return division;
    }
    public float getSalary(){
        return salary;
    
    }
    public int getContact(){
        return contact;
    
    }
    
    
    public byte[] getImage(){
        return picture;
        
    }
    
    
    
    
}
