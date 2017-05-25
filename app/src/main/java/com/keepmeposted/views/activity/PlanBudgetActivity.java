package com.keepmeposted.views.activity;;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.keepmeposted.R;
import com.keepmeposted.base.BaseActivity;
import com.keepmeposted.utility.adapter.AndroidListAdapter;
import com.keepmeposted.utility.adapter.Constants;
import com.keepmeposted.model.Budget;
import com.keepmeposted.model.Expense;
import com.keepmeposted.model.Income;
import com.keepmeposted.model.ShoppingListItem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Shazna on 10/19/2016.
 */

public class PlanBudgetActivity extends AppCompatActivity {


    public String categoryName;
    public String categoryId;

    View v;
    FloatingActionButton addFabPI;
    Button deleteAllbtnPI;
    ListView list;
    DatabaseReference ref;
    Button saveBtn;
    Dialog dialog;
    private DatabaseReference mFirebaseRef;

    //private FirebaseRecyclerAdapter<Income,IncomeHolder> piFireAdapter;
    private LinearLayoutManager incomeLayoutManager;
    public ArrayList<String> arrayList = new ArrayList<>();
    TextView addamount, mytext;
    TextView editTextPi, daterange, viewbalance;
    Button addincome, addexpense, expensetot, incometot, btnbalance;
    TextView name, name1, name2, expensename, expensename1, expensename2, lbltotincome, lbltotexpense;
    EditText amount, amount1, amount2, expenseamount, expenseamount1, expenseamount2;
    String id = ".";
    String[] Items = {"shaz", "Raz", "Cat"};
    String toshow = "-" , mUID;
    int i = 0;
    Income loggedUser;
    ShoppingListItem user;
    FloatingActionButton calenderbtn;
    CardView incomecard, expensecard, balancecard;
    Double totalincome = 0.0;
    Double totalexpense = 0.0;
    Double totexp = 0.0;
    Double totinc = 0.0;
    Double balance = 0.0;
    Income income1 = null;
    Expense expense1 = null;
    public int addpresscount = 0;
    Expense myexp = null;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems = new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgetplan);

        //getLoggedUser
       // getLogged();

        //saveBtnWork
        saveButton();

        //Go Back
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Plan Budget");

        //adding expense details
        addexpense = (Button) findViewById(R.id.addexpense);
        addExpenseDetails();

        // giving a date range
        daterange = (TextView) findViewById(R.id.daterange);
        getSelectedDateRange();

        //show the balance
        //viewBalance();

        //showing list view
        //ListActivityShow();

        calenderbtn = (FloatingActionButton) findViewById(R.id.btnselectDate);
        calenderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCalender();

            }
        });

        /*Firebase.setAndroidContext(this);*/

        //ref =  FirebaseDatabase.getInstance().getReference();

        /*mFirebaseRef = new Firebase(Constants.FIREBASE_URL);*/

        incomeLayoutManager = new LinearLayoutManager(this);
        incomeLayoutManager.setStackFromEnd(true);

        addincome = (Button) findViewById(R.id.addicon);
        name = (TextView) findViewById(R.id.incomenameView);
        amount = (EditText) findViewById(R.id.incomeamountView);
        name1 = (TextView) findViewById(R.id.incomenameView1);
        amount1 = (EditText) findViewById(R.id.incomeamountView1);
        name2 = (TextView) findViewById(R.id.incomenameView2);
        amount2 = (EditText) findViewById(R.id.incomeamountView2);
        incomecard = (CardView) findViewById(R.id.incomecard);
        lbltotincome = (TextView) findViewById(R.id.totalIncome);
        //incometot = (Button) findViewById(R.id.incTot);

        expensecard = (CardView) findViewById(R.id.expensecard);
        expensename = (TextView) findViewById(R.id.expensenameView);
        expenseamount = (EditText) findViewById(R.id.expenseamountView);
        expensename1 = (TextView) findViewById(R.id.expensenameView1);
        expenseamount1 = (EditText) findViewById(R.id.expenseamountView1);
        expensename2 = (TextView) findViewById(R.id.expensenameView2);
        expenseamount2 = (EditText) findViewById(R.id.expenseamountView2);
        lbltotexpense = (TextView) findViewById(R.id.totalexpense);
        //expensetot = (Button) findViewById(R.id.expTot);

        viewbalance = (TextView) findViewById(R.id.balanceamount);
        btnbalance = (Button) findViewById(R.id.btnbalance);
        balancecard = (CardView) findViewById(R.id.balancecard);


        btnbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                balancecard.setVisibility(View.VISIBLE);
                // balance = totinc - totexp;

                if(lbltotincome.getText().toString().equals("-") || lbltotexpense.getText().toString().equals("-") )
                    Snackbar.make(v, "Please add income and expense for checking balance", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                else
                {
                    double totinc = income1.getTotalincome();
                    double totexp = expense1.getTotalexpense();

                    balance = totinc - totexp;
                    if (balance >= 0)
                        viewbalance.setTextColor(getResources().getColor(R.color.darkgreen));
                    else
                        viewbalance.setTextColor(getResources().getColor(R.color.purered));
                    viewbalance.setText("      Rs. " + balance + "");
                }
            }
        });


        //deleteAllbtnPI = (Button) findViewById(R.id.deleteallbtnPI);

        //adding into the listview
        //list = (ListView)findViewById(R.id.list);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);


        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"qqqqq",Toast.LENGTH_LONG).show();
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.incom_input_dialog);
                dialog.setTitle("Income Details");
                saveBtn = (Button) dialog.findViewById(R.id.saveIncomeBtn);
                dialog.show();


                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        addamount = (TextView) dialog.findViewById(R.id.amountIncomeadd);
                        editTextPi = (TextView) dialog.findViewById(R.id.nameIncomeadd);

                        if( addamount.getText().toString().equals("") || editTextPi.getText().toString().equals(""))
                            Snackbar.make(v, "Please add income type and amount", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        else {
                            try {
                                Double incomeamount = Double.parseDouble(addamount.getText().toString());

                                String incometype = editTextPi.getText().toString();
                                id = editTextPi.getText().toString() + "ID";
                                final Income income = new Income(incometype, incomeamount, id);

                                //DatabaseReference dbRef = ref.child("Income/"+income).push().setValue(income);
                                /*Firebase ref = mFirebaseRef.child("Income/" + id).push();
                                ref.setValue(income);*/
                                Toast.makeText(v.getContext(), "Added successfully ", Toast.LENGTH_LONG).show();


                                //adding to a list view
                        /*
                        listItems.add(incometype +" : " + incomeamount);
                        adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, listItems);
                        //list.setAdapter(adapter);
                        if(list == nu
                        ll)
                        {
                            list = (ListView)findViewById(R.id.listDemo);
                            list.setAdapter(adapter);
                            listItems.add(incometype +" : " + incomeamount);
                            adapter.notifyDataSetChanged();

                        }
                        else
                        {
                            list = (ListView)findViewById(R.id.listDemo);
                            list.setAdapter(adapter);
                            listItems.add(incometype +" : " + incomeamount);
                            adapter.notifyDataSetChanged();
                        }
                        */


                                //viewing added incomes
//                        mFirebaseRef.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot snapshot) {
//                                // DataSnapshot usersnapshot = snapshot.child("users").child(mUID);
//                                //if(id.equals(editTextPi.getText().toString() + "ID"))
//                                DataSnapshot usersnapshot = snapshot.child("Income/" + id).child("KUfZa2WNjXMDd_pBvy9");
//                                loggedUser = usersnapshot.getValue(Income.class);
//                                String s1 = loggedUser.getIncomeName();
//                                String s2 = loggedUser.getAmount() + "";
//                                name.setText(s2 + "</br>");
//                                amount.setText(s1);
//
//                            }
//
//                            @Override
//                            public void onCancelled(FirebaseError firebaseError) {
//                                Log.e("error", "error because " + firebaseError.getMessage());
//                            }
//
//
//                        });


                                incomecard.setVisibility(View.VISIBLE);

                                if (name.getText().toString().equals(" ")) {
                                    name.setText(incometype);
                                    amount.setText(incomeamount + "");
                                } else if (name1.getText().toString().equals(" ")) {
                                    name1.setText(incometype);
                                    amount1.setText(incomeamount + "");
                                } else if (name2.getText().toString().equals(" ")) {
                                    name2.setText(incometype);
                                    amount2.setText(incomeamount + "");
                                }

                                totalincome = incomeamount + totalincome;
                                lbltotincome.setText("" + totalincome);
                                income1 = new Income(totalincome);
                                income1.setTotalincome(totalincome);

//                                incometot.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                        double first = 0.0, second = 0.0, third = 0.0;
//
//                                        if(amount.getText().toString() != (""))
//                                            first = Double.parseDouble(amount.getText().toString());
//                                        if(amount1.getText().toString() != (""))
//                                            second = Double.parseDouble(amount1.getText().toString());
//                                        if(amount2.getText().toString() != (""))
//                                            third = Double.parseDouble(amount2.getText().toString());
//
//                                        totexp = first + second + third;
//                                        income1.setTotalincome(totexp);
//                                        lbltotincome.setText("" + totexp);
//
//                                    }
//                                });

                            } catch (NumberFormatException ex) {
                                Snackbar.make(v, "Please add a valid amount", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                            }
                        }


                    }
                });


            }

        });
    }

    public void goToCalender() {
        Intent intent = new Intent(this, BudgetCalenderActivity.class);
        startActivity(intent);

    }

    private void getSelectedDateRange() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String nameq = extras.getString("daterange");
            daterange.setText(nameq);
        }
    }

    private void addExpenseDetails() {
        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"qqqqq",Toast.LENGTH_LONG).show();
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.incom_input_dialog);
                dialog.setTitle("Expense Details");
                saveBtn = (Button) dialog.findViewById(R.id.saveIncomeBtn);
                dialog.show();




                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        addamount = (TextView) dialog.findViewById(R.id.amountIncomeadd);
                        editTextPi = (TextView) dialog.findViewById(R.id.nameIncomeadd);

                        if(addamount.getText().toString().equals("") || editTextPi.getText().toString().equals(""))
                            Snackbar.make(v, "Please add expense type and amount", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        else {
                            final Double expenseamountt = Double.parseDouble(addamount.getText().toString());
                            String expensetype = editTextPi.getText().toString();
                            id = editTextPi.getText().toString() + "ID";
                            final Expense expense = new Expense(expensetype, expenseamountt, id);
                            myexp = new Expense();
                            String expcollection = myexp.setExpenseCollection(expensetype);

                            arrayList.add(expensetype);
                            adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, arrayList);
                            // ListView androidListView = (ListView) findViewById(R.id.custom_listview_example);
                            // androidListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


//                        ListActivityShow(addpresscount);
//                        addpresscount++;
//                        adapter.notifyDataSetChanged();
//                        ListActivityShow(addpresscount);

                            // String collecname = getOneString(expensetype);
//                        arrayList.add(collecname);

                            //DatabaseReference dbRef = ref.child("Income/"+income).push().setValue(income);
                            /*Firebase ref = mFirebaseRef.child("Expense/" + id).push();
                            ref.setValue(expense);*/
                            Toast.makeText(v.getContext(), "Added successfully ", Toast.LENGTH_LONG).show();

                            expensecard.setVisibility(View.VISIBLE);

                            //arrayList.add(expensetype);


                            if (expensename.getText().toString().equals(" ")) {
                                expensename.setText(expensetype);
                                expenseamount.setText(expenseamountt + "");
                            } else if (expensename1.getText().toString().equals(" ")) {
                                expensename1.setText(expensetype);
                                expenseamount1.setText(expenseamountt + "");
                            } else if (expensename2.getText().toString().equals(" ")) {
                                expensename2.setText(expensetype);
                                expenseamount2.setText(expenseamountt + "");
                            }
                            totalexpense = expenseamountt + totalexpense;
                            lbltotexpense.setText("" + totalexpense);

                            expense1 = new Expense(totalexpense);
                            expense1.setTotalexpense(totalexpense);

//                            expensetot.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    //lbltotexpense.setText(""+totalexpense);
//                                    double i = Double.parseDouble(expenseamount.getText().toString());
//                                    double i2 = Double.parseDouble(expenseamount1.getText().toString());
//                                    double i3 = Double.parseDouble(expenseamount2.getText().toString());
//                                    totexp = i + i2 + i3;
//
//                                    expense1.setTotalexpense(totexp);
//                                    lbltotexpense.setText("" + totexp);
//
//                                }
//                            });
                        }


                    }
                });


            }

        });
    }

    private void viewBalance() {
        btnbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                balancecard.setVisibility(View.VISIBLE);
                // balance = totinc - totexp;
                Income income = new Income();
                Expense expense = new Expense();
                double totinc = income.getTotalincome();
                double totexp = expense.getTotalexpense();

                balance = totinc - totexp;
                if (balance >= 0)
                    viewbalance.setHighlightColor(getResources().getColor(R.color.darkgreen));
                else
                    viewbalance.setHighlightColor(getResources().getColor(R.color.purered));
                viewbalance.setText(balance + "");
            }
        });

    }

    public void saveBudgetPlan() {

        name.getText().toString();
        amount = (EditText) findViewById(R.id.incomeamountView);
        name1 = (TextView) findViewById(R.id.incomenameView1);
        amount1 = (EditText) findViewById(R.id.incomeamountView1);
        name2 = (TextView) findViewById(R.id.incomenameView2);
        amount2 = (EditText) findViewById(R.id.incomeamountView2);
        incomecard = (CardView) findViewById(R.id.incomecard);
        lbltotincome = (TextView) findViewById(R.id.totalIncome);
        //incometot = (Button) findViewById(R.id.incTot);

        expensecard = (CardView) findViewById(R.id.expensecard);
        expensename = (TextView) findViewById(R.id.expensenameView);
        expenseamount = (EditText) findViewById(R.id.expenseamountView);
        expensename1 = (TextView) findViewById(R.id.expensenameView1);
        expenseamount1 = (EditText) findViewById(R.id.expenseamountView1);
        expensename2 = (TextView) findViewById(R.id.expensenameView2);
        expenseamount2 = (EditText) findViewById(R.id.expenseamountView2);
        lbltotexpense = (TextView) findViewById(R.id.totalexpense);
        //expensetot = (Button) findViewById(R.id.expTot);


        Budget budget = new Budget();
    }

    public void ListActivityShow(int presscount) {
        String androidListViewStrings[] = {""};

        Integer image_id[] = {R.drawable.calender, R.drawable.calender, R.drawable.calender,
                R.drawable.calender, R.drawable.calender, R.drawable.calender,
                R.drawable.calender, R.drawable.calender, R.drawable.calender,};

       // arrayList.add("Shazna");
            //setContentView(R.layout.activity_budgetplan);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);


            AndroidListAdapter androidListAdapter = new AndroidListAdapter(this, image_id, adapter, androidListViewStrings, arrayList);
           // ListView androidListView = (ListView) findViewById(R.id.custom_listview_example);
           // androidListAdapter.myaddview(presscount);
           // androidListView.setAdapter(androidListAdapter);




    }


    public void viewDetailsInLIst(double expamount, String expdetails)
    {
        String androidListViewStrings[] = {"expdetails", "Android Custom ListView Example", "Custom ListView Example",
                "Android List Adapter", "Custom Adapter ListView", "ListView Tutorial",
                "ListView with Image and Text", "Custom ListView Text and Image", "ListView Custom Tutorial"};

        //arrayList.add(expdetails);
    }

//    public void getLogged(){
//
//
//        mFirebaseRef.addAuthStateListener(new Firebase.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(AuthData authData) {
//                if (authData != null) {
//                    mUID = authData.getUid();
//
//                } else {
//
//                }
//            }
//        });
//
//
//    }

    public void saveButton()
    {
        Button Savebtn = (Button)findViewById(R.id.savePlanBtn);
        final TextView dateRange = (TextView)findViewById(R.id.daterange);
        final TextView balanceText = (TextView)findViewById(R.id.balanceamount);
        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(balanceText.getText().toString().equals("-") || dateRange.getText().toString().equals("Date Range: ") )
                    Snackbar.make(v, "Please pick a date range ,add income, add expense and check balance", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                else
                    Snackbar.make(v, "Your Budget Plan is Saved Successfully!!!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

   }

