import java.util.Scanner;
import java.util.Random;
// 0 = empty
// 1 = miss
// 2 = element
// 3 = hit
// 4 = start
class Main {
  public static void main(String[] args) throws InterruptedException{
		Random rand = new Random(); 
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter how many rows you'd like in the table.");
		int columns = scan.nextInt();
		while(columns < 2){
			System.out.println("Please enter a value greater than 1!");
			columns = scan.nextInt();
		}
		System.out.println("Please enter how many columns you'd like in the table.");
		int rows = scan.nextInt();
		while(rows < 2){
			System.out.println("Please enter a value greater than 1!");
			rows = scan.nextInt();
		}
		int elementPositionColumn = rand.nextInt(columns);
		int elementPositionRow = rand.nextInt(rows);
		int startPositionColumn = rand.nextInt(columns);
		int startPositionRow = rand.nextInt(rows);
		while(startPositionColumn == elementPositionColumn && startPositionRow == elementPositionRow){
			startPositionColumn = rand.nextInt(columns);
			startPositionRow = rand.nextInt(rows);
		}
    int[][] table = new int[columns][rows];
		for(int i = 0; i < table.length; i++)
			for(int x = 0; x < table[i].length; x++)
				table[i][x] = 0;        
		table[elementPositionColumn][elementPositionRow] = 2;
		table[startPositionColumn][startPositionRow] = 4;
		print2DArray(table);
		recursiveFunction(table, elementPositionColumn, elementPositionRow, startPositionRow, startPositionColumn);
  }

	public static void recursiveFunction(int[][] table, int elementColumn, int elementRow, int lastColumn, int lastRow) throws InterruptedException{
		Thread.sleep(1000);
		if(table[elementColumn][elementRow] != 2){
			System.out.println("Found!");
			return;
		}
		Random rand = new Random();
		int direction = rand.nextInt(2);
		int randomNumber = 0;
		boolean changed = false;
		int counter = 0;
		boolean newSpot = false;
		while(changed == false){
			while(direction == 0){ // vertical movement
				counter = 0;
				randomNumber = rand.nextInt(table.length);
				for(int i = 0; i < table.length; i++){
					if(table[i][lastColumn] == 4 || table[i][lastColumn] == 1)
						counter++;
				}
				if(counter >= table.length){
					for(int i = 0; i < table[0].length; i++){ //check if row and column are full
						if(table[lastRow][i] == 4 || table[lastRow][i] == 1)
						counter++;
					}
					if(counter >= (table.length) + (table[0].length)){
						for(int i = 0; i < table.length; i++){ //look for next available square
							for(int x = 0; x < table[i].length; x++){
								if(table[i][x] == 0 || table[i][x] == 2){
									lastRow = i;
									lastColumn = x;
									newSpot = true;
									break;
								}
								if(newSpot == true)
									break;
							}
						}
					}
					direction = 1;
					break;
				}
				if(table[randomNumber][lastColumn] == 0){ //hits empty square
					table[randomNumber][lastColumn] = 1;
					changed = true;
					lastRow = randomNumber;
					break;
				}
				if(table[randomNumber][lastColumn] == 2){ //hits element
					table[randomNumber][lastColumn] = 3;
					changed = true;
					break;
				}
			}
			while(direction == 1){ // horizontal movement
				counter = 0;
				 randomNumber = rand.nextInt(table[0].length);
				 for(int i = 0; i < table[0].length; i++){ //check if entire row is full
					if(table[lastRow][i] == 4 || table[lastRow][i] == 1)
						counter++;
				}
				if(counter >= table[0].length){
					for(int i = 0; i < table.length; i++){ //check if row and column are full
						if(table[i][lastColumn] == 4 || table[i][lastColumn] == 1)
						counter++;
					}
					if(counter >= (table.length) + (table[0].length)){
						for(int i = 0; i < table.length; i++){ //look for next available square
							for(int x = 0; x < table[i].length; x++){
								if(table[i][x] == 0 || table[i][x] == 2){
									lastRow = i;
									lastColumn = x;
									newSpot = true;
									break;
								}
								if(newSpot == true)
									break;
							}
						}
					}
					direction = 0;
					break;
				}
				if(table[lastRow][randomNumber] == 0){ //hits empty square
					table[lastRow][randomNumber] = 1;
					changed = true;
					lastColumn = randomNumber;
					break;
				}
				if(table[lastRow][randomNumber] == 2){ //hits element
					table[lastRow][randomNumber] = 3;
					changed = true;
					lastColumn = randomNumber;
					break;
				}
			}
		}
		print2DArray(table);
		recursiveFunction(table, elementColumn, elementRow, lastColumn, lastRow);
	}

	public static void print2DArray(int[][] table){
		for(int i = 0; i < table.length; i++){
			for(int x = 0; x < table[i].length; x++){
        if(table[i][x] == 0)
          System.out.print("🚨"); //must use double quotes to avoid character literal error
				if(table[i][x] == 1)
          System.out.print("🌖"); //must use double quotes to avoid character literal error
        if(table[i][x] == 2)
          System.out.print("🌎"); //must use double quotes to avoid character literal error
				if(table[i][x] == 3)
          System.out.print("🔥"); //must use double quotes to avoid character literal error
        if(table[i][x] == 4)
          System.out.print("👾"); //must use double quotes to avoid character literal error
			}
			System.out.println();
		}
		System.out.println();
	}
}