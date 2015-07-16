import retrofit.Callback;
import responses.*;
import retrofit.RetrofitError;
import retrofit.client.Response;
import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main{

		static String gify="a";
		static int num = 0;

	public static void main(String[] args) {
			
			Main osoFeo = new Main(); 
			osoFeo.askData();
			osoFeo.searchGifs(gify, num);
	}

	public void askData(){

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Buscar gifs de:");
		gify = scan.nextLine();
		System.out.println("Cantidad: ");
		num = scan.nextInt();
		System.out.println("");
	}

	public static void searchGifs(String term, int numImages){
		GiphyService service = new GiphyService();

		GiphyResponse gifs = service.searchGifs(term);
		int i = 0;
		int randy = 0;

		for (Gif gif : gifs.getData()) {
			i++;
			if(i <= numImages){
				randy = (int)Math.floor((float)Math.random()*gifs.getData().size());
				Gif rand = gifs.getData().get(randy);
				System.out.println(randy);
				Main.openInBrowser(rand.getImages().fixed_height.url);
			}
		}
	}

	public static void openInBrowser(String url){
		if(Desktop.isDesktopSupported()){
			try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
			
		}
	}
}