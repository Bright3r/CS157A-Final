1. Install PostgreSQL

\*\*For macOS:

- Install Homebrew (Optional):
  /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

- Instal PostgreSQL from Homebrew:
  brew install postgresql

- Start PostgreSQL service:
  brew services start postgresql
- Verify installation:
  psql --version

\*\*For Linux:

- Update the package list:
  sudo apt update

- Install PostgreSQL
  sudo apt install postgresql postgresql-contrib

-Start PostgreSQL:
sudo systemctl start postgresql
sudo systemctl enable postgresql

- Verify installation:
  psql --version

2. Create Database:

- Open the PostgreSQL
  psql postgres

- Create a database
  CREATE DATABASE ecommerce;

- Exit the PostgreSQL
  \q

3.  Connect to PostgresSQL
    psql -h localhost -p 5432 -U admin -d ecommerce
