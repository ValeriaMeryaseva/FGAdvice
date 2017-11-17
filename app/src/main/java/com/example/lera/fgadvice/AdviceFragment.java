package com.example.lera.fgadvice;

import android.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.example.lera.fgadvice.model.Advice;
import com.example.lera.fgadvice.net.LoadAdvice;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EFragment(R.layout.fragment_advice)
public class AdviceFragment extends Fragment {

    private final static String URL = "http://fucking-great-advice.ru/";

    private Advice mAdvice;

    @ViewById(R.id.advice_text)
    TextView mAdviceTextView;

    @Click(R.id.new_advice_button)
    public void showNewAdvice() {
        Request();
    }

    @AfterViews
    public void bindViews() {
        Request();
    }

    private void Request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoadAdvice loadAdvice = retrofit.create(LoadAdvice.class);
        Call<Advice> call = loadAdvice.getAdvice();

        call.enqueue(new Callback<Advice>() {
            @Override
            public void onResponse(Call<Advice> call, Response<Advice> response) {
                String text = Html.fromHtml(response.body().getText()).toString();
                mAdvice = response.body();
                mAdvice.setText(text);
                mAdviceTextView.setText(mAdvice.getText());
            }

            @Override
            public void onFailure(Call<Advice> call, Throwable t) {
                Log.d("TAG", t.toString());
            }
        });
    }
}
