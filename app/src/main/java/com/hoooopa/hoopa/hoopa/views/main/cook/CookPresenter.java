package com.hoooopa.hoopa.hoopa.views.main.cook;

import com.hoooopa.hoopa.hoopa.base.BasePresenter;
import com.hoooopa.hoopa.hoopa.bean.cookbean.cookid.CookFromIDBean;
import com.hoooopa.hoopa.hoopa.constants.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pray on 2018/4/28.
 */

public class CookPresenter extends BasePresenter<ICookView> {

    private ICookView view;

    public CookPresenter(ICookView view){
        this.view = view;
    }

    public void getBannerData(){

        view.showBanner_Start();

            CookModel.getBannerData(new ICookCallback() {
                @Override
                public void onBannerDataSucess(CookFromIDBean data) {
                }

                @Override
                public void onBannerDataFailure(String data) {

                }
            });




    }

    private List<Integer> getRandomID(){

        List<Integer> ids =new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);

        return ids;
    }




}
