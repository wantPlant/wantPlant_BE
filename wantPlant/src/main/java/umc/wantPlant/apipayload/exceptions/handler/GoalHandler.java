package umc.wantPlant.apipayload.exceptions.handler;

import umc.wantPlant.apipayload.code.BaseErrorCode;
import umc.wantPlant.apipayload.exceptions.GeneralException;

public class GoalHandler extends GeneralException {
    public GoalHandler(BaseErrorCode code) {
        super(code);
    }
}
