package umc.wantPlant.apipayload.exceptions.handler;

import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.exceptions.GeneralException;

public class GardenCategoryHandler extends GeneralException {
	public GardenCategoryHandler(BaseErrorCode code) {
		super(code);
	}
}
