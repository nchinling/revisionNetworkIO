package sg.edu.nus.iss.app.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import static sg.edu.nus.iss.app.server.Calculator.*;

public class ServerApp {
    private static final String ARG_MESSAGE = 
        "usage: java sg.edu.nus.iss.app.server.ServerApp " 
        + " <port no>" + " <result file>";
    public static void main( String[] args )
    {
        Socket sock = null;
        InputStream is = null;
        OutputStream os = null;

        try{
            // Get server port from java cli first argument
            String serverPort = args[0];
            System.out.println(serverPort);
            // Name the result file with second arg
            String resultFile = args[1];
            System.out.println(resultFile);

            System.out.printf("Cookie Server started at %s\n"
                    , serverPort);
            // Instantiate server socket object pass in the server
            ServerSocket server = 
                    new ServerSocket(Integer.parseInt(serverPort)); 
            while(true){
                // Client connect to the server ... this is the line where 
                // the server accept a connection from the client
                sock = server.accept(); 
                
                // Get the input data from the client program in byte
                is = sock.getInputStream();
                DataInputStream dis= new DataInputStream(is);

                // Write and response
                os = sock.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                // reading data stream assign to a variable where data is String
                while(true){
                    try{
                        String dataFromClient = dis.readUTF();
                        
                        if(dataFromClient.equals("get-numbers")){
                            String result = getCalculator(dataFromClient);
                            dos.writeUTF("Result is " + result);
                        }else{
                            dos.writeUTF("Invalid command !");
                        }
                    }catch (EOFException e) {
                        System.out.println("Client disconnected");
                        sock.close();
                        break;
                    }   
                }
            }
        
        }catch(ArrayIndexOutOfBoundsException e){
            // validate arguments array must be more than 1 arg value
            System.out.println(ARG_MESSAGE);
            System.exit(1);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
