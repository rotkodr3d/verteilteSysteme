package aufgabe5.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import aufgabe5.Reply;

public interface ServerInterface<T> extends Remote{
	
	public void addToBuffer(T element) throws RemoteException;
	
	public Reply<T> removeFromBuffer() throws RemoteException;
	
	public Reply<Integer> getBufferSize() throws RemoteException;
	
	public Reply<Integer> getAmountOfBufferEntries() throws RemoteException;
}
