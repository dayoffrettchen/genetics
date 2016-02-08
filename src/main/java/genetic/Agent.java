package genetic;

public abstract class Agent {
    private float fitness;

    public Agent(Gene gene) {
        this.gene = gene;
    }

    private Gene gene;

    public float getFitness() {
        return fitness;
    }

    public abstract Agent createOffSpring(Agent second);

    public void calculateFitness(Fitness fitness) {
        this.fitness = fitness.calc(this);
    }

    public Gene getGene() {
        return gene;
    }
}
