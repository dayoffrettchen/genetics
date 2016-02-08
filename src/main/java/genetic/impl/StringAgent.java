package genetic.impl;

import genetic.Agent;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Random;

import static java.lang.Math.min;
import static java.lang.StrictMath.abs;

class StringAgent extends Agent {
    private final Random random;
    private double mutation;

    public StringAgent(String string, double mutation) {
        super(new StringGene(string));
        this.mutation = mutation;
        this.random = new Random(new Date().getTime());
    }

    private int getRandomRest(int thisSize, int size) {
        int abs = Math.abs(random.nextInt(thisSize - size));
        return abs + size;
    }

    @Override
    public String toString() {
        return  ((StringGene) getGene()).getString()+ getFitness();
    }

    @Override
    public Agent createOffSpring(Agent second) {
        String secondString = ((StringGene) second.getGene()).getString();
        int secondLength = secondString.length();
        String firstString = ((StringGene) this.getGene()).getString();
        int firstLength = firstString.length();
        int firstEnd = abs(random.nextInt(firstLength));
        int secondStart = abs(random.nextInt(secondLength));
        StringBuilder sb = new StringBuilder();
        sb.append(firstString.substring(0, firstEnd));
        sb.append(secondString.substring(secondStart, secondLength));
        if (random.nextDouble() < mutation) {
            sb.setCharAt(random.nextInt(sb.length()), RandomStringUtils.randomAlphabetic(1).charAt(0));
        }

        return new StringAgent(sb.toString(), mutation);
    }

    private String getSubString(String string) {
        if (random.nextBoolean()) {
            return string.substring(0, Math.abs(random.nextInt(string.length())));
        } else {
            return string.substring(Math.abs(random.nextInt(string.length()-1)), string.length());
        }
    }

    private int getSize(int otherSize, int thisSize) {
        return min(otherSize, thisSize);
    }
}
