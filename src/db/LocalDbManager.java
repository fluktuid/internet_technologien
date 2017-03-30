package db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Die klasse kümmert sich darum den Json String auch lokal zu speichern
 * 
 * @author fluktuid
 *
 */
class LocalDbManager {

    static boolean writeData(String path, String input) {
    	File f = new File(path+"/Warehouse/books.json");
    	f.getParentFile().mkdirs();
    	try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        try (java.io.FileWriter writer = new java.io.FileWriter(f)) {
        	System.out.println("path:"+path);
            writer.write(input);
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
        return true;
    }

    static String getData(String path) {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(path+"/Warehouse/books.json"))) {
            stream.forEachOrdered(builder::append);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
        return builder.toString();
    }
}