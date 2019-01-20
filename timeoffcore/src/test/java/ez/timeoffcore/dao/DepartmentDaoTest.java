package ez.timeoffcore.dao;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static junit.framework.TestCase.*;

public class DepartmentDaoTest {

    private EntityManager em;

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("timeoffcore.test.persistence.unit");
        em = emf.createEntityManager();
    }

    @Test
    public void saveTest() {
        assertNotNull(em);
    }
}
