import java.net.*;
import java.io.*;

import org.json.*;


public class TcpClient {
    public Socket client = null;
    String serverAddr = "localhost";
    int serverPort = 8888;
    PrintWriter out;

    public TcpClient(String serverAddr, int serverPort) {
        this.serverAddr = serverAddr;
        this.serverPort = serverPort;
        //初始化socket
        try {
            client = new Socket(serverAddr, serverPort);
            out = new PrintWriter(client.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JSONObject action(Object o) {
        //转化成json字符串发送
        JSONObject reqobj = new JSONObject(o);
        String request = reqobj.toString();
        try {
            out.write(request);
            out.flush();
            client.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String response = null;
            while ((response = bReader.readLine()) != null) {
                JSONObject rspobj = new JSONObject(response);
                return rspobj;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (client != null)
                    client.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        return null;
    }

}
