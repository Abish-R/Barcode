package com.aipl.leecodemoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MeetingRoom extends AppCompatActivity {
    /**
     * Global Declarations
     * */
    EditText no_of_rooms, vister1_time, vister2_time;
    Button submit;
    int globallength=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_room);

        initializeViews();
    }

    /*
    * Initializing the views
    * */
    private void initializeViews() {
        no_of_rooms = (EditText)findViewById(R.id.no_of_rooms);
        vister1_time = (EditText)findViewById(R.id.vister1_time);
        vister2_time = (EditText)findViewById(R.id.vister2_time);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Funtion call to perform the operation
                 * */
                computeMeetingPoint();
            }
        });
    }

    private void computeMeetingPoint() {
        /*
        * initialization and input field validations
        * */
        int noOfRooms=0, vister1Time=0, vister2Time=0,forward=0,backward=0,temp=1,j=1,nowReallyCrossedNext=0;
        if(no_of_rooms.getText().toString().isEmpty())
            Toast.makeText(this,"Fill the number of Rooms in the Museum",Toast.LENGTH_SHORT).show();
        else if(vister1_time.getText().toString().isEmpty())
            Toast.makeText(this,"Fill the Vister 1 Time spend in Each Room",Toast.LENGTH_SHORT).show();
        else if(vister2_time.getText().toString().isEmpty())
            Toast.makeText(this,"Fill the Vister 2 Time spend in Each Room",Toast.LENGTH_SHORT).show();
        else {
            /**
            * Getting all the values from input fields and converting into integer
            * */
            noOfRooms = Integer.parseInt(no_of_rooms.getText().toString());
            vister1Time = Integer.parseInt(vister1_time.getText().toString());
            vister2Time = Integer.parseInt(vister2_time.getText().toString());
            backward=noOfRooms;
            forward=1;
        }

        /**
        * To check whether all are positive numbers before doing the calculations
        * */
        if(noOfRooms > 0 && vister1Time > 0 && vister2Time >0) {
            /**
             * if the number is even, both visiters time were equal they won't meet inside the room. We don't want to go for check
             * */
            if (noOfRooms % 2 == 0 && vister1Time == vister2Time) {
                Toast.makeText(this, "They won't meet inside a room", Toast.LENGTH_SHORT).show();
            } else {
                if (vister1Time > vister2Time) {
                    /**
                     * Keeping global variable to add the value from its last value
                     * */
                    globallength += vister1Time;
                    /**
                     * Actually forward++ and backward-- will perform first and next we are checking the condition
                     * By that time they were actually crossed the rooms. So, we are adding 1 to produce the correct result
                     * */
                    nowReallyCrossedNext = 1;
                } else if (vister1Time < vister2Time) {
                    /**
                     * Keeping global variable to add the value from its last value
                     * */
                    globallength += vister2Time;
                    /**
                     * Actually forward++ and backward-- will perform first and next we are checking the condition
                     * By that time they were actually crossed the rooms. So, we are subtracting 1 to produce the correct result
                     * */
                    nowReallyCrossedNext = -1;
                } else
                    globallength += vister1Time;

                /**
                 * Loop will executes number of Room times if in between the result found it stops executing
                 * */
                for (int i = 1; i <= noOfRooms; i++) {
                    /**
                     * Loop will executes globallength times and stops if result found in between
                     * globallength and j keeps adding the number from it last finishes the loop
                     * This I am doing to keep track of the seconds vistors spending in the room
                     * */
                    for (j = temp; j <= globallength; j++) {
                        if (j % vister1Time == 0)
                            forward++;
                        if (j % vister2Time == 0)
                            backward--;

                        if (forward == backward) {
                            /**
                             * To stop the loop from executing
                             * */
                            i = noOfRooms;
                            j = globallength;

                            /**
                             * Mobile screen remains there. So, reseting the values for next time processing.
                             * */
                            temp = 1;
                            globallength = 1;

                            /**
                             * Printing the result
                             * */
                            forward += nowReallyCrossedNext;
                            System.out.print("Reached at Pos " + forward);
                            Toast.makeText(this, "yes found : " + forward, Toast.LENGTH_SHORT).show();
                            break;
                        } else if (forward > backward) {
                            /**
                             * To stop the loop from executing
                             * */
                            i = noOfRooms;
                            j = globallength;

                            /**
                             * Mobile screen remains there. So, reseting the values for next time processing
                             * */
                            temp = 1;
                            globallength = 1;

                            /**
                             * Printing the result
                             * */
                            forward += nowReallyCrossedNext;
                            System.out.print("Not Reached at Pos " + forward);
                            Toast.makeText(this, "They won't meet inside the room : " + forward, Toast.LENGTH_SHORT).show();
                        }

                    }
                    /**
                     * To continue the j's value till i reaches end or till stop traversing with the seconds
                     * */
                    temp += globallength;
                    globallength += globallength;
                }
            }
        }else
            Toast.makeText(this, "Input Positive Numbers", Toast.LENGTH_SHORT).show();
    }
}
