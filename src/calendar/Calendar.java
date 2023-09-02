package calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.AbstractButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class Calendar extends JFrame {

    static final int R = 80, G = 80, B = 80;
    static JFrame frame;
    static JLabel monthlabel, datelabel;
    JPanel p1, p2, p3, p4, p5;
    static JButton[][] dayslabel = new JButton[6][7];
    static JLabel[][] gdayslabel = new JLabel[6][7];
    JLabel[] dayname = new JLabel[7];
    static int[] chake = new int[3];
    static JButton back, next;
    static int gday, gmonth, gyear;
    static int etday, etmonth, etyear;
    static int ethday, ethmonth, ethyear;
    static int redday,redmonth,redyear;
    static String month ="",storedate="0";
    static JComboBox box,box1; //used to store name of month
    static   String[] Mname = {"Null", "JANUARY", "February", "March", "April", "May", "June", "July", "Augest", "Ceptember", "October", "November", "December"};
    static JTextField tday, tmonth, tyear, tday1, tmonth1, tyear1;
    static JTextField tnameday, tnamemonth, tnameyear;
    static JTextField tgnameday, tgnamemonth, tgnameyear;
    JButton tcompute, tcompute1, tnamecompute,gmonthinnumber,tgnamecompute;
    JPanel tp1, tp2, tp3, tp4, tp5,tp6,tp7;
    static boolean tv1 = false, tv2 =false;
   // static int displaygmonth ;
    static ImageIcon icon ; 
    static String[][] holiday = new String[31][14];
    
    static int displayday,displaymonth,displayyear;
    static boolean tvdisplay =false;
    Calendar() {
        
        for(int i=0 ;i<=30;i++){
            for(int j=0; j<=13; j++){
                holiday[i][j] = "";
            }
        }
        setHoliday();
        icon = new ImageIcon(getClass().getResource("logo.png"));
        LocalDate currentdate = LocalDate.now();

        gday = currentdate.getDayOfMonth();
        Month month1 = currentdate.getMonth();
        gyear = currentdate.getYear();
        month = month1.toString();
        gmonth = indexOfMonth(month);

        redday = gday;
        redmonth = gmonth;
        redyear = gyear;    
        
        monthlabel = new JLabel("");
        monthlabel.setFont(new Font("Serif", 3, 26));

        datelabel = new JLabel("");
        datelabel.setFont(new Font("Serif", 3, 26));

        p1 = new JPanel(new GridLayout(1, 7));
        p1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
         
        dayname[0] = new JLabel("Monday");
        dayname[1] = new JLabel("Tuesday");
        dayname[2] = new JLabel("Wendsday");
        dayname[3] = new JLabel("Thursday");
        dayname[4] = new JLabel("Friday");
        dayname[5] = new JLabel("Saturday");
        dayname[6] = new JLabel("Sunday");

        for (int i = 0; i < 7; i++) {
            dayname[i].setHorizontalAlignment(JLabel.CENTER);
            dayname[i].setForeground(Color.BLACK);
            dayname[i].setFont(new Font("Serif",3,16));
            p1.add(dayname[i]);
        }

        JPanel panel = new JPanel();
        panel.add(monthlabel);
        panel.setBorder(new TitledBorder("Ethiopian month & year"));

        JPanel panel2 = new JPanel();
        panel2.add(datelabel);
        panel2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

        p5 = new JPanel(new GridLayout(2, 1));
        p5.add(panel2);
        p5.add(panel);
        p5.setBorder(new LineBorder(Color.GRAY));

        back = new JButton("<");
        back.setFont(new Font("Serif", Font.BOLD, 73));
        back.setBorder(new LineBorder(Color.BLACK));

        next = new JButton(">");
        next.setFont(new Font("Serif", Font.BOLD, 73));
        next.setBorder(new LineBorder(Color.BLACK));
        p2 = new JPanel(new BorderLayout());

        p3 = new JPanel(new GridLayout(6, 6));

        for (byte i = 0; i < 6; i++) {
            for (byte j = 0; j < 7; j++) {
                dayslabel[i][j] = new JButton("");
                dayslabel[i][j].setBackground(Color.WHITE);
                gdayslabel[i][j] = new JLabel("");

                dayslabel[i][j].add(gdayslabel[i][j]);
                dayslabel[i][j].setVisible(false);
                p3.add(dayslabel[i][j]);

            }
        }
        p2.add(p1, BorderLayout.NORTH);
        p2.add(p3, BorderLayout.CENTER);

        p4 = new JPanel(new BorderLayout());
        p4.add(back, BorderLayout.WEST);
        p4.add(p2, BorderLayout.CENTER);
        p4.add(next, BorderLayout.EAST);

        add(p5, BorderLayout.NORTH);
        add(p4, BorderLayout.CENTER);
        
        tp1 = new JPanel(new GridLayout(4, 2));
        tp1.setBorder(new TitledBorder("Change TO Ethiopian Calendar"));
        tp1.add(new JLabel("Day"));
        tday = new JTextField(10);
        tp1.add(tday);

        tp1.add(new JLabel("Month "));
        box = new JComboBox(Mname);
        box.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tmonth = new JTextField(10);
        tp1.add(box);
        
         box.addItemListener((ItemEvent e) -> {
             String s = "";
             if(box.getSelectedIndex() == 0) {
                 s = "";
             }else{
                 s = String.valueOf(box.getSelectedIndex());
             }
             
             tmonth.setText(s);
        });
       
        tp1.add(new JLabel("Year "));
        tyear = new JTextField(10);
        tp1.add(tyear);

        tp1.add(new JLabel(" "));
        tcompute = new JButton("compute");
        tp1.add(tcompute);

        
        
        tp7 = new JPanel(new GridLayout(4, 2));
        tp7.setBorder(new TitledBorder("Get name Using Gregorian Calendar"));
        tp7.add(new JLabel("Day"));
        tgnameday = new JTextField(10);
        tp7.add(tgnameday);

        tp7.add(new JLabel("Month "));
        box1 = new JComboBox(Mname);
        box1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tgnamemonth = new JTextField(10);
        tp7.add(box1);
        
        box1.addItemListener((ItemEvent e) -> {
             String s = "";
             if(box1.getSelectedIndex() == 0) {
                 s = "";
             }else{
                 s = String.valueOf(box1.getSelectedIndex());
             }
             
             tgnamemonth.setText(s);
        });
       
        tp7.add(new JLabel("Year "));
        tgnameyear = new JTextField(10);
        tp7.add(tgnameyear);

        tp7.add(new JLabel(" "));
        tgnamecompute = new JButton("compute");
        tp7.add(tgnamecompute);
        
        
        tp4 = new JPanel(new GridLayout(4, 2));
        tp4.setBorder(new TitledBorder("Change TO Gregorian Calendar"));
        tp4.add(new JLabel("Day"));
        tday1 = new JTextField(10);
        tp4.add(tday1);

        tp4.add(new JLabel("Month "));
        tmonth1 = new JTextField(10);
        tp4.add(tmonth1);

        tp4.add(new JLabel("Year "));
        tyear1 = new JTextField(10);
        tp4.add(tyear1);

        tp4.add(new JLabel(" "));
        tcompute1 = new JButton("compute");
        tp4.add(tcompute1);

        tp2 = new JPanel(new GridLayout(4, 2));
        tp2.setBorder(new TitledBorder("get Name of day by using Ethiopian Calendar "));
        tp2.add(new JLabel("Day "));
        tnameday = new JTextField(10);
        tp2.add(tnameday);

        tp2.add(new JLabel("Month "));
        tnamemonth = new JTextField(10);
        tp2.add(tnamemonth);

        tp2.add(new JLabel("Year "));
        tnameyear = new JTextField(10);
        tp2.add(tnameyear);

        tp2.add(new JLabel(" "));
        tnamecompute = new JButton("get name");
        tp2.add(tnamecompute);

        tp3 = new JPanel(new GridLayout(1, 2));

        tp3.add(tp1);
        tp3.add(tp4);
        tp3.add(tp2);
        tp3.add(tp7);
        
        gmonthinnumber = new JButton(" Get Month and thier Index");
      
        JPanel indexbutton = new JPanel(new FlowLayout(FlowLayout.LEFT,0,10));
        indexbutton.add(gmonthinnumber);
        indexbutton.setBorder(new LineBorder(Color.BLACK,1));
        indexbutton.setBackground(Color.WHITE);
  
        tp6 = new JPanel(new BorderLayout());
        tp6.add(indexbutton,BorderLayout.NORTH);
        tp6.add(tp3);

        add(p5, BorderLayout.NORTH);
        add(p4, BorderLayout.CENTER);
        add(tp6, BorderLayout.SOUTH);

        gmonthinnumber.addActionListener((ActionEvent e) -> { 
            JOptionPane.showMessageDialog(null, "NO  MONTH\n01    JANUARY\n02   February\n03   March\n04   April\n05   May\n06   June\n07   July\n"
                        + "08    Augest\n09   Ceptember\n10    October\n11   November\n12    December", "MONTH IN NUMBER", JOptionPane.PLAIN_MESSAGE);
        });
        
        displayDaysOfMonth(gday,gmonth,gyear);

        back.addActionListener((ActionEvent e) -> {
            previousMonth();
        });
        next.addActionListener((ActionEvent e) -> {
            nextMonth();
        });
        tnamecompute.addActionListener((ActionEvent e) -> {
            nameOfDay();
        });
        tcompute.addActionListener((ActionEvent e) -> {
            changeToEthiopia();
        });
        tgnamecompute.addActionListener((ActionEvent e) -> {
            getGName();
        });
        tcompute1.addActionListener((ActionEvent e) -> {
            changeToGregorian();
        });
        
        ButtonListener buttonlistener =  new ButtonListener();
        for (byte i = 0; i < 6; i++) {
            for (byte j = 0; j < 7; j++) {
                 dayslabel[i][j].addActionListener(buttonlistener);
            }
        }

    }
    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String getgday,getetday;
            for (byte i = 0; i < 6; i++) {
                for (byte j = 0; j < 7; j++) {
                     if(dayslabel[i][j] == event.getSource()){
                         getgday = gdayslabel[i][j].getText();
                         getetday = dayslabel[i][j].getText();
                         tvdisplay = true;
                        // System.out.println(dayslabel[i][j].getForeground().toString());
                         if(dayslabel[i][j].getForeground().equals(Color.GRAY)){
                             
                             int etmonthcopy = etmonth-1;
                             if(etmonthcopy<1){
                                 etmonthcopy = 13;
                                 if(etmonthcopy == 13 && Integer.parseInt(getetday)==30){
                                     etmonthcopy--;
                                   getGregorianDate(Integer.parseInt(getetday),etmonthcopy,etyear-1);
                                 JOptionPane.showMessageDialog(null, show1(nameOfDay(Integer.parseInt(getetday),etmonthcopy,etyear))+"\nGregorian Calendar   "+getgday+" - "+nameOfMonth(displaymonth)+" - "+displayyear+"\n"+
                                "Ethiopian  Calendar   "+getetday+" - "+etmonthcopy+" - "+(etyear-1)+"\n" + holiday[Integer.parseInt(getetday)][etmonthcopy]);
                        
                                 }else{
                                    getGregorianDate(Integer.parseInt(getetday),etmonthcopy,etyear-1);
                                 JOptionPane.showMessageDialog(null, show1(nameOfDay(Integer.parseInt(getetday),etmonthcopy,etyear))+"\nGregorian Calendar   "+getgday+" - "+nameOfMonth(displaymonth)+" - "+displayyear+"\n"+
                                "Ethiopian  Calendar   "+getetday+" - "+etmonthcopy+" - "+(etyear-1)+"\n" + holiday[Integer.parseInt(getetday)][etmonthcopy]);
                       
                                 }
                                 
                             }         
                             else{
                                  getGregorianDate(Integer.parseInt(getetday),etmonth-1,etyear);
                             JOptionPane.showMessageDialog(null, show1(nameOfDay(Integer.parseInt(getetday),etmonthcopy,etyear))+"\nGregorian Calendar   "+getgday+" - "+nameOfMonth(displaymonth)+" - "+displayyear+"\n"+
                                "Ethiopian  Calendar   "+getetday+" - "+etmonthcopy+" - "+etyear+"\n" + holiday[Integer.parseInt(getetday)][etmonthcopy]);
                             }
                         }else{
                             getGregorianDate(Integer.parseInt(getetday),etmonth,etyear);
                             JOptionPane.showMessageDialog(null, show1(nameOfDay(Integer.parseInt(getetday),etmonth,etyear))+"\nGregorian Calendar   "+getgday+" - "+nameOfMonth(displaymonth)+" - "+displayyear+"\n"+
                                "Ethiopian  Calendar   "+getetday+" - "+etmonth+" - "+etyear+"\n" + holiday[Integer.parseInt(getetday)][etmonth]);
                      
                         }
                            tvdisplay =false;
                         return;
                     }
                }
            }
        }
    }
    public static String nameOfMonth(int m) {
        String monthname = "";
        if(m<1)
            m=12;
        switch (m) {
            case 1:
                monthname = "January";
                break;
            case 2:
                monthname = "February";
                break;
            case 3:
                monthname = "March";
                break;
            case 4:
                monthname = "April";
                break;
            case 5:
                monthname = "May";
                break;
            case 6:
                monthname = "June";
                break;
            case 7:
                monthname = "July";
                break;
            case 8:
                monthname = "Augest";
                break;
            case 9:
                monthname = "Ceptember";
                break;
            case 10:
                monthname = "October";
                break;
            case 11:
                monthname = "November";
                break;
            case 12:
                monthname = "December";
                break;
        }
        return monthname;
    }
    public static void main(String[] args) {
        
        frame = new Calendar();

        frame.setSize(950, 730);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);

    }
    public static void setHoliday(){
        holiday[1][1] = "New Year";
        holiday[17][1] = "Meskel";
        holiday[29][4] = "Gena";
        holiday[11][5] = "Tmket";
        holiday[23][6] = "Adwa";
        holiday[20][9] = "Gnbot 20";
    }
    public static void changeToEthiopia() {
         
        boolean  valid = true;
        if (tday.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter day", "Warning", JOptionPane.ERROR_MESSAGE);
            tday.requestFocusInWindow();
        } else if (tmonth.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter month", "Warning", JOptionPane.ERROR_MESSAGE);
            tmonth.requestFocusInWindow();
        } else if (tyear.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter year", "Warrning", JOptionPane.ERROR_MESSAGE);
            tyear.requestFocusInWindow();
        } else {
            try {
                int iday,imonth,iyear;
                iday = Integer.parseInt(tday.getText());
                imonth = Integer.parseInt(tmonth.getText());
                iyear = Integer.parseInt(tyear.getText());
                
                if(iyear<1){
                    JOptionPane.showMessageDialog(null, "invalid year", "Warrning", JOptionPane.ERROR_MESSAGE);
                    tyear.requestFocusInWindow();  
                    valid = false;
                }else if((imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10 || imonth == 12)){
                        if(iday >0 && iday < 32){
                            changeToEthiopia1(iday,imonth,iyear);
                             
                        }else{
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
                            tday.requestFocusInWindow(); 
                            valid = false;
                        }
                }else if(imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11){
                        if(iday >0 && iday < 31){
                            changeToEthiopia1(iday,imonth,iyear);
                             
                        }else{
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
                            tday.requestFocusInWindow();
                            valid = false;
                        }
                }else if(imonth == 2 && (iyear % 400 == 0 || (iyear % 4 == 0 && iyear % 100 != 0))){
                        if(iday >0 && iday < 30){
                           changeToEthiopia1(iday,imonth,iyear);
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
                            tday.requestFocusInWindow(); 
                            valid = false;
                        }
                }else if(imonth == 2){
                        if(iday >0 && iday < 29){
                           changeToEthiopia1(iday,imonth,iyear);
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
                            tday.requestFocusInWindow();   
                            valid = false;
                        }
                }else{
                        JOptionPane.showMessageDialog(null, "invalid month", "Warrning", JOptionPane.ERROR_MESSAGE);
                        valid = false;
                    }
                //changeToEthiopia(Integer.parseInt(tday.getText()), Integer.parseInt(tmonth.getText()), Integer.parseInt(tyear.getText()));

                if(valid) {
                    JOptionPane.showMessageDialog(null, "Day    Month   Year\n" + ethday + "         " + ethmonth + "            " + ethyear, "ETHIOPIAN CALENDAR", JOptionPane.PLAIN_MESSAGE);

                    tday.setText("");
                    tmonth.setText("");
                    tyear.setText("");
                    box .setSelectedIndex(0);
                    
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "invalid input", "Warrning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void getGName() {
        
        if (tgnameday.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter day", "Warning", JOptionPane.ERROR_MESSAGE);
            tgnameday.requestFocusInWindow();
        } else if (tgnamemonth.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter month", "Warning", JOptionPane.ERROR_MESSAGE);
            tgnamemonth.requestFocusInWindow();
        } else if (tgnameyear.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter year", "Warrning", JOptionPane.ERROR_MESSAGE);
            tgnameyear.requestFocusInWindow();
        } else {
            try {
                
                int iday,imonth,iyear;
                iday = Integer.parseInt(tgnameday.getText());
                imonth = Integer.parseInt(tgnamemonth.getText());
                iyear = Integer.parseInt(tgnameyear.getText());
                
                if(iyear<1){
                    JOptionPane.showMessageDialog(null, "invalid year", "Warrning", JOptionPane.ERROR_MESSAGE);
                    tgnameyear.requestFocusInWindow();   
                }else if((imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10 || imonth == 12)){
                        if(iday >0 && iday < 32){
                            getGName(iday,imonth,iyear);
                        }else{
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
                            tgnameday.requestFocusInWindow();                
                        }
                }else if(imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11){
                        if(iday >0 && iday < 31){
                            getGName(iday,imonth,iyear);
                        }else{
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
                            tgnameday.requestFocusInWindow();                
                        }
                }else if(imonth == 2 && (iyear % 400 == 0 || (iyear % 4 == 0 && iyear % 100 != 0))){
                        if(iday >0 && iday < 30){
                           getGName(iday,imonth,iyear); 
                        }else{
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
                            tgnameday.requestFocusInWindow();                
                        }
                }else if(imonth == 2){
                        if(iday >0 && iday < 29){
                           getGName(iday,imonth,iyear); 
                        }else{
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
                            tgnameday.requestFocusInWindow();                
                        }
                }else{
                        JOptionPane.showMessageDialog(null, "invalid invalid month", "Warrning", JOptionPane.ERROR_MESSAGE);
                  
                    }
                 
              
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "invalid input", "Warrning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void getGName(int d,int m ,int y){
        int a1[] = {d, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int a2[] = {d, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int sum = 0;
        
        if(y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)){
           for(int i=0;i<m;i++){
               sum += a1[i];
           } 
        }else{
            for(int i=0;i<m;i++){
               sum += a2[i];
           } 
        }
                y--;
                y += sum + (y/4);
                showG(y%7);
                
                
                 
                    tgnameday.setText("");
                    tgnamemonth.setText("");
                    tgnameyear.setText("");
                    box1 .setSelectedIndex(0);
                   

    }
    public static void changeToGregorian() {
        tv1 = false;
        
        if (tday1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter day", "Warning", JOptionPane.ERROR_MESSAGE);
            tday1.requestFocusInWindow();
        } else if (tmonth1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter month", "Warning", JOptionPane.ERROR_MESSAGE);
            tmonth1.requestFocusInWindow();
        } else if (tyear1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter year", "Warrning", JOptionPane.ERROR_MESSAGE);
            tyear1.requestFocusInWindow();
        } else {
            try {
                //changeToEthiopia(Integer.parseInt(tday1.getText()),Integer.parseInt(tmonth1.getText()),Integer.parseInt(tyear1.getText()));
                int d,m,y,z;
                
                d = Integer.parseInt(tday1.getText());
                m = Integer.parseInt(tmonth1.getText());
                y = Integer.parseInt(tyear1.getText());
                
                z= y+1;
                if(y<1){
                    JOptionPane.showMessageDialog(null, "invalid year", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                }
                else if ((z % 400 == 0) || (z % 4 == 0 && z % 100 != 0)) {
                    if (m > 0 && m < 13) {
                        if (d > 0 && d < 31) {
                            tv1 = true;
                            getGregorianDate(d, m, y);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                        }
                    } else if (m == 13) {
                        if (d > 0 && d < 7) {
                            tv1 = true;
                            getGregorianDate(d, m, y);
                        } else {
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "invalid month", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                    }
                } else {
                    if (m > 0 && m < 13) {
                        if (d > 0 && d < 31) {
                            tv1 = true;
                            getGregorianDate(d, m, y);
                        } else {
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                        }
                    } else if (m == 13) {
                        if (d > 0 && d < 6) {
                            tv1 = true;
                            getGregorianDate(d, m, y);
                        } else {
                           JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                        }
                    } else {
                       JOptionPane.showMessageDialog(null, "invalid Month", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                    }
                }
                if (tv1) {
                    //JOptionPane.showMessageDialog(null, "Day    Month   Year\n" + ethday + "         " + ethmonth+ "            " + ethyear, "ETHIOPIAN CALENDAR", JOptionPane.PLAIN_MESSAGE);

                    tday1.setText("");
                    tmonth1.setText("");
                    tyear1.setText("");
                    tv1 = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "invalid input", "Warrning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void nameOfDay() {
      int name = 0;
        if (tnameday.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter day", "Warning", JOptionPane.ERROR_MESSAGE);
            tnameday.requestFocusInWindow();
        } else if (tnamemonth.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter month", "Warning", JOptionPane.ERROR_MESSAGE);
            tnamemonth.requestFocusInWindow();
        } else if (tnameyear.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "please enter year", "Warrning", JOptionPane.ERROR_MESSAGE);
            tnameyear.requestFocusInWindow();
        } else {
            try {
                
                
                int d,m,y,z;
                
                d = Integer.parseInt(tnameday.getText());
                m = Integer.parseInt(tnamemonth.getText());
                y = Integer.parseInt(tnameyear.getText());
                
                z= y+1;
                if(y<1){
                    JOptionPane.showMessageDialog(null, "invalid year", "Warrning", JOptionPane.ERROR_MESSAGE);
                }
                else if ((z % 400 == 0) || (z % 4 == 0 && z % 100 != 0)) {
                    if (m > 0 && m < 13) {
                        if (d > 0 && d < 31) {
                             name = nameOfDay(d, m, y);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                        }
                    } else if (m == 13) {
                        if (d > 0 && d < 7) {
                            name = nameOfDay(d, m, y);
                        } else {
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "invalid month", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                    }
                } else {
                    if (m > 0 && m < 13) {
                        if (d > 0 && d < 31) {
                            name = nameOfDay(d, m, y);
                        } else {
                            JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                        }
                    } else if (m == 13) {
                        if (d > 0 && d < 6) {
                            name = nameOfDay(d, m, y);
                        } else {
                           JOptionPane.showMessageDialog(null, "invalid day", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                        }
                    } else {
                       JOptionPane.showMessageDialog(null, "invalid Month", "Warrning", JOptionPane.ERROR_MESSAGE);
         
                    }
                }
                
                
                
                //int name = nameOfDay(Integer.parseInt(tnameday.getText()), Integer.parseInt(tnamemonth.getText()), Integer.parseInt(tnameyear.getText()));
                if (name != 0) {
                    show(name);
                    tnameday.setText("");
                    tnamemonth.setText("");
                    tnameyear.setText("");
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "invalid input", "Warrning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static String show(int c) {
        String dayname = "";
        switch (c) {
            case 6:
                dayname = "MONDAY";
                JOptionPane.showMessageDialog(null, "MONDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 7:
                dayname = "TUESDAY";
                JOptionPane.showMessageDialog(null, "TUESDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                dayname = "WEDNESDAY";
                JOptionPane.showMessageDialog(null, "WEDNESDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "THURSDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                dayname = "FRIDAY";
                JOptionPane.showMessageDialog(null, "FRIDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 4:
                dayname = "SATURDAY";
                JOptionPane.showMessageDialog(null, "SATURDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 5:
                dayname = "SUNDAY";
                JOptionPane.showMessageDialog(null, "SUNDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
        }

        return dayname;
    }
    public static String show1(int c) {
        String dayname = "";
        switch (c) {
            case 6:
                dayname = "MONDAY";
                break;
            case 7:
                dayname = "TUESDAY";
                break;
            case 1:
                dayname = "WEDNESDAY";
                break;
            case 2:
                 dayname = "THURSDAY";
                 break;
            case 3:
                dayname = "FRIDAY";
                break;
            case 4:
                dayname = "SATURDAY";
                break;
            case 5:
                dayname = "SUNDAY";
                break;
        }

        return dayname;
    }
    public static String showG(int c) {
        String dayname = "";
        switch (c) {
            case 2:
                dayname = "MONDAY";
                JOptionPane.showMessageDialog(null, "MONDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                dayname = "TUESDAY";
                JOptionPane.showMessageDialog(null, "TUESDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 4:
                dayname = "WEDNESDAY";
                JOptionPane.showMessageDialog(null, "WEDNESDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "THURSDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 6:
                dayname = "FRIDAY";
                JOptionPane.showMessageDialog(null, "FRIDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 0:
                dayname = "SATURDAY";
                JOptionPane.showMessageDialog(null, "SATURDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                dayname = "SUNDAY";
                JOptionPane.showMessageDialog(null, "SUNDAY", "", JOptionPane.INFORMATION_MESSAGE);
                break;
        }

        return dayname;
    }
    public static int indexOfMonth(String s) {
        int monthindex = 0;
        switch (s) {
            case "JANUARY":
                monthindex = 1;
                break;
            case "FEBRUARY":
                monthindex = 2;
                break;
            case "MARCH":
                monthindex = 3;
                break;
            case "APRIL":
                monthindex = 4;
                break;
            case "MAY":
                monthindex = 5;
                break;
            case "JUNE":
                monthindex = 6;
                break;
            case "JULY":
                monthindex = 7;
                break;
            case "AUGUST":
                monthindex = 8;
                break;
            case "SEPTEMBER":
                monthindex = 9;
                break;
            case "OCTOBER":
                monthindex = 10;
                break;
            case "NOVEMBER":
                monthindex = 11;
                break;
            case "DECEMBER":
                monthindex = 12;
                break;
        }
        return monthindex;
    }
    public static void displayDaysOfMonth(int gd, int gm, int gy) {
        changeToEthiopia(gd, gm, gy);
        alignDate(etday,etmonth,etyear); 
        datelabel.setText(storedate);
        getGregorianDate(1,etmonth,etyear);
        int firstday = nameOfDay(1, etmonth, etyear);
        getCalendar(getFirstDay(1, firstday));
        
    }
    public static void alignDate(int ed, int em, int ey) {
        if (String.valueOf(em).length() == 1 && String.valueOf(ed).length() == 1) {
            //storemonth = "0" + em + "-" + ey;
            if(String.valueOf(gday).length()== 1)
                storedate = "0" + gday + "-" + month + "-" + gyear + "    =>    " + "0" + ed + "-0" + em + "-" + ey;
            else
                storedate = "" + gday + "-" + month + "-" + gyear + "    =>    " + "0" + ed + "-0" + em + "-" + ey;
          

        } else if (String.valueOf(em).length() == 1) {
            //storemonth = "0" + em + "-" + ey;
            if(String.valueOf(gday).length()== 1)
                storedate = "0" + gday + "-" + month + "-" + gyear + "    =>    " + "" + ed + "-0" + em + "-" + ey;
            else
                storedate = "" + gday + "-" + month + "-" + gyear + "    =>    " + "" + ed + "-0" + em + "-" + ey;
          

        } else if (String.valueOf(ed).length() == 1) {
           // storemonth = "" + em + "-" + ey;
            if(String.valueOf(gday).length()== 1)
                storedate = "0" + gday + "-" + month + "-" + gyear + "    =>    " + "0" + ed + "-" + em + "-" + ey;
            else
                storedate = "" + gday + "-" + month + "-" + gyear + "    =>    " + "0" + ed + "-" + em + "-" + ey;
          

        } else {
           // storemonth = "" + em + "-" + ey;
           // storedate = "" + gday + "-" + gmonth + "-" + gyear + "    =>    " + "" + ed + "-" + em + "-" + ey;
             if(String.valueOf(gday).length()== 1)
                storedate = "0" + gday + "-" + month + "-" + gyear + "    =>    " + "" + ed + "-" + em + "-" + ey;
            else
                storedate = "" + gday + "-" + month + "-" + gyear + "    =>    " + "" + ed + "-" + em + "-" + ey;
          
        }

    }
    public static void getCalendar(int count) {
        
        displaymonth = gmonth;
        
            if(String.valueOf(etmonth).length() == 1)
                monthlabel.setText("0"+etmonth+"-"+etyear);
            else
                monthlabel.setText(etmonth+"-"+etyear);
                
            int pregday, preetday, nextgday, nextetday,lastmonth,redcounter =0;
            nextgday = gday;
            nextetday = 1;
            preetday = getNumberOfDayE(etmonth-1,etyear);
            
              
             
            pregday = gday - 1;
            if(pregday<1){
                pregday = getNumberOfDay(gmonth-1,gyear);
                 displaymonth--;
            }
            lastmonth = getNumberOfDay(gmonth,gyear);
            
            if(count != 7){//7+1%7 ==1 not subtract -7
                preetday -= count;
                pregday -= count;
            } 
            

             for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if ((i == 0) && j < ((count + 1) % 7)) {
                        
                        dayslabel[i][j].setVisible(true);
                        gdayslabel[i][j].setText("" + pregday);
                        gdayslabel[i][j].setForeground(Color.GRAY);
                        dayslabel[i][j].setVerticalAlignment(AbstractButton.BOTTOM);
                        dayslabel[i][j].setHorizontalAlignment(AbstractButton.RIGHT); 
                        if(preetday == 0)
                             dayslabel[i][j].setText("" + 30);
                        else
                              dayslabel[i][j].setText("" + preetday);
                        dayslabel[i][j].setForeground(Color.GRAY);
                        //checkForHoliday(i,j,preetday,etmonth,etyear);
                        pregday++;
                        preetday++;
                        
                        if(pregday>lastmonth){
                            pregday = getNumberOfDay(gmonth,gyear);
                            
                        }
                    }else{
                        dayslabel[i][j].setVisible(true);
                        dayslabel[i][j].setForeground(Color.BLACK);
                        gdayslabel[i][j].setText("" + nextgday);
                        gdayslabel[i][j].setForeground(Color.GRAY);
                        dayslabel[i][j].setVerticalAlignment(AbstractButton.BOTTOM);
                        dayslabel[i][j].setHorizontalAlignment(AbstractButton.RIGHT);
                        dayslabel[i][j].setText("" + nextetday);
                        //System.out.println(gmonth+":"+redmonth+":"+displaymonth);
                        if(redday == nextgday && redmonth == displaymonth && redyear == gyear){
                            if(redcounter<1)
                               dayslabel[i][j].setBorder(new LineBorder(Color.red,1));              
                            redcounter++;      
                        }
                        checkForHoliday(i,j,nextetday,etmonth,etyear);
                        nextgday++;
                        nextetday++;
                        
                        if(etmonth == 13){
                            if(nextetday > getNumberOfDayE(etmonth,etyear)){
                                return;
                            }
                        }else if(nextetday > 30){
                            return;
                        }
                        
                        if(nextgday > lastmonth){
                           
                           nextgday = 1; 
                           displaymonth++;
                        }
                    }
                }
            } 
            
          
        

    }
    public static void changeToEthiopia(int d, int m, int y) {

        int ed, ey, em;//e stands for Ethiopia
        em = 5;
        ed = 0;
        int sum = 0;
        ey = y - 8;

        int a1[] = {d, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int a2[] = {d, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if ((y % 400 == 0) || ((y % 4 == 0) && !(y % 100 == 0))) {
            if (((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (d > 0 && d < 32))
                    || ((m == 4 || m == 6 || m == 9 || m == 11) && (d > 0 && d <= 30)) || (m == 2 && (d > 0 && d < 30))) {
                for (int i = 0; i < m; i++) {
                    sum = sum + a1[i];
                }
                for (int i = 1; i <= sum; i++) {

                    if (i < 10) {
                        ed = 21 + i;
                        if (i > 1) {
                            continue;
                        }
                        em--;
                    } else {
                        ed++;
                        if (ed == 31) {
                            ed = 1;
                            em++;
                        }
                        int eyc = ey;
                        eyc++;
                        if (em == 13) {
                            if ((eyc % 400 == 0) || ((eyc % 4 == 0) && !(eyc % 100 == 0))) {
                                if (ed == 7) {
                                    ed = 1;
                                    em++;
                                }
                            } else {
                                if (ed == 6) {
                                    ed = 1;
                                    em++;
                                }
                            }
                        }
                        if (em > 13) {
                            if (em == 14) {
                                ey++;
                            }
                            em -= 13;
                        }
                    }
                }
                 
                    etday = ed;
                    etmonth = em;
                    etyear = ey;
               
                 
            } else {
                // JOptionPane.showMessageDialog(null, "INVALID DATE", "ETHIOPIAN CALENDER", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            if (((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (d > 0 && d < 32))
                    || ((m == 4 || m == 6 || m == 9 || m == 11) && (d > 0 && d <= 30)) || (m == 2 && (d > 0 && d < 29))) {
                for (int i = 0; i < m; i++) {
                    sum = sum + a2[i];
                }
                for (int i = 1; i <= sum; i++) {

                    if (i < 9) {
                        ed = 22 + i;
                        if (i > 1) {
                            continue;
                        }
                        em--;
                    } else {
                        ed++;
                        if (ed == 31) {
                            ed = 1;
                            em++;
                        }
                        int eyc = ey;
                        eyc++;
                        if (em == 13) {
                            if ((eyc % 400 == 0) || ((eyc % 4 == 0) && !(eyc % 100 == 0))) {
                                if (ed == 7) {
                                    ed = 1;
                                    em++;
                                }
                            } else {
                                if (ed == 6) {
                                    ed = 1;
                                    em++;
                                }
                            }
                        }
                        if (em > 13) {
                            if (em == 14) {
                                ey++;
                            }
                            em -= 13;
                        }
                    }
                }

                
                    etday = ed;
                    etmonth = em;
                    etyear = ey;
                
            } else {
                //JOptionPane.showMessageDialog(null, "INVALID DATE", "ETHIOPIAN CALENDER", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }
    public static void changeToEthiopia1(int d, int m, int y) {

        int ed, ey, em;//e stands for Ethiopia
        em = 5;
        ed = 0;
        int sum = 0;
        ey = y - 8;

        int a1[] = {d, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int a2[] = {d, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if ((y % 400 == 0) || ((y % 4 == 0) && !(y % 100 == 0))) {
            if (((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (d > 0 && d < 32))
                    || ((m == 4 || m == 6 || m == 9 || m == 11) && (d > 0 && d <= 30)) || (m == 2 && (d > 0 && d < 30))) {
                for (int i = 0; i < m; i++) {
                    sum = sum + a1[i];
                }
                for (int i = 1; i <= sum; i++) {

                    if (i < 10) {
                        ed = 21 + i;
                        if (i > 1) {
                            continue;
                        }
                        em--;
                    } else {
                        ed++;
                        if (ed == 31) {
                            ed = 1;
                            em++;
                        }
                        int eyc = ey;
                        eyc++;
                        if (em == 13) {
                            if ((eyc % 400 == 0) || ((eyc % 4 == 0) && !(eyc % 100 == 0))) {
                                if (ed == 7) {
                                    ed = 1;
                                    em++;
                                }
                            } else {
                                if (ed == 6) {
                                    ed = 1;
                                    em++;
                                }
                            }
                        }
                        if (em > 13) {
                            if (em == 14) {
                                ey++;
                            }
                            em -= 13;
                        }
                    }
                }
                
                   ethday = ed;
                   ethmonth = em;
                   ethyear = ey;
                 
                 
            } else {
                // JOptionPane.showMessageDialog(null, "INVALID DATE", "ETHIOPIAN CALENDER", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            if (((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (d > 0 && d < 32))
                    || ((m == 4 || m == 6 || m == 9 || m == 11) && (d > 0 && d <= 30)) || (m == 2 && (d > 0 && d < 29))) {
                for (int i = 0; i < m; i++) {
                    sum = sum + a2[i];
                }
                for (int i = 1; i <= sum; i++) {

                    if (i < 9) {
                        ed = 22 + i;
                        if (i > 1) {
                            continue;
                        }
                        em--;
                    } else {
                        ed++;
                        if (ed == 31) {
                            ed = 1;
                            em++;
                        }
                        int eyc = ey;
                        eyc++;
                        if (em == 13) {
                            if ((eyc % 400 == 0) || ((eyc % 4 == 0) && !(eyc % 100 == 0))) {
                                if (ed == 7) {
                                    ed = 1;
                                    em++;
                                }
                            } else {
                                if (ed == 6) {
                                    ed = 1;
                                    em++;
                                }
                            }
                        }
                        if (em > 13) {
                            if (em == 14) {
                                ey++;
                            }
                            em -= 13;
                        }
                    }
                }
                   ethday = ed;
                   ethmonth = em;
                   ethyear = ey;
               
            } else {
                //JOptionPane.showMessageDialog(null, "INVALID DATE", "ETHIOPIAN CALENDER", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }
    public static int nameOfDay(int d, int m, int y) {
        int leapcounter = 1, daycounter = 0, a, c = 0;
        if (y < 1) {
            JOptionPane.showMessageDialog(null, "Invallid date", "Warrning", JOptionPane.ERROR_MESSAGE);
        } else if (m < 1 || m > 13) {
            JOptionPane.showMessageDialog(null, "Invalid date", "Warrning", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 1; i < y; i++) {
                leapcounter++;
                if (leapcounter == 4) {
                    leapcounter = 0;
                    for (int j = 1; j <= 366; j++) {
                        daycounter++;
                        if (daycounter == 8) {
                            daycounter = 1;
                        }
                    }
                } else {
                    for (int j = 1; j <= 365; j++) {
                        daycounter++;
                        if (daycounter == 8) {
                            daycounter = 1;
                        }
                    }
                }
            }
            c = daycounter;

            if (d >= 1 && d <= 30) {
                switch (m) {
                    case 1:
                        a = d;
                        for (int i = 1; i <= a; i++) {
                            c++;
                            if (c == 8) {
                                c = 1;
                            }
                        }
                        return c;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        a = (((m - 1) * 30) + d);
                        for (int i = 1; i <= a; i++) {
                            c++;
                            if (c == 8) {
                                c = 1;
                            }
                        }
                        return c;
                    case 13:
                        if (leapcounter == 3 && d >= 1 && d <= 6) {
                            a = 360 + d;
                            for (int i = 1; i <= a; i++) {
                                c++;
                                if (c == 8) {
                                    c = 1;
                                }
                            }
                            return c;
                        } else if (!(leapcounter == 3) && d >= 1 && d <= 5) {
                            a = 360 + d;
                            for (int i = 1; i <= a; i++) {
                                c++;
                                if (c == 8) {
                                    c = 1;
                                }
                            }
                            return c;
                        }
                        break;
                    default:
                        break;
                }
            } else {
                //JOptionPane.showMessageDialog(null, "Invlid date", "Warning", JOptionPane.ERROR_MESSAGE);
            }

        }
        return c;
    }
    public static int getFirstDay(int d, int count) {
        for (int i = d - 1; i > 0; i--) {
            count--;
            if (count == 0) {
                count = 7;
            }
        }

        return count;
    }
    public static int getNumberOfDay(int m, int y) {
        int day = 0;
        
        if(m<1){
            m = 12;
            y--;
        }
        if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
            day = 31;
        } else if (m == 4 || m == 6 || m == 9 || m == 11) {
            day = 30;
        } else if (((y % 100 != 0 && y % 4 == 0) || (y % 400 == 0)) && m == 2) {
            day = 29;
        } else {
            day = 28;
        }
        return day;
    }
    public static int getNumberOfDayE(int m, int y) {
        int day = 0;
        if(m<1){
            m = 13;
            y--;
        }
        if (m >= 1 && m <= 12) {
            day = 30;
        } else if ((((y + 1) % 100 != 0 && (y + 1) % 4 == 0) || ((y + 1) % 400 == 0)) && m == 13) {
            day = 6;
        } else {
            day = 5;
        }
        return day;
    }
    public static void toGregorian(int d, int m, int y) {
        int ed, ey, em;//e stands for Ethiopia
        em = 5;
        ed = 0;
        int sum = 0;
        ey = y - 8;
        int a1[] = {d, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int a2[] = {d, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if ((y % 400 == 0) || ((y % 4 == 0) && !(y % 100 == 0))) {
            if (((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (d > 0 && d < 32))
                    || ((m == 4 || m == 6 || m == 9 || m == 11) && (d > 0 && d <= 30)) || (m == 2 && (d > 0 && d < 30))) {
                for (int i = 0; i < m; i++) {
                    sum = sum + a1[i];
                }
                for (int i = 1; i <= sum; i++) {

                    if (i < 10) {
                        ed = 21 + i;
                        if (i > 1) {
                            continue;
                        }
                        em--;
                    } else {
                        ed++;
                        if (ed == 31) {
                            ed = 1;
                            em++;
                        }
                        int eyc = ey;
                        eyc++;
                        if (em == 13) {
                            if ((eyc % 400 == 0) || ((eyc % 4 == 0) && !(eyc % 100 == 0))) {
                                if (ed == 7) {
                                    ed = 1;
                                    em++;
                                }
                            } else {
                                if (ed == 6) {
                                    ed = 1;
                                    em++;
                                }
                            }
                        }
                        if (em > 13) {
                            if (em == 14) {
                                ey++;
                            }
                            em -= 13;
                        }
                    }
                }

                //JOptionPane.showMessageDialog(null, "Day    Month   Year\n" + ed + "         " + em + "            " + ey, "ETHIOPIAN CALENDAR", JOptionPane.PLAIN_MESSAGE);
                chake[0] = ed;
                chake[1] = em;
                chake[2] = ey;
                //System.out.println(chake[0] + ":" + chake[1] + ":" + chake[2]);
            } else {
                //JOptionPane.showMessageDialog(null, "INVALID DATE", "ETHIOPIAN CALENDER", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            if (((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (d > 0 && d < 32))
                    || ((m == 4 || m == 6 || m == 9 || m == 11) && (d > 0 && d <= 30)) || (m == 2 && (d > 0 && d < 29))) {
                for (int i = 0; i < m; i++) {
                    sum = sum + a2[i];
                }
                for (int i = 1; i <= sum; i++) {

                    if (i < 9) {
                        ed = 22 + i;
                        if (i > 1) {
                            continue;
                        }
                        em--;
                    } else {
                        ed++;
                        if (ed == 31) {
                            ed = 1;
                            em++;
                        }
                        int eyc = ey;
                        eyc++;
                        if (em == 13) {
                            if ((eyc % 400 == 0) || ((eyc % 4 == 0) && !(eyc % 100 == 0))) {
                                if (ed == 7) {
                                    ed = 1;
                                    em++;
                                }
                            } else {
                                if (ed == 6) {
                                    ed = 1;
                                    em++;
                                }
                            }
                        }
                        if (em > 13) {
                            if (em == 14) {
                                ey++;
                            }
                            em -= 13;
                        }
                    }
                }

                //JOptionPane.showMessageDialog(null, "Day  Month Year\n" + ed + "     " + em + "      " + ey, "ETHIOPIAN CALENDER", JOptionPane.PLAIN_MESSAGE);
                chake[0] = ed;
                chake[1] = em;
                chake[2] = ey;
                //System.out.println(chake[0] + ":" + chake[1] + ":" + chake[2]);
            } else {
                //JOptionPane.showMessageDialog(null, "INVALID DATE", "ETHIOPIAN CALENDER", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }
    public static void getGregorianDate(int d, int m, int y) {
        int gd, gm, gy;

        gy = y + 7;
        gm = m + 8;
        gd = 4;

         for(int i=gy;i<=(y+9);i++){
             for(int j=1;j<13;j++){
                 for(int k=1;k<32;k++){
                     
                     toGregorian(k,j,i);
                     if(y==chake[2]&& m== chake[1] && d==chake[0]){
                         
                         if(tv1){
                             JOptionPane.showMessageDialog(null, "Day    Month   Year\n" + k + "         " + j + "            " + i, "GREGORIAN CALENDAR", JOptionPane.PLAIN_MESSAGE);
               
                         }else if(tvdisplay){
                             displayday = k;
                             displaymonth = j;
                             displayyear = i; 
                         }else{
                             gday = k;
                             gmonth = j;
                             gyear = i; 
                         }
                         //JOptionPane.showMessageDialog(null,i+":"+j+":"+k);
                        
                         return;
                     }
                 }
             }
         }
        
    }
    public static void clearButton() {
       for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {    
                dayslabel[i][j].setText("");
                dayslabel[i][j].setBackground(Color.WHITE);
                gdayslabel[i][j].setText("");
                dayslabel[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY)); 
                dayslabel[i][j].setVisible(false);      
            }
        }
    }
    public static void nextMonth() {
        etmonth++;
         
        if (etmonth > 13) {
            etmonth = 1;
            etyear++;
        }
        clearButton();
        getGregorianDate(1,etmonth,etyear);
        int firstday = nameOfDay(1, etmonth, etyear);
        getCalendar(getFirstDay(1, firstday));
    }
    public static void previousMonth() {
        etmonth--;
        if (etmonth < 1) {
            etmonth = 13;
            etyear--;
        }
        if (etyear == 8) {
            return;
        }
        clearButton();
        getGregorianDate(1,etmonth,etyear);
        int firstday = nameOfDay(1, etmonth, etyear);
        getCalendar(getFirstDay(1, firstday));
    } 
    public static void checkForHoliday(int i,int j,int d,int m,int y){
        
        for(int o=1;o<31;o++){
            for(int n=1;n<14;n++){
                if(holiday[o][n].length() != 0 && (d==o && m == n)){
                    dayslabel[i][j].setBackground(new Color(204,204,255)) ;
                    dayslabel[i][j].setForeground(Color.BLACK); 
                    return;
                }
            }
        }
         
    }
  
}
