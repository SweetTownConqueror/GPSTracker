package TCP_Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;


public class Tcp_Client {
    DataOutputStream out;
    InputStreamReader streamReader;
    Socket s;
    public Tcp_Client() {
        System.out.println("coucou de tcp_cient depuis gps_sevice");
        //connectSocket();
        //sendMessage("gps_location:depuismatabletttroppcool!!!");

    }

    public void connectSocket(){
        System.out.println("coucou de tcp_client000");
        try{
            // Creating new socket connection to the IP (first parameter) and its opened port (second parameter)
            s = new Socket("192.168.1.23", 6789);
            //s = new Socket("159.89.214.31", 2000);

        } catch(Exception ex){
            //:TODO Handle exceptions
            ex.printStackTrace();
            System.out.println("exceptionpbb: "+ex.toString());
        }
        this.setSocket(s);
    }

    private void setSocket(Socket _s) {
        this.s = _s;
    }

    public void sendMessage(String message, Socket _s){
        System.out.println("testdebugalex0");
        try {
            streamReader = new InputStreamReader(_s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Initialize output stream to write message to the socket stream
        //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        //PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
        try {
            out = new DataOutputStream(_s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("testdebugalex1");
        BufferedReader reader = new BufferedReader(streamReader);
        System.out.println("testdebugalex2");

        System.out.println("testdebugalex3");
        //reader.close();
        //System.out.println("server first response: "+advice);
        //Thread.sleep(1000) ;
        //Thread.sleep(1000) ;
        System.out.println("coucou de tcp_client");
        String outMsg = "";

        outMsg = message;
        //outMsg = "This is Client";

        // Write message to stream
        try {
            out.writeBytes(outMsg);
        } catch (IOException e) {
            try {
                _s.close();
                System.out.println("socket just closed and isconnected= "+_s.isClosed());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the output stream

        //a utiliser lorsque lon souhaite terminer la co
        /*try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // Flush the data from the stream to indicate end of message


        // Close the socket connection

        System.out.println("testdebugalex4");
    }

    public Socket getSocket() {
        return this.s;
    }
}
