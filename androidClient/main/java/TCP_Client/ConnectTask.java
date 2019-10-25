package TCP_Client;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;

public class ConnectTask extends AsyncTask<Object, String, TcpClient> {

    @Override
    protected TcpClient doInBackground(Object... obj) {
/*
        //we create a TCPClient object
        TcpClient mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
            @Override
            //here the messageReceived method is implemented
            public void messageReceived(String message) {
                //this method calls the onProgressUpdate
                //message = "gps_location:lance depuis tablette!!!";
                publishProgress(message);
            }
        });

        //mTcpClient.sendMessage("gps_location:123depuis tablette ca tue la mort qui tue");
        mTcpClient.run();*/

//Tcp_Client t = new Tcp_Client();
        String message = (String) obj[0];
        Tcp_Client tcp_cli= (Tcp_Client) obj[1];
        if(tcp_cli.getSocket() != null){
            System.out.println("socket pas nulle");
            if(tcp_cli.getSocket().isClosed() == false){
                System.out.println("socket co");
                tcp_cli.sendMessage(message, tcp_cli.getSocket());
            }else{
                System.out.println("socket pas co");
                tcp_cli.connectSocket();

            }
        }else{
            System.out.println("socket nulle");
            tcp_cli.connectSocket();
        }
        System.out.println("coucou from connect task->doingbg");
        System.out.println("message pass to doingbackground: "+message);
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        //response received from server
        Log.d("test", "response " + values[0]);
        //process server response here....

    }
}
