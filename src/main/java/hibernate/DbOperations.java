package main.java.hibernate;

import main.java.juego.mapas.ciudad.Ciudad;
import main.java.juego.mapas.pelea.Batallon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import sun.java2d.BackBufferCapsProvider;

public class DbOperations {

    private static final Logger logger = LogManager.getLogger(DbOperations.class);
    public static final SessionFactory ourSessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            logger.error("No se ha podido cargar la configuración de hibernate.");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        return ourSessionFactory;
    }


    // Method 1: This Method creates a new Record of a certain Object on the Database.
    public static Integer createRecord(Object obj) {
        Session sessionObj = getSessionFactory().openSession();

        //Creating Transaction Object
        Transaction transObj = sessionObj.beginTransaction();
        String objType = obj.getClass().getName();
        logger.info("DbOperations object type = "+objType);
        sessionObj.save(obj);

        // Transaction Is Committed To Database
        transObj.commit();

        // Closing The Session Object
        sessionObj.close();
        logger.info("Successfully Created " + obj.toString());
        //TODO change return statement.
        return 1;
    }

//    // Method 2: This Method Is Used To Display The Records From The Database Table
//    @SuppressWarnings("unchecked")
//    public static List displayRecords() {
//        Session sessionObj = getSessionFactory().openSession();
//        List studentsList = sessionObj.createQuery("FROM Student").list();
//
//        // Closing The Session Object
//        sessionObj.close();
//        logger.info("Student Records Available In Database Are?= " + studentsList.size());
//        return studentsList;
//    }
//
//    // Method 3: This Method Is Used To Update A Record In The Database Table
//    public static void updateRecord(Student studentObj) {
//        Session sessionObj = getSessionFactory().openSession();
//
//        //Creating Transaction Object
//        Transaction transObj = sessionObj.beginTransaction();
//        Student stuObj = (Student) sessionObj.load(Student.class, studentObj.getStudentId());
//        stuObj.setStudentName(studentObj.getStudentName());
//        stuObj.setStudentAge(studentObj.getStudentAge());
//
//        // Transaction Is Committed To Database
//        transObj.commit();
//
//        // Closing The Session Object
//        sessionObj.close();
//        logger.info("Student Record Is Successfully Updated!= " + studentObj.toString());
//    }
//
//    // Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
//    public static void deleteRecord(Integer studentId) {
//        Session sessionObj = getSessionFactory().openSession();
//
//        //Creating Transaction Object
//        Transaction transObj = sessionObj.beginTransaction();
//        Student studObj = findRecordById(studentId);
//        sessionObj.delete(studObj);
//
//        // Transaction Is Committed To Database
//        transObj.commit();
//
//        // Closing The Session Object
//        sessionObj.close();
//        logger.info("Successfully Record Is Successfully Deleted!=  " + studObj.toString());
//
//    }
//
//    // Method 4(b): This Method To Find Particular Record In The Database Table
//    public static Student findRecordById(Integer studentId) {
//        Session sessionObj = getSessionFactory().openSession();
//        Student stu = (Student) sessionObj.load(Student.class, studentId);
//
//        // Closing The Session Object
//        sessionObj.close();
//        return stu;
//    }
//
//    // Method 5: This Method Is Used To Delete All Records From The Database Table
//    public static void deleteAllRecords(String tableName) {
//        Session sessionObj = getSessionFactory().openSession();
//
//        //Creating Transaction Object
//        Transaction transObj = sessionObj.beginTransaction();
//        Query queryObj = sessionObj.createQuery("DELETE FROM "+tableName+"");
//        queryObj.executeUpdate();
//
//        // Transaction Is Committed To Database
//        transObj.commit();
//
//        // Closing The Session Object
//        sessionObj.close();
//        logger.info("Successfully Deleted All Records From The Database Table!");
//    }


}
