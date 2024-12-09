import { useState } from "react";
import axios from "../axiosConfig";
import { useRouter } from "next/router";
import styles from "./Login.module.css";
import { useUser } from "../../context/UserContext";


interface LoginRequest {
  username: string;
  password: string;
}

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const router = useRouter();
  const { setUser } = useUser();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!username || !password) {
      setErrorMessage("Please enter both username and password");
      return;
    }

    try {
      const loginData: LoginRequest = {
        username,
        password,
      };

      // Send the POST request
      const response = await axios.post('http://localhost:8080/api/user/login', loginData, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.status === 200) {
        console.log("Login successful");
        setUser(response.data);
        console.log(response.data);
        router.push("/products");
      }
    } catch (error) {
      setErrorMessage("Invalid username or password");
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.formContainer}>
        <h1 className={styles.heading}>Login</h1>

        <form className={styles.form} onSubmit={handleSubmit}>
          <div className={styles.inputGroup}>
            <label htmlFor="username" className={styles.label}>
              Username
            </label>
            <input
              type="text"
              id="username"
              name="username"
              placeholder="Enter your username"
              className={styles.input}
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div className={styles.inputGroup}>
            <label htmlFor="password" className={styles.label}>
              Password
            </label>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Enter your password"
              className={styles.input}
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          {errorMessage && <div className={styles.error}>{errorMessage}</div>}
          <button type="submit" className={styles.button}>
            Login
          </button>
        </form>

        <div className={styles.links}>
          <a href="/forgot-password" className={styles.link}>
            Forgot Password?
          </a>
          <a href="/signup" className={styles.link}>
            Create an Account
          </a>
        </div>
      </div>
    </div>
  );
}
