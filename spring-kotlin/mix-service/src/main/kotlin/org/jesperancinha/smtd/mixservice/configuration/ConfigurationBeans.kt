package org.jesperancinha.smtd.mixservice.configuration

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

sealed class Notification {
    open class Email(val emailAddress: String) : Notification()

    open class SMS(val phoneNumber: String) : Notification()
}

@Component
object MySingletonComponent {
    fun sayHello(): Boolean {
        println("Hello from MySingletonComponent!")
        return true
    }
}

@Component
class EmailNotification : Notification.Email("example@example.com")

@Component
class SMSNotification : Notification.SMS("+123456789")

@Service
class NotificationService(
    val emailNotification: Notification.Email,
    val smsNotification: Notification.SMS
) {

    fun notifyByEmail() {
        println("Sending email to: ${emailNotification.emailAddress}")
    }

    fun notifyBySMS() {
        println("Sending SMS to: ${smsNotification.phoneNumber}")
    }
}