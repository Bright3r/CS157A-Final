import styles from "./Login.module.css";

export default function Login() {
  return (
    <div className={styles.container}>
      <div className={styles.formContainer}>
        <h1 className={styles.heading}>LOGIN</h1>
        
        <form className={styles.form}>
          <div className={styles.inputGroup}>
            <label htmlFor="username" className={styles.label}>Username</label>
            <input
              type="text"
              id="username"
              name="username"
              placeholder="Enter your username"
              className={styles.input}
            />
          </div>

          <div className={styles.inputGroup}>
            <label htmlFor="password" className={styles.label}>Password</label>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Enter your password"
              className={styles.input}
            />
          </div>

          <button type="submit" className={styles.button}>Login</button>
        </form>

        <div className={styles.links}>
          <a href="/forgot-password" className={styles.link}>Forgot Password?</a>
          <a href="/signup" className={styles.link}>Create an Account</a>
        </div>
      </div>
    </div>
  );
}