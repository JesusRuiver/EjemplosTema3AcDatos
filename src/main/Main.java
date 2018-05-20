package main;

import primero.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import hibernateutil.HibernateUtil;

public class Main {
	public static void main(String[] args) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Inserto una fila en la tabla DEPARTAMENTOS.");

		Departamentos dep = new Departamentos();
		dep.setDeptNo((byte) 60);
		dep.setDnombre("MARKETING");
		dep.setLoc("GUADALAJARA");

		session.save(dep);
		try {
			tx.commit();
		} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTO DUPLICADO");
			System.out.printf("MENSAJE: %s%n",e.getMessage());
			System.out.printf("COD ERROR: %d%n",e.getErrorCode());		
			System.out.printf("ERROR SQL: %s%n" , 
                  e.getSQLException().getMessage());
		}

		session.close();
		System.exit(0);
	}
}
