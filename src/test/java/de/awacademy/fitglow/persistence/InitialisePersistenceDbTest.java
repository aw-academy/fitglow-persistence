package de.awacademy.fitglow.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.awacademy.fitglow.persistence.TipAmount;
import de.awacademy.fitglow.persistence.TipBox;


/**
 *
 */
public class InitialisePersistenceDbTest {

	private static final int dbPort = 3306;
	private static final String dbUser = "root";
	private static final String dbPassword = "root";
	private static final String dbName = "fitglow_db";
	
	private static final String tipBoxName = "ec447f09-66ae-454a-bf56-770a720e63de";
	private static final String tipAmountValue = "fc447f09-66ae-454a-bf56-770a720e63df";
	

	private final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("PERSISTENCE_UNIT");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();

	
	@BeforeClass
	public static void setUp() throws SQLException {
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:" + dbPort + "/?user=" + dbUser + "&password=" + dbPassword);
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
		statement.execute("USE " + dbName);
		try {
			statement.executeUpdate("DROP TABLE IF EXISTS " + TipType.class.getSimpleName());
			statement.executeUpdate("DROP TABLE IF EXISTS " + TipAmount.class.getSimpleName());
			statement.executeUpdate("DROP TABLE IF EXISTS " + TipBox.class.getSimpleName());
		} catch(SQLException e) {
			statement.executeUpdate("DELETE FROM " + TipType.class.getSimpleName());
			statement.executeUpdate("DELETE FROM " + TipAmount.class.getSimpleName());
			statement.executeUpdate("DELETE FROM " + TipBox.class.getSimpleName());
		}
	}
	

	@Test
	public void initialise() throws SQLException {
		Random random = new Random();

		System.out.print("Creating ");
		
		entityManager.getTransaction().begin();
		createTipBoxes(random);
		myTipBox();
		createTipBoxes(random);
		
		entityManager.getTransaction().commit();

		System.out.print(" Done");
		System.out.println();
	}

	
	private void myTipBox() {
		TipBox tipBox = new TipBox();
		tipBox.setName(tipBoxName);

		TipAmount tipAmount = new TipAmount();
		tipAmount.setValue(57);
		
		TipType tipType = new TipType();
		tipType.setType("Cash");
		entityManager.persist(tipType);
		tipAmount.getTipTypes().add(tipType);
		
		entityManager.persist(tipAmount);
		tipBox.getTipAmounts().add(tipAmount);

		TipAmount tipAmount2 = new TipAmount();
		tipAmount2.setValue(23);
		
		TipType tipType2 = new TipType();
		tipType2.setType("Cash");
		entityManager.persist(tipType2);
		tipAmount2.getTipTypes().add(tipType2);
		
		entityManager.persist(tipAmount2);
		tipBox.getTipAmounts().add(tipAmount2);

		TipAmount tipAmount3 = new TipAmount();
		tipAmount3.setValue(11);
		
		TipType tipType3 = new TipType();
		tipType3.setType("Cash");
		entityManager.persist(tipType3);
		tipAmount3.getTipTypes().add(tipType3);
		
		entityManager.persist(tipAmount3);
		tipBox.getTipAmounts().add(tipAmount3);

		
		entityManager.persist(tipBox);
		
		System.out.print(".");
	}

	
	private void createTipBoxes(Random random) {
		
		int n = Math.abs(random.nextInt()) % 50;
		for (int j = 0; j < n; j++) {
			TipBox tipBox = new TipBox();
			tipBox.setName(UUID.randomUUID().toString());

			int m = Math.abs(random.nextInt()) % 100;
			for (int i = 0; i < m; i++) {
				TipAmount tipAmount = new TipAmount();
				tipAmount.setValue(Math.abs(random.nextInt()) % 100);
				
				TipType tipType = new TipType();
				tipType.setType("CreditCard");
				entityManager.persist(tipType);
				tipAmount.getTipTypes().add(tipType);
				
				entityManager.persist(tipAmount);
				tipBox.getTipAmounts().add(tipAmount);
			}
			
			entityManager.persist(tipBox);
			System.out.print(".");
		}
	}

}
