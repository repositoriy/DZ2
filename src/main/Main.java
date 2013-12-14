package main;





import messageSystem.MessageSystemImpl;

import org.eclipse.jetty.server.Server;


import databaseService.DatabaseServiceImpl;



import resourceSystem.DatabaseResource;
import resourceSystem.GameSessionResource;
import resourceSystem.MyResourceFactory;


import base.Frontend;
import base.MessageSystem;

import frontend.FrontendImpl;
import gameMechanics.GameMechanic;
import gameMechanics.GameMechanicImpl;


public class Main {

		public static void main(String[] args) throws Exception{
			
			MyResourceFactory factory = MyResourceFactory.instance();
			MessageSystem ms = new MessageSystemImpl();
			Frontend frontend = new FrontendImpl(ms, (GameSessionResource)factory.get("GameResource.xml"));
			GameMechanic gameMechanic = new GameMechanicImpl(ms, (GameSessionResource)factory.get("GameResource.xml"));
	        System.out.print(((GameSessionResource)factory.get("GameResource.xml")));
			Server server = new Server(8081);
			
			
			new Thread((FrontendImpl)frontend).start();
			
			new Thread(new DatabaseServiceImpl(ms, (DatabaseResource)factory.get("DatabaseResourñe.xml"))).start();
			new Thread(new DatabaseServiceImpl(ms, (DatabaseResource)factory.get("DatabaseResourñe.xml"))).start();
			new Thread(new DatabaseServiceImpl(ms, (DatabaseResource)factory.get("DatabaseResourñe.xml"))).start();
			new Thread((GameMechanicImpl)gameMechanic).start();
			server.setHandler((FrontendImpl)frontend);
		
	        server.start();
	        server.join();
	        
	      
		
		}

}
