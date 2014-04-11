public class Sudoku_Solve {
	public String[] solveSudoku(String[] board) {
		String[] res = new String[9];
		int[][] solve= new int[10][10];
		int[][] b = new int[10][10];
		char cur ='\0';
		int[][] mark = new int[82][10];
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9;j++) {
				cur = board[i - 1].charAt(j - 1);
				if( cur == '.') {
					b[i][j] = 0;
					solve[i][j] = 0;
				} else if(cur >= '1' && cur <= '9') {
					b[i][j] = cur - '0';
					solve[i][j] = cur - '0';
					int tmpNum = cur - '0';
					int rowNum = i;
					int colNum = j;
					this.markUnvalidNum(mark, rowNum, colNum, tmpNum);
				}
			}
		}
		int count = 0;
		while(this.isSolved(solve) == false) {
			for(int i = 1; i <= 9; i++) {
				for(int j = 1; j <= 9;j++) {
					if(solve[i][j] == 0) {
						int markNum = (i - 1) * 9 + j;
						if(this.isMarkCellHasEightNum(mark[markNum])) {
							solve[i][j] = this.getMarkCellHasEightIndex(mark[markNum]);
							this.markUnvalidNum(mark, i, j, solve[i][j]);
						}
					}
				}
			}
			count++;
			System.out.println("case :" + count);
			this.showNum(solve);
			if(count == 81) {
				break;
			}
			System.out.println("--------------------");
		}
		
		return res;
	}
	private void showNum(int[][] solve) {
		
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9;j++) {
				System.out.print(solve[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	private int getMarkCellHasEightIndex(int[] cell) {
		int index = 0;
		for(int i = 1; i <= 9;i++) {
			if(cell[i] == 0) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	private boolean isMarkCellHasEightNum(int [] cell) {
		int count = 0;
		for(int i = 1; i <= 9;i++) {
			count += cell[i];
		}
		return count == 8;
	}
	
	private void markUnvalidNum(int[][] mark, int row, int col, int num) {
		//mark the whole row
		for(int i = 1; i <= 9; i++) {
			int markRowNum = (row - 1) * 9 + i;
			mark[markRowNum][num] = 1;
		}
		
		//mark the whole column
		for(int i = 1; i <= 9; i++) {
			int markColNum = (i - 1) * 9 + col;
			mark[markColNum][num] = 1;
		}
		
		//mark the cell
		//cell num
		/*
		 * 1 2 3
		 * 4 5 6
		 * 7 8 9
		 * 
		 */
		int cellNum = ((row - 1) / 3) * 3 + ((col - 1) / 3) + 1;
		for(int i = 1; i <= 3; i++) {
			for(int j = 1; j <= 3; j++) {
				int cellRow = ((cellNum - 1) / 3) * 3 + i ;
				int cellCol = ((cellNum - 1) % 3) * 3 + j;
				int markCellNum = (cellRow - 1) * 9 + cellCol;
				mark[markCellNum][num] = 1;
			}
		}
		
	}
	
	private boolean isSolved(int[][] solve) {
		boolean solved = true;
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9;j++) {
				if(solve[i][j] == 0) {
					solved = false;
					break;
				}
			}
			if(solved == false) {
				break;
			}
		}
		return solved;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		String[] sudoku = {
				"..5.4....",
				".3...1.2.",
				"...798..5",
				".59....4.",
				"..2.1.6..",
				".73...19.",
				"5..174...",
				".4.6.3.8.",
				"....5.7.."
		};
		*/
		String[] sudoku = {
			"2...5..1.",
			"..8.7....",
			".5.38..6.",
			"..3...94.",
			"691...27.",
			".24...8..",
			".8.61..9.",
			"..9.4....",
			".4..2...3"
		};
		Sudoku_Solve solution = new Sudoku_Solve();
		solution.solveSudoku(sudoku);
	}
}
