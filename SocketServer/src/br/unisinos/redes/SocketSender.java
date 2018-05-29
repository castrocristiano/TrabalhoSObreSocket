package br.unisinos.redes;

import java.io.IOException;

import br.unisinos.redes.socket.GerenciadorDeSocket;

public class SocketSender {

	public static void main(String[] args) throws IOException {
		GerenciadorDeSocket s = new GerenciadorDeSocket("localhost", 1234, 1233);
		new Thread(s.iniciarServidor()).start();
		new Thread(s.iniciarCliente()).start();
	}

}
