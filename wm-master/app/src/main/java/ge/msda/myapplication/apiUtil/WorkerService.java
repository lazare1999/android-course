package ge.msda.myapplication.apiUtil;

import android.util.Log;

public class WorkerService {

    public static void doSomething(String workerName) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Log.d("MyData", workerName + " - " + i);
            Thread.sleep(1000);
        }
    }

}