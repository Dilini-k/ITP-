/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */

public class Salary extends javax.swing.JFrame {
    
    

    /**
     * Creates new form Salary
     */
    public Salary() {
        initComponents();
        getConnection();
        Show_salary();
        //con=javaconnect.Connec
    
    }
    
    public Connection getConnection(){
        
   Connection con=null;
        
       
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost/point_of_sales_system?useSSL=true", "root", "");
            //JOptionPane.showMessageDialog(null, "Connected to database");
            return con;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Not Connected");
            return null;
        }
               
    
    }
    
     public ArrayList<Salary1> getsalary1List(){
            ArrayList<Salary1> salary1List=new ArrayList<Salary1>();
            Connection con=getConnection();
            String query="SELECT * from sal_info";
            
            Statement st;
            ResultSet rs;
        
        
            
        
        try {
            
            st=con.createStatement();
            rs=st.executeQuery(query);
            Salary1 salary1;
            
            while(rs.next()){
            
                
                salary1=new Salary1(rs.getInt("sid"),rs.getInt("eid"),rs.getString("sname"),rs.getString("depart"),Float.parseFloat(rs.getString("gp")),Float.parseFloat(rs.getString("salary")),rs.getInt("days"),rs.getInt("leaves"),rs.getInt("taken"),rs.getInt("wdays"),Float.parseFloat(rs.getString("otime")),Float.parseFloat(rs.getString("totime")),Float.parseFloat(rs.getString("rate")),Float.parseFloat(rs.getString("bonus"))
                ,Float.parseFloat(rs.getString("other")),Float.parseFloat(rs.getString("deduction")),Float.parseFloat(rs.getString("amount")),Float.parseFloat(rs.getString("tsalary")));
                //employee=new Employee(rs.getInt("eid"),rs.getString("name"),rs.getString("sname"),rs.getString("bdate"),rs.getInt("age"),rs.getString("gender"),rs.getString("depart"),rs.getString("division"),Float.parseFloat(rs.getString("salary")),rs.getInt("contact"),rs.getBytes("image"));
                //emplyeeList.add(employee);
                  salary1List.add(salary1);
            
            }
            
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salary1List;
            
             
            
            
            
        
    
    
    }
     public void Show_salary(){
        
        ArrayList<Salary1> list=getsalary1List();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
    
        
        model.setRowCount(0);
        Object[] row=new Object[12];
        for(int i=0;i<list.size();i++){
        
                row[0]=list.get(i).getEID();
                row[1]=list.get(i).getName();
                row[2]=list.get(i).getDepartment();
                row[3]=list.get(i).getSalary();
                row[4]=list.get(i).getTotime();
                row[5]=list.get(i).getBonus();
                row[6]=list.get(i).getOther();
                row[7]=list.get(i).getDeduction();
                row[8]=list.get(i).getAmount();
                row[9]=list.get(i).getTsalary();
                
                model.addRow(row);
        
        }
    
    
    }
      public void ShowSalary(int index){
        
            txt_id.setText(Integer.toString(getsalary1List().get(index).getEID()));
            txt_name.setText(getsalary1List().get(index).getName());
            txt_dep.setText(getsalary1List().get(index).getDepartment());
            //txt_product.setText(Integer.toString(getEmployeeList().get(index).getAge()));        
            txt_sal.setText(Float.toString(getsalary1List().get(index).getSalary()));
            txt_total.setText(Float.toString(getsalary1List().get(index).getTotime()));
            txt_bonus.setText(Float.toString(getsalary1List().get(index).getBonus()));
            txt_other.setText(Float.toString(getsalary1List().get(index).getOther()));
            txt_ded.setText(Float.toString(getsalary1List().get(index).getDeduction()));
            txt_amount.setText(Float.toString(getsalary1List().get(index).getAmount()));
            txt_salary.setText(Float.toString(getsalary1List().get(index).getTsalary()));
     
        
    
    
    
    
    
    
    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_sal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_rate = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_bonus = new javax.swing.JTextField();
        txt_other = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txt_ded = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_gp = new javax.swing.JTextField();
        txt_days = new javax.swing.JTextField();
        txt_leaves = new javax.swing.JTextField();
        txt_taken = new javax.swing.JTextField();
        txt_days1 = new javax.swing.JTextField();
        txt_time = new javax.swing.JTextField();
        txt_dep = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txt_amount = new javax.swing.JTextField();
        txt_salary = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Zoom-icon.png"))); // NOI18N
        jLabel1.setText("Employee:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(688, 21, 107, 48);

        txt_search.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        jPanel1.add(txt_search);
        txt_search.setBounds(810, 30, 358, 39);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Employee ID:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(240, 230, 116, 36);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Name:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(300, 290, 60, 38);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Department:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(250, 360, 108, 31);

        txt_id.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_id);
        txt_id.setBounds(390, 230, 296, 36);

        txt_name.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });
        txt_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nameKeyPressed(evt);
            }
        });
        jPanel1.add(txt_name);
        txt_name.setBounds(390, 290, 296, 38);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Working days for month:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(760, 230, 210, 24);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total leaves:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(870, 300, 110, 24);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Leaves taken:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(860, 360, 121, 24);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Basic Salary:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(250, 470, 111, 36);

        txt_sal.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_sal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_sal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_salActionPerformed(evt);
            }
        });
        jPanel1.add(txt_sal);
        txt_sal.setBounds(390, 470, 140, 40);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("OverTime:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(880, 480, 94, 24);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Department", "Basic Salary", "Overtime", "Bonus", "Other", "Deduction", "Amount", "Payment"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(219, 588, 1030, 312);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("Save");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 51), new java.awt.Color(153, 0, 51), new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(1320, 650, 140, 40);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Calculate");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 51), new java.awt.Color(153, 0, 51), new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(1320, 710, 140, 42);

        jButton3.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        jButton3.setText("Clear");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(1320, 770, 140, 42);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("Rate per hour:");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(1190, 230, 124, 20);

        txt_rate.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_rate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_rate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rateActionPerformed(evt);
            }
        });
        jPanel1.add(txt_rate);
        txt_rate.setBounds(1340, 220, 190, 40);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel12.setText("Total Amount:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(1220, 480, 123, 24);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/White-BG-Logo-01.png_2052721662.png"))); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(30, 23, 144, 152);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 102));
        jLabel16.setText("Bonus:");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(1250, 290, 70, 24);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("Other:");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(1250, 350, 70, 24);

        txt_bonus.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_bonus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_bonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bonusActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bonus);
        txt_bonus.setBounds(1340, 280, 190, 40);

        txt_other.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_other.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_other);
        txt_other.setBounds(1340, 340, 190, 40);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel15.setText("Total Salary");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(1240, 540, 110, 24);

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setText("Report");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0), new java.awt.Color(102, 0, 0), new java.awt.Color(153, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(1320, 840, 140, 40);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Deductions:");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(1220, 410, 100, 24);

        txt_ded.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_ded.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_ded);
        txt_ded.setBounds(1340, 400, 190, 40);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel18.setText("GP:");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(320, 420, 40, 24);

        txt_gp.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_gp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_gp);
        txt_gp.setBounds(390, 410, 140, 40);

        txt_days.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_days.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_days);
        txt_days.setBounds(1000, 220, 100, 40);

        txt_leaves.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_leaves.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_leaves);
        txt_leaves.setBounds(1000, 290, 100, 40);

        txt_taken.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_taken.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_taken);
        txt_taken.setBounds(1000, 350, 100, 40);

        txt_days1.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_days1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_days1);
        txt_days1.setBounds(1000, 410, 100, 40);

        txt_time.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_time);
        txt_time.setBounds(1000, 470, 100, 40);

        txt_dep.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_dep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_dep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_depKeyPressed(evt);
            }
        });
        jPanel1.add(txt_dep);
        txt_dep.setBounds(390, 350, 250, 40);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 153));
        jLabel21.setText("Total OverTime:");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(830, 540, 160, 20);

        txt_total.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        jPanel1.add(txt_total);
        txt_total.setBounds(1000, 530, 100, 40);

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton5.setText("Logout");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(1620, 840, 150, 50);

        txt_amount.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_amount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_amount);
        txt_amount.setBounds(1380, 470, 130, 40);

        txt_salary.setFont(new java.awt.Font("Footlight MT Light", 1, 18)); // NOI18N
        txt_salary.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_salary);
        txt_salary.setBounds(1380, 530, 130, 40);

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton6.setText("Delete");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(40, 740, 140, 40);

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton7.setText("Edit");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(40, 650, 140, 40);

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 102));
        jButton8.setText("Worked Days");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);
        jButton8.setBounds(820, 410, 160, 40);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dollar.jpg"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(0, 0, 1850, 950);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1851, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_salActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_salActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_salActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
         //float value = Float.parseFloat(txt_amount.getText())*Float.parseFloat(txt_qty.getText());
        //txt_total.setText(String.valueOf(value));
        
        float value=Float.parseFloat(txt_time.getText())*Float.parseFloat(txt_rate.getText());
        txt_total.setText(String.valueOf(value));
        
        float value1=Float.parseFloat(txt_total.getText())+Float.parseFloat(txt_bonus.getText())+Float.parseFloat(txt_other.getText())-Float.parseFloat(txt_ded.getText());
        txt_amount.setText(String.valueOf(value1));
        
        float value2=Float.parseFloat(txt_sal.getText())+Float.parseFloat(txt_total.getText())+Float.parseFloat(txt_bonus.getText())+Float.parseFloat(txt_other.getText())-Float.parseFloat(txt_ded.getText());
        txt_salary.setText(String.valueOf(value2));
        
       //if(txt_leaves.getText()>txt_taken.getText()){
        
        
        
        //}
        
       
        
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        try{
        Connection con=getConnection();
        PreparedStatement ps=con.prepareStatement("INSERT INTO sal_info(eid,sname,depart,gp,salary,days,leaves,taken,wdays,otime,totime,rate,bonus,other,deduction,amount,tsalary)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setInt(1, Integer.parseInt(txt_id.getText()));
        ps.setString(2, txt_name.getText());
        ps.setString(3, txt_dep.getText());
        ps.setString(4, txt_gp.getText());
        ps.setString(5, txt_sal.getText());
        ps.setInt(6, Integer.parseInt(txt_days.getText()));
        ps.setInt(7, Integer.parseInt(txt_leaves.getText()));
        ps.setInt(8, Integer.parseInt(txt_taken.getText()));
        ps.setInt(9, Integer.parseInt(txt_days1.getText()));
        ps.setString(10, txt_time.getText());
        ps.setString(11, txt_total.getText());
        ps.setString(12, txt_rate.getText());
        ps.setString(13, txt_bonus.getText());
        ps.setString(14, txt_other.getText());
        ps.setString(15, txt_ded.getText());
        ps.setString(16, txt_amount.getText());
        ps.setString(17, txt_salary.getText());
        
        ps.executeUpdate();
        Show_salary();
        
        
        
        
       // ps.setString(8, txt_sal.getText());
        
        
        JOptionPane.showMessageDialog(null, "Data Saved!");
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        
        
        }
        
            
            
                       
        
                
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
                   
          try{
        
            Connection con=getConnection();
            String query="SELECT * from emp_info where eid=?";
            
            Statement st;
            ResultSet rs;
            
            
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1, txt_search.getText());
            
            rs=pst.executeQuery();
            if(rs.next()){
                String add1=rs.getString("eid");
                txt_id.setText(add1);
                 String add2=rs.getString("name");
                txt_name.setText(add2);
                 String add3=rs.getString("depart");
                txt_dep.setText(add3);
                 String add4=rs.getString("salary");
                txt_sal.setText(add4);
            
            
            }
            
            

        
        
        }catch(Exception e){
        
        //JOptionPane.showMessageDialog(null, e);
        
        
        
        }  
           
             
      /* try{
       
       Connection con=getConnection();
            String query="SELECT * from emp_info where eid=?";
            
            //ps=con.prepareStatement(sql);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            
            
            String add1=rs.getString("eid");
            txt_id.setText(add1);
            
            String add2=rs.getString("name");
            txt_name.setText(add2);
            
            String add3=rs.getString("depart");
            txt_dep.setText(add3);
            
            String add4=rs.getString("salary");
            txt_sal.setText(add4);
            
            
       
       
       
       
       }catch(Exception e){
           
       
       }finally{
           try{
           
               
              
           
           }catch(Exception e){
           
           
           }
       
       }  */   
            
              
        
        
    }//GEN-LAST:event_txt_searchKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
            txt_id.setText("");
            txt_name.setText("");
            txt_dep.setText("");
            /*pst.setDate(4, sqlDate);*/
            txt_gp.setText("");
            txt_sal.setText("");
            
           /* String value=jComboBox2.getSelectedItem().toString();
            pst.setString(7, value);*/
            txt_days.setText("");
            txt_leaves.setText("");
             txt_taken.setText("");
             txt_days1.setText("");
            txt_time.setText("0");
            txt_total.setText("0");
            txt_rate.setText("0");
            txt_bonus.setText("0");
            txt_other.setText("0");
            txt_ded.setText("0");
            txt_amount.setText("0.00");
            txt_salary.setText("0.00");
            txt_search.setText("");
        
        
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.dispose();
             new Home().setVisible(true);

        
        
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_bonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bonusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bonusActionPerformed

    private void txt_rateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rateActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        MessageFormat header=new MessageFormat("Salary details of Employees");
        MessageFormat footer=new MessageFormat("Biofood international");
        
        try{
            jTable1.print(JTable.PrintMode.FIT_WIDTH,header, footer);
        
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, "Cannot be Print!"+e.getMessage());
        
        }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nameKeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        
        
        if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
            txt_name.setEditable(true);
        
        }else{
            txt_name.setEditable(false);
            JOptionPane.showMessageDialog(null, "Invalid character");
           
        
        }
    }//GEN-LAST:event_txt_nameKeyPressed

    private void txt_depKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_depKeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        
        
        if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
            txt_dep.setEditable(true);
        
        }else{
            txt_dep.setEditable(false);
            JOptionPane.showMessageDialog(null, "Invalid character");
           
        
        }
    }//GEN-LAST:event_txt_depKeyPressed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
                   
    }//GEN-LAST:event_txt_searchKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(!txt_id.getText().equals("")){
            try {
                Connection con=getConnection();
                PreparedStatement ps=con.prepareStatement("DELETE FROM sal_info WHERE eid=?");
                int id=Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
      
                ps.executeUpdate();
                 Show_salary();
                
                JOptionPane.showMessageDialog(null, "Informations Deleted");
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Infomrmations Not Deleted");
                
                
            }
            
        
        
        
        }else{
            
              JOptionPane.showMessageDialog(null, "Data Not Deleted : No Id to Delete");
              
        
        
        
        
        }
        

        
        
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        int index=jTable1.getSelectedRow();
         ShowSalary(index);
        
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
                String UpdateQuery= null;
                PreparedStatement ps=null;
                
        
        
            try {
                
                
                Connection con=getConnection();
                UpdateQuery="UPDATE sal_info SET sname=?,depart=?,gp=?,salary=?,days=?,leaves=?,taken=?,wdays=?,otime=?,totime=?,rate=?,bonus=?,other=?,deduction=?,amount=? "+" ,tsalary=? WHERE aid=?";
                 ps.setString(1, txt_name.getText());
                ps.setString(2, txt_dep.getText());
                ps.setString(3, txt_gp.getText());
                ps.setString(4, txt_sal.getText());
                ps.setInt(5, Integer.parseInt(txt_days.getText()));
                ps.setInt(6, Integer.parseInt(txt_leaves.getText()));
                ps.setInt(7, Integer.parseInt(txt_taken.getText()));
                ps.setInt(8, Integer.parseInt(txt_days1.getText()));
                ps.setString(9, txt_time.getText());
                ps.setString(10, txt_total.getText());
                ps.setString(11, txt_rate.getText());
                ps.setString(12, txt_bonus.getText());
                ps.setString(13, txt_other.getText());
                ps.setString(14, txt_ded.getText());
                ps.setString(15, txt_amount.getText());
                ps.setString(16, txt_salary.getText());
                ps.setInt(17, Integer.parseInt(txt_id.getText()));

                     ps.executeUpdate();
                     Show_salary();
                
                JOptionPane.showMessageDialog(null, "Product Updated");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
            }

    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        float value=Float.parseFloat(txt_leaves.getText())-Float.parseFloat(txt_taken.getText());
        txt_days1.setText(String.valueOf(value));
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bonus;
    private javax.swing.JTextField txt_days;
    private javax.swing.JTextField txt_days1;
    private javax.swing.JTextField txt_ded;
    private javax.swing.JTextField txt_dep;
    private javax.swing.JTextField txt_gp;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_leaves;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_other;
    private javax.swing.JTextField txt_rate;
    private javax.swing.JTextField txt_sal;
    private javax.swing.JTextField txt_salary;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_taken;
    private javax.swing.JTextField txt_time;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
