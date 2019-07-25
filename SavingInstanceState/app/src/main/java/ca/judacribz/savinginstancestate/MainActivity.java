package ca.judacribz.savinginstancestate;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText etName, personName, personGender;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding views
        etName = findViewById(R.id.etName);
        tvName = findViewById(R.id.tvName);
        personName = findViewById(R.id.etPersonName);
        personGender = findViewById(R.id.etPersonGender);

        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void doSomething(View view) {
        switch (view.getId()) {
            case R.id.btnChangeText:
                String name = etName.getText().toString();
                tvName.setText(name);
                break;

            case R.id.btnGoToSecond:
                List<Person> personList = new ArrayList<>();
                personList.add(new Person((
                        personName.getText().toString()),
                        personGender.getText().toString())
                );
                Intent intent = new Intent(this, SecondActivity.class);
                intent.setAction("goToSecond");
                intent.putParcelableArrayListExtra(
                        "person",
                        (ArrayList<? extends Parcelable>) personList
                );
                startActivity(intent);
                break;

            case R.id.btnGoToSecondForResult:
                break;

            case R.id.btnSaveToSharedPrefs:
                break;
            case R.id.btnShareData:
                break;
        }
    }
}
