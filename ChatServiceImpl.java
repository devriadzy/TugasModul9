import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

// Implementasi dari interface remote ChatService
public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    // List untuk menyimpan semua pesan yang diterima
    private final List<String> messages;

    // Konstruktor untuk menginisialisasi objek remote
    protected ChatServiceImpl() throws RemoteException {
        super();
        messages = new ArrayList<>();
    }

    @Override
    public synchronized void sendMessage(String clientName, String message) throws RemoteException {
        // Menambahkan pesan baru ke dalam list pesan
        String fullMessage = clientName + ": " + message;
        messages.add(fullMessage);
        System.out.println(fullMessage); // Menampilkan pesan di konsol server
    }

    @Override
    public synchronized List<String> getMessages() throws RemoteException {
        // Mengembalikan salinan list pesan untuk menghindari modifikasi
        return new ArrayList<>(messages);
    }
}
