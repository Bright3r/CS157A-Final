import { useState } from "react";
import InputField from "../../components/inputField";
import styles from "./Signup.module.css";

// Define types for each field's state
interface SignUpFormData {
  userName: string;
  email: string;
  country: string;
  state: string;
  city: string;
  street: string;
  houseNumber: string;
  zipcode: string;
  phoneNumber: string;
  password: string;
  confirmPassword: string;
  error: string;
}

export default function SignUp() {
  // Initialize state with types
  const [userName, setUserName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [country, setCountry] = useState<string>("");
  const [state, setState] = useState<string>("");
  const [city, setCity] = useState<string>("");
  const [street, setStreet] = useState<string>("");
  const [houseNumber, setHouseNumber] = useState<string>("");
  const [zipcode, setZipcode] = useState<string>("");
  const [phoneNumber, setPhoneNumber] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [error, setError] = useState<string>("");

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
      country,
      state,
      city,
      street,
      houseNumber,
      zipcode,
      phoneNumber,
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
          <InputField
            id="username"
            label="Username"
            value={userName}
            onChange={setUserName}
            placeholder="Enter your username"
          />
          <InputField
            id="email"
            label="Email"
            value={email}
            onChange={setEmail}
            placeholder="Enter your email"
          />
          <InputField
            id="phoneNumber"
            label="Phone Number"
            value={phoneNumber}
            onChange={setPhoneNumber}
            placeholder="Enter your phone number"
          />
          <InputField
            id="country"
            label="Country"
            value={country}
            onChange={setCountry}
            placeholder="Enter your country"
          />
          <InputField
            id="state"
            label="State"
            value={state}
            onChange={setState}
            placeholder="Enter your state"
          />
          <InputField
            id="city"
            label="City"
            value={city}
            onChange={setCity}
            placeholder="Enter your city"
          />
          <InputField
            id="street"
            label="Street"
            value={street}
            onChange={setStreet}
            placeholder="Enter your street"
          />
          <InputField
            id="houseNumber"
            label="House Number"
            value={houseNumber}
            onChange={setHouseNumber}
            placeholder="Enter your house number"
          />
          <InputField
            id="zipcode"
            label="Zip Code"
            value={zipcode}
            onChange={setZipcode}
            placeholder="Enter your zip code"
          />
          <InputField
            id="password"
            label="Password"
            value={password}
            onChange={setPassword}
            placeholder="Enter your password"
          />
          <InputField
            id="confirm-password"
            label="Confirm Password"
            value={confirmPassword}
            onChange={setConfirmPassword}
            placeholder="Confirm password"
          />

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