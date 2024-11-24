import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interface remote untuk mendefinisikan metode yang dapat dipanggil secara jarak jauh
public interface ChatService extends Remote {
    // Metode untuk mengirim pesan ke server
    void sendMessage(String clientName, String message) throws RemoteException;

    // Metode untuk mendapatkan semua pesan yang diterima server
    List<String> getMessages() throws RemoteException;
}