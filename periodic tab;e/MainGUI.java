package com.mycompany.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainGUI extends JFrame {
    private JTextField nameField, symbolField, numberField, weightField, propertiesField;
    private JTextArea displayArea;
    private DatabaseHandler db;

    public MainGUI() {
        db = new DatabaseHandler();
        setTitle("Modern Periodic Table");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel (Form Input)
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Symbol:"));
        symbolField = new JTextField();
        inputPanel.add(symbolField);

        inputPanel.add(new JLabel("Atomic Number:"));
        numberField = new JTextField();
        inputPanel.add(numberField);

        inputPanel.add(new JLabel("Atomic Weight:"));
        weightField = new JTextField();
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Properties:"));
        propertiesField = new JTextField(); 
        inputPanel.add(propertiesField);

        JButton addButton = new JButton("Add Element");
        addButton.addActionListener(e -> addElement());
        inputPanel.add(addButton);

        JButton viewButton = new JButton("View Elements");
        viewButton.addActionListener(e -> viewElements());
        inputPanel.add(viewButton);

        JButton deleteButton = new JButton("Delete by Name");
        deleteButton.addActionListener(e -> deleteElement());
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        // Center Panel (Text Display)
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void addElement() {
        try {
            String name = nameField.getText();
            String symbol = symbolField.getText();
            int number = Integer.parseInt(numberField.getText());
            double weight = Double.parseDouble(weightField.getText());
            String props = propertiesField.getText();

            ElementData element = new ElementData(name, symbol, number, weight, props);
            db.saveElement(element);
            displayArea.setText("Element added successfully.");
        } catch (Exception ex) {
            displayArea.setText("Error: Invalid input.");
        }
    }

    private void viewElements() {
        List<ElementData> elements = db.readElements();
        StringBuilder sb = new StringBuilder("--- Element List ---\n");
        for (ElementData e : elements) {
            sb.append(e.getName()).append(" | ").append(e.getSymbol()).append(" | ")
              .append(e.getAtomicNumber()).append(" | ").append(e.getAtomicWeight())
              .append(" | ").append(e.getProperties()).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void deleteElement() {
        String nameToDelete = JOptionPane.showInputDialog(this, "Enter element name to delete:");
        if (nameToDelete != null && !nameToDelete.isEmpty()) {
            db.removeElement(nameToDelete);
            displayArea.setText("Deleted (if existed): " + nameToDelete);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI());
    }
}