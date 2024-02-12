package umc.wantPlant.tag.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umc.wantPlant.tag.repository.TagRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tag 비즈니스 로직 테스트")
class TagServiceTest {

    TagService tagService;

    TagRepository tagRepository;

    @Test
    void addTag() {

    }

    @Test
    void getTag() {
    }

    @Test
    void getTagByMonth() {
    }

    @Test
    void getTagByDay() {
    }

    @Test
    void deleteTag() {
    }

    @Test
    void updateTag() {
    }
}