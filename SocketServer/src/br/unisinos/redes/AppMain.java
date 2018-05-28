package br.unisinos.redes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppMain {
	private static final String FIM = "Fim";
	private static String nomeDoCaboclo;
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static void main(String[] args) throws IOException {
		String in = "";
		nomeDoCaboclo = "";

		ConsoleReader reader = new ConsoleReader();
		System.out.println("Olá. Informe seu nome.");
		nomeDoCaboclo = reader.read();
		System.out.println(String.format("Bem vindo %s!", nomeDoCaboclo));
		System.out.println(String.format("Estamos ouvindo. Para encerrar, diga %s.", FIM));

		do {
			in = reader.read();
			logger(in);

			if (FIM.equalsIgnoreCase(in)) {
				loggerEndMessage();
			}
		} while (!FIM.equalsIgnoreCase(in));
	}

	private static void loggerEndMessage() {
		System.out.println(String.format("Adeus %s.\n%s - %s saiu.",  nomeDoCaboclo, getTime(), nomeDoCaboclo));
	}

	private static void logger(String mensagem) {
		System.out.println(String.format("%s - %s diz: %s", getTime(), nomeDoCaboclo, mensagem));
	}

	private static String getTime() {
		return SDF.format(Calendar.getInstance().getTime());
	}

}
