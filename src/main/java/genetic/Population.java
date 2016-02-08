package genetic;

import genetic.impl.AgentFactory;

import java.util.*;

public class Population {

    protected final int popSize;
    protected final int selectionSize;
    protected List<Agent> agents = new ArrayList<>();
    protected List<Agent> best = new ArrayList<>();
    private Gene bestResult;
    private int stuck = 0;


    public Population(AgentFactory agentFacory, int popSize, int selectionSize, double mutation) {
        this.popSize = popSize;
        this.selectionSize = selectionSize;
        for (int i = 0; i < this.popSize; i++) {
            agents.add(agentFacory.create(mutation));
        }
    }

    public boolean isAlive() {
        if (best.isEmpty()) {
            return true;
        }
        Gene gene = best.get(0).getGene();
        if (gene.equals(bestResult)) {
            stuck++;
        }else {
            System.out.println(best.get(0));
            stuck = 0;
        }

        return stuck < 100;
    }

    public void checkFitness(Fitness fitness) {
        for (Agent agent : agents) {
            agent.calculateFitness(fitness);
        }
    }

    public void selectBest() {
        Random random = new Random(new Date().getTime());
        best.clear();
        float sumFit = 0;
        for (Agent agent : agents) {
            sumFit += agent.getFitness();
        }
        while (best.size() < selectionSize) {
            float sum = 0;
            double check = random.nextDouble();
            for (Agent agent : agents) {
                float prob = agent.getFitness() / sumFit;
                if (check < sum + prob) {
                    best.add(agent);
                    break;
                } else {
                    sum += prob;
                }
            }
        }
    }

    public void addOffspring() {
        for (int i = 0; i < popSize; i++) {
            Random random = new Random(new Date().getTime());
            int firstRandom = Math.abs(random.nextInt(best.size()));
            int secondRandom = Math.abs(random.nextInt(best.size()));
            Agent first = best.get(firstRandom);
            Agent second = best.get(secondRandom);
            agents.add(first.createOffSpring(second));
        }
    }

    public void shrink() {
        agents.sort(new Comparator<Agent>() {
            @Override
            public int compare(Agent o1, Agent o2) {
                return (int) (o2.getFitness() - o1.getFitness());
            }
        });
        bestResult = agents.get(0).getGene();
        agents = new ArrayList<>(agents.subList(0, popSize));
    }

    public Gene bestResult() {
        return bestResult;
    }
}
