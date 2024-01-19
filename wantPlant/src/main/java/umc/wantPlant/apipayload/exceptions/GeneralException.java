package umc.wantPlant.apipayload.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

	private BaseErrorCode code;

	public ErrorReasonDTO getErrorReason() {
		return this.code.getReason();
	}

	public ErrorReasonDTO getErrorReasonHttpStatus() {
		return this.code.getReasonHttpStatus();
	}
}
