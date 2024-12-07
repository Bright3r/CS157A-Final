import "../../types"
import Product from "@/components/product";
import { useEffect, useState } from "react";
import { useCart } from "@/context/CartContext";
import styles from "./Cart.module.css";
import CartProduct from "@/components/cartProduct";

export default function Cart() {
  const { cartProducts } = useCart();

  useEffect(() => {
    
  }, [cartProducts]);
  
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
    </>
  );
  }