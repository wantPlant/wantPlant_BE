package umc.wantPlant.apipayload.code.status;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
	_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
	_BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
	_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
	_FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
	TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

	//정원
	GARDEN_NOT_FOUND(HttpStatus.NOT_FOUND, "GARDEN400", "정원을 찾을 수 없습니다"),
	GARDEN_NOT_EXIST(HttpStatus.NOT_FOUND, "GARDEN401", "정원이 하나도 존재하지않습니다."),


	//정원 카테고리
	GARDEN_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "GARDEN_CATEGORY400", "정원 카테고리를 찾을 수 없습니다.  정원 카테고리 :EXERCISE, HOBBY , STUDY")

	//화분
	,POT_NOT_FOUND(HttpStatus.NOT_FOUND, "POT4001", "화분을 찾을 수 없습니다.")
	,POT_DELETE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "POT4002", "화분을 삭제할 수 없습니다.")
	,POT_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "POT4003", "잘못된 화분 타입입니다.")
	,POT_TAG_COLOR_NOT_FOUND(HttpStatus.NOT_FOUND, "POT4003", "잘못된 태그 컬러입니다.")

	//목표
	,GOAL_NOT_FOUND(HttpStatus.NOT_FOUND, "GOAL4001", "목표를 찾을 수 없습니다.")

	//투두
	,TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "TODO4001", "투두를 찾을 수 없습니다.")

	//기타
	,PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "PAGE4001", "올바른 페이지 범위가 아닙니다.")
  //로그인
	,LOGIN_GENERAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "LOGIN500", "로그인 에러")
	;


	private final HttpStatus httpStatus;

	private final String code;

	private final String message;

	@Override
	public ErrorReasonDTO getReason() {
		return ErrorReasonDTO.builder()
			.message(message)
			.code(code)
			.isSuccess(false)
			.build();
	}

	@Override
	public ErrorReasonDTO getReasonHttpStatus() {
		return ErrorReasonDTO.builder()
			.message(message)
			.code(code)
			.isSuccess(false)
			.httpStatus(httpStatus)
			.build();
	}
}
