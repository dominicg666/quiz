package ui.demo.com.quizui.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Design-4 on 3/8/2018.
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    List<String> mKeyList = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(mKeyList.get(position));
    }

    @Override
    public int getCount() {
        return mKeyList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "SCOUT " + (getCount() - position);
    }

    public void add(int position, String key) {
        mKeyList.add(position, key);
        notifyDataSetChanged();
    }
}
