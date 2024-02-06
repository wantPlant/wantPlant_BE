package umc.wantPlant.apipayload.exceptions.handler;

import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.exceptions.GeneralException;

public class MemberHandler extends GeneralException {
	public MemberHandler(BaseErrorCode code) {
		super(code);
	}
}
