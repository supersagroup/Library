package bms;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.alibaba.fastjson.*;

/**
 * A simple server socket listener that listens to port number 8888, and prints
 * whatever received to the console. It starts a thread for each connection to
 * perform IO operations.
 */
public class Server {

    ServerSocket server;
    int serverPort = 8080;

    // Constructor to allocate a ServerSocket listening at the given port.
    public Server() {
        try {
            server = new ServerSocket(serverPort);
            System.out.println("ServerSocket: " + server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Start listening.
    private void listen() {
        while (true) { // run until you terminate the program
            try {
                // Wait for connection. Block until a connection is made.
                Socket socket = server.accept();
                System.out.println("Socket: " + socket);
                // Start a new thread for each client to perform block-IO operations.
                new ClientThread(socket).start();
            } catch (BindException e) {
                e.printStackTrace();
                break; // Port already in use
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server().listen();
    }

    // Fork out a thread for each connected client to perform block-IO
    class ClientThread extends Thread {

        Socket socket;
        Database db;
        public ClientThread(Socket socket) {
            this.socket = socket;
            db=new Database();
        }

        @Override
    public void run() {
        	System.out.println("d");
            InputStream in = null;
            OutputStream out = null;
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                String line;
                line = rd.readLine();
                System.out.print(line);
                JSONObject req=JSON.parseObject(line);
                String x=null;
                switch (req.getString("act")) {
                    case "loginrequest"://
                           x=db.Login(req.getString("id"), req.getString("pwd"));
                        break;
                    case "2"://

                        break;
                    case "3"://

                        break;
                    case "4"://

                        break;
                    case "5"://

                        break;
                    case "6"://

                        break;
                    default:
                        break;
                }

                socket.shutdownInput();

                System.out.println("server begin write");

                PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(out));
                pWriter.write(x);
                pWriter.flush();

                pWriter.close();
                out.close();
                in.close();

                System.out.println("Close Socket: " + socket);

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
