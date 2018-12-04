package aufgabe5.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import aufgabe5.CircularBuffer;
import aufgabe5.Reply;

public class ServerImpl<T> extends UnicastRemoteObject implements ServerInterface<T> { // Object type of the server must
                                                                                       // be defined

	public CircularBuffer<T> cb;

	public static void main(String[] args) {
		try {
			ServerImpl<String> server = new ServerImpl<>();
			Registry registry = LocateRegistry.createRegistry(1099);
			System.out.println(server);
			registry.rebind("ServerInterface", server);
			System.out.println("Server waiting for requests");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ServerImpl() throws RemoteException {
		System.out.println("Define the size of the circular buffer: ");
		Scanner s = new Scanner(System.in);
		String size = s.next();
		cb = new CircularBuffer<>(Integer.parseInt(size));
		s.close();
	}

	@Override
	public void addToBuffer(T element) {
		cb.add(element);
	}

	@Override
	public Reply<T> removeFromBuffer() {
		return new Reply<T>(cb.pop());
	}

	@Override
	public Reply<Integer> getBufferSize() {
		return new Reply<Integer>(cb.getSize());
	}

	@Override
	public Reply<Integer> getAmountOfBufferEntries() {
		return new Reply<Integer>(cb.getAmountOfEntries());
	}
}
