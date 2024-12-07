import Link from "next/link";
import styles from "@/styles/Navbar.module.css";
import Image from "next/image";

export default function Navbar() {
  return (
    <nav className={styles.navbar}>
      <div className={styles.logo}>
        <Link href="/">Ecommerce</Link>
      </div>
      <ul className={styles.navLinks}>
        <li>
          <Link href="/login">Login</Link>
        </li>
        <li>
          <Link href="/signup">Sign Up</Link>
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
      </ul>
    </nav>
  );
}