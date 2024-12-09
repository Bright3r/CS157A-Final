import { useRouter } from "next/router"; // Assuming you're using Next.js for routing
import styles from "./Home.module.css";

export default function Home() {
  const router = useRouter();

  // Redirect to login page
  const handleLoginRedirect = () => {
    router.push("/login");
  };

  // Redirect to signup page
  const handleSignupRedirect = () => {
    router.push("/signup");
  };

  return (
    <div className={styles.container}>
      <div className={styles.formContainer}>
        <h1 className={styles.heading}>Welcome to Ecommerce</h1>

        <div className={styles.buttonContainer}>
          <button onClick={handleLoginRedirect} className={styles.button}>
            Login here
          </button>

          <button onClick={handleSignupRedirect} className={styles.button}>
            New user? Sign Up
          </button>
        </div>
      </div>
    </div>
  );
}
