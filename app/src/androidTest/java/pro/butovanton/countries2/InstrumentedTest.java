package pro.butovanton.countries2;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import pro.butovanton.countries2.Net.Api;
import pro.butovanton.countries2.Repo.Model;
import pro.butovanton.countries2.Repo.Repo;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    public InstrumentedTest() {

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("pro.butovanton.countries2", appContext.getPackageName());
    }

    Repo repo = Repo.getInstance();
    private CountDownLatch countDownLatch = new CountDownLatch(1);

   @Test
   public void LoadRepo() throws InterruptedException {
       repo.startLoad(new MainContract.Model.OnFinishedListener() {
            @Override
            public void Finished() {
                countDownLatch.countDown();
            }

            @Override
            public void Failure() {
                assertTrue(false);
            }
        });
        countDownLatch.await(2, TimeUnit.MINUTES);
   }

   Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
   List<Model> countries;

   @Test
   public void ApiGet() throws IOException {
       Api api = new Api(context);
       countries = api.getAll();
       assertTrue( countries.size() > 0);

     repo.saveToRoom(countries);
     List<Model> countresnew = repo.roomGet();
     assertEquals(countries.size(), countresnew.size());
     repo.roomDelAll();
    }

    @Test
    public void DelRepo() {
       repo.roomDelAll();
       assertEquals(repo.roomGet().size(), 0);
    }

}



