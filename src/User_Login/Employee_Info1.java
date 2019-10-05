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
        btn_report = new javax.swing.JButton();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Employee ID:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("SurName:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Date of Birth:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Age:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Gender:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Department:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Division:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Basic Salary:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Contact:");

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

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

        txt_sname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_snameKeyPressed(evt);
            }
        });

        txt_age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ageActionPerformed(evt);
            }
        });

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

        txt_sal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_salActionPerformed(evt);
            }
        });

        btn_update.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\edit-icon.png")); // NOI18N
        btn_update.setText("Update");
        btn_update.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_save.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\Save-icon.png")); // NOI18N
        btn_save.setText("Save");
        btn_save.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_delete.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\Misc-Delete-Database-icon.png")); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_print.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\print-icon.png")); // NOI18N
        btn_print.setText("Print");
        btn_print.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        btn_clr.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\Actions-edit-clear-list-icon.png")); // NOI18N
        btn_clr.setText("Clear");
        btn_clr.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_clr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clrActionPerformed(evt);
            }
        });

        btn_report.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\Reports-icon.png")); // NOI18N
        btn_report.setText("Report");
        btn_report.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reportActionPerformed(evt);
            }
        });

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

        lbl_image.setBackground(new java.awt.Color(255, 255, 255));
        lbl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_choose.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\attach-2-icon.png")); // NOI18N
        btn_choose.setText("Attach");
        btn_choose.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chooseActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\icons\\Apps-Dialog-Logout-icon.png")); // NOI18N
        jButton7.setText("LogOut");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 153, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Desktop\\ITP\\biofood logo.png")); // NOI18N

        jButton2.setBackground(new java.awt.Color(102, 255, 102));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Age:");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txt_calAge.setText("0");
        txt_calAge.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_calAge1.setText("0");
        txt_calAge1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_calAge2.setText("0");
        txt_calAge2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5))
                            .addGap(35, 35, 35)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_name)
                    .addComponent(txt_sname)
                    .addComponent(txt_AddDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_age)
                    .addComponent(txt_divi)
                    .addComponent(txt_sal)
                    .addComponent(txt_dep)
                    .addComponent(txt_con)
                    .addComponent(txt_gen)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txt_calAge, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_calAge1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_calAge2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_clr, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_report, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_sname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addGap(247, 247, 247))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                                    .addComponent(txt_calAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_calAge1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_calAge2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(30, 30, 30)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txt_age, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5))
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn_clr, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(21, 21, 21)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txt_gen, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(txt_dep, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txt_divi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(23, 23, 23)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txt_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txt_con, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel10)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(43, 43, 43)
                                                        .addComponent(btn_report, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(28, 28, 28)
                                                        .addComponent(jLabel8))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(26, 26, 26)
                                                        .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addContainerGap(55, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1)
                                .addGap(18, 18, 18)))
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btn_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_reportActionPerformed

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
    private javax.swing.JButton btn_report;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
