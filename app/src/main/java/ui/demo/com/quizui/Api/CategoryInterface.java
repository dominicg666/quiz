package ui.demo.com.quizui.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ui.demo.com.quizui.Fragment.ModelCategory;

/**
 * Created by Design-4 on 3/5/2018.
 */

public interface CategoryInterface {

    @GET("api_category.php")
    Call<CategoryResponse> getCategoriesList(@Query("api_key") String apiKey);

    @GET("api_count.php")
    Call<CategoryCount> getCategoryCount(@Query("category") String category,@Query("api_key") String apiKey);

    @GET("api.php")
    Call<CategoryCount> getCategoryCount(@Query("amount") String amount,@Query("category") String category,@Query("type") String type);

    //Call<CategoryCount> getCategoryCount(@Path("id") int id, @Query("api_key") String apiKey);



}
