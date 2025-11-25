public class Music {
    String title, artist, album, genre;
    boolean approved;

    public Music(String title, String artist, String album, String genre) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.approved = false;
    }

    public String toString() {
        return title + " | " + artist + " | " + album + " | " + genre +
               (approved ? " | Approved" : " | Not approved");
    }
}
