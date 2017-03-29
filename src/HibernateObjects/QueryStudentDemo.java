package HibernateObjects;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Hibernate.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName='C'
			theStudents = session.createQuery("from Student s where s.lastName='C'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of C");
			displayStudents(theStudents);
			
			// query students: lastName='C' OR firstName='Krishna'
			theStudents =
					session.createQuery("from Student s where"
							+ " s.lastName='C' OR s.firstName='Krishna'").getResultList();
			
			System.out.println("\n\nStudents who have last name of C OR first name Krishna");
			displayStudents(theStudents);
			
			// query students where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%gmail.com'").getResultList();

			System.out.println("\n\nStudents whose email ends with gmail.com");			
			displayStudents(theStudents);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}





