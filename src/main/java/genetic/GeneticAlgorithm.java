package genetic;

public class GeneticAlgorithm {
    private final Fitness fitness;
    private final Population population;

    public GeneticAlgorithm(Fitness fitness, Population population) {
        this.fitness = fitness;
        this.population = population;
    }

    public Gene simulate(){
        while (population.isAlive()) {
            population.checkFitness(fitness);
            population.selectBest();
            population.addOffspring();
            population.checkFitness(fitness);
            population.selectBest();
            population.shrink();
        }
        return population.bestResult();
    }
}
