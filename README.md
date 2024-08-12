# Please Water Me

605.607 class project application

## Required software

This project was developed with the following versions of software:

- Java 11
- Maven 3.8.8
- Spring Tool Suite 4

## Required setup

### Database

1. Install MySQL on your computer
1. Create a MySQL database for this application
1. Update the following in src/main/resources/application.properties: spring.datasource.url, spring.datasource.username, spring.datasource.password

### Email

This application sends plant watering reminders from an application email account to the recipient's email account. The application email account is a Gmail account set up specifically for sending automated emails.

1. Create a Gmail account for the application. It is not advised to use your personal email.
1. Add an app password for the account.
1. Update the following in src/main/resources/application.properties: notification.to.email (the address you'd like to receive notifications to), notification.from.email (the application email account you created), notification.from.password (the app password)
1. In Constants.java, set FROM_EMAIL to be this Gmail account and PASSWORD to the app password you created.
1. In Constants.java, set TO_EMAIL to the address you would like to receive plant watering reminders to.

## To build and run

`mvn clean spring-boot:run`

Access the application at http://localhost:8080/

## References

Plant care information was obtained from www.thesill.com
