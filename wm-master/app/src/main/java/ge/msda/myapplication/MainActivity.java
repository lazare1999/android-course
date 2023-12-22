package ge.msda.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import ge.msda.myapplication.workers.FirstWorker;
import ge.msda.myapplication.workers.SecondWorker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        first();
//        second();
//        third();
//        fourth();
    }

    private void first() {

        Constraints.Builder constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiresBatteryNotLow(false)
                .setRequiresStorageNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED);

        OneTimeWorkRequest first = new OneTimeWorkRequest
                .Builder(FirstWorker.class)
                .setConstraints(constraints.build())
                .addTag("MEDIA")
                .build();

        OneTimeWorkRequest second = new OneTimeWorkRequest
                .Builder(SecondWorker.class)
                .addTag("MEDIA")
                .build();

        WorkManager
                .getInstance(this)
                .enqueue(Arrays.asList(first, second));

        WorkManager.getInstance(this)
                .beginWith(first)
                .then(Arrays.asList(first, first))
                .enqueue();

        WorkManager
                .getInstance(this)
                .beginUniqueWork("MY_WORK", ExistingWorkPolicy.REPLACE, first)
                .enqueue();

        WorkContinuation workContinuation1 = WorkManager.getInstance(this).beginWith(first).then(second);
        WorkContinuation workContinuation2 = WorkManager.getInstance(this).beginWith(first);

        WorkContinuation.combine(Arrays.asList(workContinuation1, workContinuation2)).enqueue();

        PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest.Builder(FirstWorker.class, 3, TimeUnit.SECONDS);
        WorkManager.getInstance(this).enqueue(builder.build());


    }

    private void second() {

        OneTimeWorkRequest first = new OneTimeWorkRequest
                .Builder(FirstWorker.class)
                .addTag("MEDIA")
                .build();

        UUID id = first.getId();

        WorkManager.getInstance(this).enqueue(first);

        WorkManager.getInstance(this).cancelWorkById(id);

    }

    private void third() {

        Data data = new Data.Builder()
                .putString("KEY_1", "Maserati MC 20")
                .putString("KEY_2", "Maserati Grand Turismo")
                .build();

        OneTimeWorkRequest first = new OneTimeWorkRequest
                .Builder(FirstWorker.class)
                .addTag("MEDIA")
                .setInputData(data)
                .build();

        WorkManager.getInstance(this).enqueue(first);

    }
    private void fourth() {

        PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest.Builder(FirstWorker.class, 3, TimeUnit.SECONDS);
        WorkManager.getInstance(this).enqueue(builder.build());

    }

}