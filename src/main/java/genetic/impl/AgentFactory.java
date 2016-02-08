package genetic.impl;

import genetic.Agent;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Random;

public class AgentFactory {
    public Agent create(double mutation) {
        Random random = new Random(new Date().getTime());
        return new StringAgent(RandomStringUtils.randomAscii(Math.abs(random.nextInt(50))),mutation);
    }

}
