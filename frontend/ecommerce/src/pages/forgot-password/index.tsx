import styles from "./ForgotPassword.module.css";

export default function ForgotPassword() {
  return (
    <div className={styles.container}>
      <div className={styles.formContainer}>
        <h1 className={styles.heading}>Forgot Password</h1>
        <form className={styles.form}>
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
            />
          </div>

          <button type="submit" className={styles.button}>
            Send Reset
          </button>
        </form>

        <div className={styles.links}>
          <a href="/login" className={styles.link}>
            Back to Login
          </a>
        </div>
      </div>
    </div>
  );
}