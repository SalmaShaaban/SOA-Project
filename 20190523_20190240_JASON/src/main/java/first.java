

import java.io.*;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class first {
    Scanner sc = new Scanner(System.in);

    String name = sc.nextLine();

    String city = sc.nextLine();

    String year = sc.nextLine();
    String fileName = "C:/Users/MOH/Desktop/file.json";


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("ender  you want add or build please");
        String chose = sc.nextLine();
        JSONArray buildList = new JSONArray();



        if (chose.equals("build")) {
            JSONObject buildDetails4 = new JSONObject();

            buildDetails4.put("Name", "UN HQ");
            buildDetails4.put("city", "NYC");
            buildDetails4.put("year", "1952");


            JSONObject buildDetails5 = new JSONObject();

            buildDetails5.put("Name", " Pyramid");
            buildDetails5.put("city", "Cairo");
            buildDetails5.put("year", "2570 BC");


            JSONObject buildDetails6 = new JSONObject();

            buildDetails6.put("Name", "Eiffel Tower");
            buildDetails6.put("city", "Paris");
            buildDetails6.put("year", "1889");


             buildList = new JSONArray();
            buildList.put(buildDetails4);
            buildList.put(buildDetails5);
            buildList.put(buildDetails6);



            try (FileWriter file = new FileWriter("file.json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(buildList.toString());
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

         if (chose.equals("add")) {
            JSONObject buildDetails4 = new JSONObject();

            buildDetails4.put("Name", "UN HQ");
            buildDetails4.put("city", "NYC");
            buildDetails4.put("year", "1952");


            JSONObject buildDetails5 = new JSONObject();

            buildDetails5.put("Name", " Pyramid");
            buildDetails5.put("city", "Cairo");
            buildDetails5.put("year", "2570 BC");


            JSONObject buildDetails6 = new JSONObject();

            buildDetails6.put("Name", "Eiffel Tower");
            buildDetails6.put("city", "Paris");
            buildDetails6.put("year", "1889");


            System.out.println("enter name");
            System.out.println("enter city");
            System.out.println("enter year");
            first f = new first();

            build b = new build(f.name, f.city, f.year);
            JSONObject buildDetails= new JSONObject();

            buildDetails.put("Name", b.getName());

            buildDetails.put("city", b.getCity());
            buildDetails.put("year", b.getYear());



            buildList.put(buildDetails4);
            buildList.put(buildDetails5);
            buildList.put(buildDetails6);
            buildList.put(buildDetails);

            try (FileWriter file = new FileWriter("file.json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(buildList.toString());
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }





        }



        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("file.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

             buildList = new JSONArray(obj.toString());

            System.out.println(buildList);

            //Iterate over employee array


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        chose= sc.nextLine();
        if (chose.equals("search")) {
            String key= sc.nextLine();
            String value= sc.nextLine();



            for (int idx = 0; idx < buildList.length(); idx++) {

                if(buildList.getJSONObject(idx).get(key).toString().contains(value))
                {
                    System.out.println(buildList.getJSONObject(idx));
                }

            }}


        if (chose.equals("delet")) {
            String key= sc.nextLine();
            String value= sc.nextLine();



            for (int idx = 0; idx < buildList.length(); idx++) {

                if(buildList.getJSONObject(idx).get(key).toString().contains(value))
                {
                    buildList.getJSONObject(idx).clear();

                }

            }
            System.out.println(buildList);
        }


    }














}



