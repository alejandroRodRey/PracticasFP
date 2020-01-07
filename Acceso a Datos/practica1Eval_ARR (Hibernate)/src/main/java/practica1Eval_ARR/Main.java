package practica1Eval_ARR;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

	public static void main(String[] args) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session session = sf.openSession();

		Cliente c1 = new Cliente(1, Date.valueOf("2010-12-12"), "dsad");
		Cliente c2 = new Cliente(2, Date.valueOf("1991-12-12"), "dsad");
		Articulo a1 = new Normal(1, "Canela", 12, 23);
		Articulo a2 = new Preventa(2, "Game", 32, Date.valueOf("2019-1-11"));
		Articulo a3 = new Preventa(3, "Columpio", 32, Date.valueOf("2019-2-22"));

		List<Articulo> articulos1 = new ArrayList<Articulo>();
		articulos1.add(a1);
		articulos1.add(a2);
		
		List<Articulo> articulos2 = new ArrayList<Articulo>();
		articulos2.add(a2);
		articulos2.add(a3);

		Pedido pedido1 = new Pedido(1, c1, articulos1);
		Pedido pedido2 = new Pedido(2, c2, articulos1);
		Pedido pedido3 = new Pedido(3, c2, articulos2);

		session.getTransaction().begin();

		session.save(c1);
		session.save(c2);
		session.save(a1);
		session.save(a2);
		session.save(a3);
		session.save(pedido1);
		session.save(pedido2);
		session.save(pedido3);

		session.getTransaction().commit();
		session.close();
		sf.close();
		sr.close();
	}

}
