package frontend;


import gameMechanics.GameMechanicImpl;
import gameMechanics.MsgSetGamerNames;
import gameMechanics.MsgUpdateClicks;


import java.io.FileNotFoundException;
import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import databaseService.DatabaseServiceImpl;

import resourceSystem.GameSessionResource;

import base.Address;
import base.Frontend;
import base.MessageSystem;



import utils.PageGenerator;
import utils.TimeHelper;

public class FrontendImpl extends AbstractHandler implements Runnable, Frontend{


	private static AtomicInteger idSessionGenerator = new AtomicInteger();
	private Map <Integer,UserSession> userSessions = new HashMap<Integer,UserSession>();
	private Address address;
	private MessageSystem ms;	
	private Integer userId;
	private UserSession userSession;
	private GameSessionResource resource;
	private boolean gameActivity = false;
	private boolean gameOver = false;

	
	public FrontendImpl (MessageSystem ms, GameSessionResource resource) throws FileNotFoundException, IOException{
		this.ms 	 = ms;
		this.resource = resource;
		this.address = new Address();		
		ms.addService(this);
	}

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
	        baseRequest.setHandled(true);
	       
	        
	    	PageGenerator pg = new PageGenerator();	    
	        pg.pageReload1(idSessionGenerator.incrementAndGet());	        
	       
	        response.getWriter().print(pg.getPage());
	      
	        if (request.getParameter("userName") != null && request.getParameter("userName") != "" && userSessions.get(Integer.parseInt(request.getParameter("sessionId")))== null ){	        	
	        	userSession = new UserSession(Integer.parseInt(request.getParameter("sessionId")));
	        	userSession.setUserName(request.getParameter("userName"));
	        	userSessions.put(userSession.hashCode(), userSession);
	        }
	        if (request.getParameter("sessionId")!= null && request.getParameter("userName")!= ""){ 
	        	userId = userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getUserId(); 
	       	}
	        if ( userId != null){
	        	response.getWriter().print("<h1> "+"Привет, геймер "+userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getUserName()+". Твой ID: " 
	        								+ userId +"<h1>");
	        	if (userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getLastVisit() == null) response.getWriter().print("<h1>" +" ты пришел к нам впервые" +"<h1>");
	        	else { response.getWriter().print("<h1>" +" последний раз ты играл " +"<h1>"+
	        			new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getLastVisit().getTime()));
	        	 response.getWriter().print("<h1>" +" и твой рекорд = " +userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getBestCountClicks()+" кликов"+"<h1>");
	        	}
	        	 userId = null;
	        	//
	        }else{
	        
	        	if (request.getParameter("userName") == null || request.getParameter("userName") == "" && gameOver) return;
	        	if (!gameOver ){response.getWriter().print("<h1>"+"Подожди аутентификации..."+ "<h1>");
	        	if(!userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).isInProgress()) {userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).setInProgress(true);
	        		ms.sendMessage( new MsgGetUserId(getAddress(), ms.getAddressService().getAddress(DatabaseServiceImpl.class), userSession.getUserName(), userSession.hashCode()));
	        	
	        		}
	        	}
	        }
	       if ( isAuthentificatedTwoUsers()) ms.sendMessage(new MsgSetGamerNames(getAddress(), ms.getAddressService().getAddress(GameMechanicImpl.class) , userSessions.values()));
	        
	        gameStarted(request, response);
	        doing(request, response);
	        gameStoped(request, response);
	        
	  
	}
	public void run()  {		
			while (true){			
				ms.execForAbonent(this);
				TimeHelper.sleep(100);				
			}		
	}
	
	public Address getAddress() {	
		return address;
	}
	public MessageSystem getMessageSystem(){
		return ms;
	}
	public void updateUserId( int  sessionId,  int userId, int bestCountClick, Calendar lastVisit) {		
		userSessions.get(sessionId).setUserId(userId);
		userSessions.get(sessionId).setLastVisit(lastVisit);
		userSessions.get(sessionId).setBestCountClicks(bestCountClick);
	}
	public boolean isAuthentificatedTwoUsers(){
		if (this.gameActivity|| this.gameOver) return false;
		System.out.println(resource+"sasadsdasdassdassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		if ( userSessions.size() < resource.getCountGamers() ) return false;
		for (UserSession userSession : userSessions.values()) {
			if ( userSession.getUserId() == null) return false;
		}
		return true;
	}
	

		
		
	public void doing (HttpServletRequest request, HttpServletResponse response){
		if (gameOver ) return;
		if (request.getParameter("param") != null &&  (Integer.parseInt(request.getParameter("param")) > userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getClicks()) ){
			System.out.println(Integer.parseInt(request.getParameter("param")));
			System.out.println(userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getClicks());
			userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).setClicks(Integer.parseInt(request.getParameter("param")));
			ms.sendMessage(new MsgUpdateClicks(this.address, ms.getAddressService().getAddress(GameMechanicImpl.class), userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getUserId(), Integer.parseInt(request.getParameter("param"))));
		}
	}

	public void gameStoped (HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		if ((this.gameActivity && userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).isWinner()) && !gameOver ) return;
		
		response.getWriter().println("Ваш результат "+ userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getClicks());
		for (UserSession userSession : userSessions.values()){
			if ( userSession.hashCode()!= Integer.parseInt(request.getParameter("sessionId"))){
				response.getWriter().println("<br></br>");
				response.getWriter().println("Ваш противник с номером "+ userSession.getUserId()+" "+ userSession.getUserName()+" сделал "+ userSession.getClicks() + " кликов" );
			}
		}
		if (userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getVictory()!=null) {
			response.getWriter().println("<br></br>");
			response.getWriter().println(userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).getVictory());
		}
	}
	public void setResult (  Integer userId, int count, String victory){
		if (!gameActivity) return;
		
		for (UserSession userSession : 	userSessions.values()) {
			if (userSession.getUserId() == userId){
				userSession.setVictory(victory);
				userSession.setWinner(true);
			}
			
		}		
		gameOver = true;
	}
 	public void gameStarted (HttpServletRequest request, HttpServletResponse response) throws IOException{
 		if (gameOver) return;
 		if (isGameActivity() && !userSessions.get(Integer.parseInt(request.getParameter("sessionId"))).isWinner() )response.getWriter().print("Игра началась ") ;		
		else return;
		
	}
	public boolean isGameActivity(){
		return this.gameActivity;
	}
	public void setGameActivity(boolean gameActivity) {
		this.gameActivity = gameActivity;
	}
}