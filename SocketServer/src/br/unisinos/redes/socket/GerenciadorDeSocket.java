package br.unisinos.redes.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * 
 * @author Cristiano Castro
 *
 */
public class GerenciadorDeSocket {
	public static final String FIM = "FIM";
	private ServerSocket servidorSocket;
	private int portaSaida;
	private int portaEntrada;
	private String clienteHost;
	
	/**
	 * Construtor.
	 * 
	 * 
	 * 
	 * @param portOut - Porta de saída, a qual enviará mensagens para outro servidor.
	 * @param portIn - Porta de escuta.
	 */
	public GerenciadorDeSocket(String clienteHost, int portOut, int portIn) {
		super();
		this.portaSaida = portOut;
		this.portaEntrada = portIn;
		this.clienteHost = clienteHost;
	}

	protected Socket getSocket(String clienteHost, int port) throws IOException {
		if(clienteHost != null && !clienteHost.trim().isEmpty()) {
			InetAddress endereco = InetAddress.getByName(clienteHost);
			servidorSocket = new ServerSocket(port, 0, endereco);
		} else {
			servidorSocket = new ServerSocket(port);
		}

		Socket cliente = servidorSocket.accept();

		return cliente;
	}
	
	protected Socket getSocket(int porta) throws IOException {
		return getSocket("", porta);
	}
	
	/* (non-Javadoc)
	 * @see br.unisinos.redes.socket.SocketsManager#iniciarServidor()
	 */
	public Runnable iniciarServidor() throws IOException {
		final Socket socket = getSocket(portaEntrada);
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
	
	/* (non-Javadoc)
	 * @see br.unisinos.redes.socket.SocketsManager#iniciarCliente()
	 */
	public Runnable iniciarCliente() throws IOException {
		final Socket socket = getSocket(clienteHost, portaSaida);
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
	 * Verifica se é a mensagem de saída, se for, sai do programa.
	 * @param mensagem
	 * @param socket 
	 * @throws IOException 
	 */
	public static void checkSaida(String mensagem, Socket socket) throws IOException {
		if(FIM.equalsIgnoreCase(mensagem)) {
			if (socket.isConnected()) {
				System.exit(0);
			}
		}
	}
}
