import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
 
public class Panel extends JFrame implements ActionListener {
	static JFrame f = new JFrame("Indian Poker");
	JTextField textField = new JTextField(30);
	JTextArea messageArea = new JTextArea(8, 30);
	BufferedReader in;
    PrintWriter out;
	
	public Panel() throws IOException {
		Color back = new Color(70,0,21); // ����
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setSize(850,600);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		
		// �г� ��, �Ʒ� �ΰ��� ���� (jp1, jp2)
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		jp1.setLayout(new BorderLayout());
		jp2.setLayout(new BorderLayout());
	
		
		// �� �г�
		JLabel Card1 = new JLabel("Card1", JLabel.CENTER);
		Card1.setOpaque(true);
		Card1.setBackground(back);
		Card1.setPreferredSize(new Dimension(200, 400));
		
		JLabel Card2 = new JLabel("Card2", JLabel.CENTER);
		Card2.setOpaque(true);
		Card2.setBackground(back);
		Card2.setPreferredSize(new Dimension(200, 400));
		
		JLabel Chip = new JLabel("Chip", JLabel.CENTER);
		Chip.setOpaque(true);
		Chip.setBackground(back);
		
		
		// �� �г� ��ġ
		jp1.add(Card2, BorderLayout.EAST);
		jp1.add(Card1, BorderLayout.WEST);
		jp1.add(Chip, BorderLayout.CENTER);
		
		
		// �Ʒ� �г�
		JLabel Betting = new JLabel("Betting, Give up", JLabel.CENTER);
		Betting.setOpaque(true);
		Betting.setBackground(back);
		Betting.setPreferredSize(new Dimension(200, 200));
		 
		JLabel Chatting = new JLabel("Chatting", JLabel.CENTER);
		Chatting.setOpaque(true);
		Chatting.setBackground(back);
		
		JLabel Timer = new JLabel("Timer", JLabel.CENTER);
		Timer.setOpaque(true);
		Timer.setBackground(back);
		Timer.setPreferredSize(new Dimension(200, 200));
		 
		
		// �Ʒ� �г� ��ġ
		jp2.add(Timer,BorderLayout.EAST);
		jp2.add(Betting,BorderLayout.WEST);
		jp2.add(Chatting,BorderLayout.CENTER);
		
		
		// ä�� ���̾ƿ� ����
		Chatting.setLayout(new BorderLayout());
		
		textField.setEditable(false);
        messageArea.setEditable(false);
        
        // ä��â add
		Chatting.add(new JScrollPane(messageArea), BorderLayout.CENTER);
		Chatting.add(textField, BorderLayout.SOUTH);
		
		textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                out.println(textField.getText());
                textField.setText("");
            }
        });
        
		
		// panel ������
		JPanel spinner_window = new JPanel();
		JPanel betting_button_window = new JPanel();
		JPanel button_window = new JPanel();
		Betting.setLayout(new BoxLayout(Betting, BoxLayout.Y_AXIS));
		spinner_window.setLayout(new BorderLayout());
		betting_button_window.setLayout(new BorderLayout());
		
		button_window.setLayout(new FlowLayout());
		
		// ���� spinner
		SpinnerModel value = new SpinnerNumberModel(0, 0, 30, 1);
		JSpinner js = new JSpinner(value);
		js.setBounds(50, 25, 100, 45);
		//STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL;
		
		JLabel Spinners = new JLabel("Spinners", JLabel.CENTER);
		Spinners.setOpaque(true);
		Spinners.setBackground(back);
		Spinners.setPreferredSize(new Dimension(200, 400));
		Spinners.add(js);
		spinner_window.add(Spinners, BorderLayout.CENTER);
		
		// ���� button
		JButton betting_button = new JButton("Betting");
		betting_button.addActionListener(this);
		JButton giveup_button = new JButton("Give Up");
		giveup_button.addActionListener(this);
		
		//betting_button.setBounds(10, 10, 20, 20);
		//giveup_button.setBounds(30, 10, 20, 20);
		
		/*JLabel Bettings = new JLabel("Bettings", JLabel.CENTER);
		Bettings.setOpaque(true);
		Bettings.setBackground(Color.white);
		Bettings.setPreferredSize(new Dimension(200, 400));
		betting_button_window.add(Bettings);
		*/
		
		button_window.setBackground(back);
		button_window.add(betting_button);
		button_window.add(giveup_button);
		
		betting_button_window.add(button_window, BorderLayout.CENTER);
		Betting.add(spinner_window);
		Betting.add(betting_button_window);
		
		f.add(jp1);
		f.add(jp2);
		f.setVisible(true);
		
		run();
	}

	// ä�� ����� �̸� �Է�
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
}
