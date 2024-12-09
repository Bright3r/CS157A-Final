import "@/styles/globals.css";
import type { AppProps } from "next/app";
import Navbar from "@/components/navbar";
import { CartProvider } from "../context/CartContext";
import { UserProvider } from "../context/UserContext";

export default function App({ Component, pageProps }: AppProps) {
  return (
    <UserProvider>
      <CartProvider>
        <Navbar />
        <Component {...pageProps} />
      </CartProvider>
    </UserProvider>
  );
}
