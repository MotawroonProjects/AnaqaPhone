package com.anaqaphone.activities_fragments.activity_checkout.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.anaqaphone.R;
import com.anaqaphone.activities_fragments.activity_checkout.CheckoutActivity;
import com.anaqaphone.databinding.FragmentPaymentTypeBinding;
import com.anaqaphone.interfaces.Listeners;
import com.anaqaphone.models.AddOrderModel;

public class Fragment_Payment_Type extends Fragment implements Listeners.PaymentTypeAction {
    private static final String TAG = "data";
    private CheckoutActivity activity;
    private FragmentPaymentTypeBinding binding;
    private String payment_type;
    private AddOrderModel addOrderModel;


    public static Fragment_Payment_Type newInstance(AddOrderModel addOrderModel)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG,addOrderModel);
        Fragment_Payment_Type fragment_payment_type = new Fragment_Payment_Type();
        fragment_payment_type.setArguments(bundle);
        return fragment_payment_type;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_type, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();



    }


    private void initView() {
        activity = (CheckoutActivity) getActivity();
        binding.setAction(this);

        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            addOrderModel = (AddOrderModel) bundle.getSerializable(TAG);
        }
    }


    public void setModel(AddOrderModel model)
    {
        this.addOrderModel =model;
    }

    @Override
    public void onCredit() {
        payment_type ="card";
        binding.img1.setVisibility(View.VISIBLE);
        binding.img2.setVisibility(View.GONE);
        binding.img3.setVisibility(View.GONE);
        addOrderModel.setPayment_type(payment_type);

    }

    @Override
    public void onPaypal() {
        payment_type = "paypal";
        binding.img1.setVisibility(View.GONE);
        binding.img2.setVisibility(View.VISIBLE);
        binding.img3.setVisibility(View.GONE);
        addOrderModel.setPayment_type(payment_type);

    }

    @Override
    public void onCash() {
        payment_type = "cash";
        binding.img1.setVisibility(View.GONE);
        binding.img2.setVisibility(View.GONE);
        binding.img3.setVisibility(View.VISIBLE);
        addOrderModel.setPayment_type(payment_type);

    }

    @Override
    public void onNext() {

        if (addOrderModel.isStep3Valid(activity))
        {
            activity.updateModel(addOrderModel);
            activity.createOrder();
        }




    }

    @Override
    public void onPrevious() {
        activity.displayFragmentDate();
    }
}