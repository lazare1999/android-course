package ge.msda.myapplication.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import ge.msda.myapplication.apiUtil.WorkerService;

public class SecondWorker extends Worker {

    public SecondWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            WorkerService.doSomething(this.getClass().getName());
            return Result.success();
        } catch (InterruptedException e) {
            return Result.retry();
        }
    }

}