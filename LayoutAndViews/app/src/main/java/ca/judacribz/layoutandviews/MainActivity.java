package ca.judacribz.layoutandviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static ca.judacribz.layoutandviews.Constants.KEY_1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText etNumber1, etNumber2, etPersonName, etPersonGender;
    Button btnName;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        etPersonName = findViewById(R.id.etPersonName);
        etPersonGender = findViewById(R.id.etPersonGender);

        btnName = findViewById(R.id.btnDoMagic);
        tvName = findViewById(R.id.tvName);

        // Setting onClick listener of the button
        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int etNum1 = Integer.parseInt(etNumber1.getText().toString());
                int etNum2 = Integer.parseInt(etNumber2.getText().toString());
                tvName.setText(String.valueOf(etNum1 + etNum2));
            }
        });

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

    public void onToSecond(View view) {
        String value = etNumber1.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.setAction("sendingValue");
        intent.putExtra(KEY_1, value);
        startActivity(intent);
    }

    public void passPersonToSecond(View view) {
        String personName = etPersonName.getText().toString();
        String personGender = etPersonGender.getText().toString();

        Person person = new Person(personName, personGender);
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setAction("sendingPerson");
        intent.putExtra(Constants.KEY_2, person);
        startActivity(intent);

    }
}
