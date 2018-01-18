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
    EditText firstName, lastName, address, phoneNumber, creditCard,
            billingFirstName, billingLastName, billingAddress;
    String firstNameTxt, lastNameTxt, addressTxt, phoneNumberTxt, creditCardTxt,
    billingFirstNameTxt, billingLastNameTxt, billingAddressTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // get all edit text elements
        firstName = findViewById(R.id.edit_first_name);
        lastName = findViewById(R.id.edit_last_name);
        address = findViewById(R.id.edit_address);
        phoneNumber = findViewById(R.id.edit_phone_number);
        creditCard = findViewById(R.id.edit_credit_card);
        billingFirstName = findViewById(R.id.edit_billing_first_name);
        billingLastName = findViewById(R.id.edit_billing_last_name);
        billingAddress = findViewById(R.id.edit_billing_address);

        Button submitBtn = findViewById(R.id.submit_button);
        db = AppDatabase.getDatabase(getApplicationContext());

        // Pull user from db
        Customer user = db.customerDao().getCustomer();
        // if no billing info provided, default to personal info
        if(user.getBillingAddress() == null){
            user.setBillingAddress(user.getAddress());
            user.setBillingFirstName(user.getFirstName());
            user.setBillingLastName(user.getLastName());
        }
        // default text for each edit text element to corresponding user property
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        address.setText(user.getAddress());
        phoneNumber.setText(user.getPhone());
        creditCard.setText(user.getCreditCardNumber());
        billingFirstName.setText(user.getBillingFirstName());
        billingLastName.setText(user.getBillingLastName());
        billingAddress.setText(user.getBillingAddress());

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get text from each edit text box
                firstNameTxt = txtFromEditText(firstName);
                lastNameTxt = txtFromEditText(lastName);
                addressTxt = txtFromEditText(address);
                phoneNumberTxt = txtFromEditText(phoneNumber);
                creditCardTxt = txtFromEditText(creditCard);
                billingFirstNameTxt = txtFromEditText(billingFirstName);
                billingLastNameTxt = txtFromEditText(billingLastName);
                billingAddressTxt = txtFromEditText(billingAddress);

                //Text Validation Rule array
                Boolean[] rules = new Boolean[]{
                        TextUtils.isEmpty(firstNameTxt),
                        TextUtils.isEmpty(lastNameTxt),
                        TextUtils.isEmpty(addressTxt),
                        TextUtils.isEmpty(phoneNumberTxt),
                        TextUtils.isEmpty(creditCardTxt),
                        TextUtils.isEmpty(billingFirstNameTxt),
                        TextUtils.isEmpty(billingLastNameTxt),
                        TextUtils.isEmpty(billingAddressTxt)
                };

                // if any fields are empty
                if (Arrays.asList(rules).contains(true)) {
                    Toast.makeText(UserInfoActivity.this,
                            "Please fill out all fields before submitting", Toast.LENGTH_SHORT).show();

                } else { // update user
                    Customer c = new Customer(firstNameTxt, lastNameTxt, addressTxt, phoneNumberTxt, creditCardTxt,
                            billingFirstNameTxt, billingLastNameTxt, billingAddressTxt);
                    db.customerDao().updateCustomer(c);
                    Toast.makeText(UserInfoActivity.this,
                            "Customer updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

