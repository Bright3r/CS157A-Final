import "../../types"
import { useEffect, useState } from "react";
import { useCart } from "@/context/CartContext";
import { useUser } from "../../context/UserContext";
import CartProduct from "@/components/cartProduct";
import styles from "./Cart.module.css";
import axios from "axios";

export default function Cart() {
  let [error, setError] = useState<string>("");

  const { cartProducts, clearCart } = useCart();
  const { user } = useUser();

  useEffect(() => {
    
  }, [cartProducts]);


  const handlePurchase = async () => {
    if (!user) {
      setError("Musted be logged in to purchase");
      return;
    }

    const order: order_t = {
      orderID: -1,  // Doesn't matter, here for type compatibility
      user,
      products: cartProducts,
      numProductsOrdered: cartProducts.length,
      dateOrdered: new Date(),
      shippingAddress: user.address
    }

    try {
      console.log(order);

      const res = await axios.post("http://localhost:8080/api/orders", order, {
        headers: {
          'Content-Type': 'application/json',
        }
      });

      if (res.data.success) {
        alert("Purchase Successful!");
        clearCart();
      } else {
        console.error("Error Response Data:", res.data);
        setError(res.data.message || "An error occurred during signup.");
      }
    } catch (error) {
      console.error("Failed to purchase", error);
      setError("Failed to purchase");
    }
  }
  
  return (
    <>
      <h1 className={styles.header}> Your Cart </h1>
      <div className={styles.productList}>
        {cartProducts.length > 0 ? 
          (
            cartProducts.map((cartProduct) => <CartProduct key={cartProduct.product.productID} {...cartProduct} />)
          ) :
          <div className={styles.emptyMessage}>
            <span><strong>Empty</strong></span>
          </div>
        }
      </div>
      {cartProducts.length > 0 &&
        <button className={styles.purchaseButton} onClick={() => handlePurchase()}>Purchase</button>
      }
      </>
  );
  }