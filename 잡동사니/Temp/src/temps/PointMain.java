package temps;
public class PointMain {
	public static void main(String[]args) {
		Point2D p = new Point3D();
		Point2D q = ((Point3D)new Point3D());
		//Point3D r = new Point2D();
		p.setX(5);
		System.out.println(p.getX());
		Point3D c =((Point3D)q);
		System.out.println(((Point3D)q).getZ());
		c.setZ(4);
		System.out.println(c.getZ());
		
	}
}