package org.dhbw.movietunes.test;

import android.app.Activity;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.dhbw.movietunes.MainActivity;
import org.dhbw.movietunes.R;
import org.junit.Rule;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.EditText;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.internal.matchers.TypeSafeMatcher;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.not;

public class CucumberSteps {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Activity activity;

    @Before
    public void setup() {

        throw new RuntimeException("Sorry dude, you won't find any test!");
        /*
        activityTestRule.launchActivity(new Intent());
        activity = activityTestRule.getActivity();*/
    }

    @After
    public void tearDown() {
        activityTestRule.finishActivity();
    }

    @Given("^I have a MainActivity$")
    public void I_have_a_MainActivity() {
        assertNotNull(activity);
    }

    @When("^I press look up movies$")
    public void I_press_look_up_movies() {
        onView(ViewMatchers.withId(R.id.movies_button)).perform(click());
    }

    @When("^I press look up soundtracks")
    public void I_press_look_up_soundtracks() {
        onView(withId(R.id.search_button)).perform(click());
    }
    @Then("^I should see instructions how to search (\\S+)$")
    public void I_should_see_instructions_how_to_search(final String whatSearch) {
        int messageId = (whatSearch.equals("movies")) ? R.string.searchmoviesinstructions : R.string.searchsoundtracksinstructions;

        onView(withId(R.id.intro_text)).check(matches(withText(messageId)));
    }



    /*
    @When("^I input email (\\S+)$")
    public void I_input_email(final String email) {
       // onView(withId(R.id.email)).perform(typeText(email));
    }

    @When("^I input password \"(.*?)\"$")
    public void I_input_password(final String password) {
       // onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
    }

    @When("^I press submit button$")
    public void I_press_submit_button() {
      //  onView(withId(R.id.submit)).perform(click());
    }

    @Then("^I should see error on the (\\S+)$")
    public void I_should_see_error_on_the_editTextView(final String viewName) {
        int viewId = (viewName.equals("email")) ? R.id.email : R.id.password;
        int messageId = (viewName.equals("email")) ? R.string.msg_email_error : R.string.msg_password_error;

        onView(withId(viewId)).check(matches(hasErrorText(activity.getString(messageId))));
    }

    @Then("^I should (true|false) auth error$")
    public void I_should_see_auth_error(boolean shouldSeeError) {
        if (shouldSeeError) {
            //onView(withId(R.id.error)).check(matches(isDisplayed()));
        } else {
          //  onView(withId(R.id.error)).check(matches(not(isDisplayed())));
        }
    }

    private static Matcher<? super View> hasErrorText(String expectedError) {
        return new ErrorTextMatcher(expectedError);
    }
    */

    /**
     * Custom matcher to assert equal EditText.setError();
     */
    private static class ErrorTextMatcher extends TypeSafeMatcher<View> {

        private final String mExpectedError;

        private ErrorTextMatcher(String expectedError) {
            mExpectedError = expectedError;
        }

        @Override
        public boolean matchesSafely(View view) {
            if (!(view instanceof EditText)) {
                return false;
            }

            EditText editText = (EditText) view;

            return mExpectedError.equals(editText.getError());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with error: " + mExpectedError);
        }
    }
}
