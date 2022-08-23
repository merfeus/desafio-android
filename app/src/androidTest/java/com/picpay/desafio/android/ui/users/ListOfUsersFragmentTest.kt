package com.picpay.desafio.android.ui.users

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.picpay.desafio.android.R
import com.picpay.desafio.android.extensions.replaceFragment
import com.picpay.desafio.android.ui.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
class ListOfUsersFragmentTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
            .onActivity { it.replaceFragment(ListOfUsersFragment()) }
    }

    @Test
    fun assert_that_the_progress_bar_will_be_loading() {
        onView(withId(R.id.user_list_progress_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun assert_Title_fragment() {
        onView(withId(R.id.title_fragment)).check(matches(withText("Contatos")))
    }
}