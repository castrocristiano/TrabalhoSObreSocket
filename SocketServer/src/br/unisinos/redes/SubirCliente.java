package br.unisinos.redes;

import java.io.IOException;

import br.unisinos.redes.socket.GerenciadorDeSocket;

public class SubirCliente {
	public static void main(String[] args) throws IOException {
		new Thread(new GerenciadorDeSocket("localhost", 1234, 1233).iniciarCliente()).start();
	}
}
