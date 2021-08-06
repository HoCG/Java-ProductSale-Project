/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package Main;

import domain.Product;

class PersistentProduct extends Product {
	private int oid;

	PersistentProduct(int id, String n, int p, int cnt) {
		super(n, p, cnt);
		oid = id;
	}

	int getId() {
		return oid;
	}
}
