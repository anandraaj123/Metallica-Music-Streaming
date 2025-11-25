import java.util.*;

public class Platform {

    List<User> users = new ArrayList<>();
    List<Music> musicContents = new ArrayList<>();
    List<Playlist> playlists = new ArrayList<>();

    // USER MANAGEMENT ==========================
    String manageUser(String name, String email, Role role, String action) {
        switch (action) {

            case "create":
                users.add(new User(name, email, role));
                return "User created successfully";

            case "update":
                for (User user : users) {
                    if (user.email.equals(email)) {
                        user.name = name;
                        user.role = role;
                        return "User updated successfully";
                    }
                }
                return "User not found";

            case "delete":
                return users.removeIf(user -> user.email.equals(email))
                        ? "User deleted successfully"
                        : "User not found";
        }
        return "Invalid action";
    }

    // MUSIC ==========================
    String uploadMusic(String title, String artist, String album, String genre) {
        musicContents.add(new Music(title, artist, album, genre));
        return "Music uploaded successfully";
    }

    String approveMusic(String title, boolean approve) {
        for (Music music : musicContents)
            if (music.title.equalsIgnoreCase(title)) {
                music.approved = approve;
                return approve ? "Music approved" : "Music rejected";
            }
        return "Music not found";
    }

    String streamMusic(String title) {
        for (Music music : musicContents)
            if (music.title.equalsIgnoreCase(title) && music.approved)
                return "Streaming: " + title + " by " + music.artist;

        return "Music not available";
    }

    // PLAYLIST ==========================

    Playlist createPlaylist(String title) {
        Playlist p = new Playlist(title);
        playlists.add(p);
        return p;
    }

    String followArtist(String name) {
        return "Now following artist: " + name;
    }

    void showMusic() {
        if (musicContents.isEmpty()) {
            System.out.println("No music uploaded.");
            return;
        }
        for (Music m : musicContents)
            System.out.println(m);
    }

    void showPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists created.");
            return;
        }
        for (Playlist p : playlists)
            System.out.println(p);
    }
}
