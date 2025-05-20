package com.mycompany.main;

import java.io.*;
import java.util.*;

public class DatabaseHandler {
    private final String fileName = "elements.txt";

    /** 
     *
     * @param element
     */
    public void saveElement(ElementData element) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(element.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public List<ElementData> readElements() {
        List<ElementData> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String name = parts[0];
                    String symbol = parts[1];
                    int atomicNumber = Integer.parseInt(parts[2]);
                    double atomicWeight = Double.parseDouble(parts[3]);
                    String properties = parts[4];
                    list.add(new ElementData(name, symbol, atomicNumber, atomicWeight, properties));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list; 
    }

    public void removeElement(String nameToDelete) {
        List<ElementData> elements = readElements();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (ElementData e : elements) {
                if (!e.getName().equalsIgnoreCase(nameToDelete)) {
                    bw.write(e.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting element: " + e.getMessage());
        }
    }
}
