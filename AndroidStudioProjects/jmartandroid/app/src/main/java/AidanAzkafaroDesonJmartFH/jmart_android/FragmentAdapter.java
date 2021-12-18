package AidanAzkafaroDesonJmartFH.jmart_android;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Class untuk adapter dari fragment
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm, @NonNull Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    /**
     *
     * @param position posisi fragment yang dilihat
     * @return
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 1 :
                return new FilterFragment();
        }
        return new ProductFragment();
    }

    /**
     * total fragment yang dimiliki
     * @return
     */
    @Override
    public int getItemCount() {
        return 2;
    }


}
