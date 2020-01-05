package me.lodthe.bdaytracker.telegram

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramException
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.request.BaseRequest
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.BaseResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class KTelegramBot(private val TOKEN: String) {
    private val bot = TelegramBot(TOKEN)

    fun runUpdatesListener(): Flow<Update> = callbackFlow {
        bot.setUpdatesListener { updates ->
            updates.forEach { offer(it) }
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }

        awaitClose()
    }

    suspend fun <T : BaseRequest<T, R>, R : BaseResponse> execute(request: T): R {
        val stackTrace = IOException().apply {
            stackTrace = stackTrace.copyOfRange(1, stackTrace.size)
        }

        return suspendCoroutine { continuation ->
            bot.execute(request, object : Callback<T, R> {
                override fun onFailure(request: T, e: IOException?) {
                    stackTrace.initCause(e)
                    continuation.resumeWithException(stackTrace)
                }

                override fun onResponse(request: T, response: R) {
                    if (response.isOk) {
                        continuation.resume(response)
                    } else {
                        val message = "${request.method} failed with error_code " +
                                "${response.errorCode()} ${response.description()}"
                        stackTrace.initCause(TelegramException(message, response))
                        continuation.resumeWithException(stackTrace)
                    }
                }
            })
        }
    }
}