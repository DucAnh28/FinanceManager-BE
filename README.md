<!-- ABOUT THE PROJECT -->

## About The Project

This project help user manage their daily expenditure. User can create new wallet for personal purpose. User can total
up expenditure daily, monthly or within a certain period of time.

<!-- GETTING STARTED -->

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Requirement

- Java 8 or higher
- gradle (development, optional)

### Configuration

See all available configuration in ```application.properties```. Config properties can be set directly in
application.properties, or application.properties file in the same folder with your war, or via env. You maybe need to
use your own .env file

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/DucAnh28/FinancialManagement-BE
   ```

### Production

To run app in "production", you need to tweak some config properties in ```application.properties```

1. **Setup database**
    ```
    JDBC_DATABASE_URL={Your local database url}
    JDBC_DATABASE_USERNAME={Your database username}
    JDBC_DATABASE_PASSWORD={Your database password}
    JDBC_DATABASE_DRIVER=com.mysql.cj.jdbc.Driver
    ```
2. **Setup send email**
    ```properties
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username={youremail}
    spring.mail.password={yourapppassword}
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    ```

### Description

We use validate in our project so these are some note you need to know

1. App User (account)
   ```description
    regex in password: ([A-Z]{1})([a-z]{4,})([0-9]{1,})
    email has to be email form
    ```
