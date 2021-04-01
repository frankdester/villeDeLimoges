package com.adventure_Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/* class for input files*/
public class Locations implements Map<Integer,Location> {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {
        //no need of closing locFile using try like this
        try (FileWriter locFile = new FileWriter("location.txt");
            FileWriter exitFile = new FileWriter("exitFile.txt")) {
            for(Location location :locations.values()){
                    locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
                    for (String exists : location.getExits().keySet()) {
                        exitFile.write(location.getLocationID() + "," + exists + "," + location.getExits().get(exists) + "\n");
                    }
            }

        }

        //simplifying try catch by adding throwing IOException in run
//        FileWriter locFile = null;
//        try{
//            locFile = new FileWriter("location.txt");
//            for(Location location : locations.values())
//                locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
//        }finally {
//            System.out.println("In finally block");
//            if (locFile != null) {
//                System.out.println("About to close file...");
//                locFile.close();
//            }
//        }

//        try{
//            locFile = new FileWriter("location.txt");
//            for(Location location : locations.values())
//                locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
//        }catch(IOException e) {
//            System.out.println("In catch block");
//            e.printStackTrace();
//        }finally {
//            System.out.println("In finally block");
//            try {
//                if (locFile != null) {
//                    System.out.println("About to close file...");
//                    locFile.close();
//                }
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
    static {
        //creating reading file
        Scanner scanner = null;
        try{
            scanner = new Scanner(new FileReader("location.txt"));
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()){
                int id =  scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("new locations "+id+" :"+description);
                Map<String,Integer> tempExit = new HashMap<>();
                locations.put(id,new Location(id,description,tempExit));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(scanner != null)
                scanner.close();
        }

        //implementing buffer
        try{
            scanner = new Scanner(new BufferedReader(new FileReader("exitFile.txt")));
            //scanner.useDelimiter(",");
            while(scanner.hasNextLine()){
//                int id = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String direction = scanner.nextLine();
//                scanner.skip(scanner.delimiter());
//                String des = scanner.nextLine();
//                int destination = Integer.parseInt(des);
                String input = scanner.nextLine();
                String[] data = input.split(",");
                int id = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                System.out.println(id+" : "+direction+" : "+destination);
                Location location = locations.get(id);
                location.addExit(direction,destination);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(scanner != null)
                scanner.close();
        }
        //creating an instance
//        Map<String, Integer> tempExit = new HashMap<>();
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java", tempExit));
//
//        tempExit = new HashMap<>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        tempExit.put("X", 6);
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building", tempExit));
//
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 5);
//        locations.put(2, new Location(2, "You are at the top of a hill", tempExit));
//
//
//        tempExit = new HashMap<>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring", tempExit));
//
//
//        tempExit = new HashMap<>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "You are in a valley beside a stream", tempExit));
//
//
//        tempExit = new HashMap<>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "You are in the forest", tempExit));
    }
    @Override
    public int size () {
        return locations.size();
    }

    @Override
    public boolean isEmpty () {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey (Object key){
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue (Object value){
        return locations.containsValue(value);
    }

    @Override
    public Location get (Object key){
        return locations.get(key);
    }

    @Override
    public Location put (Integer key, Location value){
        return locations.put(key, value);
    }

    @Override
    public Location remove (Object key){
        return locations.remove(key);
    }

    @Override
    public void putAll (Map < ? extends Integer, ? extends Location > m){

    }

    @Override
    public void clear () {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet () {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values () {
        return locations.values();
    }
    @Override
    public Set<Entry<Integer, Location>> entrySet () {
        return locations.entrySet();
    }

}


