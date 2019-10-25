package TCP_Client;

import android.os.AsyncTask;
import android.util.Log;

public class ConnectTask extends AsyncTask<Object, String, TcpClient> {

    @Override
    protected TcpClient doInBackground(Object... obj) {
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
