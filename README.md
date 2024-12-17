# sdd-catalog-backend

## Project: SDD-Catalog

Currently, video game enthusiasts, particularly players of [**Need for Speed Unbound**](https://www.ea.com/es-es/games/need-for-speed/need-for-speed-unbound), lack a centralized platform where they can create, save, and share personalized configurations for their vehicles. This includes specific features such as the engine, suspension, wheels, and other modifications that are part of their game strategies.

Players often rely on unorganized notes or screenshots, making it challenging to manage and share ideas with other users.

---

## Proposed Solution

We propose developing a web application called [**SDD-Catalog**](https://sdd-catalog.netlify.app/home), which will allow users to:

### 1. Manage Vehicle Configurations

Users will be able to create, save, and update configurations for their vehicles, defining technical aspects like the engine, suspension, turbo, and more. These configurations will be accessible for consultation at any time.

### 2. Explore Other Users' Configurations

A gallery will be available where users can browse and explore configurations shared by other players, fostering collaborative learning and inspiration.

### 3. Authentication and Security

Users will have personal accounts secured with robust authentication to ensure only they can manage their configurations.

### 4. User Interaction

The platform will include a feature to contact the administrator and eventually communicate with other users, fostering a collaborative community.

---

## Main Objective

Provide a practical, accessible, and secure tool that facilitates the creation, management, and collaboration among [**Need for Speed Unbound**](https://www.ea.com/es-es/games/need-for-speed/need-for-speed-unbound) players, enhancing the gaming experience and fostering an active community.

---

## 🗃️ **Table of Contents**

- [📦 Prerequisites](#-prerequisites)
- [🚀 Installation](#-installation)
- [⚙️ Configuration](#-configuration)
- [📚 API Usage](#-api-usage)
- [🗂 Project Structure](#-project-structure)
- [📡 Main Endpoints](#-main-endpoints)
- [🛠️ Technologies Used](#-technologies-used)
- [👎 License](#-license)
- [📞 Contact](#-contact)

---

## 📦 **Prerequisites**

Make sure you have the following installed on your machine before you start:

- **Java 17 or higher**: [Download here](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8+**: [Download here](https://maven.apache.org/download.cgi)
- **MySQL** (or your database of choice)
- **Git**: [Download here](https://git-scm.com/)
- **IntelliJ IDEA**: [Download here](https://www.jetbrains.com/idea/download) (or your IDE of choice)

---

## 🚀 **Installation**

Follow these steps to set up and run the project on your local machine.

1. **Clone the repository**:

   ```
   git clone https://github.com/EduardoMaravilla/sdd-catalog-backend.git
   ```

2. **Create environment variables on your PC**:

   You will need to set the following environment variables for the application to work:

   - `privateKeyDevBase64Part1`
   - `privateKeyDevBase64Part2`
   - `publicKeyDevBase64`
   - `MailJetEmailUsername`
   - `MailJetContactEmail`
   - `MailJetPublicKey`
   - `MailJetPrivateKey`
   - `ReCaptchaSiteKey`
   - `ReCaptchaSecretKey`

3. **Generate RSA keys using OpenSSL**:

    ```
    openssl genpkey -algorithm RSA -out private_key_dev.pem -pkeyopt rsa_keygen_bits:2048
    openssl rsa -pubout -in private_key_dev.pem -out public_key_dev.pem
    ```

4. **Convert the keys to Base64 format**:
    ```
    openssl base64 -in public_key_dev.pem -out public_key_dev_base64.txt
    openssl base64 -in private_key_dev.pem -out private_key_dev_base64.txt
    ```

5. **Save the keys as environment variables**:
   - Save the Base64-encoded `public_key_dev_base64.txt` content as the environment variable `publicKeyDevBase64`.
   - Split the `private_key_dev_base64.txt` file in half and save the two parts as two separate environment variables:
      - `privateKeyDevBase64Part1` (First half of the `private_key_dev_base64.txt`)
      - `privateKeyDevBase64Part2` (Second half of the `private_key_dev_base64.txt`)

6. **Create account [MailJet](https://www.mailjet.com)**:
   - `MailJetEmailUsername` The email address you used to create the MailJet account.
   - `MailJetContactEmail` A different email address that will be used for admin contacts.
   - `MailJetPublicKey` The public API key provided by MailJet.
   - `MailJetPrivateKey` The private API key provided by MailJet.

7. **Create account [HCaptcha](https://www.hcaptcha.com)**:
   - `ReCaptchaSiteKey` The site key provided by HCaptcha.
   - `ReCaptchaSecretKey` The secret key provided by HCaptcha.

   After completing these steps, your environment variables should be set and ready for use in your project.

8. **Install and configure MySQL**:
   - Install [MySQL](https://www.mysql.com/)
   - Create a database and use [this script](https://github.com/EduardoMaravilla/sdd-database-design/blob/master/sdd-catalog.sql) to create the necessary tables.
   - Read [Documentation](https://github.com/EduardoMaravilla/sdd-database-design/tree/master) and fill the database with information

9. **Run the application**:
   - The API will be available at http://localhost:8080/api/v1.

---

## ⚙️ **Configuration**

You need to configure the following properties for the database in `src/main/resources/application-devMySQL.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sdd_catalogo_project
spring.datasource.username=root
spring.datasource.password=root
```
> Add some data to the tables to test the backend.

 - The backend is ready for use.
   
---

## 📚 **API Usage**

To use the API, follow these steps:

1. **View the official API documentation**: [SDD-CATALOG-DOC](https://splendid-consideration-production.up.railway.app/api/v1/doc/swagger-ui/index.html)
2. **Create an account**: [SDD-CATALOG-APP](https://sdd-catalog.netlify.app/)
3. **Verify your email**.
4. **Login**: Use the `/auth/authenticate` endpoint in the [SDD-CATALOG-DOC](https://splendid-consideration-production.up.railway.app/api/v1/doc/swagger-ui/index.html).
5. **Copy the token** you receive after logging in.
6. **Authorize**: Click on the "Authorize" button at the top of the API documentation page and paste the token.
7. **Test endpoints**: You can try all `GET` methods. Other methods require `ADMIN` permissions.

---

## 🗂 **Project Structure**

This section provides an overview of the project's folder structure, key files, and their purposes for a Java Spring Boot application.

### Root Directory
- `pom.xml`: Project Object Model file containing project dependencies and configurations.
- `README.md`: Overview and instructions for the project.
- `.gitignore`: Specifies files and directories to be ignored by Git.
- `application.properties`: General configuration properties for the Spring Boot application.

### Folders

#### `src/main/java/org/eduardomaravill/sdd_catalogo`
- `SddCatalogoApplication.java`: The main class that starts the Spring Boot application.
- `configs/`: Contains configuration classes.
   - `cache/CacheConfiguration.java`: Cache configuration.
   - `docs/SwaggerConfig.java`: Configuration for Swagger API documentation.
   - `exception/GlobalExceptionHandler.java`: Handles global exceptions.
   - `mailjet/MailJetConfig.java`: Configuration for the MailJet API.
   - `mapper/ModelMapperConfig.java`: Configuration for the Model Mapper.
   - `security/`: Contains security configurations for Spring Security.
      - `authorization/CustomAuthorizationManager.java`: Custom authorization manager configuration.
      - `filter/JwtSAuthorizationFilter.java`: Filter for JWT authentication.
      - `handler/`: Custom exception handlers.
         - `CustomAccessDeniedHandler.java`: Handles access denied exceptions in Spring Security.
         - `CustomAuthenticationEntryPoint.java`: Handles authentication entry point exceptions.
      - `private_keys/PrivateKeysInitializer.java`: Generates private key files for JWT encryption.
      - `HttpSecurityConfig.java`: Configures Spring Security HTTP settings.
      - `SecurityBeansInjector.java`: Injects security-related beans.

- `controller/`: Contains REST controller classes.
   - `auth/`: Handles HTTP requests related to user authentication.
   - `cars_controllers/`: Handles HTTP requests related to car information.
   - `user_car_controllers/`: Handles HTTP requests related to user car information.

- `dtos/`: Contains Data Transfer Objects for frontend-backend communication.
- `exceptions/`: Contains custom exceptions for the project.
- `models/`: Contains entity classes representing database tables.
- `repositories/`: Contains repository interfaces for data access operations.
- `services/`: Contains service classes with business logic.
- `utils/`: Contains utility classes for the application.

#### `src/main/resources`
- `application.properties`: Configuration properties for different environments (dev, test, prod).
- `static/`: Contains static assets like HTML, CSS, JavaScript files.
- `templates/`: Contains Thymeleaf templates or other view technologies.

#### `src/test/java/org/eduardomaravill/sdd_catalogo`
- Contains all test cases for the application.

---

## 📡 **Main Endpoints**

| Endpoint                                             | Description                                  |
|------------------------------------------------------|----------------------------------------------|
| `/auth/authenticate`                                 | Authenticates a user and returns a token.    |
| `/auth/logout`                                       | Logs out a user and invalidates the token.   |
| `/auth/validate-token`                               | Validates the provided authentication token. |
| `/auth/profile`                                      | Retrieves the authenticated user's profile.  |
| `/auth/contact-email`                                | Sends a contact email from the user.         |
| `/racers/reset-password`                             | Allows users to reset their password.        |
| `/racers/register`                                   | Registers a new user in the system.          |
| `/racers/update-profile`                             | Updates the profile information of a user.   |
| `/racers/profile-verified`                           | Verifies a user's profile.                   |
| `/racers/update-password`                            | Updates the user's password.                 |
| `/racers/verify-recaptcha-token`                     | Verifies the ReCaptcha token provided.       |
| `/racers/profile-password-update`                    | Updates both profile and password.           |
| `/racer-car-configurations/save-car-configuration`   | Saves a new car configuration for the user.  |
| `/racer-car-configurations/update-car-configuration` | Updates an existing car configuration.       |
| `/racer-car-configurations/delete-car-configuration` | Deletes a car configuration.                 |
| `/racer-car-configurations/racer-all-cars`           | Retrieves all car configurations for a user. |
| `/auxiliaries`                                       | Manages auxiliary data.                      |
| `/car-configurations`                                | Manages car configurations.                  |
| `/car-configurations/search-filter`                  | Searches car configurations with filters.    |
| `/cars`                                              | Manages car information.                     |
| `/classes`                                           | Manages car classes.                         |
| `/engines`                                           | Manages engine information.                  |
| `/gears`                                             | Manages gear information.                    |
| `/init-skids`                                        | Manages initial skid information.            |
| `/levels`                                            | Manages levels information.                  |
| `/makers`                                            | Manages car makers information.              |
| `/street-types`                                      | Manages street types information.            |
| `/suspensions`                                       | Manages suspension information.              |
| `/tires`                                             | Manages tire information.                    |
| `/turbos`                                            | Manages turbo information.                   |
| `/turbo-types`                                       | Manages turbo types information.             |

---

## 🛠️ **Technologies Used**

- **Java**
- **Spring Boot**
- **Spring Security**
- **Caffeine Cache**
- **Swagger**
- **Lombok**
- **Actuator**
- **MySQL**
- **MailJet**
- **HCaptcha**

---

## 👎 **License**

[Apache License](LICENSE)

---

## 📞 **Contact**

- **Email:** eduardomaravilladev@hotmail.com


