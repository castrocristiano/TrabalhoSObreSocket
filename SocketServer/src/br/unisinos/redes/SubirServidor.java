package br.unisinos.redes;

import java.io.IOException;

import br.unisinos.redes.socket.GerenciadorDeSocket;

public class SubirServidor {
	public static void main(String[] args) throws IOException {
		new Thread(new GerenciadorDeSocket("", 1233, 1234).iniciarServidor()).start();
	}
}
