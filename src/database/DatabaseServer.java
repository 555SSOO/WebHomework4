package database;

import com.google.gson.Gson;
import shared.Grade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseServer {

    private static List<Grade> gradeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server_socket = new ServerSocket(12345);
        Socket socket;
        while (true) {
            socket = server_socket.accept();
            String input = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
            // If it's a get request, return the JSON data
            if(input.equals("GET")){
                new PrintWriter(socket.getOutputStream(), true).println(getAvgGrades());
            }
            // Else if it's a post request, save the info in the arraylist
            else{
                Grade grade = new Gson().fromJson(input, Grade.class);
                if(grade.getAssistant_name().equals("VUK")){
                    grade.setGrade(0);
                }
                gradeList.add(grade);
            }

        }
    }

    // Returns JSON - average grades and assistant names
    private static String getAvgGrades(){
        return new Gson().toJson(gradeList.stream().collect(Collectors.groupingBy(Grade::getAssistant_name, Collectors.averagingInt(Grade::getGrade))));
    }

}
