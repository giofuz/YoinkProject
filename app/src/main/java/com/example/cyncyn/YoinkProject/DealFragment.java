package com.example.cyncyn.YoinkProject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

public class DealFragment extends Fragment {

    private static final String FRAGMENT_DEAL_ID = "dealId";

    private int mDealId;
    private EditText mDescrptField;
    private EditText mCategoryField;
    private EditText mBizIdField;
    private Deal mDeal;

    public static DealFragment newInstance(int bookId) {
        DealFragment fragment = new DealFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_DEAL_ID, bookId);
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
        View fragment = inflater.inflate(R.layout.fragment_book, container, false);

        mDescrptField = (EditText) fragment.findViewById(R.id.book_title_editText);
        mCategoryField = (EditText) fragment.findViewById(R.id.book_firstname_editText);
        mBizIdField = (EditText) fragment.findViewById(R.id.book_year_editText);

        if (mDeal != null) {
            mDescrptField.setText(mDeal.getDescription());
            mCategoryField.setText(mDeal.getCategory());
            //mBizIdField.setText(mDeal.getBusinessId());
        }

        return fragment;
    }
}
