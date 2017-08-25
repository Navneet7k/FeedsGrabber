package navneet.com.hackernews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HackFragment hck = new HackFragment();
                return hck;
            case 1:
                NewsFragment newsFragment=new NewsFragment();
                return newsFragment;
            case 2:
                IGNFragment ignFragment=new IGNFragment();
                return ignFragment;
            case 3:
                Techcrunch techcrunch=new Techcrunch();
                return techcrunch;
            case 4:
                Mashable mashable=new Mashable();
                return mashable;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
