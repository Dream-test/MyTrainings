package movies;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.*;



public class TestMovies {
    String DB_URL = "jdbc:postgresql://localhost:5432/movies";
    String DB_USER = "postgres";
    String DB_PASSWORD = "postgres";


    @Test
    void appReturnedMovieGenres() {
        OkHttpClient client = new OkHttpClient();
        Request  request = new Request.Builder()
                .url("http://localhost:4000/v1/genres")
                .build();

        try {
            String response = client.newCall(request).execute().body().string();
            System.out.println(response);
            Assertions.assertTrue(response.contains("Adventure"));
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }
    }

    @Test
    void moviesListIsNotEmpty() {
        String filmTitle = "";
        String filmId = "";

        try {
           // Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, title FROM movies LIMIT 1");
            while (resultSet.next()) {
                filmTitle = resultSet.getString("title");
                filmId = resultSet.getString("id");
            }
            System.out.println("film title: " + filmTitle + ", film ID: " + filmId);
            connection.close();
            } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }



        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/movies/" + filmId)
                .build();

        try {
            String response = client.newCall(request).execute().body().string();
            System.out.println(response);
            Assertions.assertTrue(response.contains("\"title\":\"" + filmTitle + "\","));
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }
    }
}
