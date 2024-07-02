package com.example.airline;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.airline.ProfileActivity;
import com.example.airline.PurchaseActivity;
import com.example.airline.PurchasedTicketsActivity;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ProfileActivity();
            case 1:
                return new PurchaseActivity();
            default:
                return new PurchasedTicketsActivity();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Number of tabs
    }
}