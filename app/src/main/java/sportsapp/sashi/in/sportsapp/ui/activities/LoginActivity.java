package sportsapp.sashi.in.sportsapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.crashlytics.android.Crashlytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sportsapp.sashi.in.sportsapp.R;
import sportsapp.sashi.in.sportsapp.models.app.Batch;
import sportsapp.sashi.in.sportsapp.models.app.Coach;
import sportsapp.sashi.in.sportsapp.utils.Constants;
import sportsapp.sashi.in.sportsapp.utils.InputValidator;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText emailET, pwdET;
    private Button loginBtn;
    private TextView forgotTV;

    private static String email, pwd, coach_id;

    private Coach coach;
    private Batch batch;
    private List<Batch> batchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginBtn.setOnClickListener(this);
        forgotTV.setOnClickListener(this);

    }

    private void init() {
        emailET = findViewById(R.id.emailET);
        pwdET = findViewById(R.id.pwdET);
        loginBtn = findViewById(R.id.loginBtn);
        forgotTV = findViewById(R.id.forgotTV);

        email = emailET.getText().toString();
        pwd = pwdET.getText().toString();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                    doLogin(email, pwd);
//                Crashlytics.getInstance().crash();

//                if (validate()){
//                } else {
//                    Snackbar.make(findViewById(android.R.id.content), "Invalid Inputs", Snackbar.LENGTH_LONG).show();
//                }

                break;
            case R.id.forgotTV:
                // TODO: 9/21/2018 forgot pwd api
                break;
        }
    }

    private void getBatches(final String coach_id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("coach_id", coach_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(Constants.COACH_BATCH_LIST)
                .setTag("Get Coach Batches")
                .setPriority(Priority.HIGH)
                .addJSONObjectBody(jsonObject)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null){
                            Log.d(TAG, "Batch Response:\t" + response.toString());

                            try {
                                JSONObject object = new JSONObject(response.toString());

                                JSONArray array = object.getJSONArray("batch_details");
                                for (int m = 0; m < array.length(); m++) {

                                    JSONObject jsonObject = array.getJSONObject(m);

                                    String name_batch = jsonObject.getString("batch_name");
                                    String id_batch = jsonObject.getString("batch_id");

                                    batch = new Batch();
                                    batch.setBatchId(id_batch);
                                    batch.setBatchName(name_batch);

                                    batchList.add(batch);
                                    Log.d(TAG, "Batch Size:\t" + batchList.size());
                                    if (batchList.size() > 1){
                                        Intent intent = new Intent(LoginActivity.this, ChooseBatchActivity.class);
                                        intent.putExtra("coach_id", coach_id);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // TODO: 9/22/2018  Fetch Program Lists for coach current batch
                                    }

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

    private void doLogin(String email, String pwd) {

        JSONObject jsonObject = new JSONObject();
        try {
//            jsonObject.put("username", email);
//            jsonObject.put("password", pwd);
            jsonObject.put("username", "testcoach@gmail.com");
            jsonObject.put("password", "123@abcd");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(Constants.LOGIN_COACH)
                .addJSONObjectBody(jsonObject)
                .setTag("Coach Login")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null){

                            Log.d(TAG, "Login Resp:\t" + response.toString());

                            try {
                                JSONObject object = new JSONObject(response.toString());
                                JSONObject details = object.getJSONObject("coach_details");

                                coach_id = details.getString("coach_id");

//                                getBatches(coach_id);

                                String academyId = details.getString("academy_id");
                                String username = details.getString("username");
                                String firstName = details.getString("first_name");
                                String lastName = details.getString("last_name");
                                String midName = details.getString("middle_name");
                                String nick = details.getString("nick_name");
                                String gender = details.getString("gender");
                                String mobile = details.getString("mobile");
                                String state = details.getString("state");
                                String email_coach = details.getString("email");

                                // TODO: 9/21/2018 Use db
                                saveCoachDetails(coach_id, academyId, username, firstName, lastName, midName, nick, gender, mobile, state, email_coach);

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

    private void saveCoachDetails(String idCoach, String academyId, String username, String firstName, String lastName, String midName, String nick, String gender, String mobile, String state, String email) {
        coach = new Coach();
        coach.setCoachId(idCoach);
        coach.setAcademyId(academyId);
        coach.setUsername(username);
        coach.setEmailAddr(email);
        coach.setFirstName(firstName);
        coach.setLastName(lastName);
        coach.setMidName(midName);
        coach.setNickName(nick);
        coach.setGender(gender);
        coach.setMobileNum(mobile);
        coach.setOriginState(state);

        coach.save();

        Coach coachQuery = new Select()
                .from(Coach.class)
                .where("coach_id=?", coach_id)
                .executeSingle();
        Log.d(TAG, "Coach id:\t" + coachQuery.coachId);
         coach_id = coachQuery.coachId;
        getBatches(idCoach);

    }

    private void startHomeActivity(){
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    private boolean validate() {
        if (InputValidator.isEmpty(email) && InputValidator.isEmpty(pwdET.getText().toString())){
            return false;
        }
        return true;
    }
}
