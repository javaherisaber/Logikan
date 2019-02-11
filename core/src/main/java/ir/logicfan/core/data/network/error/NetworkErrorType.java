package ir.logicfan.core.data.network.error;

import android.content.Context;
import ir.logicfan.core.R;


public enum NetworkErrorType implements NetworkError {

    CONNECTIVITY_ERROR {
        @Override
        public String getLocalizedMessage(Context context) {
            return context.getResources().getString(R.string.error_connectivity_error);
        }
    }, NO_INTERNET {
        @Override
        public String getLocalizedMessage(Context context) {
            return context.getResources().getString(R.string.error_check_your_internet_connection);
        }
    }, RESOURCE_NOT_AVAILABLE {
        @Override
        public String getLocalizedMessage(Context context) {
            return context.getResources().getString(R.string.error_resource_not_available);
        }
    }, SERVER_ERROR {
        @Override
        public String getLocalizedMessage(Context context) {
            return context.getResources().getString(R.string.error_server_error);
        }
    }, CLIENT_ERROR {
        @Override
        public String getLocalizedMessage(Context context) {
            return context.getResources().getString(R.string.error_oops_something_went_wrong);
        }
    }, UNEXPECTED_ERROR {
        @Override
        public String getLocalizedMessage(Context context) {
            return context.getResources().getString(R.string.error_something_went_wrong);
        }
    }, UNAUTHENTICATED {
        @Override
        public String getLocalizedMessage(Context context) {
            return context.getResources().getString(R.string.error_un_authorized_message);
        }
    }
}
