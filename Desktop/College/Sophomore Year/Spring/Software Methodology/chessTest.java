public class chessTest{
	public static void main(String[] args){
		String[][] positionStatus = {
						{"bR", "bN", "bB", "bQ", "bK", "bB", "bN", "bR"}, 
						{"bp", "bp", "bp","bp", "bp", "bp", "bp","bp"},
						{"  ", "##","  ", "##","  ", "##","  ", "##"},
						{"##", "  ", "##","  ", "##","  ", "##","  "},
						{"  ", "##","  ", "##","  ", "##","  ", "##"},
						{"##", "  ", "##","  ", "##","  ", "##","  "},
						{"wp","wp","wp","wp","wp","wp","wp","wp"},
						{"wR", "wN", "wB", "wQ", "wK", "wB", "wN", "wR"}

		};

		for(int i = 0; i < 8;i++){
			for(int j = 0; j < 8; j++ ){
				System.out.print(positionStatus[i][j] + " ");
			}
			System.out.println(8-i);
		}

		System.out.println("a  b  c  d  e  f  g  h  ");
	}
}