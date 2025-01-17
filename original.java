﻿﻿

import java.util.Scanner;

public class Proj2 {

//skip two lines

public static void skip2() {

System.out.println("");

System.out.println("");

}

// print the game board

public static void board(char space[][]) {

System.out.println(" "+space[0][5]+" | " +space[1][5]+" | " +space[2][5]+" | " +space[3][5]+" | " +space[4][5]+" | " +space[5][5]+" | " +space[6][5]);

System.out.println(" "+space[0][4]+" | " +space[1][4]+" | " +space[2][4]+" | " +space[3][4]+" | " +space[4][4]+" | " +space[5][4]+" | " +space[6][4]);

System.out.println(" "+space[0][3]+" | " +space[1][3]+" | " +space[2][3]+" | " +space[3][3]+" | " +space[4][3]+" | " +space[5][3]+" | " +space[6][3]);

System.out.println(" "+space[0][2]+" | " +space[1][2]+" | " +space[2][2]+" | " +space[3][2]+" | " +space[4][2]+" | " +space[5][2]+" | " +space[6][2]);

System.out.println(" "+space[0][1]+" | " +space[1][1]+" | " +space[2][1]+" | " +space[3][1]+" | " +space[4][1]+" | " +space[5][1]+" | " +space[6][1]);

System.out.println(" "+space[0][0]+" | " +space[1][0]+" | " +space[2][0]+" | " +space[3][0]+" | " +space[4][0]+" | " +space[5][0]+" | " +space[6][0]);

System.out.println("---------------------------");

System.out.println(" 1 | 2 | 3 | 4 | 5 | 6 | 7");

}

//check if there is a winner

public static boolean checkWinnerAt(char[][]b, int i, int j, char c) {

//check for a diagonal line up

if((i + 3) < b.length && (j+3) < b[i].length) {

if((b[i][j]==c)&&(b[i+1][j+1]==c&&b[i+2][j+2]==c && b[i+3][j+3]==c)){

return true;

}

}

//check for a straight line vertically

if ((i + 3)< b.length && (j+3) < b[i].length) {

if((b[i][j]==c)&&(b[i][j+1]==c&&b[i][j+2]==c && b[i][j+3]==c)) {

return true;

}

}

//check for a straight line horizontally

if ((i + 3) < b.length && (j + 3) < b[i].length) {

if((b[i][j]==c)&&(b[i+1][j]==c&&b[i+2][j]==c && b[i+3][j]==c)) {

return true;

}

}

//check for diagonal down

if ((i + 3) < b.length && (j-3) > 0) {

if(b[i][j-1]==c && b[i+1][j-2]==c && b[i+2][j-3]==c && b[i+3][j-4]==c) {

return true;

}

}

return false;

}

//puts the information through the check winner at to see if there is a winner

public static boolean checkWinner(char[][]b) {

boolean r1, r2;

for(int r = 0; r < b.length; r++) {

for (int c = 0; c < b[0].length; c++) {

//check for winners

r1=checkWinnerAt(b,r,c,'R');

r2=checkWinnerAt(b,r,c,'Y');

//if there is a winner, it returns true

if(r1 == true || r2 == true) {

return true;

}

}

}

return false;

}

//get the input to play again

public static int again() {

Scanner in = new Scanner(System.in);

int win = 1;

//ask if the player wants to play again

System.out.println("If you would like to play again, enter 1, if not, enter any other number.");

win = in.nextInt();

if (win == 1) {

return win;

}

win = 0;

return win;

}

public static void main(String[] args) {

// TODO Auto-generated method stub

//initializes the scanner and all needed variables

Scanner input = new Scanner(System.in);

int column, win=1;

int xvalue = 0, yvalue=0, player1Win = 0, player2Win = 0, count = 0;

int yval[] = new int[7];

char space[][]= new char [7][6];

System.out.println("Welcome to Connect 4");

//clear the board

for(int x = 0; x < 42; x++) {

space[xvalue][yvalue] = ' ';

yvalue++;

if(yvalue > 5) {

yvalue = 0;

xvalue++;

}

}

for(int i = 0; i < 7; i++) {

yval[i] = 0;

}

do {

// goes until the game ends

for(int z = 1; z > 0; z++) {

skip2();

//print the board

board(space);

if(count >=42) {

System.out.println("Tie game!");

win = again();

count = 0;

break;

}

if(checkWinner(space) == true) {

System.out.println("Player 2 wins!");

win = again();

xvalue = 0;

yvalue = 0;

//clear the board

for(int x = 0; x < 42; x++) {

space[xvalue][yvalue] = ' ';

yvalue++;

if(yvalue > 5) {

yvalue = 0;

xvalue++;

}

}

for(int i = 0; i < 7; i++) {

yval[i] = 0;

}

player2Win++;

count = 0;

break;

}

do {

//asks the user what column that they want to play

System.out.println("Player 1: chose the column that you want:");

column = input.nextInt();

if(column >= 8 || column <= 0 || yval[column-1] > 5) {

System.out.println("Error: column already full. Please try again");

}

//makes sure that the move is in bound

}while(column >= 8 || column <= 0 || yval[column-1] > 5);

count++;

space[column-1][yval[column-1]]= 'R';

//makes the row value for that column go up by 1

switch(column){

case 1:

yval[0]++;

break;

case 2:

yval[1]++;

break;

case 3:

yval[2]++;

break;

case 4:

yval[3]++;

break;

case 5:

yval[4]++;

break;

case 6:

yval[5]++;

break;

case 7:

yval[6]++;

break;

//in case there is an error in the column number

default:

System.out.println("Error: invalid column number");

break;

}

skip2();

//print the board

board(space);

//clear the board

if(count >=42) {

System.out.println("Tie game!");

win = again();

count = 0;

break;

}

//checks for a winner

if(checkWinner(space) == true) {

System.out.println("Player 1 wins!");

win = again();

//resets the game board

xvalue = 0;

yvalue = 0;

for(int x = 0; x < 42; x++) {

space[xvalue][yvalue] = ' ';

yvalue++;

if(yvalue > 5) {

yvalue = 0;

xvalue++;

}

}

for(int i = 0; i < 6; i++) {

yval[i] = 0;

}

player1Win++;

count = 0;

break;

}

do {

//asks the user what column that they want to play

System.out.println("Player 2: chose the column that you want:");

column = input.nextInt();

if(column >= 8 || column <= 0 || yval[column-1] > 5) {

System.out.println("Error: column already full. Please try again");

}

//makes sure that the move is in bound

}while(column >= 8 || column <= 0 || yval[column-1] > 5);

count++;

space[column-1][yval[column-1]]= 'Y';

//makes the row value for that column go up by 1

switch(column){

case 1:

yval[0]++;

break;

case 2:

yval[1]++;

break;

case 3:

yval[2]++;

break;

case 4:

yval[3]++;

break;

case 5:

yval[4]++;

break;

case 6:

yval[5]++;

break;

case 7:

yval[6]++;

break;

//in case there is an error in the column number

default:

System.out.println("Error: invalid column number");

break;

}

}

}while(win == 1);

//prints the amount of times that each player won

System.out.println("Player 1 won " + player1Win + " times!");

System.out.println("Player 2 won " + player2Win + " times!");

}

}
