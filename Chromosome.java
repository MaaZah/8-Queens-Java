import java.util.concurrent.ThreadLocalRandom;

public class Chromosome implements Comparable<Chromosome>{
    int[][] queens = new int[8][2];
    int fitness;


    public Chromosome(){
        int temp;
        for(int i=0;i<8;i++){
            temp = ThreadLocalRandom.current().nextInt(0,8);
            this.queens[i][0] = i;
            this.queens[i][1] = temp;
        }

        this.fitness = this.calculateFitness();
    }

    public Chromosome(int[][] newQueens){
        this.queens = newQueens;
        this.fitness = this.calculateFitness();
    }

    public int calculateFitness(){
        int errors = 0;
        int x = 0;
        int x2 = 0;
        int y = 0;
        int y2 = 0;
        int dRow = 0;
        int dCol = 0;

        for(int i=0;i<8;i++){
            x = this.queens[i][0];
            y = this.queens[i][1];
            for(int j=0;j<8;j++){
                x2 = this.queens[j][0];
                y2 = this.queens[j][1];
                if(j!=i){
                    if(x==x2){
                        errors++;
                    }
                    if(y==y2){
                        errors++;
                    }

                    dRow = Math.abs(x-x2);
                    dCol = Math.abs(y-y2);
                    if(dRow == dCol){
                        errors++;
                    }
                }
            }
        }
        this.fitness = errors;
        return errors;
    }


    public void Mutate(){
        //insert mutation code
    }

    public Integer getFitness(){
        return (Integer)this.fitness;
    }

    public int compareTo(Chromosome c){
        return this.getFitness().compareTo(c.getFitness());
    }
}