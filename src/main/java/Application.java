import com.google.gson.Gson;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<?> future = executorService.submit(() -> {
            Data data = new Gson().fromJson(/* language=json */ """
                    {
                      "properties": {
                        "someNumber": 87
                      }
                    }
                    """, Data.class);
            int number = (Integer) data.properties.get("someNumber");
            System.out.println("Number was: " + number);
        });

        try {
            future.get();
        } catch (Exception e) {
            // to be, or not to be
        }

        // this was omitted in original code
        // executorService.shutdown();
    }

    public static class Data {

        Properties properties;
    }
}
