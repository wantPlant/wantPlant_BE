package umc.wantPlant.apipayload.exceptions.handler;

import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.exceptions.GeneralException;

public class TodoHandler extends GeneralException {
    public TodoHandler(BaseErrorCode code) {
        super(code);
    }
}
