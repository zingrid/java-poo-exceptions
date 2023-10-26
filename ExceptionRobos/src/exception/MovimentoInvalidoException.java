package exception;

public class MovimentoInvalidoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	// Para o caso do up, down, right, left
	public MovimentoInvalidoException(String comando) {
		System.out.println("Movimento" + comando + "inválido");
	}
	
	// Para o caso do 1, 2, 3, 4
	public MovimentoInvalidoException(int tecla) {
		System.out.println("Movimento " + tecla + " inválido");
	}

}
