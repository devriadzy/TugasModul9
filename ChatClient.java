import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

// Program utama untuk menjalankan client
public class ChatClient {
    public static void main(String[] args) {
        try {
            // Menghubungkan ke RMI registry pada localhost di port 2000
            Registry registry = LocateRegistry.getRegistry("localhost", 2001);

            // Mendapatkan stub dari objek remote dengan nama "ChatService"
            ChatService chatService = (ChatService) registry.lookup("ChatService");

            // Scanner untuk input dari user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukkan nama Anda: ");
            String clientName = scanner.nextLine();

            // Membuat thread untuk mendengarkan pesan baru dari server
            Thread listenerThread = new Thread(() -> {
                try {
                    while (true) {
                        // Mendapatkan semua pesan dari server
                        List<String> messages = chatService.getMessages();

                        // Menampilkan pesan di konsol
                        System.out.println("---- Pesan Chat ----");
                        messages.forEach(System.out::println);

                        // Menunggu selama 3 detik sebelum memperbarui
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    // Menangani kesalahan pada listener
                    e.printStackTrace();
                }
            });

            // Memulai thread listener
            listenerThread.start();

            // Loop utama untuk mengirim pesan
            while (true) {
                System.out.print("Masukkan pesan: ");
                String message = scanner.nextLine();
                chatService.sendMessage(clientName, message); // Mengirim pesan ke server
            }
        } catch (Exception e) {
            // Menangani kesalahan pada client
            e.printStackTrace();
        }
    }
}
