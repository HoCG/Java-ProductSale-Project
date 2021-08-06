/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package Main;

import Database.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class ProductMapper {
	// Implementation of hidden cache

	private Hashtable cache;

	private PersistentProduct getFromCache(int oid) {
		Integer key = new Integer(oid);
		return (PersistentProduct) cache.get(key);
	}

	private PersistentProduct getFromCacheByDetails(String name, int price) {
		PersistentProduct c = null;
		Enumeration enums = cache.elements();
		while (c == null && enums.hasMoreElements()) {
			PersistentProduct tmp = (PersistentProduct) enums.nextElement();
			if (name.equals(tmp.getName()) && (price==tmp.getPrice())) {
				c = tmp;
			}
		}
		return c;
	}

	private void addToCache(PersistentProduct c) {
		Integer key = new Integer(c.getId());
		cache.put(key, c);
	}

	// Constructor:

	private ProductMapper() {
		cache = new Hashtable();
	}

	// Singleton:

	private static ProductMapper uniqueInstance;

	public static ProductMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ProductMapper();
		}
		return uniqueInstance;
	}

	public PersistentProduct getProduct(String n, int p, int cnt) {
		PersistentProduct c = getFromCacheByDetails(n, p);
		if (c == null) {
			c = getProduct("SELECT * FROM product WHERE name = '" + n + "' AND price = '" + p + "'");
			if (c == null) {
				c = createProduct(n, p, cnt);
			}
			addToCache(c);
		}
		return c;
	}

	PersistentProduct getProductForOid(int oid) {
		PersistentProduct c = getFromCache(oid);
		if (c == null) {
			c = getProduct("SELECT * FROM product WHERE oid ='" + oid + "'");
			if (c != null) {
				addToCache(c);
			}
		}
		return c;
	}
	
	PersistentProduct updateProductForCount(int oid, int count) {
		PersistentProduct c = getFromCache(oid);
		if (c == null) {
			c = getProduct("UPDATE product SET count = '" + count + "' WHERE oid ='" + oid + "'");
			if (c != null) {
				addToCache(c);
			}
		}
		return c;
	}

	// Add a customer to the database

	PersistentProduct createProduct(String name, int price, int count) {
		PersistentProduct c = getFromCacheByDetails(name, price);
		if (c == null) {
			try {
				Statement stmt = Database.getInstance().getConnection().createStatement();
				int updateCount = stmt.executeUpdate(
						"INSERT INTO product (name, price,count)" + "VALUES ('" + name + "', '" + price + "' , '" + count + "')");
				stmt.close();
			}
				catch (SQLException e) {
				e.printStackTrace();
			}
			c = getProduct(name, price, count);
		}
		return c;
	}

	private PersistentProduct getProduct(String sql) {
		PersistentProduct c = null;
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			while (rset.next()) {
				int oid = rset.getInt(1);
				String name = rset.getString(2);
				int price = rset.getInt(3);
				int count = rset.getInt(4);
				c = new PersistentProduct(oid, name, price, count);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
