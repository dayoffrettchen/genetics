package genetic.impl;

import genetic.Agent;
import genetic.Fitness;

public class StringFitness extends Fitness {
    public int levenshteinDistance(CharSequence lhs, CharSequence rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) {
            cost[i] = i;
        }

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for (int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert = cost[i] + 1;
                int cost_delete = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost;
            cost = newcost;
            newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }

    @Override
    public float calc(Agent agent) {
        float v = (float) (1000 - Math.pow(calcMe (agent),2));
        return (float) v>0?v:0;


    }

    private float calcMe(Agent agent) {
        StringGene gene = (StringGene) agent.getGene();
        String result = gene.getString();


        String lhs = "Christoph Dume";
        int levenshteinDistance = getHammingDistance(lhs, result);
        return levenshteinDistance+1;
    }

    int getHammingDistance(String a, String b) {
        String longerString;
        String shorterString;

        if (a.length() > b.length()) {
            longerString = a;
            shorterString = b;
        } else {
            longerString = b;
            shorterString = a;
        }

        int dist = longerString.length() - shorterString.length() + 1;

        int cnt = 0;
        int best = longerString.length();
        for (int i = 0; i < dist; i++) {
            for (int j = 0; j < shorterString.length(); j++) {
                if (longerString.charAt(i + j) != shorterString.charAt(j)) {
                    cnt++;
                }
            }
            if (cnt == 0) {
                return dist - 1; // besser geht nicht
            }

            if (cnt < best) {
                best = cnt;
            }
            cnt = 0;
        }
        return best + dist - 1;
    }
}
