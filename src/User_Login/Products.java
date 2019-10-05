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
public class Products {
    
     private int id;
    private String name;
    private String province;
    private String product;
    private String grade;
    private float price;
    private float qty;
    private float amount;
    
   /* public Products(int pid,String pname,String pprovince,String pproduct,String pgrade,float pprice,float pqty,float pamount){
    
    this.id=pid;
    this.name=pname;
    this.province=pprovince;
    this.product=pproduct;
    this.grade=pgrade;
    this.price=pprice;
    this.qty=pqty;
    this.amount=pamount;
     
}*/
     public Products(int pid,String pname,String pprovince,String pproduct,String pgrade,float pprice,float pqty,float pamount){
         
         this.id=pid;
         this.name=pname;
         this.province=pprovince;
         this.product=pproduct;
         this.grade=pgrade;
         this.price=pprice;
         this.qty=pqty;
         this.amount=pamount;
     
     
     
     
     }
    
    
    
    
    public int getId(){
        return id;
    
    }
     public String getName(){
         return name;
     
     }       
     public String getProvince(){
         return province;
     
     }
    
     public String getProduct(){
         return product;
     
     }
     public String getGrade(){
         return grade;
         
     }
     public float getPrice(){
         return price;
     }
     public float getQty(){
         return qty;
     
     }
     public float getAmount(){
         return amount;
     }
     
    
    
    
    
    
    
    
}