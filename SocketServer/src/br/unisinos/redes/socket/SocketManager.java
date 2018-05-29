package br.unisinos.redes.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketManager {
	private ServerSocket socket;
	private int portOut;
	private int portIn;
	private String clienteHost;
	
	/**
	 * Construtor.
	 * 
	 * 
	 * 
	 * @param portOut - Porta de saída, a qual enviará mensagens para outro servidor.
	 * @param portIn - Porta de escuta.
	 */
	public SocketManager(String clienteHost, int portOut, int portIn) {
		super();
		this.portOut = portOut;
		this.portIn = portIn;
		this.clienteHost = clienteHost;
	}

	private Socket getSocket(String clienteHost, int port) throws IOException {
		if(clienteHost != null && !clienteHost.trim().isEmpty()) {
			InetAddress i = InetAddress.getByName(clienteHost);
			socket = new ServerSocket(port, 0, i);
		} else {
			socket = new ServerSocket(port);
		}

		Socket cliente = socket.accept();

		return cliente;
	}
	
	/**
	 * Inicia uma thread e abre a porta para escuta.
	 * @return
	 * @throws IOException
	 */
	public Runnable listener() throws IOException {
		final Socket socket = getSocket("", portIn);
		return new Runnable() {

			public void run() {
				InputStream is;
				try {
					is = socket.getInputStream();
					Scanner entrada = new Scanner(is);
					
					while (entrada.hasNextLine()) {
						String mensagem = entrada.nextLine();
						System.out.println(mensagem);
						
						checkSaida(mensagem, socket);
					}
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};

	}
	
	/**
	 * Inicia uma thread e abre a porta para a escrita.
	 * @return
	 * @throws IOException
	 */
	public Runnable caller() throws IOException {
		final Socket socket = getSocket(clienteHost, portOut);
		return new Runnable() {

			public void run() {
				PrintStream saida;
				try {
					saida = new PrintStream(socket.getOutputStream());

					Scanner sc = new Scanner(System.in);

					while (sc.hasNextLine()) {
						String mensagem = sc.nextLine();
						saida.println(mensagem);
						checkSaida(mensagem, socket);
					}
					
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
	}
	
	/**
	 * Verifica se é a mensagem de saída.
	 * @param mensagem
	 * @param socket 
	 * @throws IOException 
	 */
	private void checkSaida(String mensagem, Socket socket) throws IOException {
		if("FIM".equalsIgnoreCase(mensagem)) {
			if (socket.isConnected()) {
				System.exit(0);
			}
		}
	}
}
