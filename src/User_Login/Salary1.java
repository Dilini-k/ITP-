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
public class Salary1 {
    
    private int id;
    private int eid;
    private String name;
    private String department;
    private float gp;
    private float salary;
    private int days;
    private int leaves;
    private int taken;
    private int wdays;
    private float otime;
    private float totime;
    private float rate;
    private float bonus;
    private float other;
    private float deduction;
    private float amount;
    private float total;
    
    
    
    
    public Salary1(int pid,int peid,String pname,String pdepart,float pgp,float psalary,int pdays,int pleaves,int ptaken,int pwdays,float potime,float ptotime,float prate
    ,float pbonus,float pother,float pdeduction,float pamount,float ptotal){
    
    this.id=pid;
    this.eid=peid;
    this.name=pname;
    this.department=pdepart;
    this.gp=pgp;
    this.salary=psalary;
    this.days=pdays;
    this.leaves=pleaves;
    this.taken=ptaken;
    this.wdays=pwdays;
    this.otime=potime;
    this.totime=ptotime;
    this.rate=prate;
    this.bonus=pbonus;
    this.other=pother;
    this.deduction=pdeduction;
    this.amount=pamount;
    this.total=ptotal;
    
    
    
    }
    
    
        public int getID(){
        return id;
    
    }
        public int getEID(){
            return eid;
        }
        
        public String getName(){
            return name;
        
        }
        
    public String getDepartment(){
            return department;
    
    
    }
    public float getGp(){
            return gp;
    
    }
    public float getSalary(){
            return salary;
    
    }
    
    public int getDays(){
            return days;
    
    }
    public int getLeaves(){
            return leaves;
    
    }
    public int getTaken(){
            return taken;
    
    }
    public int getWdays(){
            return wdays;
    
    }
    public float getOtime(){
            return otime;
    
    }
    public float getTotime(){
            return totime;
    
    }
    public float getRate(){
            return rate;
    
    }
    public float getBonus(){
            return bonus;
    
    }
    public float getOther(){
            return other;
    
    }
    public float getDeduction(){
            return deduction;
    
    }
    public float getAmount(){
            return amount;
    
    }
    public float getTsalary(){
            return total;
    
    }
    
    
    
}
