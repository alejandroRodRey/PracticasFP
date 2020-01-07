import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		while (true) {
			System.err.println("Set a URL:");

			String scan = new Scanner(System.in).nextLine();

			InetAddress dir = InetAddress.getByName(scan);

			System.out.println("HostName: " + dir.getHostName() + "\nHostAddress: " + dir.getHostAddress()
					+ "\nCanonicalHostName: " + dir.getCanonicalHostName());

			URL url = new URL("http://" + scan);

			URLConnection con = url.openConnection();

			System.out.println("Content: " + con.getContent() + "\nDate: " + con.getDate() + "\nExpiration: "
					+ con.getExpiration());

			System.err.println("------------------------------");
		}
	}

}
