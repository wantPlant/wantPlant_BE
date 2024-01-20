package umc.wantPlant.apipayload.exceptions.handler;

import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.exceptions.GeneralException;

public class GardenHandler extends GeneralException {
	public GardenHandler(BaseErrorCode code) {
		super(code);
	}
}
