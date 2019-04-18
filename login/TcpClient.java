package login;
import java.net.*;
import java.io.*;

import com.alibaba.fastjson.*;


public class TcpClient {
    public Socket client = null;
    String serverAddr ;
    int serverPort ;
    PrintWriter out;

    public TcpClient(String serverAddr, int serverPort) {
        this.serverAddr = "192.168.43.90";
        this.serverPort = serverPort;
        //初始化socket
        try {
            //InetAddress inter=InetAddress.getByName(serverAddr);
            client = new Socket(this.serverAddr, this.serverPort);
            out = new PrintWriter(client.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JSONObject action(Object o) {
        //转化成json字符串发送

        String request = JSON.toJSONString(o);
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
                JSONObject rspobj = JSON.parseObject(response);
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
