package politic_model;

import java.util.ArrayList;
import java.util.Random;

public class Application {
    public static void main (String[] args) {
        final Random random = new Random();
        int rang = 5;
        politic_model.PersonWithNeighbours[][] gameField = new politic_model.PersonWithNeighbours[rang][rang];
        // –°–Њ–Ј–і–∞–љ–Є–µ –Љ–∞—В—А–Є—Ж—Л - –Є–≥—А–Њ–≤–Њ–≥–Њ –њ–Њ–ї—П —А–∞–Ј–Љ–µ—А–Њ–Љ rang —Е rang.
        for (int i = 0; i < rang; i ++) {
            for (int j = 0; j < rang; j++) {
                gameField[i][j] = new politic_model.PersonWithNeighbours();
                // –Я–Њ–Љ–µ—Й–µ–љ–Є–µ –≤ –Ї–∞–ґ–і—Г—О —П—З–µ–є–Ї—Г –Љ–∞—В—А–Є—Ж—Л —З–µ–ї–Њ–≤–µ–Ї–∞ —Б —Б–Њ—Б–µ–і—П–Љ–Є, –Ї–Њ—В–Њ—А—Л–µ –≤–ї–Є—П—О—В –љ–∞ –µ–≥–Њ –Љ–љ–µ–љ–Є–µ.
                System.out.print(gameField[i][j].opinion.toString() + ' ');
            }
            System.out.println();
        }
        System.out.println();

        int numberOfIterations = 2;
        // –Ъ–Њ–ї–Є—З–µ—Б—В–≤–Њ –Є—В–µ—А–∞—Ж–Є–є –Ј–∞–њ—Г—Б–Ї–∞ –Є–≥—А—Л.
        politic_model.PersonWithNeighbours[][] tempGameField = new politic_model.PersonWithNeighbours[rang][rang];
        // –°–Њ–Ј–і–∞–љ–Є–µ –≤—А–µ–Љ–µ–љ–љ–Њ–є –Љ–∞—В—А–Є—Ж—Л, –і–ї—П —Б–Њ—Е—А–∞–љ–µ–љ–Є—П –Ј–љ–∞—З–µ–љ–Є–є –Љ–µ–ґ–і—Г –Є—В–µ—А–∞—Ж–Є—П–Љ–Є.
        ArrayList<Opinion> neighbourOpinions = new ArrayList<Opinion>();
        // –°–Њ–Ј–і–∞–љ–Є–µ —Б–њ–Є—Б–Ї–∞ –і–ї—П —Б–Њ–±—А–∞–љ–Є—П –≤—Б–µ—Е –Љ–љ–µ–љ–Є–є —Б–Њ—Б–µ–і–µ–є.
        for (int n = 0; n < numberOfIterations; n ++) {
            for (int i = 0; i < rang; i++) {
                for (int j = 0; j < rang; j++) {
                    neighbourOpinions.clear();
                    int tempIJ = i < rang-1 ? i+1 : i;
                    if (gameField[i][j].field == Field.Cross) {
                        int c = 0;
                        for (int a = i<1 ? 0 : i-1; a < tempIJ +1; a++){
                            for (int b = j<1 ? 0 : j-1; b < tempIJ+1; b++){
                                c++;
                                if(c != 1 && c != 3 && c != 5 && c != 7 && c != 9)
                                    neighbourOpinions.add(gameField[a][b].opinion);
                            }
                        }
                    }
                    if (gameField[i][j].field == Field.Wheel) {
                        int c = 0;
                        for (int a = i<1 ? 0 : i-1; a < tempIJ +1; a++){
                            for (int b = j<1 ? 0 : j-1; b < tempIJ +1; b++){
                                c++;
                                if(c != 5)
                                    neighbourOpinions.add(gameField[a][b].opinion);
                            }
                        }
                    }
                    if (gameField[i][j].field == Field.Random) {
                        neighbourOpinions.add(gameField[random.nextInt(rang)][random.nextInt(rang)].opinion);
                    }
                    // –Ч–∞–њ–Њ–ї–љ–µ–љ–Є–µ —Б–њ–Є—Б–Ї–∞ —А–∞–љ—Л–Љ–Є –Љ–Њ–і–µ–ї—П–Љ–Є.
                    PersonWithNeighbours temp = new PersonWithNeighbours(gameField[i][j]);
                    temp.perception(neighbourOpinions);
                    tempGameField[i][j] = temp;
                    // –Ч–∞–њ–Њ–ї–љ–µ–љ–Є–µ –≤—А–µ–Љ–µ–љ–љ–Њ–є –Љ–∞—В—А–Є—Ж—Л –њ–µ—А—Б–Њ–љ–∞–ґ–∞–Љ–Є —Б –Є–Ј–Љ–µ–љ–µ–љ–љ—Л–Љ –Љ–љ–µ–љ–Є–µ–Љ.
                }
            }
            for (int i = 0; i < rang; i++) System.arraycopy(tempGameField[i], 0, gameField[i], 0, rang);
            // –Я–µ—А–µ–Љ–µ—Й–µ–љ–Є–µ –Ј–љ–∞—З–µ–љ–Є–є –Є–Ј –≤—А–µ–Љ–µ–љ–љ–Њ–є –Љ–∞—В—А–Є—Ж—Л –≤ –њ–Њ—Б—В–Њ—П–љ–љ—Г—О, –≥–Њ—В–Њ–≤—Г—О –і–ї—П —Б–ї–µ–і—Г—О—Й–µ–є –Є—В–µ—А–∞—Ж–Є–Є.
        }
        for (int i = 0; i < rang; i ++) {
            for (int j = 0; j < rang; j++) {
                gameField[i][j] = new politic_model.PersonWithNeighbours();
                // –Я–Њ–Љ–µ—Й–µ–љ–Є–µ –≤ –Ї–∞–ґ–і—Г—О —П—З–µ–є–Ї—Г –Љ–∞—В—А–Є—Ж—Л —З–µ–ї–Њ–≤–µ–Ї–∞ —Б —Б–Њ—Б–µ–і—П–Љ–Є, –Ї–Њ—В–Њ—А—Л–µ –≤–ї–Є—П—О—В –љ–∞ –µ–≥–Њ –Љ–љ–µ–љ–Є–µ.
                System.out.print(tempGameField[i][j].opinion.toString() + ' ');
            }
            System.out.println();
        }
    }
}
