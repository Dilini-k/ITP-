/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Login;


import java.awt.Image;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class Employee_Info1 extends javax.swing.JFrame {

    /**
     * Creates new form Employee_Info1
     */
    public Employee_Info1() {
        initComponents();
        Show_employee();
        CurrentDate();
        
         
        
    }
    
    public void CurrentDate(){
        
        Thread clock=new Thread(){
            public void run(){
                for(;;){
                
                    Calendar cal=new GregorianCalendar();
                    int month=cal.get(Calendar.MONTH);
                    int year=cal.get(Calendar.YEAR);
                    int day=cal.get(Calendar.DAY_OF_MONTH);
                    jMenu4.setText("Date "+day+"-"+(month-1)+"-"+year);
                    
                    int hour=cal.get(Calendar.HOUR);
                    int min=cal.get(Calendar.MINUTE);
                    int sec=cal.get(Calendar.SECOND);
                    jMenu5.setText("Time"+hour+":"+(min)+":"+sec);
                    
                    
                    try{
                    
                        sleep(1000);
                    
                    }catch(InterruptedException ex){
                        Logger.getLogger(Employee_Info1.class.getName()).log(Level.SEVERE, null, ex);
                    
                    
                    }
                    
                    
                }
                
            
            
            }
        
        
        };
        clock.start();
    
    
    }
    
    
    
    String ImgPath=null;
    
    public Connection getConnection(){
        
   Connection con=null;
        
       
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost/point_of_sales_system?useSSL=true", "root", "");
            //JOptionPane.showMessageDialog(null, "Connected to database");
            return con;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
               
    
    }
    //check input fields
    
        public boolean checkInputs(){
            
            if(txt_name.getText()==null||txt_sname.getText()==null||txt_age.getText()==null||txt_dep.getText()==null||txt_AddDate.getDate()==null){
                
                return false;
        }else{
                
               try{
               
                  Float.parseFloat(txt_sal.getText());
                   return true;
               
               
               }catch(Exception ex){
               
                   return false;
               
               
               } 
                
                
                
            }
    
    
    
        }
    
    
    
    //Resize image
    
    public ImageIcon ResizeImage(String imagePath, byte[] pic){
        
        ImageIcon myImage=null;
        
        if(imagePath !=null){
            
            myImage=new ImageIcon(imagePath);
        
        
        }else{
            myImage=new ImageIcon(pic);
            
    
    }
        Image img=myImage.getImage();
        Image img2=img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image=new ImageIcon(img2);
        return image;
    }
    
    
    
    //JTable
    public ArrayList<Employee> getEmployeeList(){
            ArrayList<Employee> emplyeeList=new ArrayList<Employee>();
            Connection con=getConnection();
            String query="SELECT * from emp_info";
            
            Statement st;
            ResultSet rs;
        
        
            
        
        try {
            
            st=con.createStatement();
            rs=st.executeQuery(query);
            Employee employee;
            
            while(rs.next()){
            
                
                employee=new Employee(rs.getInt("eid"),rs.getString("name"),rs.getString("sname"),rs.getString("bdate"),rs.getInt("age"),rs.getString("gender"),rs.getString("depart"),rs.getString("division"),Float.parseFloat(rs.getString("salary")),rs.getInt("contact"),rs.getBytes("image"));
                emplyeeList.add(employee);
            
            
            }
            
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emplyeeList;
            
             
            
            
            
        
    
    
    }
    
    //populate the table
    
    public void Show_employee(){
        
        ArrayList<Employee> list=getEmployeeList();
        DefaultTableModel model=(DefaultTableModel)jTable_emp.getModel();
    
        
        model.setRowCount(0);
        Object[] row=new Object[10];
        for(int i=0;i<list.size();i++){
        
                row[0]=list.get(i).getId();
                row[1]=list.get(i).getName();
                row[2]=list.get(i).getSname();
                row[3]=list.get(i).getDate();
                row[4]=list.get(i).getAge();
                row[5]=list.get(i).getGender();
                row[6]=list.get(i).getDepartment();
                row[7]=list.get(i).getDivision();
                row[8]=list.get(i).getSalary();
                row[9]=list.get(i).getContact();
                
                model.addRow(row);
        
        }
    
    
    }
    
    public void ShowEmployee(int index){
        
        txt_id.setText(Integer.toString(getEmployeeList().get(index).getId()));
            txt_name.setText(getEmployeeList().get(index).getName());
            txt_sname.setText(getEmployeeList().get(index).getSname());
            txt_age.setText(Integer.toString(getEmployeeList().get(index).getAge()));        
            txt_gen.setText(getEmployeeList().get(index).getGender());
            txt_dep.setText(getEmployeeList().get(index).getDepartment());
            txt_divi.setText(getEmployeeList().get(index).getDivision());
            txt_sal.setText(Float.toString(getEmployeeList().get(index).getSalary()));
            txt_con.setText(Integer.toString(getEmployeeList().get(index).getContact()));

        
   
        try {
                        
            
            Date addDate=null;
            addDate =new SimpleDateFormat("yyyy-MM-dd").parse((String)getEmployeeList().get(index).getDate());
            txt_AddDate.setDate(addDate);
        } catch (ParseException ex) {
            Logger.getLogger(Employee_Info1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            lbl_image.setIcon(ResizeImage(null,getEmployeeList().get(index).getImage()));
    
    
    
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. Thecontent of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_gen = new javax.swing.JTextField();
        txt_sname = new javax.swing.JTextField();
        txt_age = new javax.swing.JTextField();
        txt_dep = new javax.swing.JTextField();
        txt_divi = new javax.swing.JTextField();
        txt_sal = new javax.swing.JTextField();
        txt_con = new javax.swing.JTextField();
        txt_AddDate = new com.toedter.calendar.JDateChooser();
        btn_update = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        btn_clr = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_emp = new javax.swing.JTable();
        lbl_image = new javax.swing.JLabel();
        btn_choose = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txt_calAge = new javax.swing.JLabel();
        txt_calAge1 = new javax.swing.JLabel();
        txt_calAge2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 0));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Employee ID:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(68, 294, 160, 28);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(72, 343, 100, 28);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SurName:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(69, 405, 130, 28);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date of Birth:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(69, 469, 160, 28);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Age:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(80, 570, 90, 28);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Gender:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(75, 618, 110, 28);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Department:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(67, 679, 150, 28);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Division:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(69, 738, 120, 28);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Basic Salary:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(70, 804, 150, 28);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Contact:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(67, 861, 120, 28);

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        jPanel1.add(txt_id);
        txt_id.setBounds(243, 288, 308, 36);

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
        txt_name.setBounds(243, 337, 308, 37);

        txt_gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_genActionPerformed(evt);
            }
        });
        txt_gen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_genKeyPressed(evt);
            }
        });
        jPanel1.add(txt_gen);
        txt_gen.setBounds(243, 618, 308, 39);

        txt_sname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_snameKeyPressed(evt);
            }
        });
        jPanel1.add(txt_sname);
        txt_sname.setBounds(243, 399, 308, 36);

        txt_age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ageActionPerformed(evt);
            }
        });
        jPanel1.add(txt_age);
        txt_age.setBounds(243, 562, 308, 38);

        txt_dep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_depActionPerformed(evt);
            }
        });
        txt_dep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_depKeyPressed(evt);
            }
        });
        jPanel1.add(txt_dep);
        txt_dep.setBounds(243, 677, 308, 41);

        txt_divi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diviActionPerformed(evt);
            }
        });
        txt_divi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_diviKeyPressed(evt);
            }
        });
        jPanel1.add(txt_divi);
        txt_divi.setBounds(243, 736, 308, 37);

        txt_sal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_salActionPerformed(evt);
            }
        });
        jPanel1.add(txt_sal);
        txt_sal.setBounds(243, 796, 308, 40);
        jPanel1.add(txt_con);
        txt_con.setBounds(243, 854, 308, 39);
        jPanel1.add(txt_AddDate);
        txt_AddDate.setBounds(243, 458, 308, 33);

        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit-icon.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        jPanel1.add(btn_update);
        btn_update.setBounds(720, 393, 153, 49);

        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save-icon.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        jPanel1.add(btn_save);
        btn_save.setBounds(720, 314, 153, 49);

        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Misc-Delete-Database-icon.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_delete);
        btn_delete.setBounds(720, 472, 153, 49);

        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/print-icon.png"))); // NOI18N
        btn_print.setText("Print Report");
        btn_print.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel1.add(btn_print);
        btn_print.setBounds(710, 690, 160, 60);

        btn_clr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Actions-edit-clear-list-icon.png"))); // NOI18N
        btn_clr.setText("Clear");
        btn_clr.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_clr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clrActionPerformed(evt);
            }
        });
        jPanel1.add(btn_clr);
        btn_clr.setBounds(720, 548, 153, 49);

        jTable_emp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EID", "Name", "SurName", "Date of Birth", "Age", "Gender", "Department", "Division", "Salary", "Contact"
            }
        ));
        jTable_emp.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTable_empComponentAdded(evt);
            }
        });
        jTable_emp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_empMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_emp);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(976, 284, 818, 548);

        lbl_image.setBackground(new java.awt.Color(255, 255, 255));
        lbl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lbl_image);
        lbl_image.setBounds(1608, 48, 186, 154);

        btn_choose.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\attach-2-icon.png")); // NOI18N
        btn_choose.setText("Attach");
        btn_choose.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chooseActionPerformed(evt);
            }
        });
        jPanel1.add(btn_choose);
        btn_choose.setBounds(1620, 224, 122, 42);

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\Apps-Dialog-Logout-icon.png")); // NOI18N
        jButton7.setText("LogOut");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(1644, 850, 159, 52);

        jLabel11.setBackground(new java.awt.Color(0, 153, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/biofood logo.png"))); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(70, 30, 166, 169);

        jButton2.setBackground(new java.awt.Color(102, 255, 102));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Age:");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(253, 498, 112, 34);

        txt_calAge.setForeground(new java.awt.Color(255, 255, 255));
        txt_calAge.setText("0");
        txt_calAge.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_calAge);
        txt_calAge.setBounds(391, 498, 43, 34);

        txt_calAge1.setForeground(new java.awt.Color(255, 255, 255));
        txt_calAge1.setText("0");
        txt_calAge1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_calAge1);
        txt_calAge1.setBounds(441, 498, 42, 34);

        txt_calAge2.setForeground(new java.awt.Color(255, 255, 255));
        txt_calAge2.setText("0");
        txt_calAge2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txt_calAge2);
        txt_calAge2.setBounds(490, 498, 43, 34);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back2.jpg"))); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(0, 0, 1870, 210);

        jMenuBar1.setAlignmentX(1.0F);
        jMenuBar1.setAlignmentY(1.0F);

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jMenuItem1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\Button-Delete-icon.png")); // NOI18N
        jMenuItem1.setText("Exit");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");
        jMenu3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jMenuItem2.setText("FAQ");
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Date");
        jMenu4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Time");
        jMenu5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        
        
        
        if(!txt_id.getText().equals("")){
            try {
                Connection con=getConnection();
                PreparedStatement ps=con.prepareStatement("DELETE FROM emp_info WHERE eid=?");
                int id=Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
      
                ps.executeUpdate();
                 Show_employee();
                
                JOptionPane.showMessageDialog(null, "Informations Deleted");
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Infomrmations Not Deleted");
                
                
            }
            
        
        
        
        }else{
            
              JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id to Delete");
              
        
        
        
        
        }
        
        
        
        
        
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        
        MessageFormat header=new MessageFormat("Employee Informations"); 
        MessageFormat footer=new MessageFormat("Biofood International");
        
        
        try {
            jTable_emp.print(JTable.PrintMode.FIT_WIDTH, header,footer);
        } catch (java.awt.print.PrinterException ex) {
            //Logger.getLogger(Employee_Info1.class.getName()).log(Level.SEVERE, null, ex);
            
            JOptionPane.showMessageDialog(null, "Cannot be Print!"+ex.getMessage());
            
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_btn_printActionPerformed

    private void btn_clrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clrActionPerformed
        // TODO add your handling code here:
        txt_id.setText("");
        txt_name.setText("");
        txt_sname.setText("");
        txt_gen.setText("");
        txt_age.setText("");
        txt_dep.setText("");
        txt_divi.setText("");
        txt_sal.setText("");
        txt_con.setText("");
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_btn_clrActionPerformed

    private void btn_chooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chooseActionPerformed
        // TODO add your handling code here:
        
        JFileChooser file=new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter=new FileNameExtensionFilter("*.images","jpg","png");
        file.addChoosableFileFilter(filter);
        int result=file.showSaveDialog(null);
        
        if(result==JFileChooser.APPROVE_OPTION){
        
            File selectedFile=file.getSelectedFile();
            String path=selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path,null));
            ImgPath=path;
        
        }else{
            
            System.out.println("No File Selected");
        
        }
        
        
    }//GEN-LAST:event_btn_chooseActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        
        if(checkInputs() && ImgPath !=null){
        
            try {
                Connection con=getConnection();
                PreparedStatement ps=con.prepareStatement("INSERT INTO emp_info(name,sname,bdate,age,gender,depart,division,salary,contact,image)" + "values(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_sname.getText());
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                String addDate=dateFormat.format(txt_AddDate.getDate());
                ps.setString(3, addDate);
                ps.setInt(4, Integer.parseInt(txt_age.getText()));
                ps.setString(5, txt_gen.getText());
                ps.setString(6, txt_dep.getText());
                ps.setString(7, txt_divi.getText());
                ps.setString(8, txt_sal.getText());
                ps.setInt(9, Integer.parseInt(txt_con.getText()));
                
                InputStream img=new FileInputStream(new File(ImgPath));
                ps.setBlob(10, img);
                ps.executeUpdate();
                 Show_employee();

                
                
                
                
                
                
                JOptionPane.showMessageDialog(null, "Data Saved!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        
        }else{
        
            JOptionPane.showMessageDialog(null, "One or More field Are Empty!");
            
            System.out.println("SName =>"+txt_sname.getText());
            System.out.println("Date =>"+txt_AddDate.getDate());
            System.out.println("Age =>"+txt_age.getText());
            System.out.println("Gender =>"+txt_gen.getText());
            System.out.println("Department =>"+txt_dep.getText());
            System.out.println("Division =>"+txt_divi.getText());
            System.out.println("Salary =>"+txt_sal.getText());
            System.out.println("Contact =>"+txt_con.getText());
            System.out.println("Image =>"+ImgPath);
        
        }
        
       
        
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        
        if(checkInputs() && txt_id.getText() !=null){
        
            String UpdateQuery= null;
            PreparedStatement ps=null;
            Connection con=getConnection();
                
            //update without image
                if(ImgPath==null){
                    
                try {
                    UpdateQuery="UPDATE emp_info SET name=?,sname=?,bdate=?,age=?,gender=?,depart=?,division=?,salary=? "+",contact=? WHERE eid=?";
                    ps=con.prepareStatement(UpdateQuery);
                    
                    
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_sname.getText());
                    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    String addDate=dateFormat.format(txt_AddDate.getDate());
                    ps.setString(3, addDate);
                    
                   
                ps.setInt(4, Integer.parseInt(txt_age.getText()));
                ps.setString(5, txt_gen.getText());
               ps.setString(6, txt_dep.getText());
               ps.setString(7, txt_divi.getText());
                ps.setString(8, txt_sal.getText());
                ps.setInt(9, Integer.parseInt(txt_con.getText()));
                
                ps.setInt(10, Integer.parseInt(txt_id.getText()));
                ps.executeUpdate();
                Show_employee();

                
                JOptionPane.showMessageDialog(null, "Product Updated");
          
                    
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(Level.SEVERE, null, ex);
                }
                            
                
                            }else{
                                   try{ 
                                      InputStream img=new FileInputStream(new File(ImgPath));
                                
                                         UpdateQuery="UPDATE emp_info SET name=?,sname=?,bdate=?,age=?,gender=?,depart=?,division=?,salary=? "+",contact=?,image=? WHERE eid=?";
                
                                        ps.setString(1, txt_name.getText());
                                        ps.setString(2, txt_sname.getText());
                                         SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                                         String addDate=dateFormat.format(txt_AddDate.getDate());
                                         ps.setString(3, addDate);
                    
                   
                                        ps.setInt(4, Integer.parseInt(txt_age.getText()));
                                         ps.setString(5, txt_gen.getText());
                                         ps.setString(6, txt_dep.getText());
                                          ps.setString(7, txt_divi.getText());
                                        ps.setString(8, txt_sal.getText());
                                         ps.setInt(9, Integer.parseInt(txt_con.getText()));
                
                                        ps.setBlob(10, img);
                                        ps.setInt(11, Integer.parseInt(txt_id.getText()));
                                       
                                        ps.executeUpdate();
                                         Show_employee();
                                        
                                        JOptionPane.showMessageDialog(null, "Product Updated");
                                     
                                     
                                   }catch(Exception ex){
                                       
                                     JOptionPane.showMessageDialog(null, ex.getMessage());
                                   
                                   
                                   
                                   
                                   }
                
                }
                
        
        
        
        
        
        }else{
            
            JOptionPane.showMessageDialog(null, "One or More Fields are Empty or Wrong");
            
        
        
        
        }
        
                
            //UpdateQuery = "UPDATE emp_info SET name=?,sname=?,bdate=?,age=?,gender=?,depart=?,division=?,salary=? "+",image=?,contact=? WHERE eid=?"; 
        
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_btn_updateActionPerformed

    private void jTable_empComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable_empComponentAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable_empComponentAdded

    private void txt_diviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diviActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_diviActionPerformed

    private void txt_depActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_depActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_depActionPerformed

    private void txt_genActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_genActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_genActionPerformed

    private void txt_ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ageActionPerformed

    private void jTable_empMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_empMouseClicked
        // TODO add your handling code here:
        
        
        int index=jTable_emp.getSelectedRow();
         ShowEmployee(index);
        
        
        
        
        
    }//GEN-LAST:event_jTable_empMouseClicked

    private void txt_salActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_salActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_salActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        String addDate= ((JTextField)txt_AddDate.getDateEditor().getUiComponent()).getText();
        //System.out.println(""+addDate);
        
        String dob[]=addDate.split("/");
       // System.out.println(""+dob[1]);
       
        int day=Integer.parseInt(dob[0]);
        int month=Integer.parseInt(dob[1]);
        int year=Integer.parseInt(dob[2]);
        
        LocalDate selectedDate=LocalDate.of(year, month, day);
        LocalDate currentDate=LocalDate.now();
        
        int resultYear=Period.between(selectedDate, currentDate).getYears();
        txt_calAge.setText(" "+resultYear);
        
        //String dateOfbirth=((txt_calAge)txt_AddDate.getDateEditor().getUiComponent()).getText();
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
             this.dispose();
             new Home().setVisible(true);
             
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
        
        
        
        
        
    }//GEN-LAST:event_txt_idActionPerformed

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

    private void txt_snameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_snameKeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        
        
        if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
            txt_sname.setEditable(true);
        
        }else{
            txt_sname.setEditable(false);
            JOptionPane.showMessageDialog(null, "Invalid character");
           
        
        }
    }//GEN-LAST:event_txt_snameKeyPressed

    private void txt_genKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_genKeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        
        
        if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
            txt_gen.setEditable(true);
        
        }else{
            txt_gen.setEditable(false);
            JOptionPane.showMessageDialog(null, "Invalid character");
           
        
        }
    }//GEN-LAST:event_txt_genKeyPressed

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

    private void txt_diviKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diviKeyPressed
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        
        
        if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
            txt_divi.setEditable(true);
        
        }else{
            txt_divi.setEditable(false);
            JOptionPane.showMessageDialog(null, "Invalid character");
           
        
        }
    }//GEN-LAST:event_txt_diviKeyPressed

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
            java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_Info1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_Info1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_choose;
    private javax.swing.JButton btn_clr;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_emp;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_AddDate;
    private javax.swing.JTextField txt_age;
    private javax.swing.JLabel txt_calAge;
    private javax.swing.JLabel txt_calAge1;
    private javax.swing.JLabel txt_calAge2;
    private javax.swing.JTextField txt_con;
    private javax.swing.JTextField txt_dep;
    private javax.swing.JTextField txt_divi;
    private javax.swing.JTextField txt_gen;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_sal;
    private javax.swing.JTextField txt_sname;
    // End of variables declaration//GEN-END:variables
}
