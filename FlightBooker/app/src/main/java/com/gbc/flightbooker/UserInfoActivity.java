package com.gbc.flightbooker;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Customer;
import static com.gbc.flightbooker.utilities.Helper.txtFromEditText;

import java.util.Arrays;



public class UserInfoActivity extends Activity {
    private static final String TAG = "User Info";
    AppDatabase db;
    EditText firstName, lastName, address, phoneNumber, creditCard;
    String firstNameTxt, lastNameTxt, addressTxt, phoneNumberTxt, creditCardTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        firstName = findViewById(R.id.edit_first_name);
        lastName = findViewById(R.id.edit_last_name);
        address = findViewById(R.id.edit_address);
        phoneNumber = findViewById(R.id.edit_phone_number);
        creditCard = findViewById(R.id.edit_credit_card);
        Button submitBtn = findViewById(R.id.submit_button);
        db = AppDatabase.getDatabase(getApplicationContext());

        Customer user = db.customerDao().getCustomer();

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        address.setText(user.getAddress());
        phoneNumber.setText(user.getPhone());
        creditCard.setText(user.getCreditCardNumber());

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Submit Info clicked");
                firstNameTxt = txtFromEditText(firstName);
                lastNameTxt = txtFromEditText(lastName);
                addressTxt = txtFromEditText(address);
                phoneNumberTxt = txtFromEditText(phoneNumber);
                creditCardTxt = txtFromEditText(creditCard);

                //Text Validation Rule array
                Boolean[] rules = new Boolean[]{
                        TextUtils.isEmpty(firstNameTxt),
                        TextUtils.isEmpty(lastNameTxt),
                        TextUtils.isEmpty(addressTxt),
                        TextUtils.isEmpty(phoneNumberTxt),
                        TextUtils.isEmpty(creditCardTxt)
                };

                if (Arrays.asList(rules).contains(true)) {
                    // if any fields are empty
                    Toast.makeText(UserInfoActivity.this,
                            "Please fill out all fields before submitting", Toast.LENGTH_SHORT).show();

                } else {
                    Customer c = new Customer(firstNameTxt, lastNameTxt, addressTxt, phoneNumberTxt, creditCardTxt);
                    db.customerDao().updateCustomer(c);
                }
            }
        });
    }
}

