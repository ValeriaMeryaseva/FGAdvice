package com.example.lera.fgadvice;

import android.app.Fragment;
import android.widget.ListView;

import com.example.lera.fgadvice.adapters.FavoritesListAdapter;
import com.example.lera.fgadvice.db.DatabaseDAO;
import com.example.lera.fgadvice.db.DatabaseHelper;
import com.example.lera.fgadvice.model.Advice;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_favorite_advices)
public class FavoritesFragment extends Fragment {

    @ViewById(R.id.favorites_list)
    ListView mFavoritesListView;
    FavoritesListAdapter adapter;

    @AfterViews
    public void bindViews() {
        DatabaseDAO db = DatabaseDAO.getDBInstance(getActivity());
        List<Advice> adviceList = DatabaseHelper.getAdvices(db);
        adapter = new FavoritesListAdapter(getActivity(), adviceList);
        mFavoritesListView.setAdapter(adapter);
    }

    public void UpdateList(Advice advice){
        adapter.addElement(advice);
        adapter.notifyDataSetChanged();
    }
}
