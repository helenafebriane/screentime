package com.example.screentime.fragment.AppsWithUsageLimitInfoFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.screentime.DialogController.AppsUsageRestrictionInfoDialogs;
import com.example.screentime.adapter.AppAddUsageLimitAdapter;
import com.example.screentime.helper.DBWrappers;
import com.example.screentime.model.AppAddUsageLimit;
import com.example.screentime.R;

import java.util.List;


public class AppsWithTimeLimitFragment extends Fragment {

    private View rootView;
    private ListView allAppsListView;
    private List<AppAddUsageLimit> allAppsList;

    private boolean isFragmentCreated = false;

    public AppsWithTimeLimitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_apps_list, container, false);
        isFragmentCreated = true;
        allAppsListView = (ListView) rootView.findViewById(R.id.allAppsListView);
        allAppsList = DBWrappers.getAppsWithTimeLimit(getActivity());

        AppAddUsageLimitAdapter appAddUsageLimitAdapter = new AppAddUsageLimitAdapter(getActivity(), allAppsList);
        allAppsListView.setAdapter(appAddUsageLimitAdapter);

        allAppsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        AppAddUsageLimit appAddUsageLimit = allAppsList.get(position);

                        if (!appAddUsageLimit.getAppName().equals(DBWrappers.NO_TIME_RESTRICTED_APPS)) {
                            AppsUsageRestrictionInfoDialogs.showDialogAppsInfoWithTimeLimit(getActivity(), appAddUsageLimit);
                        }
                    }
                }
        );

        return rootView;
    }


    public void updateList() {
        if (!isFragmentCreated) {
            return;
        }

        allAppsList = DBWrappers.getAppsWithTimeLimit(getActivity());
        AppAddUsageLimitAdapter appAddUsageLimitAdapter = new AppAddUsageLimitAdapter(getActivity(), allAppsList);
        allAppsListView.setAdapter(appAddUsageLimitAdapter);
    }

    @Override
    public void onDestroyView() {
        isFragmentCreated = false;
        super.onDestroyView();
    }


}
