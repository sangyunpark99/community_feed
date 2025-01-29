package org.sangyunpark99.acceptance.utils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT) // 고정으로 8080포트를 사용하겠다는 의미
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanUp cleanUp;

    @Autowired
    private DataLoader loader;

    @BeforeEach
    public void init() {
        cleanUp.execute();
        loader.loadData();
    }

    protected void cleanUp() {
        cleanUp.execute();
    }

    protected String getEmailToken(String email) {
        return loader.getEmailToken(email);
    }

    protected boolean isEmailVerified(String email) {
        return loader.isEmailVerified(email);
    }

    protected Long getUserId(String email) {
        return loader.getUser(email);
    }
}
