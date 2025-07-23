package mk.ukim.finki.emt2025.config;

public class AppConstants {

    public static final String DB_HOST = System.getenv("DB_HOST") !=null ? System.getenv("DB_HOST") : "localhost";
    public static final String DB_PORT = System.getenv("DB_PORT") !=null ? System.getenv("DB_PORT") : "2345";
    public static final String FRONTEND_URL = "http://" + (System.getenv("FRONTEND_HOST") !=null ? System.getenv("FRONTEND_HOST") + ":" + System.getenv("FRONTEND_PORT") : "http://localhost:3000");

}
