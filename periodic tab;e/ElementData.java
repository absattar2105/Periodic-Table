package com.mycompany.main; 

public class ElementData { 
    
    private String name;
    private String symbol;
    private int atomicNumber;
    private double atomicWeight;
    private String properties;

    public ElementData(String name, String symbol, int atomicNumber, double atomicWeight, String properties) {
        this.name = name;
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
        this.atomicWeight = atomicWeight;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public double getAtomicWeight() {
        return atomicWeight;
    }

    public String getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return name + "," + symbol + "," + atomicNumber + "," + atomicWeight + "," + properties;
    }
}
