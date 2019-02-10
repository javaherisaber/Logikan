package ir.logicfan.core.ui.common;


import ir.logicfan.core.data.network.error.NetworkErrorType;

public interface ViewContract {
    void showProgress();
    void hideProgress();
    void showError(NetworkErrorType error);
}
