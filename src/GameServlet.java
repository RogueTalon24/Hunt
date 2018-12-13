

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
        // TODO Auto-generated constructor stub
    }
    int counter = 0;
    boolean firstPrint = true;
    
    //create 10 separate dungeons and store in an array of dungeons?
    //have a current dungeon floor variable
    Dungeon dungeon1 = new Dungeon();
    Dungeon dungeon2 = new Dungeon();
    Dungeon dungeon3 = new Dungeon();
    Dungeon dungeon4 = new Dungeon();
    Dungeon dungeon5 = new Dungeon();
    Dungeon dungeon6 = new Dungeon();
    Dungeon dungeon7 = new Dungeon();
    Dungeon dungeon8 = new Dungeon();
    Dungeon dungeon9 = new Dungeon();
    Dungeon dungeon10 = new Dungeon();
    Dungeon[] dungeon = {dungeon1,dungeon2,dungeon3,dungeon4,dungeon5,dungeon6,dungeon7,dungeon8,dungeon9,dungeon10};
    int floor = 0;
    
    int[] location = dungeon[floor].getLocation();
    Player player = new Player("Dave", 10, 10, 10, '@', "#FFFF00",location[1],location[0]);
   
	
    int[] monLocation = dungeon[floor].getLocation();
	Monster monster = new Monster("goblin", 10, 1, 1, 1, Game.club, null, 'G', "#006400"
			, (monLocation[1]),(monLocation[0]));
	
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
		Game.addActors(player);
		Game.addActors(monster);
		
		
		//out.println("key press " + key + " Counter: " + counter);
		
		switch (key) {
			case 38: // up
				player.move(0, -1, dungeon[floor]);
				break;
			case 37: // left
				player.move(-1, 0, dungeon[floor]);
				break;
			case 39: // right
				player.move(1, 0, dungeon[floor]);
				break;
			case 40: // down
				player.move( 0, 1, dungeon[floor]);
				break;
			case 75: // k
				//player.setY(2);
				Game.menu = 1;
				//player.setX(0);
				break;
		}
		
		//if player on upfloor tile then move to next dungeon floor
		int[] up = dungeon[floor].getUpFloor();
		int[] down = dungeon[floor].getDownFloor();
		
		if(player.getX()==up[1]&&player.getY()==up[0]){
			floor++;
			if(floor>9) {
				floor--;
			}
			down = dungeon[floor].getDownFloor();
			player.setX(down[1]);
			player.setY(down[0]);
		}
		//if player on downfloor tile then move to down dungeon floor
		else if(player.getX()==down[1]&&player.getY()==down[0]){
			floor--;
			if(floor<0) {
				floor++;
			}
			up = dungeon[floor].getUpFloor();
			player.setX(up[1]);
			player.setY(up[0]);
		}
		
		//check inventory for item
		if(floor>9) {
			floor--;
		}
		
		//set player new location
		//have to clear previous character from previous floor
		
		Game.update(dungeon[floor]);
		Game.display(out, dungeon[floor]);
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
