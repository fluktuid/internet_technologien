package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

public class Quote{
	String text="";
	String whom="";
	private static final int quotesInFileCount = 24623;
	public Quote(ServletContext context) throws IOException {
		String path = context.getRealPath("/ZITATE/zitate.csv");
		
		int lineNr = 0 + (int)(Math.random() * ((quotesInFileCount - 0) + 1));
		String line;
		
		//Liest die Linie an der Stelle 'lineNr' ein
		try (Stream<String> lines = Files.lines(Paths.get(path))) {
		    line = lines.skip(lineNr).findFirst().get();
		}
		
		line = line.replaceAll("\\\\\"", "");
		String[] splitted = line.split(";");
		text=splitted[1].substring(1, splitted[1].length()-1);
		whom=splitted[2].substring(1, splitted[2].length()-1);
	}
	
	public String getText() {
		return text;
	}
	
	public String getWhom() {
		return whom;
	}
}
