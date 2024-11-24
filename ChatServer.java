import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Program utama untuk menjalankan server
public class ChatServer {
    public static void main(String[] args) {
        try {
            // Membuat instance dari ChatServiceImpl
            ChatService chatService = new ChatServiceImpl();

            // Membuat registry RMI pada port 2000
            Registry registry = LocateRegistry.createRegistry(2001);

            // Mendaftarkan objek remote ke registry dengan nama "ChatService"
            registry.rebind("ChatService", chatService);

            // Menampilkan pesan bahwa server siap menerima koneksi
            System.out.println("Chat Server siap berjalan...");
        } catch (Exception e) {
            // Menangani kesalahan dan menampilkan error di konsol
            e.printStackTrace();
        }
    }
}
