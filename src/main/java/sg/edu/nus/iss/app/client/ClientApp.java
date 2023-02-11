package sg.edu.nus.iss.app.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ClientApp {
    public static void main(String[] args){
        String[] arrSplit = args[0].split(":");
        System.out.println(arrSplit[0]);
        System.out.println(arrSplit[1]);
        try{
            while(true){
                //Socket(address, port_no) address is localhost, port is self-assigned
                Socket sock = new Socket(arrSplit[0]
                    , Integer.parseInt(arrSplit[1]));
            
                InputStream is = sock.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                OutputStream os = sock.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                
                Console cons = System.console();
                String input = 
                        cons.readLine("Send first list of 5 numbers: ");

                dos.writeUTF(input);
                dos.flush();

                String input2 = 
                cons.readLine("Send second list of 5 numbers: ");

                dos.writeUTF(input2);
                dos.flush();

                String response = dis.readUTF();
                if(response.contains("Result is")){
                    String[] numberValArr = response.split("_");
                    System.out.printf("The server returned %s\n",numberValArr[1]);

                }else{
                    System.err.println(response);
                }

                is.close();
                os.close();
                sock.close();
            }
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}