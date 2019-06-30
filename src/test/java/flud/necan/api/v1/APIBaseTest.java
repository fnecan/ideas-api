package flud.necan.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public abstract class APIBaseTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mvc;

    @BeforeEach
    void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

}
