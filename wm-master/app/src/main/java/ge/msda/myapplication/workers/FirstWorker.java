package ge.msda.myapplication.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import ge.msda.myapplication.apiUtil.WorkerService;

public class FirstWorker extends Worker {

    public FirstWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        String car1 = getInputData().getString("KEY_1");
        String car2 = getInputData().getString("KEY_1");

        try {
            WorkerService.doSomething(this.getClass().getName());
            return Result.success();
        } catch (InterruptedException e) {
            return Result.retry();
        }

    }

}