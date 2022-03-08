package net.ancronik.cookbook.backend.domain.mapper;

import net.ancronik.cookbook.backend.StringTestUtils;
import net.ancronik.cookbook.backend.data.model.Author;
import net.ancronik.cookbook.backend.web.dto.author.AuthorUpdateRequest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
public class AuthorUpdateRequestToAuthorMapperTest {

    AuthorUpdateRequestToAuthorMapper mapper = new AuthorUpdateRequestToAuthorMapper(new ModelMapper());

    @Test
    public void map_NullGiven_ThrowException() {
        assertThrows(IllegalArgumentException.class, () -> mapper.map(null));
    }

    @Test
    public void map_DtoGiven_ReturnPopulatedModel() {
        AuthorUpdateRequest request = new AuthorUpdateRequest("Roki Novi", LocalDate.of(2020, 2, 2), null, null);

        Author author = mapper.map(request);

        assertNotNull(author);
        assertEquals(request.getFullName(), author.getFullName());
        assertEquals(request.getDateOfBirth(), author.getDateOfBirth());
        assertNull(author.getImageUrl());
        assertNull(author.getBio());
        assertNull(author.getUsername());
    }

    @Test
    public void map_DtoGiven_ReturnPopulatedModel2() {
        AuthorUpdateRequest request = new AuthorUpdateRequest(null, null, null, null);

        Author author = mapper.map(request);

        assertNotNull(author);
        assertNull(author.getFullName());
        assertNull(author.getDateOfBirth());
        assertNull(author.getImageUrl());
        assertNull(author.getBio());
        assertNull(author.getUsername());
    }

    @Test
    public void map_DtoGiven_ReturnPopulatedModel3() {
        AuthorUpdateRequest request = new AuthorUpdateRequest("Roki Novi", LocalDate.of(2020, 2, 2), "Nothing special", StringTestUtils.generateRandomUrl());

        Author author = mapper.map(request);

        assertNotNull(author);
        assertEquals(request.getFullName(), author.getFullName());
        assertEquals(request.getDateOfBirth(), author.getDateOfBirth());
        assertEquals(request.getImageUrl(), author.getImageUrl());
        assertEquals(request.getBio(), author.getBio());
    }
}