package __08_EXAM_19_August_2020;

import java.util.Scanner;

public class _02_Bee {
    static int rowOfBee = 0;
    static int colOfBee = 0;
    static int pollinatedFlowers = 0;
    static boolean isOut = false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sizeOfMatrix = Integer.parseInt(sc.nextLine());
        char [][] matrix = new char[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            String line = sc.nextLine();
            matrix[i] = new char[sizeOfMatrix];
            for (int j = 0; j < line.length(); j++) {
                    matrix[i][j] = line.charAt(j);
                    if(matrix[i][j] == 'B'){
                        rowOfBee = i;
                        colOfBee = j;
                    };
            };
        };
        String command = sc.nextLine();
        while(!command.equals("End")){
            move(command, matrix);
            if(isOut){
                break;
            }

            command = sc.nextLine();
        }
        if(isOut){
            System.out.println("The bee got lost!");
        }
        if(pollinatedFlowers < 5){
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more\n", 5 - pollinatedFlowers);
        }else{
            System.out.printf("Great job, the bee manage to pollinate %d flowers!\n", pollinatedFlowers);
        }
        printMatrix(matrix);
    }
    private static void move(String command, char[][] matrix) {
        int newRow = rowOfBee;
        int newCol = colOfBee;

        if(command.equals("up")){
            newRow -= 1;
        }else if(command.equals("down")){
            newRow += 1;
        }else if(command.equals("left")){
            newCol -= 1;
        }else if(command.equals("right")){
            newCol += 1;
        };

        if(isOutOfBounds(newRow, newCol, matrix)){
            isOut = true;
            matrix[rowOfBee][colOfBee] = '.';
        }else{
          if(matrix[newRow][newCol] == 'f'){
              pollinatedFlowers++;
              matrix[newRow][newCol] = 'B';
              matrix[rowOfBee][colOfBee] = '.';
              rowOfBee = newRow;
              colOfBee = newCol;;
          }else if(matrix[newRow][newCol] == 'O'){
              matrix[rowOfBee][colOfBee] = '.';
              rowOfBee = newRow;
              colOfBee = newCol;
              matrix[newRow][newCol] = 'B';
              move(command, matrix);
          }else if(matrix[newRow][newCol] == '.') {
              matrix[newRow][newCol] = 'B';
              if (matrix[rowOfBee][colOfBee] != 'O') {
                  matrix[rowOfBee][colOfBee] = '.';
              }
              rowOfBee = newRow;
              colOfBee = newCol;;
          }

        };
    };
    private static boolean isOutOfBounds(int row, int col, char[][] matrix) {
        return row >= matrix.length || row < 0 || col >= matrix.length || col < 0;
    }
    private static void printMatrix(char[][] matrix){
        for(char[] arr : matrix){
            for(char symbol : arr){
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
