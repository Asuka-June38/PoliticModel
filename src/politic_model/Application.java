package politic_model;

import java.util.ArrayList;
import java.util.Random;

import static politic_model.Field.Random;

public class Application {
    public static void main(String[] args) {
        try {
            final Random random = new Random();
            int rang = 5; // Rang of matrix.
            politic_model.PersonWithNeighbours[][] gameField = new politic_model.PersonWithNeighbours[rang][rang];
            for (int i = 0; i < rang; i++) {
                for (int j = 0; j < rang; j++) {
                    gameField[i][j] = new politic_model.PersonWithNeighbours();
                    System.out.print(gameField[i][j].opinion.toString() + ' ');
                }
                System.out.println();
            }
            System.out.println();

            int numberOfIterations = 2; // Number of iterations matrix goes throw.
            politic_model.PersonWithNeighbours[][] tempGameField = new politic_model.PersonWithNeighbours[rang][rang];
            ArrayList<Opinion> neighbourOpinions = new ArrayList<Opinion>();

            // Going throw every cell and getting opinion.
            for (int n = 0; n < numberOfIterations; n++) {
                for (int i = 0; i < rang; i++) {
                    for (int j = 0; j < rang; j++) {
                        neighbourOpinions.clear();
                        int tempIJ = i < rang - 1 ? i + 1 : i;

                        // Getting list of opinions from Cross field.
                        if (gameField[i][j].field == Field.Cross) {
                            int c = 0;
                            for (int a = i < 1 ? 0 : i - 1; a < tempIJ + 1; a++) {
                                for (int b = j < 1 ? 0 : j - 1; b < tempIJ + 1; b++) {
                                    c++;
                                    if (c != 1 && c != 3 && c != 5 && c != 7 && c != 9)
                                        neighbourOpinions.add(gameField[a][b].opinion);
                                }
                            }
                        }
                        // Getting list of opinions from Wheel field.
                        if (gameField[i][j].field == Field.Wheel) {
                            int c = 0;
                            for (int a = i < 1 ? 0 : i - 1; a < tempIJ + 1; a++) {
                                for (int b = j < 1 ? 0 : j - 1; b < tempIJ + 1; b++) {
                                    c++;
                                    if (c != 5)
                                        neighbourOpinions.add(gameField[a][b].opinion);
                                }
                            }
                        }
                        // Getting list of opinions from Random Cell.
                        if (gameField[i][j].field == Random) {
                            neighbourOpinions.add(gameField[random.nextInt(rang)][random.nextInt(rang)].opinion);
                        }
                        // Put persons with changed opinions to new temporary matrix
                        PersonWithNeighbours temp = new PersonWithNeighbours(gameField[i][j]);
                        temp.perception(neighbourOpinions);
                        tempGameField[i][j] = temp;
                    }
                }
                // Modifying matrix after iteration.
                for (int i = 0; i < rang; i++) System.arraycopy(tempGameField[i], 0, gameField[i], 0, rang);
            }
            // Printing matrix after iteration string by string.
            for (int i = 0; i < rang; i++) {
                for (int j = 0; j < rang; j++) {
                    gameField[i][j] = new politic_model.PersonWithNeighbours();
                    System.out.print(tempGameField[i][j].opinion.toString() + ' ');
                }
                System.out.println();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        } catch(Exception e) {
            System.out.println("Unexcepted Exception");
            e.printStackTrace();
        }
    }
}
