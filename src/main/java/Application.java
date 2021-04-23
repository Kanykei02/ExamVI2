import entities.Human;
import entities.Medicine;
import entities.Virus;
import org.hibernate.Session;
import util.HibernateUtil;


public class Application {
    public static void main(String[] args) {
        Virus virus = Virus.builder().name("COVID19").build();
        saveOrUpdate(virus);

        Human human = Human.builder().fullName("Dean").age(45).virus(virus).build();
        Human human1 = Human.builder().fullName("Sam").age(42).virus(virus).build();
        Human human2 = Human.builder().fullName("Castiel").age(44).virus(virus).build();
        saveOrUpdate(human);
        saveOrUpdate(human1);
        saveOrUpdate(human2);

        Virus virus1 = Virus.builder().name("The plague").build();
        saveOrUpdate(virus1);

        Medicine medicine = Medicine.builder().name("AntiVir").virus(virus).build();
        saveOrUpdate(medicine);

        Virus virus2 = Virus.builder().name("Avian flu").medicine(medicine).build();
        saveOrUpdate(virus2);
    }
    public static <T> T saveOrUpdate(T entity) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(entity);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Запись создана/обновлена успешно");
        return entity;
    }
}

