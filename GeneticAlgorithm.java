import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {

    public static void main(String[] args){
        int populationSize = 100;
        int numRuns = 10;
        int averageGen = 0;
        
        for(int i =0;i<numRuns;i++){
            averageGen += runAlgoritm(populationSize);
        }
        System.out.println("Average Generation: " + averageGen/numRuns);

    }
    
    public static int runAlgoritm(int populationSize){
        ArrayList<Chromosome> population = new ArrayList<Chromosome>();

        for(int i=0; i<populationSize;i++){
            population.add(new Chromosome());
        }

        Collections.sort(population);
        
        boolean finished = false;
        int q = 0;
        while(!finished){
            population = (ArrayList<Chromosome>)Transform(population).clone();
            Collections.sort(population);

            //exit conditions
            if(population.get(0).getFitness()==0){
                finished = true;
                System.out.println(q);
                for(int i=0;i<8;i++){
                    System.out.print(population.get(0).queens[i][0]+", ");
                    System.out.print(population.get(0).queens[i][1]);
                    System.out.println();
                }
            }
            
            q++;
            if(q>5000){
                finished = true;
            }

        }
        System.out.println("*************");
        return q;
    }

    //perform crossover then mutation
    public static ArrayList<Chromosome> Transform(ArrayList<Chromosome> pop){
        int pivot;
        int tempx;
        int tempy;
        int size = pop.size();

        //single point crossover
        for(int j=0;j<size;j+=2){
            pivot = ThreadLocalRandom.current().nextInt(0,8);
            Chromosome chrome1 = pop.get(j);
            Chromosome chrome2 = pop.get(j+1);
            for(int i=pivot;i<8;i++){
                tempx = chrome1.queens[i][0];
                tempy = chrome1.queens[i][1];
                chrome1.queens[i][0] = chrome2.queens[i][0];
                chrome1.queens[i][1] = chrome2.queens[i][1];
                chrome2.queens[i][0] = tempx;
                chrome2.queens[i][1] = tempy;
            }
           
            chrome1.calculateFitness();
            chrome2.calculateFitness();
            if(chrome1.getFitness() < pop.get(j).getFitness()){
                pop.set(j, chrome1);
            }
            if(chrome2.getFitness()<pop.get(j+1).getFitness()){
                pop.set(j+1, chrome2);
            }
            
            //mutation 5%
            for(int i=0;i<8;i++){
                if(Math.random() < 0.05){
                    chrome1.queens[i][1] = ThreadLocalRandom.current().nextInt(0,8);
                    chrome1.calculateFitness();    
                    if(chrome1.getFitness() < pop.get(j).getFitness()){
                        pop.set(j, chrome1);
                    }
                }
                if(Math.random() < 0.05){
                    chrome2.queens[i][1] = ThreadLocalRandom.current().nextInt(0,8);
                    chrome2.calculateFitness();
                    if(chrome2.getFitness()<pop.get(j+1).getFitness()){
                        pop.set(j+1, chrome2);
                    }
                }
            }
            
            
        }

        return pop;
    }
}