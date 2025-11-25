import java.util.*;

public class Playlist {

    String title;
    List<Music> songs = new ArrayList<>();

    public Playlist(String title) {
        this.title = title;
    }

    public void addSong(Music song) {
        songs.add(song);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(title + ": ");
        for (Music m : songs) sb.append(m.title).append(", ");
        return sb.toString();
    }
}
