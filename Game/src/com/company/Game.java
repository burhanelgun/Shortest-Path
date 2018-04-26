package com.company;
import java.util.*;

public class Game {

    private Cell[][] gameBoard;  // Composition(Has-A relation) ilkesi kullanılarak Cell ler den oluşan iki boyutlu bir gameBoard arrayi
    private int shortestPathLengthSoFar = Integer.MAX_VALUE;
    private int mazeWidth = 0;
    private int mazeHeight = 0;

    Game(){
        gameBoard=new Cell[8][8];
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                gameBoard[i][j]=new Cell(0);//ilk başta tüm boardı boş olarak oluşturdum.
            }
        }

        Random r=new Random();
        int a=r.nextInt(9)+1;  // kaç tane siyah pul olacağını random olarak üretir
        for(;a>0;a--){
            int i=r.nextInt(8); // hangi satıra siyah pulun ekleneceğini random olarak belirler
            int j=r.nextInt(7);  //hangi sütuna siyah pulun ekleneceğini random olarak belirler
            gameBoard[i][j].setSituation(1);// random yerlere 0 ile 9 arasında miktarda (random belirlenir) 1 değerini verdim(siyah pul)
        }

        gameBoard[7][0].setSituation(3); // A1 hücresine yani 7. satır 0. sutüna 3 değerini verdim(beyaz pul)
        gameBoard[0][7].setSituation(4); // H8 hücresine yani 0. satır 7. sutüna 4 değerini verdim(bitiş noktası)

        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                gameBoard[i][j].setName(""+(char)(65+j)+(8-i)); // her bir hücreyi adlandırdım(A1, A3, H1,H2 ,B1, B4 gibi)
                gameBoard[i][j].setCol(j);
                gameBoard[i][j].setRow(i);
            }
        }
    }


    public Cell[][] getGameBoard() {
        return gameBoard;
    }


    public void printBoard(){   // boardı print eder
        System.out.print("   ");

        for (int i = 0;i<8;i++){
            System.out.print((char)(65+i)+" ");  // sütun numarasını A B C D gibi harflere çevirdim
        }
        System.out.println();
        System.out.print("   ");

        for (int i = 0;i<8;i++){
            System.out.print("-"+" ");
        }
        System.out.println();
        for(int i =0;i<8;i++){
            System.out.print(""+(8-i)+ "| ");
            for(int j=0;j<8;j++){
                if(gameBoard[i][j].getSituation()==3){
                    System.out.print("B ");
                }
                else if(gameBoard[i][j].getSituation()==1){
                    System.out.print("S ");
                }
                else if(gameBoard[i][j].getSituation()==4){
                    System.out.print("0 ");
                }
                else {
                    System.out.print(gameBoard[i][j]+ "");
                }
            }
            System.out.print("|"+(8-i));
            System.out.println();
        }
        System.out.print("   ");
        for (int i = 0;i<8;i++){
            System.out.print("-"+" ");
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0;i<8;i++){
            System.out.print((char)(65+i)+" ");
        }
    }

    public Cell getGameBoard(int i,int j) {  //getter
        return gameBoard[i][j];
    }


    public static int shortestPath(Cell[][] map, Cell start, Cell end, Stack<Cell> path) {  // map i iki parçaya böldüm ilk parça ilk siyah pul görülen sütuna kadar olan parça dır. İlk parçada görülen siyah noktanın boş olan komşusunu bitiş noktası olarak kabul ettim.
                                                                                            //Ve bu ilk parçanın en kısa pathini buldum bu sayede de bir siyah nokta ile karşılaşmış oldum.
        for(int i=7;i>-1;i--){                                                              //sonra diğer parçayıda, kaldığım yer ile H8 arasındaki en kısa mesafeyi hesaplayarak bitirdim.Sonra bu iki parçanın en kısa mesafeleri Stackte topladı ve tek bir path haline geldi.
            for(int j=0;j<8;j++){
                if(map[i][j].getSituation()==1){
                    if(j+1<8){
                        if(map[i][j+1].getSituation() == 0){
                            if(j+2<8){
                                if(map[i][j+2].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i , j+2 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i , j+1), path) + 2;
                                }
                            }
                            if(i-1>-1 && j+1<8){
                                if(map[i-1][j+1].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i -1, j+1 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i , j+1), path) + 2;
                                }
                            }
                            if(i+1<8 && j+1<8){
                                if(map[i+1][j+1].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i +1, j+1 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i , j+1), path) + 2;
                                }
                            }
                        }
                    }
                    if(i+1<8 && j+1<8){
                        if(map[i+1][j + 1].getSituation() == 0){
                            if(i+1<8 && j+2<8){
                                if(map[i+1][j+2].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i+1 , j+2 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i+1 , j+1), path) + 2;
                                }
                            }
                            if(i+1<8 && j+1<8){
                                if(map[i+1][j+1].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i , j+1 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i+1 , j+1), path) + 2;
                                }
                            }
                            if(i+2<8 && j+1<8){
                                if(map[i+2][j+1].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i +2, j+1 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i+1 , j+1), path) + 2;
                                }
                            }

                        }
                    }
                    if(i-1>-1 && j+1<8){
                        if(map[i-1][j + 1].getSituation() == 0){
                            if(i-1>-1 && j+2<8){
                                if(map[i-1][j+2].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i-1 , j+2 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i-1 , j+1), path) + 2;
                                }
                            }

                            if(j+1<8){
                                if(map[i][j+1].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i , j+1 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i , j+1), path) + 2;
                                }
                            }

                            if(i-2>-1 && j+1<8){
                                if(map[i-2][j+1].getSituation() == 0){
                                    return shortestPathHelper(map, new Cell(i-2, j+1 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i-2 , j+1), path) + 2;
                                }
                            }

                        }
                    }
                    if(map[i][j].getSituation() == 0){
                        if(map[i][j+1].getSituation() == 0){
                            return shortestPathHelper(map, new Cell(i , j+1 ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i , j), path) + 2;
                        }
                        if(map[i-1][j].getSituation() == 0){
                            return shortestPathHelper(map, new Cell(i-1 , j ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i , j), path) + 2;
                        }
                        if(map[i+1][j].getSituation() == 0){
                            return shortestPathHelper(map, new Cell(i+1, j ), new Cell(0, 7), path) + shortestPathHelper(map, new Cell(7, 0), new Cell(i , j), path) + 2;
                        }
                    }
                }
            }
        }
        return 0;
    }


    public void karsilasilanSiyahPullar(Cell b, List<String> a) {   //karşılaşılan siyah pulları parametre olarak boş gelen listenin içine atar
        if (b.getCol() + 1 < 8) {
            if (getGameBoard(b.getRow(), b.getCol() + 1).getSituation() == 1) {
                if (!a.contains(getGameBoard(b.getRow(), b.getCol() + 1).getName())) {
                    a.add(getGameBoard(b.getRow(), b.getCol() + 1).getName());
                }
            }
        }
        if (b.getCol() - 1 > -1) {
            if (getGameBoard(b.getRow(), b.getCol() - 1).getSituation() == 1) {

                if (!a.contains(getGameBoard(b.getRow(), b.getCol() - 1).getName())) {
                    a.add(getGameBoard(b.getRow(), b.getCol() - 1).getName());
                }
            }
        }
        if (b.getRow() + 1 < 8) {
            if (getGameBoard(b.getRow() + 1, b.getCol()).getSituation() == 1) {

                if (!a.contains(getGameBoard(b.getRow() + 1, b.getCol()).getName())) {
                    a.add(getGameBoard(b.getRow() + 1, b.getCol()).getName());
                }
            }
        }
        if (b.getRow() - 1 > -1) {
            if (getGameBoard(b.getRow() - 1, b.getCol()).getSituation() == 1) {

                if (!a.contains(getGameBoard(b.getRow() - 1, b.getCol()).getName())) {
                    a.add(getGameBoard(b.getRow() - 1, b.getCol()).getName());
                }
            }
        }
    }


    public static int shortestPathHelper(Cell[][] map, Cell start, Cell end, Stack<Cell> path) { //shortestPath methodunun içinde çağırılır iki defa çağırıldığı için bu methodun yazılmasına ihtiyaç duyulmuştur
        int[][] distances = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            distances[i] = new int[map[i].length];
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        int distance = 0;
        List<Cell> currentCells = Arrays.asList(start);
        int flag = 0;
        while (distances[end.getRow()][end.getCol()] == Integer.MAX_VALUE && !currentCells.isEmpty()) {
            List<Cell> nextCells = new ArrayList<>();
            for (Cell cell : currentCells) {
                if (distances[cell.getRow()][cell.getCol()] == Integer.MAX_VALUE && !(map[cell.getRow()][cell.getCol()].getSituation() == 1)) {
                    distances[cell.getRow()][cell.getCol()] = distance;
                    addNeighbors(cell, nextCells, map.length, map[0].length);
                }
            }
            currentCells = nextCells;
            distance++;
        }

        if (distances[end.getRow()][end.getCol()] < Integer.MAX_VALUE) { //path bulunur
            Cell cell = end;
            path.push(end);
            for (int d = distances[end.getRow()][end.getCol()] - 1; d >= 0; d--) {
                cell = getNeighbor(cell, d, distances);
                path.push(cell);
            }
        }
        return distances[end.getRow()][end.getCol()];
    }


    private static void addNeighbors(Cell cell, List<Cell> list, int maxRow, int maxCol) { // cell in tüm komşuları listeye atılır
        int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : ds) {
            int row = cell.getRow() + d[0];
            int col = cell.getCol() + d[1];
            if (isValid(row, col, maxRow, maxCol)){
                list.add(new Cell(row, col));
            }
        }
    }


    private static Cell getNeighbor(Cell cell, int distance, int[][] distances) { // bir cellin komşularını bulur
        int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : ds) {
            int row = cell.getRow() + d[0];
            int col = cell.getCol() + d[1];
            if (isValid(row, col, distances.length, distances[0].length) && distances[row][col] == distance){
                return new Cell(row, col);
            }
        }
        return null;
    }


    private static boolean isValid(int row, int col, int maxRow, int maxCol) { // koordinatların doğruluğunu kontrol eder
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    }

}
