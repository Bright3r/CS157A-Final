import Link from "next/link";
import styles from "@/styles/Navbar.module.css";
import Image from "next/image";
import { useUser } from "../context/UserContext";
import { useEffect } from "react";

export default function Navbar() {
  const { user, isLoggedIn, logout } = useUser();

  useEffect(() => {

  }, [user]);

  return (
    <nav className={styles.navbar}>
      <div className={styles.logo}>
        <Link href="/">Ecommerce</Link>
      </div>
      <ul className={styles.navLinks}>
        {/* Show Login and Sign Up only when not logged in */}
        {!isLoggedIn() && (
          <>
            <li>
              <Link href="/login">Login</Link>
            </li>
            <li>
              <Link href="/signup">Sign Up</Link>
            </li>
          </>
        )}
        
        {/* Show Orders, Products, and Cart only when logged in */}
        {isLoggedIn() && (
          <>
            <li>
              <strong>Hello, { user?.userName }</strong>
            </li>
            <li>
              <Link href="/login" onClick={() => logout()}>Sign Out</Link>
            </li>
            <li>
              <Link href="/orders">Orders</Link>
            </li>
            <li>
              <Link href="/products">Products</Link>
            </li>
            <li>
              <Link href="/cart">
                <Image
                  className={styles.cartButton}
                  src="/cart.svg"
                  alt="Shopping Cart"
                  width={30}
                  height={30}
                  priority
                />
              </Link>
            </li>
          </>
        )}
      </ul>
    </nav>
  );
}