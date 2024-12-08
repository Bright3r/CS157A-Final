import { useState } from "react";
import styles from "./ForgotPassword.module.css";

export default function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState(""); // To display the response message

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // Send email to backend to check if it is registered
    const response = await fetch("http://localhost:8080/api/forgot-password", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email }),
    });

    if (response.ok) {
      const data = await response.json();
      setMessage(data.message || "A reset code has been sent to your email.");
    } else {
      const data = await response.json();
      setMessage(data.message || "Email not registered yet.");
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.formContainer}>
        <h1 className={styles.heading}>Forgot Password</h1>
        <form className={styles.form} onSubmit={handleSubmit}>
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
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <button type="submit" className={styles.button}>
            Send Reset
          </button>
        </form>

        {/* Display message */}
        {message && <div className={styles.message}>{message}</div>}

        <div className={styles.links}>
          <a href="/login" className={styles.link}>
            Back to Login
          </a>
        </div>
      </div>
    </div>
  );
}
