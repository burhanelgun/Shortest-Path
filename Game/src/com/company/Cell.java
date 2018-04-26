package com.company;


public class Cell {

    private String name; // Cell in adını tutar A8, A1, H8 gibi
    private int row; // cell in row unu tutar
    private int col; //cell in column unu tutar
    private int situation; // cell in durumunu tutar(0 = boş, 1=siyah pul, 3=beyaz pul(A1), 4=bitiş noktası(H8)

    Cell(int situation){
        this.situation=situation;
    }
    Cell(int row,int col)
    {
        this.row=row;
        this.col=col;

    }
    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {

        return situation+" " ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return ""+(char)(65+col)+""+(8-row); // col ve row a göre A8,H5 gibi cell adlarını oluşturma
    }

}
