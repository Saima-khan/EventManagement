package embs.dbutil;
import java.sql.*;
public class CrudOperation
{
private static Connection con;//Connection type reference variable
public static Connection createConnection()//static function so object isn't created
{
	try {
		Class.forName("com.mysql.jdbc.Driver");//forName() method is used to create object of Driver Class.Also known as factoryMethod.
		/*factory method is used to create object of class implicitly.*/
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/eps","root","root");
		/*jdbc:mysql is a protocol for mysql.
		 * localhost is the name or IPAddress of the machine where database is installed.
		 * 3306 is the POrt number of mysql.PortNumber is the logical number on which database listens*/
	} catch (SQLException | ClassNotFoundException cse) //when the class driver is not found then ClassNotFoundException raised.
	{
		// TODO: handle exception
		System.out.println(cse);
		
	}
	return con;
}

public static void main(String[] args) {
	con=createConnection();
	System.out.println(con);
}


}
