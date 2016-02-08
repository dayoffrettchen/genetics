package genetic.impl;

import genetic.Gene;

public class StringGene extends Gene {

    private String string;

    public StringGene(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "StringGene{" +
                "string='" + string + '\'' +
                '}';
    }

    public String getString() {
        return string;
    }
}
