import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Schema3 {
	
//	CREATE TABLE Sailors(sid INT PRIMARY KEY, sname CHAR(20), rating INT, age REAL);


	 public static long insertSailor(int ID, String Name, int rating , double age,Connection conn) {
         String SQL = "INSERT INTO Sailors(sid,sname,rating,age) "
                 + "VALUES(?,?,?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setInt(1, ID);
                pstmt.setString(2, Name);
                pstmt.setInt(3,rating);
                pstmt.setDouble(4,age);

             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(1);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }
//	 CREATE TABLE Boat(bid INT PRIMARY KEY, bname CHAR(20), color CHAR(10));
	 public static long insertBoat(int ID, String Name, String color, Connection conn) {
         String SQL = "INSERT INTO Boat(bid,bname,color) "
                 + "VALUES(?,?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setInt(1, ID);
                pstmt.setString(2, Name);
                pstmt.setString(3,color);

             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(1);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }
//	 CREATE TABLE Reserves(sid INT REFERENCES Sailors, bid INT REFERENCES Boat, day date, PRIMARY KEY(sid,bid));
	 public static long insertReserves(int sID, int bID, Date day, Connection conn) {
         String SQL = "INSERT INTO Reserves(sid,bid,day) "
                 + "VALUES(?,?,?);";
      
         long id = 0;
        try{
        	 conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
     
                pstmt.setInt(1, sID);
                pstmt.setInt(2, bID);
                pstmt.setDate(3, day);

             int affectedRows = pstmt.executeUpdate();
             System.out.println("Number of affected rows is " + affectedRows);
             // check the affected rows 
             if (affectedRows > 0) {
                 // get the ID back
                 try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                	 System.out.println(rs.next());
                     if (rs.next()) {
                         id = rs.getLong(1);
                         pstmt.close();
                         conn.commit();
                     }
                 } catch (SQLException ex) {
                	 ex.printStackTrace();
                     System.out.println(ex.getMessage());
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             ex.printStackTrace();
         }
         return id;
     }
	 
	 
	 
	 ///////////////////////////////////////////////////////// Data Population Methods //////////////////////////////////////////////////////////
	 public static void populateSailor(Connection conn) {
		 for (int i = 1; i <= 9000; i++) {
				if (insertSailor(i, "Sailor" + i,i,i, conn) == 0) {
					System.err.println("insertion of record " + i + " failed");
					break;
				} else
					System.out.println("insertion was successful");
			}
	 }
	 public static void populateBoat(Connection conn) {
		 for (int i = 1; i <= 1500; i++) {
				if (insertBoat(i, "Boat" + i,"red", conn) == 0) {
					System.err.println("insertion of record " + i + " failed");
					break;
				} else
					System.out.println("insertion was successful");
			}
     for (int i = 1501; i <= 3000; i++) {
       if (insertBoat(i, "Boat" + i,"blue", conn) == 0) {
         System.err.println("insertion of record " + i + " failed");
         break;
       } else
         System.out.println("insertion was successful");
     }
	 }
  public static int getTwosComplement(String binaryInt) {
    //Check if the number is negative.
    //We know it's negative if it starts with a 1
    if (binaryInt.charAt(0) == '1') {
      //Call our invert digits method
      String invertedInt = invertDigits(binaryInt);
      //Change this to decimal format.
      int decimalValue = Integer.parseInt(invertedInt, 2);
      //Add 1 to the curernt decimal and multiply it by -1
      //because we know it's a negative number
      decimalValue = (decimalValue + 1) * -1;
      //return the final result
      return decimalValue;
    } else {
      //Else we know it's a positive number, so just convert
      //the number to decimal base.
      return Integer.parseInt(binaryInt, 2);
    }
  }

  public static String invertDigits(String binaryInt) {
    String result = binaryInt;
    result = result.replace("0", " "); //temp replace 0s
    result = result.replace("1", "0"); //replace 1s with 0s
    result = result.replace(" ", "1"); //put the 1s back in
    return result;
  }
	 @SuppressWarnings("deprecation")
	public static void populateReserves(Connection conn) {
     HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	   for (int i = 1; i <= 15000; i++) {
       int b=(int) ((Math.random() * (3000 - 1)) + 1);
       int s=(int) ((Math.random() * (9000 - 1)) + 1);
	     ArrayList<Integer> sb= map.get(s);
       if(map.get(s)!=null){
         while (sb.contains(b)){
           b= (int) ((Math.random() * (3000 - 1)) + 1);
         }
         ((ArrayList<Integer>)map.get(s)).add(b);
         if (insertReserves(s, b,new Date(1,1,1999), conn) == 0) {
           System.err.println("insertion of record " + i + " failed");
           break;
         } else
           System.out.println("insertion was successful");
       }
       else{
         ArrayList<Integer> li = new ArrayList<>();
         li.add(b);
         map.put(s,li);
         if (insertReserves(s, b,new Date(1,1,1999), conn) == 0) {
           System.err.println("insertion of record " + i + " failed");
           break;
         } else
           System.out.println("insertion was successful");
       }
			}
	 }
	 public static void insertSchema3(Connection connection) {
			populateSailor(connection);
			populateBoat(connection);
			populateReserves(connection);
	 }
	 
	public static void main(String[] argv) {

    System.out.println(getTwosComplement("001000"));
	}
}
