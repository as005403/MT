//package com.foxrider.mt_lab2;
//
//import static org.junit.Assert.fail;
//
//import com.foxrider.mt_lab2.model.MemeResponse;
//import com.foxrider.mt_lab2.model.RetrofitClient;
//
//import org.junit.Test;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//public class ExampleUnitTest {
//
//    List<MemeResponse> memeResponses;
//
//    private void getModel() {
//        Call<List<MemeResponse>> call = RetrofitClient.getInstance().getMyApi().getModel();
//        call.enqueue(new Callback<List<MemeResponse>>() {
//            @Override
//            public void onResponse(Call<List<MemeResponse>> call, Response<List<MemeResponse>> response) {
//                System.out.println(response + " success");
//                memeResponses = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<MemeResponse>> call, Throwable t) {
//                System.out.println(t.getCause());
//                fail();
//            }
//
//        });
//
//
//    }
//
//    @Test
//    public void addition_isCorrect() {
//        getModel();
//        System.out.println(memeResponses);
//    }
//}