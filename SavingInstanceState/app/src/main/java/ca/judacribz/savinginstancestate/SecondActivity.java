package ca.judacribz.savinginstancestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private TextView name, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.tvName);
        gender = findViewById(R.id.tvGender);
    }

    @Override
    protected void onResume() {
        super.onResume();

        switch (getIntent().getAction()) {
            case "goToSecond":
                List<Person> personList;
                personList = getIntent().getParcelableArrayListExtra("person");
                Toast.makeText(this, String.valueOf(personList.size()), Toast.LENGTH_SHORT).show();

                for (Person person : personList) {
                    Log.d(TAG, "onResume: " + person.toString());
                }
                break;
        }
    }
}
