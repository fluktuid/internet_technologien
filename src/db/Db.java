package db;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.imut.ec.keyvaluestore.KeyValueStore;

/*
 *
                                                         __    __  ________  __      __ 
                                                        /  |  /  |/        |/  \    /  |
 __    __  _______    _______   ______    ______        $$ | /$$/ $$$$$$$$/ $$  \  /$$/ 
/  |  /  |/       \  /       | /      \  /      \       $$ |/$$/  $$ |__     $$  \/$$/  
$$ |  $$ |$$$$$$$  |/$$$$$$$/ /$$$$$$  |/$$$$$$  |      $$  $$<   $$    |     $$  $$/   
$$ |  $$ |$$ |  $$ |$$      \ $$    $$ |$$ |  $$/       $$$$$  \  $$$$$/       $$$$/    
$$ \__$$ |$$ |  $$ | $$$$$$  |$$$$$$$$/ $$ |            $$ |$$  \ $$ |_____     $$ |    
$$    $$/ $$ |  $$ |/     $$/ $$       |$$ |            $$ | $$  |$$       |    $$ |    
 $$$$$$/  $$/   $$/ $$$$$$$/   $$$$$$$/ $$/             $$/   $$/ $$$$$$$$/     $$/     

N0acw38OSV7MlKJWk53MUMNQ5UrF4MwI8OI65TJV

URL: http://mhb.et-inf.fho-emden.de:9204/OpenKeyValueStore/index.jsp
*/

/**
 * Servlet implementation class db
 * @author Tobias Bogeo
 * @author Lukas f. Paluch
 * @author Okko Veenhuis
 */
//TODO: Servlet auspflegen

public class Db extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//des Key um über den KeyValueStore auf den Speicher zuzugreifen.
	private static final String KEY = "N0acw38OSV7MlKJWk53MUMNQ5UrF4MwI8OI65TJV";

	KeyValueStore kvs = new KeyValueStore();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Db() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().println("MING MING MING MING");
		response.getWriter().println("HIER IST ALLES SUPER!!!W000000tttt!!");
		response.getWriter().println("Value: ");
	}

	/**
	 * Speichere übergebene Daten im KeyValueStore
	 * @param eingabe
	 */
	public void saveData(String path,String eingabe) {
		kvs.put(KEY, eingabe);
		LocalDbManager.writeData(path,eingabe);
		System.out.println("saved");
	}

	public String loadData(String path) {
		String ausgabe = kvs.get(KEY);
		System.out.println("loaded"+ausgabe);
		if(ausgabe!=null && !ausgabe.isEmpty())
			return ausgabe;
		return LocalDbManager.getData(path);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
