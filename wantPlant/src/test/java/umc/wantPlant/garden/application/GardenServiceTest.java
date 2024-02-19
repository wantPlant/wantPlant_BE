package umc.wantPlant.garden.application;

import static org.junit.jupiter.api.Assertions.*;
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

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.garden.repository.GardenRepository;
import umc.wantPlant.member.domain.Member;

@SpringBootTest
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

		GardenRequestDTO.GardenCreatRequestDTO requestDTO = GardenRequestDTO.GardenCreatRequestDTO.builder()
			.name("testGarden")
			.description("testGarden")
			.category(EXERCISE)
			.build();

		Garden gardenToSave = createGardenEntity(member);
		when(gardenRepository.save(any(Garden.class))).thenReturn(gardenToSave);


		// When
		GardenResponseDTO.GardenCreatResultDTO resultDTO = gardenCommandService.toCreatGarden(member, requestDTO);

		// Then
		assertNotNull(gardenToSave);
		assertNotNull(resultDTO);
		assertEquals(gardenToSave.getId(), resultDTO.getGardenId());
		assertEquals(requestDTO.getName(), resultDTO.getName());
		assertEquals(requestDTO.getDescription(), resultDTO.getDescription());
		assertEquals(requestDTO.getCategory(), resultDTO.getGardenCategory());

		// Verify that the save method was called with the correct arguments
		verify(gardenRepository, times(1)).save(any(Garden.class));
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
			.id(1L)
			.nickname("test")
			.email("test@test.com")
			.profileImage("test.com")
			.build();
	}

	private Garden createGardenEntity(Member member) {
		return Garden.builder()
			.id(1L)
			.name("testGarden")
			.description("testGarden")
			.category(EXERCISE)
			.member(member)
			.build();
	}

}
