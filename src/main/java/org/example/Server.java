package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    

    public static void main(String[] args) {
        String citi = " ";

        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Server started");// стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                ) {
                    out.println(citi.equals(" ") ? "????" : "Последнее слово: " + citi);
                    String enteredCity = in.readLine();
                    System.out.println(enteredCity);
                    if (citi.equals(" ")){
                        citi = enteredCity;
                        out.println("OK");
                    } else if (enteredCity.toLowerCase().charAt(0) == citi.toLowerCase().charAt(citi.length()-1)){
                        citi = enteredCity;
                        out.println("OK");
                    }else {
                        out.println("NOT OK");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}

