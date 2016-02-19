package com.example.cyncyn.YoinkProject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class DealFragment extends Fragment {

    private static final String FRAGMENT_DEAL_ID = "dealId";

    private int mDealId;
    private TextView mDescrptField;
    private TextView mCategoryField;
    private TextView mBusinessName;
    private TextView mBusinessAddress;
    public Deal mDeal;

    public static DealFragment newInstance(int dealId) {
        DealFragment fragment = new DealFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_DEAL_ID, dealId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDealId = getArguments().getInt(FRAGMENT_DEAL_ID);
        }
        DealStore store = DealStore.getInstance();
        List<Deal> deals = store.getDeals();

        mDeal = null;
        for (int i = 0; i != deals.size(); i++) {
            Deal b = deals.get(i);
            if (b.getId() == mDealId) {
                mDeal = b;
                break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_deal, container, false);

        mDescrptField = (TextView) fragment.findViewById(R.id.deal_desc_editText);
        mCategoryField = (TextView) fragment.findViewById(R.id.deal_cat_editText);
        mBusinessName = (TextView) fragment.findViewById(R.id.deal_biz_editText);
        mBusinessAddress = (TextView) fragment.findViewById(R.id.biz_add_editText);

        if (mDeal != null) {
            mDescrptField.setText(mDeal.getDescription());
            mCategoryField.setText(mDeal.getCategory());
            mBusinessName.setText(mDeal.getBusinessName());
            mBusinessAddress.setText(mDeal.getBusinessAddress());
        }

        return fragment;

    }
}
