package umc.wantPlant.apipayload.code;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReasonDTO {

	private HttpStatus httpStatus;

	private final boolean isSuccess;
	private final String code;
	private final String message;

	public boolean getIsSuccess() {
		return isSuccess;
	}
}
