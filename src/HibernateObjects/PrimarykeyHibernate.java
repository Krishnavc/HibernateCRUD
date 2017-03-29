package HibernateObjects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Hibernate.entity.Student;

public class PrimarykeyHibernate {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// create a student object
			System.out.println("Creating 4 student objects...");
			Student tempStudent1 = new Student("Pushpa", "C", "Pushpa@gmail.com");
			Student tempStudent2 = new Student("Krishna", "C", "krishna@gmail.com");
			Student tempStudent3 = new Student("Veni", "C", "venic43@gmail.com");
			Student tempStudent4 = new Student("Mohanakrishna", "Ch", "mohanakrishna@gmail.com");
			
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			session.save(tempStudent4);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
