package model;

import exception.MovimentoInvalidoException;

//import java.util.ArrayList;

public class RoboInteligente extends Robo {
	private boolean ultimoMove;
	
	public RoboInteligente(String cor) {
		super(cor);
		ultimoMove = true;
	}
	
	public void mover(int tecla) throws MovimentoInvalidoException { //recebe um parametro aleatorio da main
        if(ultimoMove==true) {
            try {
                super.mover(tecla);
                return;
            }catch(Exception e){
                ultimoMove=false;
                e.getMessage();
                }
        }else {
            movimentoPerfeito();
        }
	}
	
	public void movimentoPerfeito() {
        quantMoves = quantMoves + 1;
        while(true) {
            int tecla = (int)Math.floor(Math.random() *(4-1)+1);
            switch(tecla) {
            case 1:
                if(y!=4) {
                    y++;
                    break;
                }else {
                    continue;
                }
            case 2:
                if(y==0) {
                    continue;
                }else {
                    y--;
                }
                break;
            case 3:
                if(x!=4) {
                    x++;
                    break;
                }else {
                    continue;
                }
            case 4:
                if(x==0) {
                    continue;
                }else {
                    x--;
                }
                break;
            }
            ultimoMove=true;
            break;
        }
    }
}
