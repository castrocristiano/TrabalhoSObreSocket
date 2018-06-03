package br.unisinos.redes.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class Cliente {
	public static void main(String argv[]) throws Exception {
		String mensagemParaServidor;
		String respostaServidor;
		BufferedReader digitado = new BufferedReader(new InputStreamReader(System.in));
		Socket meuSocket = new Socket("localhost", 6789);
		DataOutputStream saida = new DataOutputStream(meuSocket.getOutputStream());
		BufferedReader entrada = new BufferedReader(new InputStreamReader(meuSocket.getInputStream()));
		mensagemParaServidor = digitado.readLine();
		saida.writeBytes(mensagemParaServidor + '\n');
		respostaServidor = entrada.readLine();
		System.out.println("Servidor enviou: " + respostaServidor);
		meuSocket.close();
	}
}
