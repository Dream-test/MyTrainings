package movies;

import com.google.gson.Gson;
import movies.models.AuthResponse;
import okhttp3.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminRoutesTest {
    static String DB_URL = "jdbc:postgresql://localhost:5432/movies";
    static String DB_USER = "postgres";
    static String DB_PASSWORD = "postgres";
    static Connection connection;
    OkHttpClient client;

    @BeforeAll static void initDBConnection() {
        try {
            // Class.forName("org.postgresql.Driver");
            AdminRoutesTest.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }
    }

    @AfterAll static void closeDBConnection() {
        try {
            AdminRoutesTest.connection.close();
        } catch (Exception e) {
            Assertions.fail("Exception:" + e.getMessage());
        }
    }

    @BeforeEach void initHttClient() {
        this.client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + this.getAccessToken())
                            .build();
                    return chain.proceed(request);
                })
                .build();
    }

    String getAccessToken() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"email\":\"admin@example.com\",\"password\":\"secret\"}", mediaType);
        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/authenticate")
                .post(body)
                .build();

        try {
            String response = client.newCall(request).execute().body().string();
            AuthResponse authResponse = new Gson().fromJson(response, AuthResponse.class);
            return authResponse.accessToken;
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
            return "";
        }
    }

    @Test
    void adminCanGetMovies() {
        String filmTitle = "";
        try {
            // Class.forName("org.postgresql.Driver");
            Statement statement = AdminRoutesTest.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title FROM movies where id=6");
            while (resultSet.next()) {
                filmTitle = resultSet.getString("title");
            }
            System.out.println("adminCanGetMovies / film title: " + filmTitle);
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }

        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/admin/movies/6")
                .build();
        try {
            String response = this.client.newCall(request).execute().body().string();
            System.out.println("adminCanGetMovies response:" + response);
            Assertions.assertTrue(response.contains("\"title\":\"" + filmTitle + "\","));
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }
    }

    @Test
    void appReturnedMovieGenres() {
        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/genres")
                .build();

        try {
            String response = this.client.newCall(request).execute().body().string();
            System.out.println("appReturnedMovieGenres response:" + response);
            Assertions.assertTrue(response.contains("\"id\":11,\"genre\":\"Adventure\""));
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }
    }

    @Test
    void appReturnedMovies() {
        String filmTitle = "";
        String filmID = "";
        try {
            // Class.forName("org.postgresql.Driver");
            Statement statement = AdminRoutesTest.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, title FROM movies LIMIT 1");
            while (resultSet.next()) {
                filmTitle = resultSet.getString("title");
                filmID = resultSet.getString("id");
            }
            System.out.println("appReturnedMovies /film title: " + filmTitle + "  film id: " + filmID);
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }

        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/movies")
                .build();

                try {
                    String response = this.client.newCall(request).execute().body().string();
                    System.out.println("appReturnedMovies response:" + response);
                    Assertions.assertTrue(response.contains("\"id\":" + filmID + ",\"title\":\"" + filmTitle + "\","));
                } catch (Exception e) {
                    Assertions.fail("Exception: " + e.getMessage());
                }
    }

    @Test
    void appReturnMoviesByGenres() {
        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/movies/genres/11")
                .build();

        try {
            String response = this.client.newCall(request).execute().body().string();
            System.out.println("appReturnMoviesByGenres response:" + response);
            Assertions.assertTrue(response.contains("\"id\":6,\"title\":\"Spirited Away\""));
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }

    }

    @Test
    void appReturnedMoviesById() {
        String filmTitle = "";
        String filmID = "5";
        try {
            // Class.forName("org.postgresql.Driver");
            Statement statement = AdminRoutesTest.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, title FROM movies WHERE id = " + filmID);
            while (resultSet.next()) {
                filmTitle = resultSet.getString("title");
                filmID = resultSet.getString("id");
            }
            System.out.println("appReturnedMoviesById /film title: " + filmTitle + "  film id: " + filmID);
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }

        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/movies/" + filmID)
                .build();

        try {
            String response = this.client.newCall(request).execute().body().string();
            System.out.println("appReturnedMoviesById response:" + response);
            Assertions.assertTrue(response.contains("\"id\":" + filmID + ",\"title\":\"" + filmTitle + "\","));
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }
    }

    @Test
    void logOutLogInUser() {
        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/logout")
                .build();

        try {
            Response response = this.client.newCall(request).execute();
            System.out.println("logOutLogInUser response:" + response.code());
            Assertions.assertEquals(202, response.code());
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"email\":\"admin@example.com\",\"password\":\"secret\"}", mediaType);
        request = new Request.Builder()
                .url("http://localhost:4000/v1/authenticate")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("logOutLogInUser response:" + response.code());
            Assertions.assertEquals(200, response.code());
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }
    }

/*
    @Test
    void patchMovie() {
        String filmTitle = "";
        String filmID = "";
        try {
            Statement statement = AdminRoutesTest.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, title FROM movies LIMIT 1");
            while (resultSet.next()) {
                filmTitle = resultSet.getString("title");
                filmID = resultSet.getString("id");
            }
            System.out.println("film title: " + filmTitle);
        } catch (Exception e) {
            Assertions.fail("Exception: " + e.getMessage());
        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"title\":\"" + filmTitle + "\", \"description\":\"new description\"}", mediaType);
        Request request = new Request.Builder()
                .url("http://localhost:4000/v1/admin/movies/" + filmID)
                .patch(body)
                .build();
        try {
            String response = this.client.newCall(request).execute().body().string();
            System.out.println(response);
            Assertions.assertTrue(response.contains("new description"));
        } catch (Exception e) {
            Assertions.fail("Exception:" + e.getMessage());
        }
    }

 */
}
