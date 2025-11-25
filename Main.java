import java.util.*;

public class Main {

    static String selectGenre(Scanner sc) {
        System.out.println("Choose music genre:");
        for (int i = 0; i < GenreData.GENRES.length; i++)
            System.out.println((i + 1) + ". " + GenreData.GENRES[i]);

        System.out.print("Enter genre number: ");
        int option = sc.nextInt();
        sc.nextLine();

        return (option >= 1 && option <= GenreData.GENRES.length)
                ? GenreData.GENRES[option - 1]
                : "Other";
    }

    public static void main(String[] args) {

        Platform platform = new Platform();
        Scanner sc = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("        ALL USER OPTIONS");
        System.out.println("====================================");
        System.out.println("ADMIN OPTIONS");
        System.out.println("  1. Create user");
        System.out.println("  2. Update user");
        System.out.println("  3. Delete user");
        System.out.println("  4. Approve music");
        System.out.println("------------------------------------");
        System.out.println("ARTIST OPTIONS");
        System.out.println("  5. Upload music");
        System.out.println("  6. Interact with fans");
        System.out.println("  7. Track music performance");
        System.out.println("------------------------------------");
        System.out.println("LISTENER OPTIONS");
        System.out.println("  8. Stream music");
        System.out.println("  9. Create playlist");
        System.out.println(" 10. Add music to playlist");
        System.out.println(" 11. Follow artist");
        System.out.println("====================================");

        while (true) {

            System.out.print("\nSelect your role (ADMIN, ARTIST, LISTENER) or EXIT: ");
            String roleStr = sc.nextLine().trim().toUpperCase();

            if (roleStr.equals("EXIT")) break;
            Role role = Role.valueOf(roleStr);

            System.out.print("Select an option (0 to exit): ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 0) break;

            switch (option) {

                case 1:
                    System.out.print("Enter user (name email role): ");
                    String name1 = sc.next(), email1 = sc.next(), r1 = sc.next();
                    System.out.println(platform.manageUser(name1, email1, Role.valueOf(r1.toUpperCase()), "create"));
                    sc.nextLine();
                    break;

                case 2:
                    System.out.print("Enter details (name email role): ");
                    String name2 = sc.next(), email2 = sc.next(), r2 = sc.next();
                    System.out.println(platform.manageUser(name2, email2, Role.valueOf(r2.toUpperCase()), "update"));
                    sc.nextLine();
                    break;

                case 3:
                    System.out.print("Enter email to delete: ");
                    System.out.println(platform.manageUser("", sc.next(), null, "delete"));
                    sc.nextLine();
                    break;

                case 4:
                    System.out.print("Approve music (title): ");
                    System.out.println(platform.approveMusic(sc.nextLine(), true));
                    break;

                case 5:
                    System.out.print("Upload music (title album): ");
                    String title5 = sc.next(), album5 = sc.next();
                    String genre5 = selectGenre(sc);
                    System.out.println(platform.uploadMusic(title5, "ArtistName", album5, genre5));
                    sc.nextLine();
                    break;

                case 6:
                    System.out.print("Message to fans: ");
                    System.out.println("Interaction sent: " + sc.nextLine());
                    break;

                case 7:
                    System.out.print("Enter music title: ");
                    System.out.println("Performance: Streams=1000, Likes=500");
                    break;

                case 8:
                    System.out.print("Stream music (title): ");
                    System.out.println(platform.streamMusic(sc.nextLine()));
                    break;

                case 9:
                    System.out.print("Playlist title: ");
                    Playlist p = platform.createPlaylist(sc.nextLine());
                    System.out.print("Add song: ");
                    String song9 = sc.nextLine();

                    platform.musicContents.stream()
                            .filter(m -> m.title.equalsIgnoreCase(song9))
                            .findFirst()
                            .ifPresent(p::addSong);

                    System.out.println("Added!");
                    break;

                case 10:
                    System.out.print("Playlist: ");
                    String pl = sc.nextLine();
                    System.out.print("Song: ");
                    String song10 = sc.nextLine();

                    for (Playlist plist : platform.playlists) {
                        if (plist.title.equalsIgnoreCase(pl)) {
                            platform.musicContents.stream()
                                    .filter(m -> m.title.equalsIgnoreCase(song10))
                                    .findFirst()
                                    .ifPresent(plist::addSong);
                            System.out.println("Added!");
                        }
                    }
                    break;

                case 11:
                    System.out.print("Artist name: ");
                    System.out.println(platform.followArtist(sc.nextLine()));
                    break;

                default:
                    System.out.println("Invalid option!");
            }

            System.out.println("\nCurrent Music:");
            platform.showMusic();
            System.out.println("\nCurrent Playlists:");
            platform.showPlaylists();
        }

        System.out.println("Exited. Thank you!");
        sc.close();
    }
}
