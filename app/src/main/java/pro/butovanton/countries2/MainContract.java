package pro.butovanton.countries2;

/**
 * @Param
 */

public interface MainContract {

    interface View {
        void LoadOk();
        void LoadFailure();
    }

    interface Presenter {
        void onDestroy();
    }

    interface Model {

        interface OnFinishedListener {
            void Finished();
            void Failure();
        }

        void startLoad(OnFinishedListener onFinishedListener);
    }
}




