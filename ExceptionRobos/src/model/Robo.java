package model;

import exception.MovimentoInvalidoException;

public class Robo {
	protected String cor;
	protected int x;
    protected int y;
    protected int quantMoves;
    protected int quantInvalidos;
    
    public static final String RESET = "\033[0m";
    public static final String VERMELHO = "\033[0;31m";  
    public static final String VERDE = "\033[0;32m";   
    public static final String AZUL = "\033[0;34m";
    public static final String ROXO = "\033[0;35m";
    public static final String AMARELO = "\033[0;33m";
    public static final String CIANO = "\033[0;36m";


    public Robo(String cor) {
        this.cor = cor;
        x = 0;
        y = 0;
        quantMoves = 0;
    }
    
    public String getCor() {
    	return cor;
    }
    
    public int getQuantMoves(){
        return quantMoves;
    }
    
    public int getQuantInvalidos() {
    	return quantInvalidos;
    }
    
    public void setQuantInvalidos(int quantInvalidos) {
    	this.quantInvalidos = quantInvalidos;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int novoX) {
        this.x = novoX;
}

    public int getY() {
        return y;
    }

    public void setY(int novoY) {
        this.y = novoY;
    }

    public void mover(String comando) throws MovimentoInvalidoException {
        quantMoves = quantMoves + 1;
        switch(comando) {
        case "up":
            y++;
            break;
        case "down":
            y--;
            if(y<0) {
                y++; 
                throw new MovimentoInvalidoException(" down ");
            }
            break;
        case "right":
            x++;
            break;
        case "left":
            x--;
            if(x<0) {
                x++;
                throw new MovimentoInvalidoException(" left ");
            }
            break;
        default:
        	throw new MovimentoInvalidoException(" " + comando + " ");
        }
    }

    public void mover(int tecla) throws MovimentoInvalidoException {
        quantMoves = quantMoves + 1;
        switch(tecla) {
        case 1:
            if(y==4) {
            	throw new MovimentoInvalidoException(1);
            }else {
            	y++;
                break;
            }
        case 2:
            if(y==0) {
                throw new MovimentoInvalidoException(2);
            }else {
                y--;
                break;
            }
        case 3:
            if(x==4) {
            	throw new MovimentoInvalidoException(3);
            }else {
                x++;
                break;
            }
        case 4:
            if(x==0) {
                throw new MovimentoInvalidoException(4);
            } else {
                x--;
                break;
            }
        default:
        	throw new MovimentoInvalidoException(tecla);
        }
    }

    public boolean encontrouAlimento(int xAlim, int yAlim) {
        if(x == xAlim && y == yAlim) {
            return true;
        }
        return false;
    }
    
    public void mostrarMatriz(int xAlim, int yAlim) {
    	// Plano cartesiano determinado varia de (0, 0) a (0, 4) no eixo y e de (0, 0) a (4, 0) no eixo x
    	for(int i=4; i>=0; i--) {
    		System.out.println(" ");
    		for(int j=0; j<=4; j++) {
    			if((i == y) && (j == x)) {
    				switch(cor) {
					case "verde":
						System.out.print(VERDE + "$     " + RESET);
						break;
					case "azul":
						System.out.print(AZUL + "$     " + RESET);
						break;
					case "roxo":
						System.out.print(ROXO + "$     " + RESET);
						break;
					case "amarelo":
						System.out.print(AMARELO + "$     " + RESET);
						break;
					case "ciano":
						System.out.print(CIANO + "$     " + RESET);
						break;
					}
    			} else if((i == yAlim) && (j == xAlim)) {
    				System.out.print(VERMELHO + "@     " + RESET);
    			} else {
    				System.out.print("*     ");
    			}
    		}
    	}
    	System.out.println();
    }
    
    public void mostrarMatriz(int xAlim, int yAlim, Robo outro) {
    	// Plano cartesiano determinado varia de (0, 0) a (0, 4) no eixo y e de (0, 0) a (4, 0) no eixo x
    	boolean mesmaPosicao = false; // boolean mesmaPosicao = false; ignora o fato de ambos começarem em 0-0
		for(int i=4; i>=0; i--) {
			System.out.println(" ");
			for(int j=0; j<=4; j++) {
				// executa enquanto r1 e r2 estiverem em posições diferentes
				if((this.x == outro.x) && (this.y == outro.y)) {
					mesmaPosicao = true;
				}
				
				if(!mesmaPosicao) {
					
					if(((i == this.y) && (j == this.x))) {
						switch(this.cor) {
						case "verde":
							System.out.print(VERDE + "$     " + RESET);
							break;
						case "azul":
							System.out.print(AZUL + "$     " + RESET);
							break;
						case "roxo":
							System.out.print(ROXO + "$     " + RESET);
							break;
						case "amarelo":
							System.out.print(AMARELO + "$     " + RESET);
							break;
						case "ciano":
							System.out.print(CIANO + "$     " + RESET);
							break;
						}
					}
					else if((i == outro.y) && (j == outro.x)) {
						switch(outro.cor) {
						case "verde":
							System.out.print(VERDE + "$     " + RESET);
							break;
						case "azul":
							System.out.print(AZUL + "$     " + RESET);
							break;
						case "roxo":
							System.out.print(ROXO + "$     " + RESET);
							break;
						case "amarelo":
							System.out.print(AMARELO + "$     " + RESET);
							break;
						case "ciano":
							System.out.print(CIANO + "$     " + RESET);
							break;
						}
					} else if((i == yAlim) && j == xAlim) {
	                    System.out.print(VERMELHO + "@     " + RESET);
	                } else {
	                    System.out.print("*     ");
	                }
				} 
				
				
				if(mesmaPosicao) {
					if(((i == this.y) && (j == this.x))) {
						switch(this.cor) {
						case "verde":
							System.out.print(VERDE + "$" + RESET + "-" + comparaCor(outro.cor) + "$   " + RESET);
							break;
						case "azul":
							System.out.print(AZUL + "$" + RESET + "-" + comparaCor(outro.cor) + "$   " + RESET);
							break;
						case "roxo":
							System.out.print(ROXO + "$" + RESET + "-" + comparaCor(outro.cor) + "$   " + RESET);
							break;
						case "amarelo":
							System.out.print(AMARELO + "$" + RESET + "-" + comparaCor(outro.cor) + "$   " + RESET);
							break;
						case "ciano":
							System.out.print(CIANO + "$" + RESET + "-" + comparaCor(outro.cor) + "$   " + RESET);
							break;
						}
					} else if((i == yAlim) && (j == xAlim)) {
						System.out.print(VERMELHO + "@     " + RESET);
	                } else {
	                	System.out.print("*     ");
	                }
				} 
					
			}
				
		}
		System.out.println();
    } 
    
    public void mostrarMatriz(int xAlim, int yAlim, Robo r2, Robo r3) {
    	boolean mesmaPosicao = false;
		for(int i=4; i>=0; i--) {
			System.out.println(" ");
			for(int j=0; j<=4; j++) {
				
				if((this.x == r2.x) && (this.y == r2.y)) {
					mesmaPosicao = true;
				}
				
				if(!mesmaPosicao) {
					
					if(((i == this.y) && (j == this.x))) {
						switch(this.cor) {
						case "verde":
							System.out.print(VERDE + "$     " + RESET);
							break;
						case "azul":
							System.out.print(AZUL + "$     " + RESET);
							break;
						case "roxo":
							System.out.print(ROXO + "$     " + RESET);
							break;
						case "amarelo":
							System.out.print(AMARELO + "$     " + RESET);
							break;
						case "ciano":
							System.out.print(CIANO + "$     " + RESET);
							break;
						}
					} else if((i == r2.y) && (j == r2.x)) {
						switch(r2.cor) {
						case "verde":
							System.out.print(VERDE + "$     " + RESET);
							break;
						case "azul":
							System.out.print(AZUL + "$     " + RESET);
							break;
						case "roxo":
							System.out.print(ROXO + "$     " + RESET);
							break;
						case "amarelo":
							System.out.print(AMARELO + "$     " + RESET);
							break;
						case "ciano":
							System.out.print(CIANO + "$     " + RESET);
							break;
						}
					}
					else if((i == r3.y) && (j == r3.x)) {
						switch(r3.cor) {
						case "verde":
							System.out.print(VERDE + "$     " + RESET);
							break;
						case "azul":
							System.out.print(AZUL + "$     " + RESET);
							break;
						case "roxo":
							System.out.print(ROXO + "$     " + RESET);
							break;
						case "amarelo":
							System.out.print(AMARELO + "$     " + RESET);
							break;
						case "ciano":
							System.out.print(CIANO + "$     " + RESET);
							break;
						}
					} else if((i == yAlim) && j == xAlim) {
	                    System.out.print(VERMELHO + "@     " + RESET);
	                } else {
	                    System.out.print("*     ");
	                }
				} 
				
				
				if(mesmaPosicao) {
					if(((i == this.y) && (j == this.x))) {
						switch(this.cor) {
						case "verde":
							System.out.print(VERDE + "$" + RESET + "-" + comparaCor(r2.cor) + "$   " + RESET);
							break;
						case "azul":
							System.out.print(AZUL + "$" + RESET + "-" + comparaCor(r2.cor) + "$   " + RESET);
							break;
						case "roxo":
							System.out.print(ROXO + "$" + RESET + "-" + comparaCor(r2.cor) + "$   " + RESET);
							break;
						case "amarelo":
							System.out.print(AMARELO + "$" + RESET + "-" + comparaCor(r2.cor) + "$   " + RESET);
							break;
						case "ciano":
							System.out.print(CIANO + "$" + RESET + "-" + comparaCor(r2.cor) + "$   " + RESET);
							break;
						}
					} else if((i == r3.y) && (j == r3.x)) {
						switch(r3.cor) {
						case "verde":
							System.out.print(VERDE + "$     " + RESET);
							break;
						case "azul":
							System.out.print(AZUL + "$     " + RESET);
							break;
						case "roxo":
							System.out.print(ROXO + "$     " + RESET);
							break;
						case "amarelo":
							System.out.print(AMARELO + "$     " + RESET);
							break;
						case "ciano":
							System.out.print(CIANO + "$     " + RESET);
							break;
						}
					} else if((i == yAlim) && (j == xAlim)) {
						System.out.print(VERMELHO + "@     " + RESET);
	                } else {
	                	System.out.print("*     ");
	                }
				} 
					
			}
				
		}
		System.out.println();
    }
    
	
	public static String comparaCor(String cor) {
		switch(cor) {
		case "verde":
			return VERDE;
		case "azul":
			return AZUL;
		case "roxo":
			return ROXO;
		case "amarelo":
			return AMARELO;
		case "ciano":
			return CIANO;
		}
		return " ";
	}
    
}



