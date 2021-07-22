package lk.ijse.dep7.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class ServerAppInitializer {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8585)) {
            System.out.println("Server has been started...");

            while (true){
                System.out.println("Listening for incoming connections...");
                Socket localSocket = serverSocket.accept();

                new Thread(()->{
                    try(InputStream is = localSocket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr)){

                        br.lines().forEach(System.out::println);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }).start();
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
