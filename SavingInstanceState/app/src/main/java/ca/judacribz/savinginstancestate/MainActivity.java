package ca.judacribz.savinginstancestate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public void doSomething(View view) throws NoSuchAlgorithmException {
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

                personList.add(new Person(
                        getMessageDigest(personName.getText().toString()),
                        getMessageDigest(personGender.getText().toString())
                ));

                Intent intent = new Intent(this, SecondActivity.class);
                intent.setAction("goToSecond");
                intent.putParcelableArrayListExtra(
                        "person",
                        (ArrayList<? extends Parcelable>) personList
                );
                startActivity(intent);
                break;

            case R.id.btnGoToSecondForResult:
                Intent forResultIntent = new Intent(this, SecondActivity.class);
                Person person = new Person(personName.getText().toString(), personGender.getText().toString());
                forResultIntent.setAction("goToSecondForResult");
                forResultIntent.putExtra("person", person);
                startActivityForResult(forResultIntent, 2);
                break;

            case R.id.btnSaveToSharedPrefs:
                SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", personName.getText().toString());
                editor.putString("gender", personGender.getText().toString());
                editor.commit();
                Intent intent1 = new Intent(this, SecondActivity.class);
                intent1.setAction("sharedPrefs");
                startActivity(intent1);
                break;

            case R.id.btnShareData:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 2) {
            String name = data.getStringExtra("personName");
            tvName.setText(name);
        }
    }

    private String getMessageDigest(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.reset();
        messageDigest.update(str.getBytes(Charset.forName("UTF-8")));
        StringBuilder hexString = new StringBuilder();
        byte[] messageDigestArray = messageDigest.digest();

        for (byte message : messageDigestArray) {
            hexString.append(Integer.toHexString(0xFF & message));
        }

        return hexString.toString();
    }
}
