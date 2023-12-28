import java.util.*;

public class TCT2 {
    static int count = 0;
    static int x_c=0, y_c=0;

    static char ttt[][] = new char[3][3];

    final static String RESET = "\u001B[0m";
    final static String RED = "\u001B[31m";
    final static String GREEN = "\u001B[32m";
    final static String YELLOW = "\u001B[33m";


    static void prepareBoard()
    {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                ttt[i][j] = (char)(48+3*i+j+1);
            }
        }
    }

    static void dispBoard()
    {
        String colour; 

        for(int i=0; i<3; i++)  {
            for(int j=0; j<3; j++) {
                colour = (Character.isDigit(ttt[i][j])) ? RESET : ttt[i][j]=='X' ? RED : GREEN;

                System.out.print(colour + ttt[i][j] + " " + RESET);
                if(j==2)
                    System.out.println("");
            }
        }
        System.out.println(" ");
    }
    
    static int checkWinner()
    {
        String row ="", col ="", diag = "", anti_diag = "";
        //Check row
        for(int j=0; j<3; j++) {
            row = row + ttt[y_c][j];
        }

        //Check column
        for(int i=0; i<3; i++) {
            col = col + ttt[i][x_c];
        }

        //Check diagonals
        for(int i=0; i<3; i++) {
            diag = diag + ttt[i][i];
            anti_diag = anti_diag + ttt[i][2-i];
        }

        if(row.equals("XXX") || col.equals("XXX") || diag.equals("XXX") || anti_diag.equals("XXX")) {
            System.out.println("X is the winner");
            return 1;
        } else if (row.equals("OOO") || col.equals("OOO") || diag.equals("OOO") || anti_diag.equals("OOO")) {
            System.out.println("O is the winner");
            return 1;
        } else {
            if(count == 9)  {
                System.out.println("It is a draw");  
                return 1;
            }
            return 0;
        }
    }
    
    static void getCoordinates(int loc)
    {
        switch(loc)
        {
            case 1: x_c = 0; y_c = 0;
                break;
            case 2: x_c = 1; y_c = 0;
                break;
            case 3: x_c = 2; y_c = 0;
                break;
            case 4: x_c = 0; y_c = 1;
                break;
            case 5: x_c = 1; y_c = 1;
                break;
            case 6: x_c = 2; y_c = 1;
                break;
            case 7: x_c = 0; y_c = 2;
                break;
            case 8: x_c = 1; y_c = 2;
                break;
            case 9: x_c = 2; y_c = 2;
                break;
            default: System.out.println("Invalid entry");
        }
    }

    static int accept()
    {
        Scanner sc = new Scanner(System.in);
        char turn = ' ';
        turn = (count%2 == 0) ? 'O' : 'X';
        int loc;
        char l;
        do {
            System.out.println("It is the turn of " + turn);
            System.out.println("\tEnter location to place " + turn);
            //loc = sc.nextInt();
            l = sc.next().charAt(0);
            if(Character.isDigit(l)) {
                loc = (char)(l-48);
            } else {
                continue;
            }
            getCoordinates(loc);

            if(Character.isDigit(ttt[y_c][x_c])) {
                ttt[y_c][x_c] = turn;
                dispBoard();
                break;
            } else {
                System.out.println("Invalid entry\nPlease enter location again");
            }
        } while(true);
        
        return (checkWinner());
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            prepareBoard();
            dispBoard();

            System.out.println("X starts the game");
            System.out.println("Enter the number where you want to place the piece");

            while(count<9) {
                count++;
                if(accept() == 1)
                    break;
            }
            System.out.println(YELLOW + "Game done");
            System.out.println("Do you wanna play again? (Y/N)" + RESET);
            count = 0;
        } while(Character.toUpperCase(sc.next().charAt(0)) == 'Y');  
    }
}
