package com.zybooks.pizzaparty;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public final int SLICES_PER_PIZZA = 8;

    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private Spinner mHowHungrySpinner;
    // private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumAttendEditText = findViewById(R.id.num_attend_edit_text);
        mNumPizzasTextView = findViewById(R.id.num_pizzas_text_view);
        mHowHungrySpinner = findViewById(R.id.hunger_spinner);
        // mHowHungryRadioGroup = findViewById(R.id.hungry_radio_group);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hunger_levels, R.layout.spinner_list);
        adapter.setDropDownViewResource(R.layout.spinner_list);
        mHowHungrySpinner.setAdapter(adapter);
    }

    public void calculateClick(View view) {
        // Get how many are attending the party
        int numAttend;
        try {
            String numAttendStr = mNumAttendEditText.getText().toString();
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }

        // Get hunger level selection
         int checkedId = mHowHungrySpinner.getSelectedItemPosition();
        // int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
         PizzaCalculator.HungerLevel hungerLevel = null;
        // PizzaCalculator.HungerLevel hungerLevel = PizzaCalculator.HungerLevel.RAVENOUS;
        // if (checkedId == R.id.light_radio_button) {
        if (checkedId == 0) {
            hungerLevel = PizzaCalculator.HungerLevel.LIGHT;
        }
        // else if (checkedId == R.id.medium_radio_button) {
        else if (checkedId == 1) {
            hungerLevel = PizzaCalculator.HungerLevel.MEDIUM;
        }
        else if (checkedId == 2) {
            hungerLevel = PizzaCalculator.HungerLevel.RAVENOUS;
        }

        // Get the number of pizzas needed
        PizzaCalculator calc = new PizzaCalculator(numAttend, hungerLevel);
        int totalPizzas = calc.getTotalPizzas();

        // Place totalPizzas into the string resource and display
        String totalText = getString(R.string.total_pizzas_num, totalPizzas);
        mNumPizzasTextView.setText(totalText);
    }
}