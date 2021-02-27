package easyon.alphacalcolutions;

import easyon.alphacalcolutions.controller.MainController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlphacalcolutionsApplicationTests {

    @Autowired // Dependency inject the MainController
    private MainController mainController;

    @Test
    void contextLoads() {
        assertNotNull(mainController);
    }

}
