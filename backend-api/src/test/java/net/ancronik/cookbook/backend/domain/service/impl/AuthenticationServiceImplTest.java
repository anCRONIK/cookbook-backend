package net.ancronik.cookbook.backend.domain.service.impl;

import net.ancronik.cookbook.backend.TestTypes;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag(TestTypes.UNIT)
public class AuthenticationServiceImplTest {

    AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();

    @Test
    public void getAuthenticatedUsername_ReturnMockData() {
        assertEquals("testUser", authenticationService.getAuthenticatedUsername());
    }

}
