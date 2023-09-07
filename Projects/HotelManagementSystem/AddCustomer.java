import javax.swing.*;
import java.awt.*;
//import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.ImageGraphicAttribute;
import java.sql.ResultSet;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener {

    JButton add,back;
    JComboBox comboid;
    JTextField tfname,tfnumber,tfcountry,tfdeposite;
    JRadioButton rmale,rfemale;
    Choice croom;
    JLabel checkintime;

    AddCustomer(){
        JLabel text=new JLabel("NEW CUSTOMER FORM");
        text.setBounds(100,20,300,30);
        text.setFont(new Font("Raleway",Font.PLAIN,20));
        add(text);

        JLabel lbid=new JLabel("ID");
        lbid.setBounds(35,80,100,20);
        lbid.setFont(new Font("Raleway",Font.PLAIN,20));
        add(lbid);

        String options[]={"Adhar Card","Passport","Rashon Card"};
         comboid=new JComboBox(options);
        comboid.setBounds(200,80,150,25);
        comboid.setBackground(Color.white);
        add(comboid);

        JLabel lbnumber=new JLabel("Number");
        lbnumber.setBounds(35,120,100,20);
        lbnumber.setFont(new Font("Raleway",Font.PLAIN,20));
        add(lbnumber);

         tfnumber=new JTextField();
        tfnumber.setBounds(200,120,150,25);
        add(tfnumber);

        JLabel lbname=new JLabel("Name");
        lbname.setBounds(35,160,100,20);
        lbname.setFont(new Font("Raleway",Font.PLAIN,20));
        add(lbname);

         tfname=new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);

        JLabel lbgender=new JLabel("Gender");
        lbgender.setBounds(35,200,100,20);
        lbgender.setFont(new Font("Raleway",Font.PLAIN,20));
        add(lbgender);

         rmale=new JRadioButton("Male");
        rmale.setBackground(Color.white);
        rmale.setBounds(200,200,60,25);
        add(rmale);

         rfemale=new JRadioButton("Female");
        rfemale.setBackground(Color.white);
        rfemale.setBounds(270,200,100,25);
        add(rfemale);

        ButtonGroup bg=new ButtonGroup();   //this is for selecting only one value
        bg.add(rmale);
        bg.add(rfemale);

        JLabel lbcountry=new JLabel("Country");
        lbcountry.setBounds(35,240,100,25);
        lbcountry.setFont(new Font("Raleway",Font.PLAIN,20));
        add(lbcountry);

         tfcountry=new JTextField();
        tfcountry.setBounds(200,240,150,25);
        add(tfcountry);

        JLabel lbroom=new JLabel("Allocated Room ");
        lbroom.setBounds(35,280,150,25);
        lbroom.setFont(new Font("Raleway",Font.PLAIN,20));
        add(lbroom);

         croom=new Choice();
        try {
            Conn conn=new Conn();
            String query="select * from hotelmanagementsystem.room where availability='Available'";
            ResultSet rs=conn.st.executeQuery(query);
            while(rs.next()){
                croom.add(rs.getString("roomnumber"));
            }

        }catch (Exception e){
            System.out.println(e);
        }
        croom.setBounds(200,280,150,25);
        add(croom);

        JLabel lbtime=new JLabel("Check In Time");
        lbtime.setBounds(35,320,150,25);
        lbtime.setFont(new Font("Raleway",Font.PLAIN,20));
        add(lbtime);

        Date date=new Date();

         checkintime=new JLabel(" " + date);
        checkintime.setBounds(200,320,150,25);
        checkintime.setFont(new Font("Raleway",Font.PLAIN,20));
        add(checkintime);

        JLabel lbdeposite=new JLabel("Deposite");
        lbdeposite.setBounds(35,360,100,25);
        lbdeposite.setFont(new Font("Raleway",Font.PLAIN,20));
        add(lbdeposite);

         tfdeposite=new JTextField();
        tfdeposite.setBounds(200,360,150,25);
        add(tfdeposite);

        add=new JButton("ADD");
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.setBounds(50,410,120,30);
        add.addActionListener(this);
        add(add);

         back=new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(200,410,120,30);
        back.addActionListener(this);
        add(back);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2=i1.getImage().getScaledInstance(300,400,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,50,300,400);
        add(image);

        setLayout(null);
        setBounds(250,150,800,550);
        getContentPane().setBackground(Color.white);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==add){
            String combo=(String) comboid.getSelectedItem();
            String number=tfnumber.getText();
            String name=tfname.getText();
            String gender;
            if (rmale.isSelected()){
                gender= "Male";
            }else {
                gender="Female";
            }
            String country=tfcountry.getText();
            String room=croom.getSelectedItem();
            String time=checkintime.getText();
            String deposite = tfdeposite.getText();

            try {
                Conn conn=new Conn();
                String query1="insert into hotelmanagementsystem.customer values('"+combo+"','"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+time+"','"+deposite+"')";
                String query2="update hotelmanagementsystem.room set availability ='Occupied' where roomnumber='"+room+"'";

                conn.st.executeUpdate(query1);
                conn.st.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,"Customer Added Succesfully ");
                setVisible(false);

            }catch (Exception ce){
                System.out.println(ce);
            }


        } else if (e.getSource()==back) {
            new Reception();
            setVisible(false);

        }

    }

    public static void main(String[] args) {
        new AddCustomer();
    }


}
