package umc.wantPlant.apipayload.exceptions.handler;

import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.exceptions.GeneralException;

public class TagHandler extends GeneralException {

    public TagHandler(BaseErrorCode code) {
        super(code);
    }
}
