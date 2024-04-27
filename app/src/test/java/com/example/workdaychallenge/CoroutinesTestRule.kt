package com.example.workdaychallenge

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


@ExperimentalCoroutinesApi
class CoroutinesTestRule(
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestRule {
    private val testScope = TestCoroutineScope(testDispatcher)

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    // Set the main dispatcher to the test dispatcher
                    kotlinx.coroutines.Dispatchers.setMain(testDispatcher)
                    testScope.runBlockingTest {
                        base.evaluate()
                    }
                } finally {
                    // Reset main dispatcher to the original one
                    kotlinx.coroutines.Dispatchers.resetMain()
                    // Cleanup test scope
                    testScope.cleanupTestCoroutines()
                }
            }
        }
    }
}