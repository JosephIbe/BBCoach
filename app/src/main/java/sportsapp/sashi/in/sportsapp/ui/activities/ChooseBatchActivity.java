package sportsapp.sashi.in.sportsapp.ui.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sportsapp.sashi.in.sportsapp.R;
import sportsapp.sashi.in.sportsapp.adapters.BatchAdapter;
import sportsapp.sashi.in.sportsapp.models.app.Batch;
import sportsapp.sashi.in.sportsapp.models.app.BatchFooter;
import sportsapp.sashi.in.sportsapp.models.app.Coach;
import sportsapp.sashi.in.sportsapp.utils.Constants;

public class ChooseBatchActivity extends AppCompatActivity {

    private static final String TAG = ChooseBatchActivity.class.getSimpleName();

    private TextView coachNameTV;
    private ShimmerFrameLayout mShimmerFrameLayout;
    private RecyclerView batchRV;

    private Batch batch;
    private String name_batch, id_batch, message;

//    private List<Object> list = new ArrayList<>();
    private List<Batch> list = new ArrayList<>();
    private BatchAdapter adapter;

    private String coachName = null, coachId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_batch);

        init();

        batch = new Batch();
        queryCoachInfo();

    }

    private void queryCoachInfo() {
        Coach coach = new Select()
                .from(Coach.class)
                .orderBy("id ASC")
                .executeSingle();
        coachName = coach.firstName;
        coachId = coach.coachId;

        if (coachName != null && coachId != null) {
            coachNameTV.setText(coachName);
            getBatches(coachId);
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    getBatches(coachId);
//                }
//            }, 5000);
        }
    }

    private void getBatches(String coachId) {
        final JSONObject batchObj = new JSONObject();
        try {
            batchObj.put("coach_id", Integer.parseInt(coachId));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(Constants.COACH_BATCH_LIST)
                .addJSONObjectBody(batchObj)
                .setTag("Fetch Batch List")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            Log.d(TAG, "Batches Response:\t" + response.toString());
                            try {
                                JSONObject object = new JSONObject(response.toString());

                                message = object.getString("message");

                                JSONArray array = object.getJSONArray("batch_details");
                                for (int m = 0; m < array.length(); m++) {

                                    JSONObject jsonObject = array.getJSONObject(m);

                                    name_batch = jsonObject.getString("batch_name");
                                    id_batch = jsonObject.getString("batch_id");

//                                    fetchProgramList(id_batch, coachId);

                                    Log.d(TAG, "name:\t" + name_batch);
                                    batch.setBatchId(id_batch);
                                    batch.setBatchName(name_batch);
                                    batch.save();
                                    Log.d(TAG, "BN local:\t" + batch.getBatchName() + "BN n/w:\t" + name_batch);

                                    list.add(batch);

//                                    list.add(getList());

                                    BatchFooter footer = new BatchFooter();
//                                    list.add(footer.getButton());
//                                    list.add(batch.getBatchFooter());

                                    Log.d(TAG, "List size:\t" + list.size());

                                    adapter = new BatchAdapter(ChooseBatchActivity.this, list);
                                    adapter.setMessageValue(message);
                                    batchRV.setAdapter(adapter);

                                    mShimmerFrameLayout.stopShimmerAnimation();

                                    Log.d(TAG, "List items total:\t" + adapter.getItemCount());

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private List<Batch> getList() {
        return new Select()
                .from(Batch.class)
                .orderBy("id ASC")
                .execute();
    }

    private void init() {
        coachNameTV = findViewById(R.id.coachNameTV);
        batchRV = findViewById(R.id.batchRV);

        batchRV.setLayoutManager(new LinearLayoutManager(this));
        batchRV.setHasFixedSize(true);

        mShimmerFrameLayout = findViewById(R.id.batchShimmerView);
        mShimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShimmerFrameLayout.stopShimmerAnimation();
    }
}
