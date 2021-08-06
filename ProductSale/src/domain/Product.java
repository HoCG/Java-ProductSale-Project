/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package domain ;

public class Product
{
  private String name ;
  private int price;
  public int count;

  public Product(String n, int p, int cnt)
  {
    name = n ;
    price = p ;
    count = cnt ;
  }

public String getName()
  {
    return name ;
  }

  public int getPrice()
  {
    return price ;
  }
  
  public int getCount()
  {
    return count ;
  }
}
