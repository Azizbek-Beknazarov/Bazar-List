package com.example.roomdemo2.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdemo2.Model.BazarDB;
import com.example.roomdemo2.Model.BazarDao;
import com.example.roomdemo2.Model.BazarData;

import java.util.List;

public class BazarRepo {
    private BazarDao mBazarDao;
    private LiveData<List<BazarData>> mList;
    BazarRepo mRepo=new BazarRepo()

    public BazarRepo(Application application) {
        BazarDB db = BazarDB.getInstance(application);
        mBazarDao = db.bazarDao();
        mList = mBazarDao.getAllList();
    }

    public void insert(BazarData bazarData) {
        new InsertBazarAsyncTask(mBazarDao).execute(bazarData);
    }
    public void delete(BazarData bazarData){
        new DeleteBazarAsyncTask(mBazarDao).execute(bazarData);
    }
    public void reset(){
        new ResetBazarAsyncTask(mBazarDao).execute();
    }


    public void updateText(int id,String text){
        MyTaskParams params=new MyTaskParams(id,text);
        new UpdateTextBazarAsyncTask(mBazarDao).execute(params);
    }

    private static class UpdateTextBazarAsyncTask extends AsyncTask<MyTaskParams, Void, Void> {
        private BazarDao mDao;
        String text;
        int id;

        public UpdateTextBazarAsyncTask(BazarDao bazarDao) {
            this.mDao=bazarDao;
        }


        @Override
        protected Void doInBackground(MyTaskParams... myTaskParams) {
            int id =myTaskParams[0].id;
            String text = myTaskParams[0].text;
            mDao.updateText(id,text);
            return null;
        }
    }

    private static class MyTaskParams {
        int id;
        String text;

        MyTaskParams(int id, String text) {
            this.id = id;
            this.text = text;
        }
    }
    public LiveData<List<BazarData>> getAllBazarList() {
        return mList;
    }

    private static class InsertBazarAsyncTask extends AsyncTask<BazarData, Void, Void> {
        private BazarDao mDao;

        public InsertBazarAsyncTask(BazarDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(BazarData... bazarData) {
            mDao.insert(bazarData[0]);
            return null;
        }
    }

    private static class DeleteBazarAsyncTask extends AsyncTask<BazarData, Void, Void> {
        private BazarDao mDao;

        public DeleteBazarAsyncTask(BazarDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(BazarData... bazarData) {
            mDao.delete(bazarData[0]);
            return null;
        }
    }

    private static class ResetBazarAsyncTask extends AsyncTask<Void, Void, Void> {
        private BazarDao mDao;

        public ResetBazarAsyncTask(BazarDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.reset();
            return null;
        }
    }
}
