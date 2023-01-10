package point2D;

/**
 * @author Efraim Vishnevetsky and Shon Dayan (original interface by Ronny Sivan  (ronny.sivan@gmail.com))
 *
 * @date May 01, 2021
 * 
 */

/**
 * Cartesian - describe a point in the plane using Cartesian representation
 *
 */
public class Cartesian implements Point2D
{
	private double x;
	private double y;
	
	/**
	 * Constructor
	 */
	public Cartesian(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
    * getX - return the point's X coordinate in the Cartesian plane
    *
    * @return the point's X coordinate
    */
	public double getX()
	{
		return this.x;
	}
	
	 /**
    * getY - return the point's Y coordinate in the Cartesian plane
    *
    * @return the point's Y coordinate
    */
	public double getY()
	{
		return this.y;
	}
	
	/**
    * getRho - return the point's radius-vector in the polar plane
    *
    * @return the distance from the origin to the point
    */
	public double getRho()
	{
		return Math.sqrt((this.x*this.x)+(this.y*this.y));
	}
	
	/**
    * getPhi - return the point's phase in the polar plane.
    *          The phase of the zero vector (0,0) is also 0.
    *
    * @return  the counter-clockwise angle the vector makes with the 
    *          positive direction of the x axis in radians
    */
	public double getPhi() 
	{
		if(this.x == 0)
		{
			if(this.y > 0) return Math.PI/2;
			else if(this.y == 0) return 0;
			else return 1.5*Math.PI;
		}
		else
		{
			double phi = Math.atan(this.y/this.x);
			if(phi < 0) return phi+2*Math.PI;
			else return phi;
		}
	}
	
	/**
    * getTanPhi - return tan( phi ), which is sometimes easier to calculate.
    *
    * @return the tangent of the phase phi
    */
	public double getTanPhi()
	{
		return Math.tan(this.getPhi());
	}
	
	/**
    * setX - set the X coordinate of the point (without modifying 
    *        its Y coordinate)
    *
    * @param x the new X coordinate for the point
    */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
    * setY - set the Y coordinate of the point (without modifying 
    *        its X coordinate)
    *
    * @param y the new Y coordinate for the point
    */
	public void setY(double y)
	{
		this.y = y;
	}
	
	/**
    * setRho - set the radius-vector of the point, using the same phase, 
    *          essentially extending the vector in the direction it is
    *          pointing already. 
    *          This method may not be applied to the zero vector (0,0).
    *
    * @param rho the new distance from the origin to the point
    */
	public void setRho(double rho)
	{
	  if(this.x != 0 && this.y != 0)
	  {
		  double phi = this.getPhi();
		  this.x = rho*(Math.cos(phi));
		  this.y = rho*(Math.sin(phi));
	  }
	}
	
	/**
    * setPhi - set the phase of the point without changing its radius-vector.
    *          Essentially it rotates the vector about the origin by the given 
    *          angle phi. 
    *          This method may not be applied to the zero vector (0,0)
    *
    * @param phi the angle by which to rotate the vector counter-clockwise 
    *          about the origin in radians
    */
	public void setPhi(double phi)
	{
	  if(this.x != 0 && this.y != 0)
	  {
		  double rho = this.getRho();
		  this.x = rho*(Math.cos(phi));
		  this.y = rho*(Math.sin(phi));
	  }
	}
	
	/**
    * angle - returns the angle formed by the radius-vectors of 
    *         the two Point2D: this and p
    *
    * @param p
    * @return the counterclockwise angle between this & p in radians
    */
	public double angle(Point2D p) 
	{
		double angle = ((p.getPhi()-this.getPhi())%(2*Math.PI));
		if(angle < 0) return angle + (2*Math.PI);
		else return angle;
	}
	
	/**
    * distance - returns the distance between the two Point2D: this and p
    *
    * @param p the point to measure the distance to
    * @return the distance from this point to p
    */
	public double distance(Point2D p)
	{
		return(Math.sqrt((p.getX()-this.x)*(p.getX()-this.x) + (p.getY()-this.y)*(p.getY()-this.y)));
	}
	
	/**
    * middle - return the midpoint between this and p.
	*
	* @param param
	* @return the midpoint between this & p
	*/
	public Point2D middle(Point2D p)
	{
		return new Cartesian(((this.x+p.getX())/2),((this.y+p.getY())/2));
	}
}
