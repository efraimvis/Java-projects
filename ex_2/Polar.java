package point2D;

/**
 * @author Efraim Vishnevetsky and Shon Dayan (original interface by Ronny Sivan  (ronny.sivan@gmail.com))
 *
 * @date May 01, 2021
 * 
 */

/**
 * Polar - describe a point in the plane using Polar representation
 *
 */
public class Polar implements Point2D
{
	private double rho;
	private double phi;
	
	/**
	 * Constructor               
	 * */
	public Polar(double rho, double phi) // verify
	{
		this.rho = Math.abs(rho);
		this.phi = phi%(2*Math.PI);
		if(this.phi < 0)
		{
			this.phi += 2*Math.PI;
		}
	}
	
	/**
    * getX - return the point's X coordinate in the Cartesian plane
    *
    * @return the point's X coordinate
    */
	public double getX()
	{
		double x = ((this.rho*Math.cos(this.phi)));
		if(Math.abs(x) < 0.0001) return 0;
		return x;
	}
	
	/**
    * getY - return the point's Y coordinate in the Cartesian plane
    *
    * @return the point's Y coordinate
    */
	public double getY()
	{
		double y = (this.rho*Math.sin(this.phi));
		if(Math.abs(y) < 0.0001) return 0;
		return y;
	}

	/**
    * getRho - return the point's radius-vector in the polar plane
    *
    * @return the distance from the origin to the point
    */
	public double getRho() {
		return this.rho;
	}
	
	/**
    * getPhi - return the point's phase in the polar plane.
    *          The phase of the zero vector (0,0) is also 0.
    *
    * @return  the counter-clockwise angle the vector makes with the 
    *          positive direction of the x axis
    */
	public double getPhi()
	{
		return this.phi;
	}
	
	 /**
    * getTanPhi - return tan( phi ), which is sometimes easier to calculate.
    *
    * @return the tangent of the phase phi
    */
	public double getTanPhi()
	{
		return Math.tan(this.phi);
	}
	
	/**
    * setX - set the X coordinate of the point (without modifying 
    *        its Y coordinate)
    *
    * @param x the new X coordinate for the point
    */
	public void setX(double x) 
	{
		if(x == 0)
		{
			this.rho = Math.abs(this.getY());
			if(this.getY() > 0) this.phi = Math.PI/2;
			else if(this.getY() < 0) this.phi = Math.PI*1.5;
			else this.phi = 0;
		}
		else
		{
			double y = this.getY();
			this.phi = Math.atan(y/x);
			this.rho = Math.sqrt((x*x)+(y*y));
		}
	}
	
   /**
    * setY - set the Y coordinate of the point (without modifying 
    *        its X coordinate)
    *
    * @param y the new Y coordinate for the point
    */
	public void setY(double y) 
	{
		if(this.getX() == 0)
		{
			this.rho = Math.abs(this.getY());
			if(y > 0) this.phi = Math.PI/2;
			else if(y < 0) this.phi = Math.PI*1.5;
			else this.phi = 0;
		}
		else
		{
			double x = this.getX();
			this.phi = Math.atan(y/x);
			this.rho = Math.sqrt((x*x)+y*y);
		}
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
		if(this.rho != 0 && ((this.phi)%2*Math.PI != 0))
		{
			this.rho = rho;
		}
	}
	
	/**
    * setPhi - set the phase of the point without changing its radius-vector.
    *          Essentially it rotates the vector about the origin by the given 
    *          angle phi. 
    *          This method may not be applied to the zero vector (0,0)
    *
    * @param phi the angle by which to rotate the vector counter-clockwise 
    *          about the origin
    */
	public void setPhi(double phi) 
	{
		if(this.rho != 0 && this.phi != 0)
		{
			phi = phi%(2*Math.PI);
			if(phi > 0)
			{
				this.phi = phi;
			}
			else this.phi = phi+(2*Math.PI);
		}
	}
	
	/**
    * angle - returns the angle formed by the radius-vectors of 
    *         the two Point2D: this and p
    *
    * @param p
    * @return the counterclockwise angle berween this & p in radians
    */
	public double angle(Point2D p)
	{
		double angle = (p.getPhi()-this.phi)%(2*Math.PI);
		if(angle < 0) return angle+(2*Math.PI);
		else return angle;
		//return((p.getPhi()-this.phi)%(2*Math.PI));
	}
	
	/**
    * distance - returns the distance between the two Point2D: this and p
    *
    * @param p the point to measure the distance to
    * @return the distance from this point to p
    */
	public double distance(Point2D p) 
	{
		double a = this.rho;
		double b = p.getRho();
		return Math.sqrt(a*a +b*b-2*a*b*Math.cos(angle(p)));
	}
	
	/**
    * middle - return the midpoint between this and p.
	*
	* @param param
	* @return the midpoint between this & p
	*/
	public Point2D middle(Point2D p) 
	{
		Polar m = new Polar(0,0);
		double x = (this.getX()+p.getX())/2;
		double y = (this.getY()+p.getY())/2;
		m.setX(x);
		m.setY(y);
		return m;
	}
}
