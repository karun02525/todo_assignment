package com.sps.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sps.database.data.TodoDatabase
import com.sps.database.data.dao.TodoDao
import com.sps.database.data.entity.TodoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class TodoDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TodoDatabase
    private lateinit var userDao: TodoDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        ).build()
        userDao = database.dao
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetItem() = runBlockingTest {
        val item = TodoEntity( "hi dear hello")
        userDao.insertTodo(item)

        val retrievedUser = userDao.getTodos("dear")
        Assert.assertEquals(item, retrievedUser)
    }

    @Test
    fun insert() = runBlockingTest {
        val user1 = TodoEntity( "this nice book")
        val user2 = TodoEntity( "java is")
        userDao.insertTodo(user1)
        userDao.insertTodo(user2)

        val retrievedUser1 = userDao.getTodos("book")
        val retrievedUser2 = userDao.getTodos("is")

        Assert.assertNull(retrievedUser1)
        Assert.assertNull(retrievedUser2)
    }
}
