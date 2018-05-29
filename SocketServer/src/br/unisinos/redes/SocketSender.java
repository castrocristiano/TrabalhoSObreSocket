package br.unisinos.redes;

import java.io.IOException;

import br.unisinos.redes.socket.SocketManager;

public class SocketSender {

	public static void main(String[] args) throws IOException {
		SocketManager s = new SocketManager("localhost", 1234, 1233);
		new Thread(s.caller()).start();
		new Thread(s.listener()).start();
	}

}
