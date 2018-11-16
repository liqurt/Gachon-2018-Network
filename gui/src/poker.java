import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class poker extends JFrame implements ActionListener {
   static JFrame f = new JFrame("Indian Poker");
   JTextField textField = new JTextField(25);
   JTextArea messageArea = new JTextArea(8, 25);
   BufferedReader in;
    PrintWriter out;
   
   public poker() throws IOException {
      Color back = new Color(70,0,21); // 배경색
      
      JFrame.setDefaultLookAndFeelDecorated(true);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      f.setSize(850,600);
      JPanel jp1 = new JPanel();
      JPanel jp2 = new JPanel();
      
      MyPanel mypanel =new MyPanel();
      
      // 패널 위, 아래 두개로 나눔 (jp1, jp2)
      f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
      jp1.setLayout(new BorderLayout());
      jp2.setLayout(new BorderLayout());
   
      
      // 위 패널
      JLabel Card1 = new JLabel("", SwingConstants.CENTER);
      Card1.setToolTipText("");
      Card1.setIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\gui\\\uB4B7\uC7A5.PNG"));
      Card1.setOpaque(true);
      Card1.setBackground(back);
      Card1.setPreferredSize(new Dimension(250, 400));
      
      JLabel Card2 = new JLabel("", JLabel.CENTER);
      Card2.setIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\gui\\7.PNG"));
      Card2.setOpaque(true);
      Card2.setBackground(back);
      Card2.setPreferredSize(new Dimension(250, 400));
      
      JLabel Chip = new JLabel("", JLabel.CENTER);
      Chip.setIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\gui\\\uCE69.PNG"));
      Chip.setOpaque(true);
      Chip.setBackground(back);
      
      // 위 패널 배치
      jp1.add(Card2, BorderLayout.EAST);
      jp1.add(Card1, BorderLayout.WEST);
      jp1.add(Chip, BorderLayout.CENTER);
      
      
      // 아래 패널
      JLabel Betting = new JLabel("Betting, Give up", JLabel.CENTER);
      Betting.setOpaque(true);
      Betting.setBackground(back);
      Betting.setPreferredSize(new Dimension(250, 200));
       
      JLabel Chatting = new JLabel("Chatting", JLabel.CENTER);
      Chatting.setOpaque(true);
      Chatting.setBackground(back);
      
      JLabel Timer = new JLabel("Timer", JLabel.CENTER);
      TimerThread3 th;
        
        getContentPane().setLayout(null);
        th=new TimerThread3(Timer);
        Timer.setBounds(400,330,150,50);
        Timer.setFont(new Font("Calibri", Font.BOLD, 50));
        //add(Timer);
        th.start();
        
        Timer.setOpaque(true);
        Timer.setBackground(back);
        Timer.setPreferredSize(new Dimension(250, 200));
        Timer.setForeground(Color.white);
       
      
      // 아래 패널 배치
      jp2.add(Timer,BorderLayout.EAST);
      jp2.add(Betting,BorderLayout.WEST);
      jp2.add(Chatting,BorderLayout.CENTER);
      
      
      // 채팅 레이아웃 설정
      Chatting.setLayout(new BorderLayout());
      textField.setForeground(Color.BLACK);
      
      textField.setEditable(false);
        messageArea.setForeground(Color.BLACK);
        messageArea.setEditable(false);
        
        // 채팅창 add
      Chatting.add(new JScrollPane(messageArea), BorderLayout.CENTER);
      Chatting.add(textField, BorderLayout.SOUTH);
      
      textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                out.println(textField.getText());
                textField.setText("");
            }
        });
        
      
      // panel 나누기
      JPanel spinner_window = new JPanel();
      JPanel betting_button_window = new JPanel();
      JPanel button_window = new JPanel();
      button_window.setBounds(0, 0, 250, 92);
      Betting.setLayout(new BoxLayout(Betting, BoxLayout.Y_AXIS));
      
      // 베팅 spinner
      SpinnerModel value = new SpinnerNumberModel(0, 0, 30, 1);
      spinner_window.setLayout(null);
      
      // 베팅 button
      JButton betting_button = new JButton("Betting");
      betting_button.setFont(new Font("Calibri", Font.PLAIN, 15));
      betting_button.setBounds(34, 12, 85, 33);
      betting_button.addActionListener(this);
      JButton giveup_button = new JButton("GiveUp");
      giveup_button.setFont(new Font("Calibri", Font.PLAIN, 15));
      giveup_button.setBounds(133, 12, 85, 33);
      giveup_button.addActionListener(this);
      
      //betting_button.setBounds(10, 10, 20, 20);
      //giveup_button.setBounds(30, 10, 20, 20);
      
      /*JLabel Bettings = new JLabel("Bettings", JLabel.CENTER);
      Bettings.setOpaque(true);
      Bettings.setBackground(Color.white);
      Bettings.setPreferredSize(new Dimension(200, 400));
      betting_button_window.add(Bettings);
      */
      betting_button_window.setLayout(null);
      
      button_window.setBackground(back);
      button_window.setLayout(null);
      button_window.add(betting_button);
      button_window.add(giveup_button);
      
      betting_button_window.add(button_window);
      Betting.add(spinner_window);
      
      JSpinner spinner = new JSpinner();
      spinner.setModel(new SpinnerNumberModel(0, 0, 50, 1));
      spinner.setFont(new Font("Calibri", Font.PLAIN, 22));
      spinner.setBounds(70, 32, 114, 49);
      spinner_window.add(spinner);
      //STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL;
      
      JLabel Spinners = new JLabel("Spinners", JLabel.CENTER);
      Spinners.setBounds(0, 0, 250, 93);
      Spinners.setOpaque(true);
      Spinners.setBackground(back);
      Spinners.setPreferredSize(new Dimension(200, 400));
      spinner_window.add(Spinners);
      
    /*  JTextPane textPane = new JTextPane();
      textPane.setForeground(Color.WHITE);
      textPane.setText("\uCE69 \uAC1C\uC218");
      textPane.setBounds(70, 0, 114, 31);
      spinner_window.add(textPane);
      
      JTextPane textPane_1 = new JTextPane();
      textPane_1.setText("\uCE69 \uAC1C\uC218");
      textPane_1.setBounds(83, 0, 86, 31);
      spinner_window.add(textPane_1);*/
      Betting.add(betting_button_window);
      
      f.getContentPane().add(jp1);
      
      JLabel title_label = new JLabel("Indian Poker", SwingConstants.CENTER);
      title_label.setFont(new Font("Calibri", Font.BOLD, 25));
      title_label.setBounds(335, 16, 164, 36);
      JPanel title = new JPanel();
      title.setBackground(back);
      title_label.setForeground(Color.white);
      title.setPreferredSize(new Dimension(850, 60));
      jp1.add(title, BorderLayout.NORTH);
      title.setLayout(null);
      title.add(title_label);
      
      JTextArea txtrMyRemainChip = new JTextArea();
      txtrMyRemainChip.setBackground(back);
      txtrMyRemainChip.setForeground(Color.WHITE);
      txtrMyRemainChip.setFont(new Font("바탕체", Font.BOLD, 14));
      txtrMyRemainChip.setWrapStyleWord(true);
      txtrMyRemainChip.setTabSize(1);
      txtrMyRemainChip.setLineWrap(true);
      txtrMyRemainChip.setText("     my remain chip : 50                                                     your remain chip : 30");
      jp1.add(txtrMyRemainChip, BorderLayout.SOUTH);
      f.getContentPane().add(jp2);

      JPanel panel = new JPanel();
      panel.setBackground(back);
      panel.setPreferredSize(new Dimension(850, 25));
      jp2.add(panel, BorderLayout.SOUTH);
      f.setVisible(true);
      
      run();
   }

   // 채팅 사용자 이름 입력
    public String getName() {
        return JOptionPane.showInputDialog(
            f, 
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }

      void run() throws IOException {
        Socket socket = new Socket("127.0.0.1", 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) 
        {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
      }
      
   public static void main(String[] arguments) throws Exception {
      Panel p = new Panel();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      
   }
   
   class TimerThread3 extends Thread {
      public int n; 
       JLabel timerLabel; 
       boolean isRun = true; 
       
       public TimerThread3(JLabel timerLabel){ 
          this.timerLabel = timerLabel; 
       } 
       
       public void run() {
          n = 30;
          
          while (n >= 0) {
             
             if(n < 10)
                timerLabel.setText("00:"+"0"+n);
             else
                timerLabel.setText("00:"+n);

             n--;
             timerLabel.repaint(); 
               
             try { 
                Thread.sleep(1000);
                       
                if (n == 100)
                   n = 0;
             } catch (InterruptedException e) { 
                      return; 
             }
               
             if(n==0)
           	  timerLabel.repaint(); 
            	 timerLabel.setText("TIME OVER");
                 
          }
       }
   }
}