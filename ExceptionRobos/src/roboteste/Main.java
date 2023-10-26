package roboteste;

import java.util.Scanner;

import exception.MovimentoInvalidoException;
import model.Robo;
import model.RoboInteligente;

public class Main{
	static int xAlim;
	static int yAlim;

	static Scanner scan = new Scanner(System.in);

	public static void posicaoAlimento() throws NumberFormatException {
		
		boolean precisaReiniciarLoop = true;
		while(precisaReiniciarLoop) {
			System.out.println("Digite a posição do alimento! Posições válidas: (0, 0) - (4, 4)");
			System.out.print("Posição x do alimento: ");
			String xAlimTeste = scan.next().toString();
			System.out.print("Posição y do alimento: ");
			String yAlimTeste = scan.next().toString();
			try {
				// se realmente foi um número, passa os valores lidos pra xAlim e yAlim
				xAlim = Integer.parseInt(xAlimTeste);
				yAlim = Integer.parseInt(yAlimTeste);
				if(((xAlim >= 0 && xAlim <= 4) && (yAlim >= 0 && yAlim <= 4))) {
					// se está nos confins da matriz, não precisa reiniciar o loop
					precisaReiniciarLoop = false;
				} else {
					System.out.println("Posição inválida! Por favor, digite uma posição válida!\n");
				}
			} catch (NumberFormatException e) {
				// se não ler um número, reinicia o loop pra tentar ler de novo
				System.out.println("Posição inválida! Por favor, digite uma posição válida!\n");
			}		
		}

    }
	
	public static void main(String[] args) throws InterruptedException, NumberFormatException {
		
		boolean inputInvalido = false;
		while(!inputInvalido) {
			System.out.println("-----------------------------------");
			System.out.println("Digite a opção desejada:");
			System.out.println("-----------------------------------");
			System.out.println("0 - Sair\n1 - Questão 1 \n2 - Questão 2\n3 - Questão 3");
			System.out.println("-----------------------------------");
			
			try {
				int escolhaTeste = scan.nextInt();
				String escolha = String.valueOf(escolhaTeste);
				while(!(escolha.equals("0"))) {
					switch(escolha) {
					case "1":
						main(1);
						break;
					case "2":
						main(0, 2);
						break;
					case "3":
						main(0, 0, 3);
						break;
					default:
						System.out.println("Opção inválida\n");
						break;
					}
					
					System.out.println("-----------------------------------");
					System.out.println("Digite a opção desejada:");
					System.out.println("-----------------------------------");
					System.out.println("0 - Sair\n1 - Questão 1 \n2 - Questão 2\n3 - Questão 3");
					System.out.println("-----------------------------------");
					escolha = scan.next();
				}
				System.exit(0);
			} catch (NumberFormatException e) {
				System.out.println("Opção inválida\n");
				inputInvalido = true;
			}
		}
		
	}
	
	public static void main(int num) throws InterruptedException, NumberFormatException {
		// Cores disponíveis para os robôs: amarelo, verde, azul, roxo

		posicaoAlimento();

		Robo robo = new Robo("roxo");

		// Método que printa a matriz com o alimento na posição lida e o robô na posição (0, 0)

		System.out.println("\nPOSIÇÃO INICIAL DO ROBÔ: (" + robo.getX() + ", " + robo.getY() + ")");
		robo.mostrarMatriz(xAlim, yAlim);
		System.out.println("-----------------------------------");

		while(!(robo.encontrouAlimento(xAlim, yAlim))) {
			System.out.print("\nDigite o comando para mover o robô: ");
			String comando = scan.next().toString();

			boolean numInvalido = false; // assume que o comando lido foi um número
	
			try {
				// se realmente foi um número, chama o método mover(int tecla)
				int tecla = Integer.parseInt(comando);
		
				if(!numInvalido) {
					try {
					robo.mover(tecla);
			
					} catch (MovimentoInvalidoException e) {
						e.getMessage();
					}
				}
		
			} catch (NumberFormatException e) {
				numInvalido = true;
			}

			// Se não foi um número, então chamamos o metodo mover(String comando)
			if(numInvalido) {
				try {
				robo.mover(comando);
				} catch (MovimentoInvalidoException e) {
					e.getMessage();
				}
			}
	
			// Mostra o robô se movendo em direção ao alimento; não permite que ele saia da matriz
			visualizar(robo);

		}

		System.out.println(" ");
		System.out.println("Alimento encontrado!\n");
	}
	
	public static void main(int num1, int num2) throws InterruptedException {
		int min = 1;
		int max = 4;
		int range = max - min + 1;

		posicaoAlimento();

		// Instancia dois robôs para que busquem aleatoriamente pelo alimento
		Robo robo1 = new Robo("ciano"); // trocar pra ciano
		Robo robo2 = new Robo("verde");
		
		// Mostrar as posições iniciais
		
		System.out.println("\nPOSIÇÃO INICIAL DOS ROBÔS: (" + robo1.getX() + ", " + robo1.getY() + ")");
		robo1.mostrarMatriz(xAlim, yAlim, robo2);
		System.out.println("-----------------------------------");
		
		/*
		boolean robo1AchouPrimeiro;
		// Antes, só fazendo robo1AchouPrimeiro = false, quando xAlim e yAlim eram 0, o código entendia que o segundo robô encontrava primeiro
		
		if(xAlim == 0 && yAlim == 0) {
			robo1AchouPrimeiro = true;
		} else {
			robo1AchouPrimeiro = false;
		} */
		
		if(xAlim == 0 && yAlim == 0) {
			System.out.println("Os robôs encontraram o alimento ao mesmo tempo");
		}
		boolean robo1AchouPrimeiro = false;
		// Loop roda enquanto nem robo1 nem robo2 encontram o alimento; se um dos dois encontrar, ele para
		while(!(robo1.encontrouAlimento(xAlim, yAlim)) && !(robo2.encontrouAlimento(xAlim, yAlim))) {
		// Tentar mover os robôs um por vez

			try {
				int randomComando = (int) Math.floor((Math.random() * range) + min);
				robo1.mover(randomComando);
			} catch (MovimentoInvalidoException e) {
				robo1.setQuantInvalidos(robo1.getQuantInvalidos() + 1);
				e.getMessage();
			}
			
			if(robo1.encontrouAlimento(xAlim, yAlim)) {
				robo1AchouPrimeiro = true;
				visualizar(robo1, robo2);
				break;
				//visualizar(robo1);
				//break; // pra não mostrar a matriz do robo2
			}

			try {
				int randomComando = (int) Math.floor((Math.random() * range) + min);
				robo2.mover(randomComando);
			} catch (MovimentoInvalidoException e) {
				robo2.setQuantInvalidos(robo2.getQuantInvalidos() + 1);
				e.getMessage();
			}
			
			visualizar(robo1, robo2);
			//visualizar(robo1);
			//visualizar(robo2);
			Thread.sleep(1000);
			
		}

		if(robo1AchouPrimeiro) {
			System.out.println("Robô " + robo1.getCor() + " encontrou o alimento em " + robo1.getQuantMoves() + " movimentos");
		} else {
			System.out.println("Robô " + robo2.getCor() + " encontrou o alimento em " + robo2.getQuantMoves() + " movimentos\n");
		}

		System.out.println("Movimentos inválidos do Robô " + robo1.getCor() + ": " + robo1.getQuantInvalidos());
    	System.out.println("Movimentos válidos do Robô " + robo1.getCor() + ": " + (robo1.getQuantMoves() - robo1.getQuantInvalidos()));
    	
    	System.out.println();
    	
    	System.out.println("Movimentos inválidos do Robô " + robo2.getCor() + ": " + robo2.getQuantInvalidos());
    	System.out.println("Movimentos válidos do Robô " + robo2.getCor() + ": " + (robo2.getQuantMoves() - robo2.getQuantInvalidos()));
    	
    	System.out.println();
		
	}

	public static void main(int num1, int num2, int num3) throws InterruptedException {

		posicaoAlimento();

		Robo r1 = new Robo("azul");
		RoboInteligente r2 = new RoboInteligente("amarelo");
		
		System.out.println("\nPOSIÇÃO INICIAL DOS ROBÔS: (" + r1.getX() + ", " + r1.getY() + ")");
		r1.mostrarMatriz(xAlim, yAlim, r2);
		System.out.println("-----------------------------------");
		
		while(!(r2.encontrouAlimento(xAlim, yAlim))||!(r1.encontrouAlimento(xAlim, yAlim))){
			
			if(!(r1.encontrouAlimento(xAlim, yAlim))) {
				try {
					r1.mover((int)Math.floor(Math.random()*(4) +1));
			    } catch(Exception e){
			    	e.getMessage();
			    }
			}
			
			if(!((r2.encontrouAlimento(xAlim, yAlim)))){
				 try {
					 r2.mover((int)Math.floor(Math.random()*(4) + 1));
				 } catch (MovimentoInvalidoException e) {
					e.getMessage();
				 }
				
		    }
			
			visualizar(r1, r2);
			
			Thread.sleep(1000);
			
		}
		
		System.out.println(r1.getQuantMoves()+" Movimentos do Robô Normal\n"+r2.getQuantMoves()+" Movimentos do Robô Inteligente\n");
	}

	public static void visualizar(Robo robo) {
		System.out.println("\n");
		System.out.println("POSIÇÃO DO ROBÔ: (" + robo.getX() + ", " + robo.getY() + ")");
		//System.out.println("Número de movimentos: " + robo.getQuantMoves());
		robo.mostrarMatriz(xAlim, yAlim);
	}
	
	
	public static void visualizar(Robo robo, Robo outro) {
		System.out.println("\n");
		System.out.println("POSIÇÃO DO ROBÔ " + robo.getCor() + ": (" + robo.getX() + ", " + robo.getY() + ")");
		System.out.println("Número de movimentos do Robô " + robo.getCor() + ": " + robo.getQuantMoves());
		System.out.println("\nPOSIÇÃO DO ROBÔ " + outro.getCor() + ": (" + outro.getX() + ", " + outro.getY() + ")");
		System.out.println("Número de movimentos do Robô " + outro.getCor() + ": " + outro.getQuantMoves());
		robo.mostrarMatriz(xAlim, yAlim, outro);
	}
	
	public static void visualizar(Robo r1, Robo r2, Robo r3) {
		System.out.println("\n");
		System.out.println("POSIÇÃO DO ROBÔ " + r1.getCor() + ": (" + r1.getX() + ", " + r1.getY() + ")");
		System.out.println("Número de movimentos do Robô " + r1.getCor() + ": " + r1.getQuantMoves());
		System.out.println("\nPOSIÇÃO DO ROBÔ " + r2.getCor() + ": (" + r2.getX() + ", " + r2.getY() + ")");
		System.out.println("Número de movimentos do Robô " + r2.getCor() + ": " + r2.getQuantMoves());
		System.out.println("\nPOSIÇÃO DO ROBÔ " + r3.getCor() + ": (" + r3.getX() + ", " + r3.getY() + ")");
		System.out.println("Número de movimentos do Robô " + r3.getCor() + ": " + r3.getQuantMoves());
		r1.mostrarMatriz(xAlim, yAlim, r2, r3);
	}
	
}