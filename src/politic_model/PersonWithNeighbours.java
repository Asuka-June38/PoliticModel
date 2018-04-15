package politic_model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PersonWithNeighbours extends Person{

    public Field field;
    public boolean conformist;

    final Random random = new Random();

    public PersonWithNeighbours(){
        super();
        field = Field.values()[random.nextInt(Field.values().length)];
        conformist = random.nextBoolean();
    }

    public PersonWithNeighbours(PersonWithNeighbours source){
        this.zealot = source.zealot;
        this.opinion = source.opinion;
        this.field = field;
        this.conformist = conformist;
    }

    public void perception(ArrayList<Opinion> neighbourOpinions){
        if (!zealot) {
            influence(neighbourOpinions);
        }
    }

    public void influence(ArrayList<Opinion> neighbourOpinions){
        Opinion majorOpinion, minorOpinion;
        int dog = Collections.frequency(neighbourOpinions, Opinion.Dog);
        int cat = Collections.frequency(neighbourOpinions, Opinion.Cat);

        majorOpinion = cat < dog? Opinion.Dog : Opinion.Cat;
        minorOpinion = cat < dog? Opinion.Cat : Opinion.Dog;

        this.opinion = this.conformist ? majorOpinion : minorOpinion;
    }
}
