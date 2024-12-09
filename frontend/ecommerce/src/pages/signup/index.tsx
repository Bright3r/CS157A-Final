import { useState } from "react";
import InputField from "../../components/inputField";
import styles from "./Signup.module.css";
import axios from "axios";
import { useRouter } from "next/router";


// Response fields for creating new user
interface APIResponse {
  message: string;
  success: Boolean;
};

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

  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // Basic validation for matching passwords
    if (password !== confirmPassword) {
      setError("Passwords do not match");
      return;
    }

    // Clear previous errors
    setError("");

    // Create an address object with form data
    const userAddr: address_t = {
      addrID: -1, // Doesn't matter, included for type compatibility
      country,
      state,
      city,
      street,
      houseNumber,
      zipcode
    }

    // Create a user object with form data
    const userData: user_t = {
      userID: -1, // Doesn't matter, included for type compatibility
      userName,
      email,
      password,
      phoneNumber,
      address: userAddr,
    };

    try {
      const res = await axios.post<APIResponse>("http://localhost:8080/api/user/register", userData, {
        headers: {
          'Content-Type': 'application/json',
        }
      });

      console.log("Response Status:", res.status);
      if (res.data.success) {
        console.log("Response Data:", res.data);
        alert("Signup successful. Please login!");
        router.push("/login");
      } else {
        console.log("Error Response Data:", res.data);
        setError(res.data.message || "An error occurred during signup.");
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