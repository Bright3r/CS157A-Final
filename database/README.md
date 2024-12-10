# Setting Up PostgreSQL for the E-commerce Platform

## Installation Guide

### For macOS:

1. **Install Homebrew (Optional):**

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

2. Install PostgreSQL using Homebrew:
brew install postgresql

3. Start the PostgreSQL service:
brew services start postgresql

4. Verify the installation:
psql --version

### For Linux:

1. Update the package list:
sudo apt update

2. Install PostgreSQL and additional components:
sudo apt install postgresql postgresql-contrib

3. Start and enable the PostgreSQL service:
sudo systemctl start postgresql
sudo systemctl enable postgresql

4. Verify the installation:
psql --version

## Database Setup

1. Create a PostgreSQL user and database:
Log in to PostgreSQL:
  psql -h localhost -p 5432
Run the following SQL commands:
  CREATE USER admin WITH PASSWORD 'admin';
  CREATE DATABASE ecommerce;
  GRANT ALL PRIVILEGES ON DATABASE ecommerce TO admin;

2. Connect to the ecommerce database:
psql -h localhost -p 5432 -U admin -d ecommerce

## Running the Application

1. Navigate to the project directory:
src/main/java/com

2. Run the database setup schema:
Execute DatabaseSetup.java to initialize the database schema.

3. Start the Spring Boot application:
Run EcommerceApplication.java using Spring Boot. Ensure the application is running properly.

## Notes
Make sure PostgreSQL is running before starting the Spring Boot application.
Verify configuration settings in the application.properties file for Spring Boot to connect to the PostgreSQL database correctly.
```
