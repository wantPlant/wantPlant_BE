package umc.wantPlant.apipayload.exceptions.handler;

import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.exceptions.GeneralException;

public class PotHandler extends GeneralException {
    public PotHandler(BaseErrorCode code) {
        super(code);
    }
}
