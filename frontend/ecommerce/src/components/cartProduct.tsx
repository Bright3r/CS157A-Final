import "../types";
import { useCart } from "../context/CartContext";
import { useState } from "react";
import styles from "@/styles/CartProduct.module.css";

export default function CartProduct({ product, purchaseQuantity }: cart_product_t) {
    const { productID, productName, brand, price, rating, listingDate } = product;

    let [localQuantity, setLocalQuantity] = useState<number>(purchaseQuantity);

    const { removeCartProduct, updateCartProduct } = useCart();


    const handleRemoveFromCart = () => {
        removeCartProduct(productID);
    };

    const handleUpdateQuantity = (newQuantity: number) => {
        setLocalQuantity(newQuantity);

        const updatedProduct: cart_product_t = { product, purchaseQuantity: newQuantity };
        updateCartProduct(updatedProduct);
    }

    return (
        <div className={styles.productCard}>
            <h2 className={styles.productTitle}>{productName}</h2>
            <p className={styles.productBrand}>Brand: <strong>{brand}</strong></p>
            <p className={styles.productPrice}>Price: <strong>${price.toFixed(2)}</strong></p>
            <p className={styles.productRating}>
                Rating: <strong>{rating.toFixed(2)} / 5</strong>
            </p>

            <div className={styles.purchaseDetails}>
                <div className={styles.quantityContainer}>
                    <p className={styles.purchaseQuantity}>Purchase Qty: </p>
                    <input
                        className={styles.purchaseQuantitySelector}
                        type="number"
                        id="quantity"
                        name="purchaseQuantity"
                        min="0"
                        max="99"
                        value={localQuantity}
                        onChange={(e) => handleUpdateQuantity(Number(e.target.value))}
                    />
                </div>
                <p className={styles.total}>Total: <strong>${(localQuantity * price).toFixed(2)}</strong></p>
            </div>
            <button className="button" onClick={() => handleRemoveFromCart()}>Remove</button>
        </div>
    );
}