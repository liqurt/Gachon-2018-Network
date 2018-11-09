import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 9001;
    private static HashMap<String, PrintWriter>names=new HashMap<String, PrintWriter>();
    
   
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }
        
        public String getName(String name) 
        {
        	return this.name;
        }
        
        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Request a name from this client.  Keep requesting until
                // a name is submitted that is not already used.  Note that
                // checking for the existence of a name and adding the name
                // must be done while locking the set of names.
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                     }
                    
                    synchronized (names) {
                        if (!names.containsKey(name)) {
                            
                        	
                        	/*Print new user info in chat room except new user's chat room*/
                        	for (PrintWriter writer : names.values()) 
                        	{
                        		writer.println("MESSAGE " + "<Entrance new user: "+name+">");
                        	}
     
                        	names.put(name,out);
                            break;
                        }
                    }
                }

                // Now that a successful name has been chosen, add the
                // socket's print writer to the set of all writers so
                // this client can receive broadcast messages.
                out.println("NAMEACCEPTED");
           
                names.put(name, out);

                // Accept messages from this client and broadcast them.
                // Ignore other clients that cannot be broadcasted to.
                // then if it is match whisper format then send distinct user, not all
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    int i=1;
                    /*check the whisper format
                     * if match is whisper format(<username/>) then send to  user have this username
                     * assumption the username is already exit
                     * it is not match, then consider it is just message, so boradcast all user. 
                     * */
                    if(input.startsWith("<")) 
                    {
                    	//String whisper="";
                    	for(i=1;i<input.length();i++) 
                    	{
                    		if(input.charAt(i)=='/') 
                    		{
                    			if(input.charAt(i+1)=='>') 
                    			{
                    				
                    				break;
                    			}
                    		}
                    		//whisper+=input.charAt(i);
                    	}
                    	
                    	/*it is check it is match
                    	 * */
                    	if(input.charAt(i)=='/'&&input.charAt(i+1)=='>') 
                    	{
                    		input=in.readLine();
                    		/*for(HashMap.Entry<String,PrintWriter> entry : names.entrySet())
                    		{
                    			if(entry.getKey().equals(whisper))
                    			{
                    				entry.getValue().println("MESSAGE "+"Whisper from "+name+ ": "+input);
                    			}
                    		}*/
                    	
                    	}
                    	/*if the message is start with'<'but it is not match format, so consider just message then broadcast all user
                    	 * */
                    	else 
                    	{
                    		for (PrintWriter writer : names.values()) 
                        	{
                        		writer.println("MESSAGE " +name+": "+input);
                        	}		
                    	}
                    }
                    /*braodcast message all user*/
                    else
                    {
                    	for (PrintWriter writer : names.values()) 
                	{
                		writer.println("MESSAGE " +name+": "+input);
                	}		
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    names.remove(out);
                    
                    /*The exit user info(name) broadcast all user.
                     * */
                    for (PrintWriter writer : names.values()) 
                	{
                		writer.println("MESSAGE " + "<EXIT : "+name+">");
                	}
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}