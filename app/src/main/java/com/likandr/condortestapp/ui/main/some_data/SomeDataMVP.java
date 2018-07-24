package com.likandr.condortestapp.ui.main.some_data;

import com.likandr.condortestapp._common.base.BaseMvpView;
import com.likandr.condortestapp.data._models.SomeData;
import com.likandr.condortestapp.data.somedata.responses.SomeDataResponse;

import java.util.List;

public class SomeDataMVP {
    public interface View extends BaseMvpView {
        void showSomeDataList(List<SomeData> someData);
        void onFailure(Throwable e);
    }

    interface Presenter {
        void getSomeData(String code);
        void getSomeData(String code, int page);
        void onSuccess(SomeDataResponse response);
        void onFailure(Throwable e);
    }
}
