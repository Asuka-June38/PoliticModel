package politic_model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Person {

    public Opinion opinion;
    public boolean zealot;

    final Random random = new Random();

    public Person() {
        opinion = Opinion.values()[random.nextInt(Opinion.values().length)];
        zealot = random.nextBoolean();
    }

    public Person(Opinion opinion, boolean zealot, Field field, boolean conformist) {
        this.opinion = opinion;
        this.zealot = zealot;
    }

    abstract public void perception(ArrayList<Opinion> neighbourOpinions);
}


