package tests.gameoflife;

/*

https://coderpad.io/NZ6MPDDN

Initial board:
101100001001
000010001011
010011100000
100100111100
101110000011

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Out-of-bound cells should be considered dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules:

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Please have your program print out the next iteration of the board.

 */

public class GameOfLife1 {

    public static final int H_SIZE = 5;
    public static final int W_SIZE = 12;

    public static final int H_SIZE_T1 = 3;
    public static final int W_SIZE_T1 = 3;

    public static void main(String[] args) {

        test_flow_2();
        System.out.println("----------------------------------");
        test_flow_1();
        System.out.println("----------------------------------");
        main_flow();
    }

    private static void test_flow_1() {
        // 12 * 5
        int[][] cells = {
                {1,0,1},
                {0,0,0},
                {0,1,0}
        };

        int[][] workCells = new int[H_SIZE_T1][W_SIZE_T1];

        printCells(cells, H_SIZE_T1, W_SIZE_T1);
        for(int i = 0; i < 2; i++) {
            cells = iterate(cells, workCells, H_SIZE_T1, W_SIZE_T1);
            printCells(cells, H_SIZE_T1, W_SIZE_T1);
        }
    }

    private static void test_flow_2() {
        // 12 * 5
        int[][] cells = {
                {1,1,1},
                {1,0,0},
                {0,1,0}
        };

        int[][] workCells = new int[H_SIZE_T1][W_SIZE_T1];

        printCells(cells, H_SIZE_T1, W_SIZE_T1);
        for(int i = 0; i < 2; i++) {
            cells = iterate(cells, workCells, H_SIZE_T1, W_SIZE_T1);
            printCells(cells, H_SIZE_T1, W_SIZE_T1);
        }
    }

    private static void main_flow() {
        // 12 * 5
        int[][] cells = {
                {1,0,1,1,0,0,0,0,1,0,0,1},
                {0,0,0,0,1,0,0,0,1,0,1,1},
                {0,1,0,0,1,1,1,0,0,0,0,0},
                {1,0,0,1,0,0,1,1,1,1,0,0},
                {1,0,1,1,1,0,0,0,0,0,1,1}
        };

        int[][] workCells = new int[H_SIZE][W_SIZE];

        printCells(cells, H_SIZE, W_SIZE);
        for(int i = 0; i < 2; i++) {
            cells = iterate(cells, workCells, H_SIZE, W_SIZE);
            printCells(cells, H_SIZE, W_SIZE);
        }
    }


    static void printCells(int[][] cells, int h_size, int w_size) {
        System.out.println();
        for(int h = 0; h <= h_size - 1; h++) {
            for(int w = 0; w <= w_size - 1; w++) {
                System.out.print(cells[h][w]);
            }
            System.out.println();
        }
    }

    static int[][] iterate(int[][] cells, int[][] workCells, int h_size, int w_size) {
        for(int h = 0; h <= h_size - 1; h++) {
            for(int w = 0; w <= w_size - 1; w++) {
                workCells[h][w] = checkCell(h, w, cells, h_size, w_size);
            }
        }
        return workCells;
    }

    /*

        h-1 w-1     h-1 w        h-1 w+1
        h   w-1     h   w        h   w+1
        h+1 w-1     h+1 w        h+1 w+1

     */

    static int checkCell(int h, int w, int[][] cells, int h_size, int w_size) {

        int cnt = 0;


        // upper raw
        if(h > 0) {

            if(w > 0) {
                if(cells[h-1][w-1] == 1 ) {
                    cnt += 1;
                }
            }

            if(cells[h-1][w] == 1 ) {
                cnt += 1;
            }

            if(w < w_size - 1) {
                if(cells[h-1][w+1] == 1 ) {
                    cnt += 1;
                }
            }
        }

        // current raw
        if(w > 0) {
            if(cells[h][w-1] == 1 ) {
                cnt += 1;
            }
        }

        if(w < w_size - 1) {
            if(cells[h][w+1] == 1 ) {
                cnt += 1;
            }
        }

        // lover raw
        if(h < h_size - 1) {

            if(w > 0) {
                if(cells[h+1][w-1] == 1 ) {
                    cnt += 1;
                }
            }

            if(cells[h+1][w] == 1 ) {
                cnt += 1;
            }

            if(w < w_size - 1) {
                if(cells[h+1][w+1] == 1 ) {
                    cnt += 1;
                }
            }
        }

        int oldRes = cells[h][w];
        int res = 0;
        if(cnt < 2) {
            res = 0;
        } else if (cnt == 2 || cnt == 3) {
            res = oldRes;
            if(cnt == 3) {
                res =1;
            }
        } else if (cnt > 3) {
            res = 0;
        }

        return res;
    }

}
