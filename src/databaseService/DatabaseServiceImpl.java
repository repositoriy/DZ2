package databaseService;

import java.util.Calendar;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import org.hibernate.service.ServiceRegistryBuilder;



import frontend.FrontendImpl;
import frontend.MsgUpdateUserId;

import resourceSystem.DatabaseResource;

import utils.TimeHelper;


import base.AccountService;
import base.Address;
import base.MessageSystem;

public class DatabaseServiceImpl implements AccountService, Runnable{

	private Configuration configuration ;
	private SessionFactory sessionFactory;
	private ServiceRegistryBuilder builder;
	private MessageSystem ms;
	private Address address;
	private DatabaseResource resource;
	private List<Gamer> list;
	//private REsu
	
	public DatabaseServiceImpl(MessageSystem ms, DatabaseResource resource){
		this.ms = ms;
		this.address = new Address();
		this.resource = resource;
		ms.addService(this);
		this.configuration = new org.hibernate.cfg.Configuration();
		this.configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		this.configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		this.configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/game");
		this.configuration.setProperty("hibernate.connection.username", "root");
		this.configuration.setProperty("hibernate.connection.password", "12345");
		this.configuration.setProperty("hibernate.show_sql","true");
		this.configuration.setProperty("hibernate.hbm2ddl.auto","update");
		this.builder = new ServiceRegistryBuilder();
		this.builder.applySettings(configuration.getProperties());
		this.configuration.addAnnotatedClass(Gamer.class);		     
		this.sessionFactory = configuration.buildSessionFactory( this.builder.buildServiceRegistry());

	}

	public Address getAddress() {
		
		return this.address;
	}

	public MessageSystem getMessageSystem() {
		// TODO Auto-generated method stub
		return this.ms;
	}

	
	public void getUserId(String userName, Integer sessionId) {
		
		this.getAddress().setThreadUsed(true);
		TimeHelper.sleep(5000);
		System.out.println("Для "+userName + " отработал DatabaseService номер = " +this.getAddress().hashCode() );
		Session session = this.sessionFactory.openSession();
		Gamer testGamer  = (Gamer)session.createCriteria(Gamer.class).add(Restrictions.eq("userName", userName)).uniqueResult();
		Integer id = null;
		Calendar calendar = null;
		int bestCountClicks = 0;
		if (testGamer == null){ 
			Gamer newGamer = new Gamer();
			newGamer.setUserName(userName);
			save(session,newGamer);
			testGamer  = (Gamer)session.createCriteria(Gamer.class).add(Restrictions.eq("userName", userName)).uniqueResult();
			id =  testGamer.getUserId();
			
		}else {
			bestCountClicks = testGamer.getBestCount();
			calendar = testGamer.getLastDate();
			testGamer.setLastDate(Calendar.getInstance());
			Transaction transaction = session.beginTransaction();
			session.update(testGamer);
			transaction.commit();
			id = testGamer.getUserId();
			
		}
		session.close();
		this.ms.sendMessage(new MsgUpdateUserId(this.address, this.ms.getAddressService().getAddress(FrontendImpl.class),
				id, sessionId , bestCountClicks , calendar));
	
		this.getAddress().setThreadUsed(false);
		
	}
	public void save( Session session ,Gamer gamer){
		Transaction transaction = session.beginTransaction();
		session.save(gamer);
		transaction.commit();
		//session.close();
	}
	public void save( Gamer gamer) {
		//this.sessionFactory = configuration.buildSessionFactory( this.builder.buildServiceRegistry());
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(gamer);
		transaction.commit();
		session.close();

		
	}
	public void contains(){		
		Session session = sessionFactory.openSession();
		List<Gamer> list = session.createCriteria(Gamer.class).list();
		for (Gamer gamer2 : list) {
			System.out.println("contains(Gamer gamer)" + gamer2.getUserName());
		}

	}
	public Gamer read ( String name ) {
		Session session  = sessionFactory.openSession();
		return (Gamer)session.load(Gamer.class, name);
	}
	public Gamer read (int id){
		Session session = sessionFactory.openSession();
		Gamer gamer = (Gamer)session.load(Gamer.class, id);
		session.close();
		return gamer;
	}
	public void delete (String arg, Gamer gamer){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(arg, gamer);
		transaction.commit();
		session.close();
	}
	public void delete (Gamer gamer){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(gamer);
		transaction.commit();
		session.close();
	}
	public void clear()	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.clear();
		transaction.commit();
		session.close();
	}

	public void run() {	
		while (true){
			ms.execForAbonent(this);				
			TimeHelper.sleep(10);
		}
	}

	public void saveResult(int id, int countClicks) {
		Session session = sessionFactory.openSession();
		Gamer gamer =(Gamer)session.load(Gamer.class, id);
		if ( gamer.getBestCount() < countClicks){
			gamer.setBestCount(countClicks);
			
			Transaction transaction = session.beginTransaction();
			session.update(gamer);
			transaction.commit();
			
		}
		session.close();
		
	}
}
