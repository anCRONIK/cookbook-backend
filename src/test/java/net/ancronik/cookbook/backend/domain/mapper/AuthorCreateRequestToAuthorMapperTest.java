package net.ancronik.cookbook.backend.domain.mapper;

import net.ancronik.cookbook.backend.data.model.Author;
import net.ancronik.cookbook.backend.web.dto.author.AuthorCreateRequest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
public class AuthorCreateRequestToAuthorMapperTest {

    AuthorCreateRequestToAuthorMapper mapper = new AuthorCreateRequestToAuthorMapper(new ModelMapper());

    @Test
    public void map_NullGiven_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> mapper.map(null));
    }

    @Test
    public void map_DtoGiven_ReturnPopulatedModel() {
        AuthorCreateRequest request = new AuthorCreateRequest("roki", LocalDate.of(2020, 2, 2));

        Author author = mapper.map(request);

        assertNotNull(author);
        assertEquals(request.getUsername(), author.getUsername());
        assertEquals(request.getDateOfBirth(), author.getDateOfBirth());
        assertNull(author.getImageUrl());
        assertNull(author.getBio());
        assertNull(author.getFullName());
    }
}
