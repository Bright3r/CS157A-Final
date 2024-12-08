import { useState } from "react";
import styles from "./Signup.module.css";

export default function SignUp() {
  const [userName, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // Basic validation for matching passwords
    if (password !== confirmPassword) {
      setError("Passwords do not match");
      return;
    }

    // Clear previous errors
    setError("");

    // Create a user object with form data
    const userData = {
      userName,
      email,
      password,
    };

    try {
      const response = await fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      console.log("Response Status:", response.status);
      if (response.ok) {
        const data = await response.json();
        console.log("Response Data:", data);
        alert("Signup successful. Please login!");
        window.location.href = "/login";
      } else {
        const data = await response.json();
        console.log("Error Response Data:", data);
        setError(data.message || "An error occurred during signup.");
      }
    } catch (error) {
      console.error("Fetch Error:", error); // Log the error to the console
      setError("An error occurred while connecting to the server.");
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.formContainer}>
        <h1 className={styles.heading}>Sign Up</h1>
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
              value={userName}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>

          <div className={styles.inputGroup}>
            <label htmlFor="email" className={styles.label}>
              Email
            </label>
            <input
              type="email"
              id="email"
              name="email"
              placeholder="Enter your email"
              className={styles.input}
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
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
              required
            />
          </div>

          <div className={styles.inputGroup}>
            <label htmlFor="confirmPassword" className={styles.label}>
              Confirm Password
            </label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              placeholder="Confirm your password"
              className={styles.input}
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />
          </div>

          {error && <div className={styles.error}>{error}</div>}

          <button type="submit" className={styles.button}>
            Sign Up
          </button>
        </form>

        <div className={styles.links}>
          <a href="/login" className={styles.link}>
            Already have an account? Login
          </a>
        </div>
      </div>
    </div>
  );
}
