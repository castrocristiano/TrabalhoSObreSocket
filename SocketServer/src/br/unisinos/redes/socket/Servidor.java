package br.unisinos.redes.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Servidor {
	public static void main(String argv[]) throws Exception {
		String mensagemRecebida;
		String mendagemEnviada = "Obrigado!";
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(6789);
		while (true) {
			Socket meuSocket = serverSocket.accept();
			BufferedReader entrada = new BufferedReader(new InputStreamReader(meuSocket.getInputStream()));
			DataOutputStream saida = new DataOutputStream(meuSocket.getOutputStream());
			mensagemRecebida = entrada.readLine();
			System.out.println("Cliente enviou: " + mensagemRecebida);
			saida.writeBytes(mendagemEnviada);
		}
	}
}
