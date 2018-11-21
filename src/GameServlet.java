

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
    	
        super();
        Game.menu = 0; // default menu
     // player will always be actor 0
		Game.addActors(player);
		
		Game.addActors(monster);
		Game.addActors(monster2);
		Game.addActors(monster3);
        // TODO Auto-generated constructor stub
    }
    int counter = 0;
    boolean firstPrint = true;
    Dungeon dungeon = new Dungeon();
    Player player = new Player("Dave", 10, 2, 10, '@', "#FFFF00"
			, 0, 1);
    
	
    
	Monster monster = new Monster("Goblin", 10, 2, 2, 2, Game.club, null, 'G', "#006400"
			, 0, 2);
	
	
	Monster monster2 = new Monster("Bat", 5, 1, 3, 1, Game.club, null, 'B', "#ffffff "
			, 1, 2);
	
	Monster monster3 = new Monster("Troll", 15, 3, 1, 4, Game.club, null, 'T', "#006400 "
			, 5, 5);
	
    
	
   // dungeon.firstPrint(out);
   //dungeon.addActor(player);
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	//	if (firstPrint) {
		//	dungeon.addActor(player);
	//		firstPrint = false;
		//}
		response.setContentType("text/html");
		int key = Integer.parseInt(request.getParameter("key"));
		PrintWriter out = response.getWriter();
		counter++;
		
		

		
		
		//out.println("key press " + key + " Counter: " + counter);
		//Game.display(out, dungeon);
		
		Game.start(dungeon);
		switch (key) {
			case 38: // up
				switch (Game.menu) {
				case 0: // no menu open
					player.move(0, -1, dungeon);
					Game.update(dungeon);
					break;
				case 1: // menu navigation
					if (Game.select == 0) {
						Game.select = 3;
					}
					else {
						Game.select--;
					}
					break;
				}

				break;
			case 37: // left
				switch (Game.menu) {
				case 0: // no menu open
					player.move(-1, 0, dungeon);
					Game.update(dungeon);
					break;
				}

				break;
			case 39: // right
				switch (Game.menu) {
				case 0: // no menu open
					player.move(1, 0, dungeon);
					Game.update(dungeon);
					break;
				}

				break;
			case 40: // down
				switch (Game.menu) {
				case 0: // no menu open
					player.move( 0, 1, dungeon);
					Game.update(dungeon);
					break;
				case 1: // menu navigation
					if (Game.select == 3) {
						Game.select = 0;
					}
					else {
						Game.select++;
					}
					break;
				}

				break;
			case 73: // i
				
				switch (Game.menu) {
				
				case 0:
					// open menu
					Game.menu = 1;
					break;
				case 1:
					// close menu
					Game.menu = 0;
					break;
				default:
					Game.menu = 1;
				}
				break;
				
			case 13: // enter
				switch (Game.menu) {
				case 1: // menu open
					Game.menu = 2;
					break;
				case 2:
					Game.menu = 1;
				}
				break;
			
		}
		Game.display(out, dungeon);
		
		
		//dungeon.firstPrint(out);
		
		//out.print(counter);
		/*if (firstPrint) {
			dungeon.firstPrint(out, player);
			firstPrint = false;
			System.out.println("First");
		}
		else {
			dungeon.printLayout(out, player);
			System.out.println("another ");
		
		}
		//out.println(dungeon.getLayout(0,0).letter);
		//out.println("<br>");
		//out.println("<b> test </b>");
		out.close();
		*/
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}