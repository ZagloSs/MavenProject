package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;

public class Main {

	public static void main(String[] args) {
		
		try(Connection con = Conexion.open()){
			
			printSQL(con,"SELECT alias, SUM(unidades) FROM magos INNER JOIN magos_pocimas ON id = idMago GROUP BY id");
			

		}catch (SQLException ex) {
			System.err.println("nonono");
		}

	}
	
	
	public static void printSQL(Connection con, String query) {
		try(PreparedStatement ps = con.prepareStatement(query)){

			
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					String nombreMago = rs.getString("alias");
					int sum = rs.getInt("SUM(unidades)");
					
					System.out.println("Nombre. " +nombreMago +", cantidad de pocimas: "+ sum);
					
				}
				
			}catch(SQLException ex) {
				
			}
			
		}catch(SQLException ex) {
			
		}
		
		
	}
}
