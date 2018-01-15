package com.example.evy.ocs;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.onView;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)

public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception
    {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.evy.ocs", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class);



    @Test
    public void testUsernameField()
    {
        //click username field "NoneUser"
        //check if the username turns "0000"
        String nullstr = "";

        onView(withId(R.id.editText_email)).perform(typeText("NoneUser"));
        onView(withId(R.id.editText_password)).perform(typeText("0000"));

        onView(withId(R.id.Login)).perform(click());
        onView(withId(R.id.Login)).perform(click());

        onView(withId(R.id.editText_email)).check(matches(withText(nullstr)));
        onView(withId(R.id.editText_password)).check(matches(withText(nullstr)));
    }

}
