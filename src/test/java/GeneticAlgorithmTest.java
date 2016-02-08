import genetic.Gene;
import genetic.GeneticAlgorithm;
import genetic.Population;
import genetic.impl.AgentFactory;
import genetic.impl.StringFitness;

public class GeneticAlgorithmTest {
    @org.junit.Test
    public void Test_test_test() {
        ////Given//////////////////////////////////////////////////
        Population population = new Population(new AgentFactory(), 100, 15, 0.5);
        StringFitness fitness = new StringFitness();

        ////When///////////////////////////////////////////////////
        Gene simulate = new GeneticAlgorithm(fitness, population).simulate();

        ////Then///////////////////////////////////////////////////
        System.out.println(simulate.toString());

    }


}