package easyon.alphacalcolutions.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTitleTest {

    UserTitle getUserTitle(){
        UserTitle ut = new UserTitle();
        ut.setUserTitleId(1);
        ut.setUserTitle("Title");
        return ut;
    }

    @Test
    void testEquals() {
        UserTitle ut1 = getUserTitle();
        UserTitle ut2 = getUserTitle();
        assertTrue(ut1.equals(ut2));
        assertTrue(ut1.equals(ut1));
        assertFalse(ut1.equals(false));
    }

    @Test
    void testHashCode() {
        UserTitle ut1 = getUserTitle();
        assertEquals(ut1.getUserTitleId(), ut1.hashCode());
    }
}