package com.dobrolinskiy.mt_lab2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.dobrolinskiy.mt_lab2.model.Entry;
import com.dobrolinskiy.mt_lab2.model.RetrofitClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dobrolinskiy.mt_lab2.model.Pic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment {


    private View view;
    public List<Entry> entries;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        getModel(view);
        return view;
    }


    private void getModel(View view) {
        Call<Pic> call = RetrofitClient.getInstance().getMyApi().getModel();
        call.enqueue(new Callback<Pic>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Pic> call, Response<Pic> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "404", Toast.LENGTH_SHORT).show();
                } else {

                    entries = response.body().getEntries();
                    MyListAdapter adapter = new MyListAdapter(getContext(), entries);

                    ListView messageList = view.findViewById(R.id.listView);

                    // устанавливаем для списка адаптер
                    messageList.setAdapter(adapter);
                    // добавляем для списка слушатель
                    messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            // получаем выбранный элемент
                            String prettyString = null;
                            try {
                                String selectedItemDescription = new ObjectMapper().writeValueAsString(entries.get(position));
                                JSONObject json = new JSONObject(selectedItemDescription);
                                prettyString = json.toString(4);


                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            System.out.println(); // Print it with specified indentation

                            Intent intent = new Intent(getContext(), DetailsActivity.class);
                            intent.putExtra("desc", prettyString.substring(2, prettyString.length()-2));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Pic> call, Throwable t) {
                Toast.makeText(getContext(), "Connection error", Toast.LENGTH_SHORT).show();
            }

        });

    }

}