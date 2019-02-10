package ir.logicfan.core.data.network.error;

import java.io.IOException;

public class UserOfflineException extends IOException {

    @Override
    public String getMessage() {
        return "Please check network connectivity";
    }
}
