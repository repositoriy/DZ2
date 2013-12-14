package resourceSystem;

public class DatabaseResource implements Resource {

	private final String dialect;
	private final String driver_class;
	private final String url;
	private final String username;
	private final String password;
	private final String show_sql;
	private final String statement;
	

	public DatabaseResource(){

		this.dialect = null;
		this.driver_class= null;
		this.url= null;
		this.username= null;
		this.password= null;
		this.show_sql= null;
		this.statement= null;

	}

	public String getDialect() {
		return dialect;
	}
	public String getDriver_class() {
		return driver_class;
	}
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getShow_sql() {
		return show_sql;
	}
	public String getStatement() {
		return statement;
	}
	
}
