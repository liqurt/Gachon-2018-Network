
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class timer extends JPanel{
	 JLabel label=new JLabel("30초");
     TimerThread3 th;
	
	public timer()
	{
	   setLayout(null);
	   th=new TimerThread3(label);
	   label.setBounds(400,330,150,50);
	   label.setFont(new Font("궁서",Font.BOLD,33));
	   add(label);
	   th.start();
	}
	
}

class TimerThread3 extends Thread
{
	public int n; 
	 JLabel timerLabel; 
	    boolean isRun = true; 
	    public TimerThread3(JLabel timerLabel) 
	    { 
	        this.timerLabel = timerLabel; 
	    } 
	 
	    public void run() 
	    {  n=12;
	        while (n>=0) 
	        {   if(n<10)
	            { 
	        	timerLabel.setText("00:"+"0"+n);
	            }
	        else
	        {
	        	timerLabel.setText("00:"+n);
	        	
	        }
	            
	            n--; 
	            timerLabel.repaint(); 
	            try 
	            { 
	                Thread.sleep(1000);//밀리세컨드                 
	                 
	                if (n == 100) 
	                { 
	                    n = 0; 
	                } 
	            } catch (InterruptedException e) 
	            { 
	                return; 
	            } 
	            
	            	
	        } 
	    }}
	    

