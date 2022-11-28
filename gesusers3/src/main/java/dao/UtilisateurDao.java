package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Utilisateur;

public class UtilisateurDao
{
	private static int lastId = 1;

	private static final ArrayList<Utilisateur>	utilisateurs = new ArrayList<Utilisateur>();
	
	static {
		Connection  conn = openConnexion();
		if( conn != null) {
			try {
				Statement stm = conn.createStatement();
				String req = "select * from utilisateurs";
				ResultSet res = stm.executeQuery(req);
				while(res.next()) {
					Utilisateur user = new Utilisateur(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("login"), res.getString("password"));
					utilisateurs.add(user);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Connection openConnexion() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestuser_DB?"+"user=root&password=");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private static boolean closeConnexion(Connection conn) {
		try {
			conn.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public static boolean ajouter(Utilisateur utilisateur)
	{
		Connection conn = openConnexion();
		String req = "insert into utilisateurs (nom, prenom, login, password) values (?, ?, ?, ?)";
		try{
			PreparedStatement ps = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, utilisateur.getNom());
			ps.setString(2, utilisateur.getPrenom());
			ps.setString(3, utilisateur.getLogin());
			ps.setString(4, utilisateur.getPassword());
			if(ps.executeUpdate()!=0) {
				ResultSet keys = ps.getGeneratedKeys();
				if(keys.next()) {
					utilisateur.setId(keys.getInt(1));
					utilisateurs.add(utilisateur);
				}
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnexion(conn);
		}
		return true;
	}

	public static ArrayList<Utilisateur> lister()
	{
		return utilisateurs;
	}

	public static boolean modifier(Utilisateur utilisateur)
	{
		for (Utilisateur user : utilisateurs)
		{
			if (utilisateur.getId() == user.getId())
			{
				user.setNom(utilisateur.getNom());
				user.setPrenom(utilisateur.getPrenom());
				user.setLogin(utilisateur.getLogin());
				user.setPassword(utilisateur.getPassword());
				// update on the DB
				Connection conn = openConnexion();
				String req = "update utilisateurs set nom=?, prenom=?, login=?, password=? where (id = ?)";
				try{
					PreparedStatement ps = conn.prepareStatement(req);
					ps.setString(1, utilisateur.getNom());
					ps.setString(2, utilisateur.getPrenom());
					ps.setString(3, utilisateur.getLogin());
					ps.setString(4, utilisateur.getPassword());
					if(ps.executeUpdate()!=0) {
						return true;
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					closeConnexion(conn);
				}
				return true;
			}
		}

		return false;
	}

	public static boolean supprimer(int id)
	{
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getId() == id)
			{
				Connection conn = openConnexion();
				String req = "delete from utilisateurs where ( id = ? )";
				try{
					PreparedStatement ps = conn.prepareStatement(req);
					ps.setInt(1, id);
					if(ps.executeUpdate()!=0) {
						utilisateurs.remove(utilisateur);
						return true;
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					closeConnexion(conn);
				}
			}
		}

		return false;
	}

	public static Utilisateur get(int id)
	{
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getId() == id)
			{
				return utilisateur;
			}
		}

		return null;
	}
	
	public static Utilisateur getByUsername(String login) {
		for(Utilisateur user : utilisateurs) {
			if(login.equals(user.getLogin())) {
				return user;
			}
		}
		return null;
	}
}
