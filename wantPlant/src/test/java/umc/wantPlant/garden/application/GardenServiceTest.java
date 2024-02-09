package umc.wantPlant.garden.application;

import static org.mockito.BDDMockito.*;
import static umc.wantPlant.garden.domain.enums.GardenCategories.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.lang.Assert;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.repository.GardenRepository;
import umc.wantPlant.member.domain.Member;

@ExtendWith(MockitoExtension.class)
public class GardenServiceTest {
	@InjectMocks
	private GardenQueryServiceImpl gardenQueryService;
	@InjectMocks
	private GardenCommandServiceImpl gardenCommandService;
	@Mock
	private GardenRepository gardenRepository;

	@Test
	@DisplayName("정원 추가 테스트")
	public void addGarden() {
		//given
		Member member = createMemberEntity();
		Garden garden = createGardenEntity(member);

		Long fakeGardenId = 0L;
		ReflectionTestUtils.setField(garden, "id", fakeGardenId);

		//mocking
		given(gardenRepository.save(any())).willReturn(garden);
		given(gardenRepository.findById(fakeGardenId)).willReturn(Optional.ofNullable(garden));

		//when
		gardenRepository.save(garden);

		//then
		Garden saveGarden = gardenRepository.findById(0L).get();
		Assertions.assertThat(garden).isEqualTo(saveGarden);
	}

	@Test
	void getGarden() {
	}

	@Test
	void getGardenToList() {
	}

	@Test
	void getGardenToPage() {
	}

	@Test
	void getGardenToPageByCategory() {
	}

	@Test
	void deleteGarden() {
	}

	@Test
	void updateGarden() {
	}

	private Member createMemberEntity() {
		return Member.builder()
			.id(99999L)
			.nickname("test")
			.email("test@test.com")
			.profileImage("test.com")
			.build();
	}

	private Garden createGardenEntity(Member member) {
		return Garden.builder()
			.name("testGarden")
			.description("testGarden")
			.category(EXERCISE)
			.member(member)
			.build();
	}

}
