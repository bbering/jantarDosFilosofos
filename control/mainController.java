package control;

import java.util.concurrent.Semaphore;

public class mainController {
    private int nPhilosophers = 5;
    private int left;
    private int right;
    private int thinking = 0;
    private int hunger = 1;
    private int eating = 2;
    int states[] = new int[nPhilosophers];
    Semaphore mutexSemaphore = new Semaphore(1);
    Semaphore arraySemaphore[] = new Semaphore[nPhilosophers];

    public void setLeft(int i) {
        this.left = (i-1)%nPhilosophers;
    }

    public void setRight(int i) {
        this.right = (i+1)%nPhilosophers;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public void grabAFork(int id){

    }
}
